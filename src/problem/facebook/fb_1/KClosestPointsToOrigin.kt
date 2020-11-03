package problem.facebook.fb_1

import java.util.concurrent.ThreadLocalRandom
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

    // region: log(N) solution

    lateinit var points: Array<IntArray>

    fun kClosestOptimal(points: Array<IntArray>, K: Int): Array<IntArray>? {
        this.points = points
        sort(0, points.size - 1, K)
        return points.copyOfRange(0, K)
    }

    fun sort(i: Int, j: Int, K: Int) {
        if (i >= j) return
        val k = ThreadLocalRandom.current().nextInt(i, j)
        swap(i, k)
        val mid = partition(i, j)
        val leftLength = mid - i + 1
        if (K < leftLength) sort(i, mid - 1, K)
        else if (K > leftLength) sort(mid + 1, j, K - leftLength)
    }

    private fun partition(i: Int, j: Int): Int {
        var i = i
        var j = j
        val oi = i
        val pivot = dist(i)
        i++
        while (true) {
            while (i < j && dist(i) < pivot) i++
            while (i <= j && dist(j) > pivot) j--
            if (i >= j) break
            swap(i, j)
        }
        swap(oi, j)
        return j
    }

    private fun dist(i: Int): Int {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1]
    }

    private fun swap(i: Int, j: Int) {
        val t0 = points[i][0]
        val t1 = points[i][1]
        points[i][0] = points[j][0]
        points[i][1] = points[j][1]
        points[j][0] = t0
        points[j][1] = t1
    }

    // endregion
}