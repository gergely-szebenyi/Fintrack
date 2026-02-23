import SwiftUI

struct TopBar: View {
    var body: some View {
        HStack {
            Spacer()
            Text("Finance Tracker")
                .font(.system(size: 18, weight: .bold))
                .foregroundColor(AppColors.textPrimary)
            Spacer()
        }
        .padding(.horizontal, 20)
        .padding(.vertical, 16)
    }
}
