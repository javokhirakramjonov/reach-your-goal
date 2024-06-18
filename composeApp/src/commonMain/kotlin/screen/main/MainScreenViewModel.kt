package screen.main

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import datastore.AppDatastore
import domain.entity.Task
import domain.entity.TaskAndPlan
import domain.enums.TaskStatus
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import repository.PlanRepository
import repository.TaskAndPlanRepository
import repository.TaskRepository
import screen.main.mvi.event.ScreenEvent
import screen.main.mvi.state.ScreenUiState

class MainScreenViewModel(
    private val planRepository: PlanRepository,
    private val taskRepository: TaskRepository,
    private val taskAndPlanRepository: TaskAndPlanRepository,
    private val appDatastore: AppDatastore
) : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            launch { loadPlans() }

            appDatastore
                .getCurrentPlan()
                .collect {currentPlanId ->
                    if(currentPlanId == null) {
                        _uiState.update {
                            it.copy(
                                currentPlan = null,
                                scheduledTasks = emptyList<Pair<Task, ImmutableList<TaskStatus>>>().toImmutableList()
                            )
                        }
                    } else {
                        launch { loadCurrentPlan(currentPlanId) }
                        launch { loadTasksForPlan(currentPlanId) }
                    }
                }
        }
    }

    private suspend fun loadPlans() {
        planRepository
            .getAll()
            .collect { plans ->
                _uiState.update { it.copy(plans = plans.toImmutableList()) }
            }
    }

    private suspend fun loadCurrentPlan(planId: Int) {
        planRepository
            .getById(planId)
            .collect { plan ->
                _uiState.update { it.copy(currentPlan = plan) }
            }
    }

    private suspend fun loadTasksForPlan(planId: Int) {
        taskAndPlanRepository
            .getAllByPlanId(planId)
            .collect { tasks ->
                val scheduledTasks = tasks
                    .map {
                        val task = taskRepository.getById(it.taskId)

                        task to it.statuses.toImmutableList()
                    }
                    .toImmutableList()

                _uiState.update { it.copy(scheduledTasks = scheduledTasks) }
            }
    }

    fun action(event: ScreenEvent.Input) {
        _uiState.update {
            when (event) {
                is ScreenEvent.Input.PlanSelected -> onPlanSelected(it, event)
                is ScreenEvent.Input.StatusChanged -> onStatusChanged(it, event)
            }
        }
    }

    private fun onPlanSelected(
        state: ScreenUiState,
        event: ScreenEvent.Input.PlanSelected
    ): ScreenUiState {
        screenModelScope.launch(Dispatchers.IO) {
            appDatastore.setCurrentPlan(event.plan.id)
        }

        return state.copy(
            currentPlan = event.plan
        )
    }

    private fun onStatusChanged(
        state: ScreenUiState,
        event: ScreenEvent.Input.StatusChanged
    ): ScreenUiState {
        screenModelScope.launch(Dispatchers.IO) {
            taskAndPlanRepository.update(
                TaskAndPlan(
                    planId = state.currentPlan!!.id,
                    taskId = event.taskId,
                    statuses = state
                        .scheduledTasks
                        .first { it.first.id == event.taskId }
                        .second
                        .mapIndexed { index, status ->
                            if (index == event.day) {
                                event.status
                            } else {
                                status
                            }
                        }
                        .joinToString(",")
                )
            )
        }

        return state.copy(
            scheduledTasks = state.scheduledTasks.map {
                if (it.first.id == event.taskId) {
                    it.first to it.second.mapIndexed { index, status ->
                        if (index == event.day) {
                            event.status
                        } else {
                            status
                        }
                    }.toImmutableList()
                } else {
                    it
                }
            }.toImmutableList()
        )
    }

}