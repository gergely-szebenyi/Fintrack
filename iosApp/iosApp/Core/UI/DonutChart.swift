import SwiftUI
import Shared

struct DonutChart: View {
    let categories: [Shared.CategorySpend]

    var body: some View {
        Canvas { context, size in
            let total = categories.reduce(0.0) { $0 + $1.amount }
            let strokeWidth: CGFloat = 40
            let radius = (min(size.width, size.height) - strokeWidth) / 2
            let center = CGPoint(x: size.width / 2, y: size.height / 2)
            var startAngle = Angle.degrees(-90)

            for spend in categories {
                let fraction = spend.amount / total
                let fullSweep = fraction * 360
                let drawSweep = fullSweep - 2

                if drawSweep > 0 {
                    var path = Path()
                    path.addArc(
                        center: center,
                        radius: radius,
                        startAngle: startAngle,
                        endAngle: startAngle + Angle.degrees(drawSweep),
                        clockwise: false
                    )
                    context.stroke(
                        path,
                        with: .color(spend.category.categoryColor),
                        style: StrokeStyle(lineWidth: strokeWidth, lineCap: .butt)
                    )
                }
                startAngle += Angle.degrees(fullSweep)
            }
        }
    }
}
