package problem

import java.util.*
import kotlin.math.abs

class OctLC {
    fun maxDistance(arrays: List<List<Int>>): Int {
        var res = 0
        var min_val = arrays[0][0]
        var max_val = arrays[0][arrays[0].size - 1]
        for (i in 1 until arrays.size) {
            res = maxOf(res, abs(arrays[i][arrays[i].size - 1] - min_val), abs(max_val - arrays[i][0]))
            min_val = minOf(min_val, arrays[i][0])
            max_val = maxOf(max_val, arrays[i][arrays[i].size - 1])
        }
        return res
    }
}

fun main() {
    println(OctLC().maxDistance(arrayListOf(
            listOf(1, 4),
            listOf(0, 5)
    )))
}

class RecentCounter {
    var slideWindow: LinkedList<Int> = LinkedList()

    fun ping(t: Int): Int {
        // step 1). append the current call
        slideWindow.addLast(t)

        // step 2). invalidate the outdated pings
        while (slideWindow.size > 0) {
            if (slideWindow.first < t - 3000) slideWindow.removeFirst() else break
        }
        return slideWindow.size
    }

}