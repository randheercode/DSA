package problem.facebook.old

import kotlin.math.abs

// https://leetcode.com/problems/minimum-time-visiting-all-points/
class MinimumTimeVisitingAllPoints {
    fun minTimeToVisitAllPoints(points: Array<IntArray>): Int {
        var time = 0
        var last = points[0]
        for (i in 1..points.lastIndex) {
            val diffX = abs(points[i][0] - last[0])
            val diffY = abs(points[i][1] - last[1])
            val step = if (diffX > diffY) {
                diffY + (diffX - diffY)
            } else {
                diffX + (diffY - diffX)
            }
            time += step
            last = points[i]
        }
        return time
    }
}

fun main() {
    println(MinimumTimeVisitingAllPoints().minTimeToVisitAllPoints(
            arrayOf(
                    intArrayOf(1, 1),
                    intArrayOf(3, 4),
                    intArrayOf(-1, 0)
            )
    ))
    println(MinimumTimeVisitingAllPoints().minTimeToVisitAllPoints(
            arrayOf(
                    intArrayOf(3, 2),
                    intArrayOf(-2, 2)
            )
    ))
}