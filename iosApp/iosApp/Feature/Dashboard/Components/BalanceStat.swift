import SwiftUI

struct BalanceStat: View {
    let label: String
    let amount: Double
    let isIncome: Bool

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            HStack(spacing: 2) {
                Text(isIncome ? "↗ " : "↘ ")
                    .foregroundColor(isIncome ? AppColors.greenIncome : AppColors.redExpense)
                    .font(.system(size: 14))
                Text(label)
                    .foregroundColor(.white.opacity(0.8))
                    .font(.system(size: 12))
            }
            Text(String(format: "$%.2f", amount))
                .font(.system(size: 18, weight: .bold))
                .foregroundColor(.white)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(12)
        .background(Color.white.opacity(0.15))
        .cornerRadius(12)
    }
}
