import SwiftUI
import Shared

// MARK: - Identifiable conformance

extension Shared.Transaction: @retroactive Identifiable {}

extension Shared.Category: @retroactive Identifiable {}

extension Shared.CategorySpend: @retroactive Identifiable {
    public var id: String { category.id }
}

// MARK: - Color resolution

extension Shared.Category {
    /// Parses the hex color string (e.g. "#3D6BF8") to a SwiftUI Color.
    var categoryColor: Color {
        let hex = color.trimmingCharacters(in: CharacterSet(charactersIn: "#"))
        guard let value = UInt32(hex, radix: 16) else { return .gray }
        return Color(hex: value)
    }
}
