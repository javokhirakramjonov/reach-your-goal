package com.javokhir.reachyourgoal.presentation.screen.statistics

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.state.ScreenUiState
import com.javokhir.reachyourgoal.repository.TaskStateRepository
import com.javokhir.reachyourgoal.repository.WeekRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

class StatisticsScreenViewModel(
    private val weekRepository: WeekRepository,
    private val taskStateRepository: TaskStateRepository,
) : ScreenModel {

    private val _uiState = MutableStateFlow(ScreenUiState())
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _commands = MutableSharedFlow<ScreenEvent.Command>()
    val commands: SharedFlow<ScreenEvent.Command> = _commands.asSharedFlow()

    init {
        loadStatusesOfToday()
        loadStatusesOfWeek()
        loadStatusesOfAllWeek()
    }

    private fun loadStatusesOfToday() {
        screenModelScope.launch(Dispatchers.IO) {
            val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
            val startDayOfWeek = today.minusDays(today.dayOfWeek.isoDayNumber - 1)

            val week = weekRepository.getWeekByStartDate(startDayOfWeek) ?: return@launch

            taskStateRepository.getTaskStatusCountsForWeekDay(week.id, today.dayOfWeek)
                .filter(List<*>::isNotEmpty)
                .collect {
                    val taskStatusesOfToday = it
                        .filterNot { it.taskStatus == TaskStatus.NOT_MANDATORY }
                        .toImmutableList()

                    _uiState.update {
                        it.copy(
                            taskStatusesOfToday = taskStatusesOfToday
                        )
                    }
                }
        }
    }

    private fun loadStatusesOfWeek() {
        screenModelScope.launch(Dispatchers.IO) {
            val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
            val startDayOfWeek = today.minusDays(today.dayOfWeek.isoDayNumber - 1)

            val week = weekRepository.getWeekByStartDate(startDayOfWeek) ?: return@launch

            taskStateRepository
                .getTaskCountsForEachDayInWeek(week.id)
                .filter(List<*>::isNotEmpty)
                .collect {
                    val dailyTaskProgressesOfWeek = it
                        .sortedBy { it.dayOfWeek.isoDayNumber }
                        .toImmutableList()

                    _uiState.update {
                        it.copy(
                            dailyTaskProgressesOfWeek = dailyTaskProgressesOfWeek
                        )
                    }
                }
        }
    }

    private fun loadStatusesOfAllWeek() {
        screenModelScope.launch(Dispatchers.IO) {
            val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
            val startDayOfWeek = today.minusDays(today.dayOfWeek.isoDayNumber - 1)

            taskStateRepository
                .getTaskCountsForEachWeek()
                .filter(List<*>::isNotEmpty)
                .map {
                    it.mapNotNull {
                        val week = weekRepository.getById(it.weekId)

                        if (week.startDate > startDayOfWeek) return@mapNotNull null

                        it.apply {
                            weekName = week.getName()
                        }
                    }
                }
                .collect { weeklyProgresses ->
                    _uiState.update {
                        it.copy(
                            weeklyProgresses = weeklyProgresses.toImmutableList()
                        )
                    }
                }
        }
    }

    private fun LocalDate.minusDays(days: Int): LocalDate {
        return this.minus(days, DateTimeUnit.DAY)
    }

}