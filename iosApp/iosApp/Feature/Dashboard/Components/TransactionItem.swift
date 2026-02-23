import SwiftUI
import Shared

struct TransactionItem: View {
    let transaction: Shared.Transaction

    private var isIncome: Bool { transaction.amount > 0 }

    var body: some View {
        HStack(spacing: 14) {
            ZStack {
                RoundedRectangle(cornerRadius: 12)
                    .fill(isIncome ? AppColors.greenIncome.opacity(0.12) : AppColors.bgGray)
                    .frame(width: 48, height: 48)
                Text(transaction.icon)
                    .font(.system(size: 22))
            }

            VStack(alignment: .leading, spacing: 2) {
                Text(transaction.name)
                    .font(.system(size: 15, weight: .semibold))
                    .foregroundColor(AppColors.textPrimary)
                Text(transaction.date)
                    .font(.system(size: 12))
                    .foregroundColor(AppColors.textSecondary)
            }

            Spacer()

            Text(isIncome
                 ? String(format: "+$%.2f", transaction.amount)
                 : String(format: "-$%.2f", -transaction.amount))
                .font(.system(size: 15, weight: .bold))
                .foregroundColor(isIncome ? AppColors.greenIncome : AppColors.textPrimary)
        }
        .padding(16)
        .background(AppColors.cardWhite)
        .cornerRadius(16)
        .shadow(color: .black.opacity(0.04), radius: 2, x: 0, y: 1)
        .padding(.horizontal, 20)
        .padding(.vertical, 6)
    }
}
