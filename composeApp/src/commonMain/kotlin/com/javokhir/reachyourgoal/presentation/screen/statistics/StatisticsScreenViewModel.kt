package com.javokhir.reachyourgoal.presentation.screen.statistics

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.javokhir.reachyourgoal.datastore.AppDatastore
import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import com.javokhir.reachyourgoal.domain.model.DailyProgress
import com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.state.ScreenUiState
import com.javokhir.reachyourgoal.repository.TaskAndPlanRepository
import com.javokhir.reachyourgoal.repository.TaskRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.random.Random

@OptIn(ExperimentalCoroutinesApi::class)
class StatisticsScreenViewModel(
    private val taskRepository: TaskRepository,
    private val taskAndPlanRepository: TaskAndPlanRepository,
    private val appDatastore: AppDatastore
) : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands: SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                it.copy(
                    taskStatusesOfToday = TaskStatus.entries
                        .filter {
                            it !in listOf(
                                TaskStatus.NOT_MANDATORY,
                                TaskStatus.NOT_COMPLETED
                            )
                        }
                        .map { it to Random.nextInt().absoluteValue % 11 }
                        .toImmutableList(),
                    dailyProgressesOfWeek = List(7) {
                        DailyProgress(
                            Random.nextInt().absoluteValue % 11,
                            Random.nextInt().absoluteValue % 11
                        )
                    }.toImmutableList()
                )
            }

            appDatastore
                .getCurrentPlan()
                .filterNotNull()
                .flatMapMerge { taskAndPlanRepository.getAllByPlanId(it) }
                .collect {

                }
        }
    }

    fun action(event: ScreenEvent.Input) {
        _uiState.update {
            when (event) {
                else -> it
            }
        }
    }

}