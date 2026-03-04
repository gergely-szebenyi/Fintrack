package com.prekogdevs.fintrack.feature.dashboard

import com.prekogdevs.fintrack.data.CategoryRepository
import com.prekogdevs.fintrack.data.TransactionRepository
import com.prekogdevs.fintrack.data.remote.RemoteCategoryRepository
import com.prekogdevs.fintrack.data.remote.RemoteTransactionRepository
import com.prekogdevs.fintrack.domain.Category
import com.prekogdevs.fintrack.domain.CategorySpend
import com.prekogdevs.fintrack.domain.Transaction
import com.prekogdevs.fintrack.domain.TransactionType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DashboardViewModel(
    private val coroutineScope: CoroutineScope,
    private val transactionRepository: TransactionRepository = RemoteTransactionRepository(),
    private val categoryRepository: CategoryRepository = RemoteCategoryRepository(),
    private val onStateChanged: (() -> Unit)? = null
) {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        coroutineScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            onStateChanged?.invoke()
            try {
                val transactions = transactionRepository.getTransactions()
                val categories = categoryRepository.getCategories()
                _uiState.update {
                    it.copy(
                        transactions = transactions,
                        categories = calculateCategories(transactions, categories),
                        availableCategories = categories,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false) }
            }
            onStateChanged?.invoke()
        }
    }

    private fun onEvent(event: Event) {
        when (event) {
            Event.AddClicked ->
                _uiState.update { it.copy(showAddDialog = true) }

            Event.DialogDismissed ->
                _uiState.update { it.copy(showAddDialog = false) }

            is Event.AddTransaction -> {
                val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
                val month = now.month.name.lowercase()
                    .replaceFirstChar { it.uppercase() }
                    .substring(0, 3)
                val newTx = event.transaction.copy(
                    id = Clock.System.now().toEpochMilliseconds().toString(),
                    date = "$month ${now.dayOfMonth}, ${now.year}"
                )
                _uiState.update {
                    val newTransactions = listOf(newTx) + it.transactions
                    it.copy(
                        transactions = newTransactions,
                        categories = calculateCategories(newTransactions, it.availableCategories),
                        showAddDialog = false
                    )
                }
            }
        }
        onStateChanged?.invoke()
    }

    // Exposed for the iOS wrapper — StateFlow.value is ambiguous from Swift.
    val currentState: UIState get() = _uiState.value

    fun onAddClicked() = onEvent(Event.AddClicked)
    fun onDialogDismissed() = onEvent(Event.DialogDismissed)
    fun onAddTransaction(transaction: Transaction) = onEvent(Event.AddTransaction(transaction))

    private fun calculateCategories(
        transactions: List<Transaction>,
        availableCategories: List<Category>
    ): List<CategorySpend> {
        val categoryMap = availableCategories.associateBy { it.name }
        val expenses = transactions.filter { it.type == TransactionType.EXPENSE }
        val total = expenses.sumOf { it.amount }
        if (total == 0.0) return emptyList()

        return expenses
            .groupBy { it.category }
            .map { (categoryName, txs) ->
                val amount = txs.sumOf { it.amount }
                val category = categoryMap[categoryName] ?: Category(
                    id = categoryName,
                    name = categoryName,
                    icon = "",
                    color = "#607D8B"
                )
                CategorySpend(
                    category = category,
                    amount = amount,
                    percentage = (amount / total * 100).toFloat()
                )
            }
            .sortedByDescending { it.amount }
    }
}



data class UIState(
    val transactions: List<Transaction> = emptyList(),
    val categories: List<CategorySpend> = emptyList(),
    val availableCategories: List<Category> = emptyList(),
    // showAddDialog is a UI concern but kept here as a pragmatic KMP tradeoff —
    // extracting it to a separate Flow would complicate the iOS wrapper significantly.
    val showAddDialog: Boolean = false,
    val isLoading: Boolean = false
) {
    // Amounts are always stored as positive; sign is derived from TransactionType.
    val income: Double get() = transactions
        .filter { it.type == TransactionType.INCOME }
        .sumOf { it.amount }
    val expenses: Double get() = transactions
        .filter { it.type == TransactionType.EXPENSE }
        .sumOf { it.amount }
    val totalBalance: Double get() = income - expenses
}

sealed interface Event {
    data object AddClicked : Event
    data object DialogDismissed : Event
    data class AddTransaction(val transaction: Transaction) : Event
}
