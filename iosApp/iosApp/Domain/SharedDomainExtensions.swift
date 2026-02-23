import SwiftUI
import Shared

// MARK: - Identifiable conformance

extension Shared.Transaction: @retroactive Identifiable {}

extension CategorySpend: @retroactive Identifiable {
    public var id: String { name }
}

// MARK: - Color resolution

extension CategorySpend {
    /// Resolves the Long color token (0xFFRRGGBB) to a SwiftUI Color.
    var color: Color {
        Color(hex: UInt32(Int(colorToken) & 0xFFFFFF))
    }
}
