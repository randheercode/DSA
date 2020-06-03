package problems

import generateIntArray

/**
 * Created by randheercode
 * Date: 3/6/20
 * Time: 5:41 pm
 * Problem: https://leetcode.com/problems/minimum-time-visiting-all-points/
 */
class MinTimeVisitingPoints {
    fun minTimeToVisitAllPoints(points: Array<IntArray>): Int {
        var res = 0
        for (i in 1 until points.size) {
            res += maxOf(Math.abs(points[i][0] - points[i - 1][0]), Math.abs(points[i][1] - points[i - 1][1]))
        }
        return res
    }
}

fun main() {
    println(MinTimeVisitingPoints().minTimeToVisitAllPoints(generateIntArray("[[1,1],[3,4],[-1,0]]")))
}