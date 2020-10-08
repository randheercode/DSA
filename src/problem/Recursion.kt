package problem

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
}

fun main() {
    println(Recursion().countOfAtoms("H2O"))
    println(Recursion().countOfAtoms("Mg(OH)2"))
    println(Recursion().countOfAtoms("K4(ON(SO3)2)2"))
    println(Recursion().countOfAtoms("Be32"))
}