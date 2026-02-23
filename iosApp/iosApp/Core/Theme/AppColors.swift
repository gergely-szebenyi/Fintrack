import SwiftUI

struct AppColors {

    // Brand
    static let bluePrimary = Color(hex: 0x3D6BF8)
    static let blueLight   = Color(hex: 0x5B84FA)
    static let blueDark    = Color(hex: 0x2A52D0)

    // Semantic
    static let greenIncome = Color(hex: 0x4CAF50)
    static let redExpense  = Color(hex: 0xE53935)

    // Surfaces
    static let bgGray    = Color(hex: 0xF5F5F5)
    static let cardWhite = Color.white

    // Text
    static let textPrimary   = Color(hex: 0x1A1A2E)
    static let textSecondary = Color(hex: 0x9E9E9E)

    // Charts
    static let food      = Color(hex: 0x3D6BF8)
    static let transport = Color(hex: 0x9B59B6)
    static let shopping  = Color(hex: 0xE91E8C)
    static let coffee    = Color(hex: 0xF39C12)
    static let health    = Color(hex: 0x2ECC71)
}

extension Color {
    init(hex: UInt32) {
        let r = Double((hex >> 16) & 0xFF) / 255.0
        let g = Double((hex >> 8) & 0xFF) / 255.0
        let b = Double(hex & 0xFF) / 255.0
        self.init(red: r, green: g, blue: b)
    }
}
