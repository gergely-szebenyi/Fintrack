import SwiftUI

struct BalanceCard: View {
    let totalBalance: Double
    let income: Double
    let expenses: Double

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack(spacing: 2) {
                Text("$  ")
                    .foregroundColor(.white.opacity(0.8))
                    .font(.system(size: 14))
                Text("Total Balance")
                    .foregroundColor(.white.opacity(0.8))
                    .font(.system(size: 14))
            }

            Text(String(format: "$%.2f", totalBalance))
                .font(.system(size: 36, weight: .bold))
                .foregroundColor(.white)

            Spacer().frame(height: 12)

            HStack(spacing: 12) {
                BalanceStat(label: "Income", amount: income, isIncome: true)
                BalanceStat(label: "Expenses", amount: expenses, isIncome: false)
            }
        }
        .padding(24)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(
            LinearGradient(
                colors: [AppColors.blueDark, AppColors.blueLight],
                startPoint: .leading,
                endPoint: .trailing
            )
        )
        .cornerRadius(20)
        .padding(.horizontal, 20)
    }
}
