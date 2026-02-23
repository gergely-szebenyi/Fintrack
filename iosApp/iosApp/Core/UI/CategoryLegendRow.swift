import SwiftUI
import Shared

struct CategoryLegendRow: View {
    let category: CategorySpend

    var body: some View {
        HStack {
            Circle()
                .fill(category.color)
                .frame(width: 12, height: 12)

            Spacer().frame(width: 10)

            Text(category.name)
                .font(.system(size: 14))
                .foregroundColor(AppColors.textPrimary)

            Spacer()

            Text(String(format: "$%.2f", category.amount))
                .font(.system(size: 14, weight: .semibold))
                .foregroundColor(AppColors.textPrimary)

            Spacer().frame(width: 12)

            Text("\(Int(category.percentage))%")
                .font(.system(size: 13))
                .foregroundColor(AppColors.textSecondary)
                .frame(width: 36, alignment: .trailing)
        }
    }
}
