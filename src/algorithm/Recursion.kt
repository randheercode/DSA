package algorithm

import utils.TreeNode
import java.util.*


class Recursion {

    // https://leetcode.com/problems/range-sum-of-bst/
    fun rangeSumBST(root: TreeNode?, L: Int, R: Int): Int {
        var sum = 0
        fun dfs(curr: TreeNode?) {
            if (curr == null) return
            if (curr.`val` in L..R) sum += curr.`val`
            if (curr.`val` > L) dfs(curr.left)
            if (curr.`val` < R) dfs(curr.right)
        }
        dfs(root)
        return sum
    }

    private fun searchCanPartitionKSubsets(groups: IntArray, row: Int, nums: IntArray, target: Int): Boolean {
        var row = row
        if (row < 0) return true
        val v = nums[row--]
        for (i in groups.indices) {
            if (groups[i] + v <= target) {
                groups[i] += v
                if (searchCanPartitionKSubsets(groups, row, nums, target)) return true
                groups[i] -= v
            }
            if (groups[i] == 0) break
        }
        return false
    }

    fun canPartitionKSubsets(nums: IntArray, k: Int): Boolean {
        var k = k
        val sum = Arrays.stream(nums).sum()
        if (sum % k > 0) return false
        val target = sum / k
        Arrays.sort(nums)
        var row = nums.size - 1
        if (nums[row] > target) return false
        while (row >= 0 && nums[row] == target) {
            row--
            k--
        }
        return searchCanPartitionKSubsets(IntArray(k), row, nums, target)
    }

    private fun countOfAtomsParser(formula: String, factor: Int): Map<String, Int> {
        val map = mutableMapOf<String, Int>()
        var i = 0
        while (i < formula.length) {

            if (formula[i].isUpperCase()) {
                var atom = formula[i].toString()
                var count = ""
                var j = i + 1
                while (j < formula.length && formula[j].isLowerCase()) {
                    atom += formula[j]
                    j++
                }
                while (j < formula.length && formula[j].isDigit()) {
                    count += formula[j]
                    j++
                }
                map[atom] = map.getOrDefault(atom, 0) + ((count.toIntOrNull() ?: 1) * factor)
                i = j
            } else if (formula[i] == '(') {
                var openCount = 1
                var close = -1
                var k = i + 1
                while (k < formula.length && close < 0) {
                    if (formula[k] == '(') openCount++
                    else if (formula[k] == ')') openCount--
                    if (openCount == 0) close = k
                    k++
                }
                var count = ""
                var j = close + 1
                while (j < formula.length && formula[j].isDigit()) {
                    count += formula[j]
                    j++
                }
                val atoms = countOfAtomsParser(formula.substring(i + 1 until close), (count.toIntOrNull() ?: 0) * factor)
                for (key in atoms.keys) {
                    map[key] = map.getOrDefault(key, 0) + atoms.getOrDefault(key, 0)
                }
                i = j
            }
        }
        return map
    }

    fun countOfAtoms(formula: String): String {
        val resultMap = countOfAtomsParser(formula, 1)
        val result = resultMap.toSortedMap(kotlin.Comparator { a, b -> a.compareTo(b) })
        return result.map { "${it.key}${if (it.value > 1) it.value.toString() else ""}" }.joinToString("")
    }

    fun isStrobogrammatic(num: String): Boolean {
        var start = 0
        var end: Int = num.lastIndex
        while (start <= end) {
            when (num[start]) {
                '0', '1', '8' -> if (num[end] != num[start]) {
                    return false
                }
                '6' -> if (num[end] != '9') {
                    return false
                }
                '9' -> if (num[end] != '6') {
                    return false
                }
                else -> return false
            }
            start++
            end--
        }
        return true
    }

    fun findStrobogrammatic(n: Int): List<String> {
        return findStrobogrammaticHelper(n, n)
    }

    private fun findStrobogrammaticHelper(n: Int, m: Int): List<String> {
        if (n == 0) return ArrayList(listOf(""))
        if (n == 1) return ArrayList(listOf("0", "1", "8"))
        val list = findStrobogrammaticHelper(n - 2, m)
        val res: MutableList<String> = ArrayList()
        for (i in list.indices) {
            val s = list[i]
            if (n != m) res.add("0" + s + "0")
            res.add("1" + s + "1")
            res.add("6" + s + "9")
            res.add("8" + s + "8")
            res.add("9" + s + "6")
        }
        return res
    }

    private val pairs = arrayOf(charArrayOf('0', '0'), charArrayOf('1', '1'), charArrayOf('6', '9'), charArrayOf('8', '8'), charArrayOf('9', '6'))

    fun strobogrammaticInRange(low: String, high: String): Int {
        val count = intArrayOf(0)
        for (len in low.length..high.length) {
            val c = CharArray(len)
            strobogrammaticInRangeDFS(low, high, c, 0, len - 1, count)
        }
        return count[0]
    }

    private fun strobogrammaticInRangeDFS(low: String, high: String, c: CharArray, left: Int, right: Int, count: IntArray) {
        if (left > right) {
            val s = String(c)
            if (s.length == low.length && s < low ||
                    s.length == high.length && s > high) {
                return
            }
            count[0]++
            return
        }
        for (p in pairs) {
            c[left] = p[0]
            c[right] = p[1]
            if (c.size != 1 && c[0] == '0') {
                continue
            }
            if (left == right && p[0] != p[1]) {
                continue
            }
            strobogrammaticInRangeDFS(low, high, c, left + 1, right - 1, count)
        }
    }

    private fun kthGrammarGenerate(N: Int): String {
        if (N == 1) return "0"
        val old = kthGrammarGenerate(N - 1)
        val result = StringBuilder()
        for (c in old) {
            if (c == '0') result.append("01")
            else result.append("10")
        }
        println(result)
        return result.toString()
    }

    fun kthGrammar(N: Int, K: Int): Int {
        return kthGrammarGenerate(N)[K - 1].toInt() - '0'.toInt()
    }

    fun kthGrammarOptimal(N: Int, K: Int): Int {
        return if (N == 1) 0 else K.inv() and 1 xor kthGrammar(N - 1, (K + 1) / 2)
    }
}
