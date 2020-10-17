package problem.challenge

import generateIntArray
import problem.old.ListNode
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

    fun removeDuplicateLetters(s: String): String? {
        val stack = Stack<Char>()
        val seen = HashSet<Char>()
        val lastOccurrence = HashMap<Char, Int>()
        for (i in s.indices) lastOccurrence[s[i]] = i
        for (i in s.indices) {
            val c = s[i]
            if (!seen.contains(c)) {
                while (!stack.isEmpty() && c < stack.peek() && lastOccurrence[stack.peek()]!! > i) {
                    seen.remove(stack.pop())
                }
                seen.add(c)
                stack.push(c)
            }
        }
        val sb = StringBuilder(stack.size)
        for (c in stack) sb.append(c)
        return sb.toString()
    }

    fun buddyStrings(A: String, B: String): Boolean {
        if (A.length != B.length || A.isEmpty()) return false
        val freq = IntArray(26)
        if (A == B) {
            for (c in A) freq[c - 'a']++
            return freq.any { it > 1 }
        }
        var first = -1
        var last = -1
        var idx = 0
        while (idx < A.length) {
            if (A[idx] != B[idx]) {
                when {
                    first == -1 -> first = idx
                    last == -1 -> last = idx
                    else -> return false
                }
            }
            idx++
        }
        return last != -1 && A[first] == B[last] && A[last] == B[first]
    }

    fun sortList(head: ListNode?): ListNode? {
        if (head?.next == null) return head
        val mid: ListNode = getMid(head)
        val left: ListNode? = sortList(head)
        val right: ListNode? = sortList(mid)
        return merge(left, right)
    }

    private fun merge(list1: ListNode?, list2: ListNode?): ListNode? {
        var list1: ListNode? = list1
        var list2: ListNode? = list2
        val dummyHead = ListNode(0)
        var tail: ListNode? = dummyHead
        while (list1 != null && list2 != null) {
            if (list1.`val` < list2.`val`) {
                tail?.next = list1
                list1 = list1.next
                tail = tail?.next
            } else {
                tail?.next = list2
                list2 = list2.next
                tail = tail?.next
            }
        }
        tail?.next = list1 ?: list2
        return dummyHead.next
    }

    private fun getMid(head: ListNode?): ListNode {
        var head: ListNode? = head
        var midPrev: ListNode? = null
        while (head?.next != null) {
            midPrev = if (midPrev == null) head else midPrev.next
            head = head.next?.next
        }
        val mid = midPrev?.next
        midPrev?.next = null
        return mid!!
    }

    fun rob(nums: IntArray): Int {
        var prevMax = 0
        var currMax = 0
        for (x in nums) {
            val temp = currMax
            currMax = maxOf(prevMax + x, currMax)
            prevMax = temp
        }
        return currMax
    }

    fun rob2(nums: IntArray): Int {
        fun robSimple(nums: IntArray, start: Int, end: Int): Int {
            var t1 = 0
            var t2 = 0
            for (i in start..end) {
                val temp = t1
                val current = nums[i]
                t1 = maxOf(current + t2, t1)
                t2 = temp
            }
            return t1
        }
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]
        val max1 = robSimple(nums, 0, nums.size - 2)
        val max2 = robSimple(nums, 1, nums.size - 1)
        return maxOf(max1, max2)
    }

    fun rob(root: TreeNode?): Int {
        fun robHelper(node: TreeNode?): IntArray {
            // return [rob this node, not rob this node]
            if (node == null) {
                return intArrayOf(0, 0)
            }
            val left = robHelper(node.left)
            val right = robHelper(node.right)
            // if we rob this node, we cannot rob its children
            val rob = node.`val` + left[1] + right[1]
            // else, we free to choose rob its children or not
            val notRob = maxOf(left[0], left[1]) + maxOf(right[0], right[1])
            return intArrayOf(rob, notRob)
        }

        val answer = robHelper(root)
        return maxOf(answer[0], answer[1])
    }

    fun rotate(nums: IntArray, k: Int) {
        var k = k
        k %= nums.size
        var count = 0
        var start = 0
        while (count < nums.size) {
            var current = start
            var prev = nums[start]
            do {
                val next = (current + k) % nums.size
                val temp = nums[next]
                nums[next] = prev
                prev = temp
                current = next
                count++
            } while (start != current)
            start++
        }
    }

    fun minMeetingRooms(intervals: Array<IntArray>): Int {
        Arrays.sort(intervals) { a, b -> a[0] - b[0] }
        val pq = PriorityQueue<IntArray> { a, b -> a[1] - b[1] }
        for (i in intervals) {
            if (!pq.isEmpty() && pq.peek()[1] <= i[0]) {
                pq.poll()
            }
            pq.add(i)
        }
        return pq.size
    }

    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val m: Int = matrix.size
        if (m == 0) return false
        val n: Int = matrix[0].size

        var left = 0
        var right = m * n - 1
        var pivotIdx: Int
        var pivotElement: Int
        while (left <= right) {
            pivotIdx = (left + right) / 2
            pivotElement = matrix[pivotIdx / n][pivotIdx % n]
            if (target == pivotElement) return true else {
                if (target < pivotElement) right = pivotIdx - 1 else left = pivotIdx + 1
            }
        }
        return false
    }

}

fun main() {
//    println(OctLC().buddyStrings("ab", "ba"))
//    println(!OctLC().buddyStrings("ab", "ab"))
//    println(OctLC().buddyStrings("aa", "aa"))
//    println(OctLC().buddyStrings("aaaaaaabc", "aaaaaaabc"))
//    println(!OctLC().buddyStrings("", "aa"))
//    println(!OctLC().buddyStrings("aabbc", "abaad"))
//    println(!OctLC().buddyStrings("abcaa", "abcbb"))
//    println(!OctLC().buddyStrings("abc", "acd"))
    println(OctLC().searchMatrix(generateIntArray("[[1,3,5,7],[10,11,16,20],[23,30,34,50]]"), 3))
    println(OctLC().searchMatrix(generateIntArray("[[1,3,5,7],[10,11,16,20],[23,30,34,50]]"), 13))
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

