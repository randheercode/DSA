package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 12/11/20
 * Time: 11:34 am
 */
class BasicCalculator2 {
    fun calculate(s: String?): Int {
        val len = s?.length ?: return 0
        if (len == 0) return 0
        val stack = Stack<Int>()
        var currentNumber = 0
        var operation = '+'
        for (i in 0 until len) {
            val currentChar = s[i]
            if (Character.isDigit(currentChar)) {
                currentNumber = currentNumber * 10 + (currentChar - '0')
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                when (operation) {
                    '-' -> {
                        stack.push(-currentNumber)
                    }
                    '+' -> {
                        stack.push(currentNumber)
                    }
                    '*' -> {
                        stack.push(stack.pop() * currentNumber)
                    }
                    '/' -> {
                        stack.push(stack.pop() / currentNumber)
                    }
                }
                operation = currentChar
                currentNumber = 0
            }
        }
        var result = 0
        while (!stack.isEmpty()) {
            result += stack.pop()
        }
        return result
    }
}