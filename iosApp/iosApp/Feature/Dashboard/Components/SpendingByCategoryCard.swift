import SwiftUI
import Shared

struct SpendingByCategoryCard: View {
    let categories: [CategorySpend]

    var body: some View {
        VStack(alignment: .leading, spacing: 20) {
            Text("Spending by Category")
                .font(.system(size: 16, weight: .bold))
                .foregroundColor(AppColors.textPrimary)

            DonutChart(categories: categories)
                .frame(width: 180, height: 180)
                .frame(maxWidth: .infinity)

            VStack(spacing: 12) {
                ForEach(categories) { cat in
                    CategoryLegendRow(category: cat)
                }
            }
        }
        .padding(20)
        .background(AppColors.cardWhite)
        .cornerRadius(20)
        .shadow(color: .black.opacity(0.06), radius: 4, x: 0, y: 2)
        .padding(.horizontal, 20)
    }
}
