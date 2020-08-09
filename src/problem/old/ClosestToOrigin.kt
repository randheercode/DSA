package problem.old


/**
 * Created by randheercode
 * Date: 30/5/20
 * Time: 6:21 pm
 * Problem Statement: We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 */
class ClosestToOrigin {
    lateinit var points: Array<IntArray>
    fun kClosest(points: Array<IntArray>, K: Int): Array<IntArray> {
        this.points = points
        sort(0, points.size - 1, K)
        return points.copyOfRange(0, K)
    }

    private fun sort(i: Int, j: Int, K: Int) {
        if (i >= j) return
        val k = (i..j).random()
        swap(i, k)
        val mid = partition(i, j)
        val leftLength = mid - i + 1
        if (K < leftLength) sort(i, mid - 1, K) else if (K > leftLength) sort(mid + 1, j, K - leftLength)
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
}

internal class ClosestToOriginAnother {
    fun kClosest(points: Array<IntArray>, K: Int): Array<IntArray> {
        fun distance(point: IntArray): Int {
            return point[0] * point[0] + point[1] * point[1]
        }
        val distanceMap = points.map { distance(it) to it }.sortedBy { it.first }
        return distanceMap.take(K).map { it.second }.toTypedArray()
    }

}