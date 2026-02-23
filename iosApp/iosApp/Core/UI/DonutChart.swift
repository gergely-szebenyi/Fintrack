import SwiftUI
import Shared

struct DonutChart: View {
    let categories: [CategorySpend]

    var body: some View {
        Canvas { context, size in
            let total = categories.reduce(0.0) { $0 + $1.amount }
            let strokeWidth: CGFloat = 40
            let radius = (min(size.width, size.height) - strokeWidth) / 2
            let center = CGPoint(x: size.width / 2, y: size.height / 2)
            var startAngle = Angle.degrees(-90)

            for cat in categories {
                let fraction = cat.amount / total
                let sweepAngle = Angle.degrees(fraction * 360 - 2) // 2° gap between segments
                var path = Path()
                path.addArc(
                    center: center,
                    radius: radius,
                    startAngle: startAngle,
                    endAngle: startAngle + sweepAngle,
                    clockwise: false
                )
                context.stroke(
                    path,
                    with: .color(cat.color),
                    style: StrokeStyle(lineWidth: strokeWidth, lineCap: .butt)
                )
                startAngle += Angle.degrees(fraction * 360)
            }
        }
    }
}
