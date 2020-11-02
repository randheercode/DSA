package problem.facebook.fb_1

import kotlin.math.sqrt

/**
 * Created by randheercode
 * Date: 2/11/20
 * Time: 8:25 pm
 */
class KClosestPointsToOrigin {
    private fun distance(point: IntArray): Double {
        val dx = point[0].toDouble()
        val dy = point[1].toDouble()
        return sqrt(dx * dx + dy * dy)
    }

    fun kClosest(points: Array<IntArray>, K: Int): Array<IntArray> {
        points.sortBy { distance(it) }
        return points.sliceArray(0 until K)
    }
}