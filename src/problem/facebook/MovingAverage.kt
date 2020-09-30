package problem.facebook

import java.util.*

// https://leetcode.com/problems/moving-average-from-data-stream/
class MovingAverage(val size: Int) {

    val data = LinkedList<Int>()
    var sum = 0.0

    fun next(`val`: Int): Double {
        if (data.size == size) {
            sum -= data.removeFirst()
        }
        data.addLast(`val`)
        sum += `val`
        return sum.div(data.size)
    }

}
