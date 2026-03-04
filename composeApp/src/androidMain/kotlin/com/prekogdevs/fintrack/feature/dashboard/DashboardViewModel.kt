package com.prekogdevs.fintrack.feature.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prekogdevs.fintrack.domain.Transaction
import kotlinx.coroutines.flow.StateFlow

// Android-specific wrapper required by androidx.lifecycle.ViewModel.
// The actual logic lives in the shared module (commonMain).
class AndroidDashboardViewModel : ViewModel() {

    private val sharedVm = DashboardViewModel(coroutineScope = viewModelScope)

    val uiState: StateFlow<UIState> = sharedVm.uiState

    fun onAddClicked() = sharedVm.onAddClicked()
    fun onDialogDismissed() = sharedVm.onDialogDismissed()
    fun onAddTransaction(transaction: Transaction) = sharedVm.onAddTransaction(transaction)
}
