import SwiftUI
import Shared

struct CategoryLegendRow: View {
    let spend: Shared.CategorySpend

    var body: some View {
        HStack {
            Circle()
                .fill(spend.category.categoryColor)
                .frame(width: 12, height: 12)

            Spacer().frame(width: 10)

            Text(spend.category.name)
                .font(.system(size: 14))
                .foregroundColor(AppColors.textPrimary)

            Spacer()

            Text(String(format: "$%.2f", spend.amount))
                .font(.system(size: 14, weight: .semibold))
                .foregroundColor(AppColors.textPrimary)

            Spacer().frame(width: 12)

            Text("\(Int(spend.percentage))%")
                .font(.system(size: 13))
                .foregroundColor(AppColors.textSecondary)
                .frame(width: 36, alignment: .trailing)
        }
    }
}
