package screen.main

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dao.PlanDao
import dao.TaskAndPlanDao
import dao.TaskAndStatusDao
import dao.TaskDao
import datastore.AppDatastore
import domain.entity.Plan
import domain.entity.TaskAndStatus
import domain.enums.TaskStatus
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import screen.main.mvi.event.ScreenEvent
import screen.main.mvi.state.ScreenUiState

class MainScreenViewModel(
    private val planDao: PlanDao,
    private val taskDao: TaskDao,
    private val taskAndPlanDao: TaskAndPlanDao,
    private val taskAndStatusDao: TaskAndStatusDao,
    private val appDatastore: AppDatastore
) : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            launch {
                planDao
                    .getAll()
                    .collect { plans ->
                        _uiState.update { it.copy(plans = plans.toImmutableList()) }
                    }
            }

            launch {
                val currentPlanId = appDatastore.getCurrentPlan()
                if (currentPlanId != null) {
                    planDao
                        .getById(currentPlanId)
                        .collect { plan ->
                            _uiState.update { it.copy(currentPlan = plan) }

                            if (plan != null) loadTasksForPlan(plan)
                        }
                }
            }
        }
    }

    private suspend fun loadTasksForPlan(plan: Plan) {
        //TODO
        val scheduleId = appDatastore.getCurrentScheduleId(plan.id)

        if (scheduleId == null) {
            taskAndPlanDao
                .getAllByPlanId(plan.id)
                .map {
                    it.map { taskAndPlan -> taskDao.getById(taskAndPlan.taskId) }
                }
                .map {
                    it.map {
                        TaskAndStatus(taskId = it.id, statuses = weeklyStatusTemplate())
                    }
                }
                .collect {
                    it.forEach { taskAndStatus ->
                        taskAndStatusDao.insert(taskAndStatus)
                    }
                }
        }

        taskAndPlanDao
            .getAllByPlanId(plan.id)
            .map {
                it.map { taskAndPlan -> taskDao.getById(taskAndPlan.taskId) }
            }
            .map {
                it.map { task -> task to taskAndStatusDao.getAllByIdAndTaskId(scheduleId, task.id) }
            }
            .collect { taskAndStatuses ->
                taskAndStatuses.forEach { taskAndStatusPair ->
                    taskAndStatusPair.second.collect { taskAndStatus ->
                        _uiState.update { state ->
                            if (state.scheduledTasks.any { it.first.id == taskAndStatus.taskId }) {
                                state.copy(
                                    scheduledTasks = state.scheduledTasks.map { (task, statuses) ->
                                        if (task.id == taskAndStatus.taskId) {
                                            task to taskAndStatus.statuses.toImmutableList()
                                        } else {
                                            task to statuses
                                        }
                                    }.toImmutableList()
                                )
                            } else {
                                state.copy(
                                    scheduledTasks = (state.scheduledTasks + (taskAndStatusPair.first to taskAndStatus.statuses.toImmutableList())).toImmutableList()
                                )
                            }
                        }
                    }
                }
            }
    }

    private fun weeklyStatusTemplate(): List<TaskStatus> {
        return List(7) { TaskStatus.NOT_STARTED }
    }

    fun action(event: ScreenEvent.Input) {
        _uiState.update {
            when (event) {
                is ScreenEvent.Input.PlanSelected -> onPlanSelected(it, event)
                is ScreenEvent.Input.ScheduleChanged -> onScheduleChanged(it, event)
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

    private fun onScheduleChanged(
        state: ScreenUiState,
        event: ScreenEvent.Input.ScheduleChanged
    ): ScreenUiState {
        return state
    }

}