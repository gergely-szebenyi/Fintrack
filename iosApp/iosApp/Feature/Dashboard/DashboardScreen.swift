import SwiftUI
import Shared

struct DashboardScreen: View {
    @StateObject private var viewModel = DashboardViewModel()

    var body: some View {
        let categoryIconMap = Dictionary(
            uniqueKeysWithValues: viewModel.availableCategories.map { ($0.name, $0.icon) }
        )

        ZStack(alignment: .bottomTrailing) {
            ScrollView {
                VStack(spacing: 16) {
                    TopBar()
                    BalanceCard(
                        totalBalance: viewModel.totalBalance,
                        income: viewModel.income,
                        expenses: viewModel.expenses
                    )
                    SpendingByCategoryCard(categories: viewModel.categories)
                    RecentTransactionsHeader()

                    ForEach(viewModel.transactions) { transaction in
                        TransactionItem(
                            transaction: transaction,
                            icon: categoryIconMap[transaction.category] ?? ""
                        )
                    }
                }
                .padding(.bottom, 100)
            }
            .background(AppColors.bgGray)

            VStack(spacing: 12) {

                // Add Transaction FAB
                Button(action: { viewModel.onAddClicked() }) {
                    Image(systemName: "plus")
                        .font(.system(size: 24, weight: .bold))
                        .foregroundColor(AppColors.bgGray)
                        .frame(width: 56, height: 56)
                        .background(AppColors.bluePrimary)
                        .clipShape(Circle())
                        .shadow(radius: 4)
                }
            }
            .padding(24)
        }
        .ignoresSafeArea(edges: .bottom)
        .sheet(isPresented: $viewModel.showAddDialog) {
            AddTransactionDialog(
                categories: viewModel.availableCategories,
                onDismiss: { viewModel.onDialogDismissed() },
                onAddTransaction: viewModel.onAddTransaction
            )
            .presentationDetents([.large])
            .presentationDragIndicator(.visible)
        }
    }
}
