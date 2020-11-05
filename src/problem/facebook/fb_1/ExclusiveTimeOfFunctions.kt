package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 5/11/20
 * Time: 3:13 pm
 */
class ExclusiveTimeOfFunctions {
    fun exclusiveTime(n: Int, logs: List<String>): IntArray {
        val stack = Stack<Int>()
        val res = IntArray(n)
        var s: List<String> = logs[0].split(":")
        stack.push(s[0].toInt())
        var i = 1
        var prev = s[2].toInt()
        while (i < logs.size) {
            s = logs[i].split(":")
            if (s[1] == "start") {
                if (!stack.isEmpty()) res[stack.peek()] += s[2].toInt() - prev
                stack.push(s[0].toInt())
                prev = s[2].toInt()
            } else {
                res[stack.peek()] += s[2].toInt() - prev + 1
                stack.pop()
                prev = s[2].toInt() + 1
            }
            i++
        }
        return res
    }
}