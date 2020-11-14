package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 14/11/20
 * Time: 12:01 pm
 */
class BTFromStr {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    private fun parseChildFrom(s: String, start: Int, end: Int): Pair<Int, Int> {
        var left = -1
        var right = -1

        var st = start
        var braces = 0

        while (st <= end) {
            if (s[st] == '(') {
                if (left == -1) left = st
                braces++
            } else if (s[st] == ')') {
                braces--
                if (braces == 0 && right == -1) right = st
            }
            if (braces == 0) {
                break
            } else {
                st++
            }
        }

        return Pair(left, right)
    }

    private fun parseStr(s: String, start: Int, end: Int): TreeNode? {

        var negative = false
        var number = ""

        var idx = start

        while (idx <= end) {
            if (s[idx] == '-') {
                negative = true
                idx++
                continue
            } else if (s[idx].isDigit()) {
                number += s[idx]
                idx++
            } else {
                break
            }
        }

        val leftChildIdx = parseChildFrom(s, idx, end)
        val rightChildIdx = if (leftChildIdx.second != -1) {
            parseChildFrom(s, leftChildIdx.second.plus(1), end)
        } else {
            Pair(-1, -1)
        }

        if (number.isEmpty()) return null

        val node = TreeNode(number.toInt().times(if (negative) -1 else 1))

        if (leftChildIdx.first != -1 && leftChildIdx.second != -1) {
            node.left = parseStr(s, leftChildIdx.first + 1, leftChildIdx.second - 1)
        }

        if (rightChildIdx.first != -1 && rightChildIdx.second != -1) {
            node.right = parseStr(s, rightChildIdx.first + 1, rightChildIdx.second - 1)
        }

        return node

    }

    fun str2tree(s: String): TreeNode? {
        return parseStr(s, 0, s.length - 1)
    }
}