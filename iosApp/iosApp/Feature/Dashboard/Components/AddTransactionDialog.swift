import SwiftUI
import Shared

struct AddTransactionDialog: View {
    let categories: [Shared.Category]
    let onDismiss: () -> Void
    let onAddTransaction: (Shared.Transaction) -> Void

    @State private var selectedType = "Expense"
    @State private var description = ""
    @State private var amount = ""
    @State private var selectedCategoryName: String

    private static let transactionTypes = ["Expense", "Income"]

    init(
        categories: [Shared.Category],
        onDismiss: @escaping () -> Void,
        onAddTransaction: @escaping (Shared.Transaction) -> Void
    ) {
        self.categories = categories
        self.onDismiss = onDismiss
        self.onAddTransaction = onAddTransaction
        _selectedCategoryName = State(initialValue: categories.first?.name ?? "")
    }

    var body: some View {
        VStack(alignment: .leading, spacing: 20) {
            // Header
            HStack {
                Text("Add Transaction")
                    .font(.system(size: 20, weight: .bold))
                    .foregroundColor(AppColors.textPrimary)
                Spacer()
                Button(action: onDismiss) {
                    Image(systemName: "xmark")
                        .font(.system(size: 14, weight: .semibold))
                        .foregroundColor(AppColors.textPrimary)
                        .frame(width: 36, height: 36)
                        .background(AppColors.bgGray)
                        .clipShape(Circle())
                }
            }

            // Type toggle
            VStack(alignment: .leading, spacing: 8) {
                Text("Type")
                    .font(.system(size: 13, weight: .medium))
                    .foregroundColor(AppColors.textSecondary)

                HStack(spacing: 10) {
                    ForEach(Self.transactionTypes, id: \.self) { type in
                        let isSelected = selectedType == type
                        Button(action: { withAnimation(.easeInOut(duration: 0.2)) { selectedType = type } }) {
                            Text(type)
                                .font(.system(size: 15, weight: .semibold))
                                .foregroundColor(isSelected ? .white : AppColors.textSecondary)
                                .frame(maxWidth: .infinity)
                                .padding(.vertical, 14)
                                .background(typeBackground(for: type, isSelected: isSelected))
                                .cornerRadius(12)
                        }
                    }
                }
            }

            // Category picker
            VStack(alignment: .leading, spacing: 8) {
                Text("Category")
                    .font(.system(size: 13, weight: .medium))
                    .foregroundColor(AppColors.textSecondary)

                Picker("Category", selection: $selectedCategoryName) {
                    ForEach(categories) { cat in
                        Text(cat.name).tag(cat.name)
                    }
                }
                .pickerStyle(.menu)
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.horizontal, 12)
                .frame(height: 52)
                .background(AppColors.bgGray)
                .cornerRadius(12)
            }

            // Description
            VStack(alignment: .leading, spacing: 8) {
                Text("Description")
                    .font(.system(size: 13, weight: .medium))
                    .foregroundColor(AppColors.textSecondary)

                TextField("Enter description", text: $description)
                    .padding(.horizontal, 12)
                    .frame(height: 52)
                    .background(AppColors.bgGray)
                    .cornerRadius(12)
            }

            // Amount
            VStack(alignment: .leading, spacing: 8) {
                Text("Amount")
                    .font(.system(size: 13, weight: .medium))
                    .foregroundColor(AppColors.textSecondary)

                HStack {
                    Text("$")
                        .foregroundColor(AppColors.textSecondary)
                    TextField("0.00", text: $amount)
                        .keyboardType(.decimalPad)
                }
                .padding(.horizontal, 12)
                .frame(height: 52)
                .background(AppColors.bgGray)
                .cornerRadius(12)
            }

            // Submit button
            Button(action: {
                if let parsedAmount = Double(amount), !description.isEmpty, parsedAmount > 0 {
                    let type: TransactionType = selectedType == "Expense" ? .expense : .income
                    onAddTransaction(Shared.Transaction(
                        id: "",
                        amount: parsedAmount,
                        description: description,
                        category: selectedCategoryName,
                        type: type,
                        date: "",
                        currency: "USD"
                    ))
                }
            }) {
                Text("Add Transaction")
                    .font(.system(size: 16, weight: .semibold))
                    .foregroundColor(.white)
                    .frame(maxWidth: .infinity)
                    .frame(height: 52)
                    .background(AppColors.bluePrimary)
                    .cornerRadius(14)
            }
        }
        .padding(24)
        .background(AppColors.cardWhite)
    }

    private func typeBackground(for type: String, isSelected: Bool) -> Color {
        guard isSelected else { return AppColors.bgGray }
        return type == "Expense" ? AppColors.redExpense : AppColors.greenIncome
    }
}
