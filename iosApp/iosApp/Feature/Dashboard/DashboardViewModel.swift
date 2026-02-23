import SwiftUI
import Shared

class DashboardViewModel: ObservableObject {
    @Published var totalBalance: Double = 3000.0
    @Published var income: Double = 4000.0
    @Published var expenses: Double = 1000.0
    @Published var transactions: [Shared.Transaction] = dummyTransactions
    @Published var categories: [CategorySpend] = dummyCategories
    @Published var showAddDialog: Bool = false

    func onAddClicked() {
        showAddDialog = true
    }

    func onDialogDismissed() {
        showAddDialog = false
    }
}
