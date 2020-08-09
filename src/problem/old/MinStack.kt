package problem.old


/**
 * Created by randheercode
 * Date: 7/6/20
 * Time: 4:47 pm
 */
class MinStack {

    /** initialize your data structure here. */
    private val data = mutableListOf<Int>()
    private var topIndex = -1
    private var minIndex = -1

    fun push(x: Int) {
        data.add(x)
        topIndex++
        if (minIndex == -1 || x <= data[minIndex]) minIndex = topIndex
    }

    fun pop(): Int {
        return if (topIndex > -1) {
            val num = data[topIndex]
            data.removeAt(topIndex)
            if (topIndex == minIndex) {
                assignNextMin()
            }
            topIndex--
            num
        } else -1
    }

    fun top(): Int {
        return if (topIndex > -1) data[topIndex]
        else -1
    }

    fun getMin(): Int {
        return if (minIndex > -1) data[minIndex]
        else -1
    }

    private fun assignNextMin() {
        minIndex = if (data.isEmpty()) {
            -1
        } else {
            val next = data.min()!!
            data.lastIndexOf(next)
        }
    }

}