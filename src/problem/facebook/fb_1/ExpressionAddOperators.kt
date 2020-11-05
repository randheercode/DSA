package problem.facebook.fb_1

import java.util.*
import java.util.function.Consumer


/**
 * Created by randheercode
 * Date: 5/11/20
 * Time: 3:05 pm
 */
class ExpressionAddOperators {
    private var answer = mutableListOf<String>()
    private lateinit var digits: String
    private var target: Long = 0

    fun recurse(index: Int, previousOperand: Long, currentOperand: Long, value: Long, ops: ArrayList<String>) {
        var currentOperand = currentOperand
        val nums = digits

        // Done processing all the digits in num
        if (index == nums.length) {
            // If the final value == target expected AND
            // no operand is left unprocessed
            if (value == target && currentOperand == 0L) {
                val sb = StringBuilder()
                ops.subList(1, ops.size).forEach(Consumer { v: String -> sb.append(v) })
                answer.add(sb.toString())
            }
            return
        }

        // Extending the current operand by one digit
        currentOperand = currentOperand * 10 + Character.getNumericValue(nums[index])
        val currentValRep = currentOperand.toString()

        // To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a
        // valid operand. Hence this check
        if (currentOperand > 0) {
            // NO OP recursion
            recurse(index + 1, previousOperand, currentOperand, value, ops)
        }

        // ADDITION
        ops.add("+")
        ops.add(currentValRep)
        recurse(index + 1, currentOperand, 0, value + currentOperand, ops)
        ops.removeAt(ops.size - 1)
        ops.removeAt(ops.size - 1)
        if (ops.size > 0) {

            // SUBTRACTION
            ops.add("-")
            ops.add(currentValRep)
            recurse(index + 1, -currentOperand, 0, value - currentOperand, ops)
            ops.removeAt(ops.size - 1)
            ops.removeAt(ops.size - 1)

            // MULTIPLICATION
            ops.add("*")
            ops.add(currentValRep)
            recurse(
                    index + 1,
                    currentOperand * previousOperand,
                    0,
                    value - previousOperand + currentOperand * previousOperand,
                    ops)
            ops.removeAt(ops.size - 1)
            ops.removeAt(ops.size - 1)
        }
    }

    fun addOperators(num: String, target: Int): List<String> {
        if (num.isEmpty()) {
            return ArrayList()
        }
        this.target = target.toLong()
        digits = num
        answer = ArrayList()
        recurse(0, 0, 0, 0, ArrayList())
        return answer
    }
}