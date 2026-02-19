package com.prekogdevs.fintrack.feature.dashboard

import androidx.lifecycle.ViewModel
import com.prekogdevs.fintrack.data.dummyCategories
import com.prekogdevs.fintrack.data.dummyTransactions
import com.prekogdevs.fintrack.domain.CategorySpend
import com.prekogdevs.fintrack.domain.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DashboardViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    fun onEvent(event: Event) {
        when (event) {
            Event.AddClicked ->
                _uiState.value = _uiState.value.copy(showAddDialog = true)

            Event.DialogDismissed ->
                _uiState.value = _uiState.value.copy(showAddDialog = false)
        }
    }
}


data class UIState(
    val totalBalance: Double = 3000.0,
    val income: Double = 4000.0,
    val expenses: Double = 1000.0,
    val transactions: List<Transaction> = dummyTransactions,
    val categories: List<CategorySpend> = dummyCategories,
    val showAddDialog: Boolean = false
)

sealed interface Event {
    data object AddClicked : Event
    data object DialogDismissed : Event
}