package problem

import utils.TreeNode
import java.util.*
import kotlin.math.abs
import kotlin.math.log


class OctLC {
    fun maxDistance(arrays: List<List<Int>>): Int {
        var res = 0
        var min_val = arrays[0][0]
        var max_val = arrays[0][arrays[0].size - 1]
        for (i in 1 until arrays.size) {
            res = maxOf(res, abs(arrays[i][arrays[i].size - 1] - min_val), abs(max_val - arrays[i][0]))
            min_val = minOf(min_val, arrays[i][0])
            max_val = maxOf(max_val, arrays[i][arrays[i].size - 1])
        }
        return res
    }

    fun findPairs(nums: IntArray, k: Int): Int {
        Arrays.sort(nums)
        var left = 0
        var right = 1
        var result = 0
        while (left < nums.size && right < nums.size) {
            if (left == right || nums[right] - nums[left] < k) {
                // List item 1 in the text
                right++
            } else if (nums[right] - nums[left] > k) {
                // List item 2 in the text
                left++
            } else {
                // List item 3 in the text
                left++
                result++
                while (left < nums.size && nums[left] == nums[left - 1]) left++
            }
        }
        return result
    }

    fun removeCoveredIntervals(intervals: Array<IntArray>): Int {
        val intervalSet = intervals.toHashSet()
        var count = 0
        for (interval in intervals) {
            intervalSet.remove(interval)
            val has = intervalSet.find { it[0] <= interval[0] && it[1] >= interval[1] }
            if (has == null) count++
            intervalSet.add(interval)
        }
        return count
    }

    fun findComplement(num: Int): Int {
        val str = StringBuilder(num.toString(2))
        for (i in str.indices)
            str[i] = if (str[i] == '0') '1' else '0'
        return str.toString().toInt(2)
    }

    fun bitwiseComplement(N: Int): Int {
        val str = StringBuilder(N.toString(2))
        for (i in str.indices)
            str[i] = if (str[i] == '0') '1' else '0'
        return str.toString().toInt(2)
    }

    fun bitwiseComplementO1(N: Int): Int {
        val l = log(N.toDouble(), 2.0).toInt() + 1
        val bitmask = (1 shl l) - 1
        return bitmask xor N
    }

    fun insertIntoBST(root: TreeNode?, `val`: Int): TreeNode? {
        if (root == null) return TreeNode(`val`)
        if (`val` > root.`val`) root.right = insertIntoBST(root.right, `val`)
        else root.left = insertIntoBST(root.left, `val`)
        return root
    }
}

fun main() {
    println("findComplement")
    println(OctLC().findComplement(5))
    println("bitwiseComplement")
    println(OctLC().bitwiseComplement(10))
    println("bitwiseComplementO1")
    println(OctLC().bitwiseComplementO1(10))
}

class RecentCounter {
    var slideWindow: LinkedList<Int> = LinkedList()

    fun ping(t: Int): Int {
        // step 1). append the current call
        slideWindow.addLast(t)

        // step 2). invalidate the outdated pings
        while (slideWindow.size > 0) {
            if (slideWindow.first < t - 3000) slideWindow.removeFirst() else break
        }
        return slideWindow.size
    }

}