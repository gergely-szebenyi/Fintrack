import SwiftUI

struct RecentTransactionsHeader: View {
    var body: some View {
        Text("Recent Transactions")
            .font(.system(size: 18, weight: .bold))
            .foregroundColor(AppColors.textPrimary)
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(.horizontal, 20)
            .padding(.vertical, 8)
    }
}
