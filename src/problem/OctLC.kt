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

    fun search(nums: IntArray, target: Int): Int {
        var start = 0
        var end = nums.lastIndex
        while (start <= end) {
            val mid = start + (end - start) / 2
            if (nums[mid] == target) return mid
            if (nums[mid] > target) end = mid - 1
            if (nums[mid] < target) start = mid + 1
        }
        return -1
    }

    fun findMinArrowShots(points: Array<IntArray>): Int {
        fun merge(intervals: Array<IntArray>): Array<IntArray> {
            if (intervals.size <= 1) return intervals
            Arrays.sort(intervals, compareBy { it[0] })
            val result = mutableListOf<IntArray>()
            for (interval in intervals) {
                if (result.isEmpty()) {
                    result.add(interval)
                    continue
                }
                if (interval[0] <= result.last()[1]) {
                    result.last()[0] = maxOf(interval[0], result.last()[0])
                    result.last()[1] = minOf(interval[1], result.last()[1])
                } else {
                    result.add(interval)
                }
            }
            return result.toTypedArray()
        }
        return merge(points).size
    }
}

fun main() {
    println(OctLC().findMinArrowShots(arrayOf(
            intArrayOf(10, 16),
            intArrayOf(2, 8),
            intArrayOf(1, 6),
            intArrayOf(7, 12)
    )))
    println(OctLC().findMinArrowShots(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4),
            intArrayOf(5, 6),
            intArrayOf(7, 8)
    )))
    println(OctLC().findMinArrowShots(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 3),
            intArrayOf(3, 4),
            intArrayOf(4, 5)
    )))
    println(OctLC().findMinArrowShots(arrayOf(
            intArrayOf(2,3),
            intArrayOf(2, 3)
    )))
    println(OctLC().findMinArrowShots(arrayOf(
            intArrayOf(1, 2)
    )))
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

class TwoSum {

    /** Initialize your data structure here. */
    private val freq = mutableMapOf<Int, Int>()
    private val nums = mutableSetOf<Int>()


    /** Add the number to an internal data structure.. */
    fun add(number: Int) {
        nums.add(number)
        freq[number] = freq.getOrDefault(number, 0) + 1
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    fun find(value: Int): Boolean {
        for (num in nums) {
            val keyToLook = value - num
            if (freq.containsKey(keyToLook) && (num != keyToLook || freq.getOrDefault(num, 0) > 1)) {
                return true
            }
        }
        return false
    }

}

class BinaryTreeCodec() {
    // Encodes a tree to a single string.
    fun serialize(root: TreeNode?): String {
        if (root == null) return "[]"
        val queue: Queue<TreeNode?> = LinkedList<TreeNode>()
        queue.add(root)
        var sol = "["
        while (!queue.isEmpty()) {
            val temp = queue.remove()
            if (temp != null) {
                queue.add(temp.left)
                queue.add(temp.right)
                sol += temp.`val`
            } else sol += "null"
            sol += ","
        }
        var j = sol.length - 1
        while (j >= 0 && !Character.isDigit(sol[j])) {
            j--
        }
        sol = sol.substring(0, j + 1)
        sol += "]"
        return sol
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        if (data.length == 2) return null
        val nodes = data.substring(1, data.length - 1).split(",".toRegex()).toTypedArray()
        val queue: Queue<TreeNode?> = LinkedList<TreeNode>()
        val sol = TreeNode(Integer.valueOf(nodes[0]))
        queue.add(sol)
        var j = 1
        while (j < nodes.size) {
            var left: TreeNode? = null
            var right: TreeNode? = null
            val temp = queue.remove()
            if (nodes[j] != "null") left = TreeNode(Integer.valueOf(nodes[j]))
            j++
            if (j < nodes.size && nodes[j] != "null") right = TreeNode(Integer.valueOf(nodes[j]))
            j++
            temp!!.left = left
            temp.right = right
            if (left != null) queue.add(left)
            if (right != null) queue.add(right)
        }
        return sol
    }
}

