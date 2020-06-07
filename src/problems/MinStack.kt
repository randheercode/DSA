package problems

import java.util.*


/**
 * Created by randheercode
 * Date: 7/6/20
 * Time: 4:47 pm
 */
internal class MinStack {
    private val stack = Stack<Int>()
    private val minStack = Stack<Int>()
    fun push(x: Int) {
        stack.push(x)
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x)
        }
    }

    fun pop() {
        if (stack.peek() == minStack.peek()) {
            minStack.pop()
        }
        stack.pop()
    }

    fun top(): Int {
        return stack.peek()
    }

    fun getMin(): Int {
        return minStack.peek()
    }
}

