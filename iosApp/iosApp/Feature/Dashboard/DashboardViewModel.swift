import SwiftUI
import Shared

// iOS-specific wrapper required by SwiftUI's ObservableObject.
// The actual logic lives in the Kotlin shared module (Shared.DashboardViewModel).
class DashboardViewModel: ObservableObject {

    private let scope = KMMScope()
    // IUO so we can pass [weak self] in the onStateChanged closure.
    private var sharedVm: Shared.DashboardViewModel!

    @Published var transactions: [Shared.Transaction] = []
    @Published var categories: [Shared.CategorySpend] = []
    @Published var availableCategories: [Shared.Category] = []
    @Published var showAddDialog: Bool = false
    @Published var isLoading: Bool = false

    // Computed properties delegate to UIState — no redundant stored state.
    var totalBalance: Double { sharedVm.currentState.totalBalance }
    var income: Double { sharedVm.currentState.income }
    var expenses: Double { sharedVm.currentState.expenses }

    init() {
        // scope is a let with a default initialiser, so all stored properties
        // have values at this point — self is safe to capture weakly.
        sharedVm = Shared.DashboardViewModel(
            coroutineScope: scope,
            transactionRepository: RemoteTransactionRepository(),
            categoryRepository: RemoteCategoryRepository(),
            onStateChanged: { [weak self] in
                DispatchQueue.main.async { self?.syncState() }
            }
        )
        syncState()
    }

    deinit {
        scope.cancel()
    }

    func onAddClicked() {
        sharedVm.onAddClicked()
    }

    func onDialogDismissed() {
        sharedVm.onDialogDismissed()
    }

    func onAddTransaction(transaction: Shared.Transaction) {
        sharedVm.onAddTransaction(transaction: transaction)
    }

    // Reads the current state from the Kotlin VM and updates the @Published properties.
    // We use currentState instead of uiState.value because the StateFlow.value
    // property is ambiguous from Swift — it appears multiple times in the protocol hierarchy.
    func syncState() {
        let state = sharedVm.currentState
        transactions = state.transactions as? [Shared.Transaction] ?? []
        categories = state.categories as? [Shared.CategorySpend] ?? []
        availableCategories = state.availableCategories as? [Shared.Category] ?? []
        showAddDialog = state.showAddDialog
        isLoading = state.isLoading
    }
}
