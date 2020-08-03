package problems

/**
 * Created by randheercode
 * Date: 3/8/20
 * Time: 11:19 pm
 */
class MaxStack {

    /** initialize your data structure here. */
    private val data = mutableListOf<Int>()
    private var topIndex = -1
    private var maxIndex = -1

    fun push(x: Int) {
        data.add(x)
        topIndex++
        if (maxIndex == -1 || x >= data[maxIndex]) maxIndex = topIndex
    }

    fun pop(): Int {
        return if (topIndex > -1) {
            val num = data[topIndex]
            data.removeAt(topIndex)
            if (topIndex == maxIndex) {
                assignNextMax()
            }
            topIndex--
            num
        } else -1
    }

    fun top(): Int {
        return if (topIndex > -1) data[topIndex]
        else -1
    }

    fun peekMax(): Int {
        return if (maxIndex > -1) data[maxIndex]
        else -1
    }

    fun popMax(): Int {
        return if (maxIndex > -1) {
            val num = data[maxIndex]
            data.removeAt(maxIndex)
            topIndex--
            assignNextMax()
            num
        } else -1
    }

    private fun assignNextMax() {
        maxIndex = if (data.isEmpty()) {
            -1
        } else {
            val next = data.max()!!
            data.lastIndexOf(next)
        }
    }

}

fun main() {
    val stack = MaxStack()
    stack.push(5);
    stack.push(1);
    stack.push(5);
    println(stack.top())
    println(stack.popMax())
    println(stack.top())
    println(stack.peekMax())
    println(stack.pop())
    println(stack.top())
}