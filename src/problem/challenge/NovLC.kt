package problem.challenge

import problem.facebook.old.TreeNode
import java.lang.StrictMath.pow
import java.util.*
import kotlin.math.ceil


class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

open class NovLC {

    fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
        intervals.sortBy { it[0] }
        for (i in 1 until intervals.size) {
            if (intervals[i - 1][1] > intervals[i][0]) return false
        }
        return true
    }

    fun getDecimalValue(head: ListNode?): Int {
        var count = 0
        var current = head
        while (current?.next != null) {
            current = current.next
            count++
        }
        var result = 0
        current = head
        while (current != null) {
            if (current.`val` == 1) {
                result += pow(2.0, count.toDouble()).toInt()
            }
            current = current.next
            count--
        }
        return result
    }

    fun getDecimalValueOptimal(head: ListNode?): Int {
        var head = head
        var num = head!!.`val`
        while (head?.next != null) {
            num = num shl 1 or head.next!!.`val`
            head = head.next
        }
        return num
    }

    fun insertionSortList(head: ListNode?): ListNode? {

        val resultHead = ListNode(Int.MIN_VALUE)
        var current = head

        while (current != null) {

            // Copy and Detach
            val temp = current
            current = current.next
            temp.next = null

            var sortedCurrent: ListNode? = resultHead
            while (sortedCurrent?.next != null && sortedCurrent.next!!.`val` <= temp.`val`) {
                sortedCurrent = sortedCurrent.next
            }
            val tempNext = sortedCurrent?.next
            sortedCurrent?.next = temp
            temp.next = tempNext
        }
        return resultHead.next
    }

    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int>? {

        // base cases
        if (n < 2) {
            val centroids = ArrayList<Int>()
            for (i in 0 until n) centroids.add(i)
            return centroids
        }

        // Build the graph with the adjacency list
        val neighbors = ArrayList<MutableSet<Int>>()
        for (i in 0 until n) neighbors.add(HashSet())
        for (edge in edges) {
            val start = edge[0]
            val end = edge[1]
            neighbors[start].add(end)
            neighbors[end].add(start)
        }

        // Initialize the first layer of leaves
        var leaves = ArrayList<Int>()
        for (i in 0 until n) if (neighbors[i].size == 1) leaves.add(i)

        // Trim the leaves until reaching the centroids
        var remainingNodes = n
        while (remainingNodes > 2) {
            remainingNodes -= leaves.size
            val newLeaves = ArrayList<Int>()

            // remove the current leaves along with the edges
            for (leaf in leaves) {
                for (neighbor in neighbors[leaf]) {
                    neighbors[neighbor].remove(leaf)
                    if (neighbors[neighbor].size == 1) newLeaves.add(neighbor)
                }
            }

            // prepare for the next round
            leaves = newLeaves
        }

        // The remaining nodes are the centroids of the graph
        return leaves
    }

    fun minCostToMoveChips(position: IntArray): Int {
        var evenCnt = 0
        var oddCnt = 0
        for (i in position) {
            if (i % 2 == 0) {
                evenCnt++
            } else {
                oddCnt++
            }
        }
        return minOf(oddCnt, evenCnt)
    }


    fun maxAncestorDiff(root: TreeNode?): Int {
        return if (root == null) {
            0
        } else helper(root, root.`val`, root.`val`)
    }

    private fun helper(node: TreeNode?, curMax: Int, curMin: Int): Int {
        // if encounter leaves, return the max-min along the path
        var curMax = curMax
        var curMin = curMin
        if (node == null) {
            return curMax - curMin
        }
        // else, update max and min
        // and return the max of left and right subtrees
        curMax = maxOf(curMax, node.`val`)
        curMin = minOf(curMin, node.`val`)
        val left = helper(node.left, curMax, curMin)
        val right = helper(node.right, curMax, curMin)
        return maxOf(left, right)
    }

    fun flipAndInvertImage(A: Array<IntArray>): Array<IntArray> {
        fun reverseAndFlip(arr: IntArray) {
            var s = 0
            var e = arr.lastIndex
            while (s < e) {
                val t = arr[s]
                arr[s] = arr[e].plus(1).rem(2)
                arr[e] = t.plus(1).rem(2)
                s++
                e--
            }
            if (s == e) arr[s] = arr[s].plus(1).rem(2)
        }
        for (i in A.indices) reverseAndFlip(A[i])
        return A
    }

    fun validSquare(p1: IntArray, p2: IntArray, p3: IntArray, p4: IntArray): Boolean {
        fun dist(p1: IntArray, p2: IntArray): Double {
            return ((p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0])).toDouble()
        }

        val p = arrayOf(p1, p2, p3, p4)
        Arrays.sort(p) { l1: IntArray, l2: IntArray -> if (l2[0] == l1[0]) l1[1] - l2[1] else l1[0] - l2[0] }
        return dist(p[0], p[1]) != 0.0 && dist(p[0], p[1]) == dist(p[1], p[3]) && dist(p[1], p[3]) == dist(p[3], p[2]) && dist(p[3], p[2]) == dist(p[2], p[0]) && dist(p[0], p[3]) == dist(p[1], p[2])
    }

    fun permuteUnique(nums: IntArray): List<List<Int>>? {
        val results: MutableList<List<Int>> = ArrayList()

        // count the occurrence of each number
        val counter = HashMap<Int, Int>()
        for (num in nums) {
            if (!counter.containsKey(num)) counter[num] = 0
            counter[num] = counter[num]!! + 1
        }
        val comb: LinkedList<Int> = LinkedList()
        backtrack(comb, nums.size, counter, results)
        return results
    }

    private fun backtrack(
            comb: LinkedList<Int>,
            N: Int,
            counter: HashMap<Int, Int>,
            results: MutableList<List<Int>>) {
        if (comb.size == N) {
            // make a deep copy of the resulting permutation,
            // since the permutation would be backtracked later.
            results.add(ArrayList(comb))
            return
        }
        for ((num, count) in counter) {
            if (count == 0) continue
            // add this number into the current combination
            comb.addLast(num)
            counter[num] = count - 1

            // continue the exploration
            backtrack(comb, N, counter, results)

            // revert the choice for the next exploration
            comb.removeLast()
            counter[num] = count
        }
    }

    class Node(var `val`: Int) {
        var left: Node? = null
        var right: Node? = null
        var next: Node? = null
    }

    fun connect(root: Node?): Node? {

        val currentNodes = LinkedList<Node?>()
        val childrenNodes = LinkedList<Node?>()

        currentNodes.add(root)

        var prev: Node? = null
        var current: Node?

        while (currentNodes.isNotEmpty()) {
            current = currentNodes.poll()

            if (current?.left != null) childrenNodes.add(current.left)
            if (current?.right != null) childrenNodes.add(current.right)

            prev?.next = current
            prev = current

            if (currentNodes.isEmpty() && childrenNodes.isNotEmpty()) {
                currentNodes.addAll(childrenNodes)
                childrenNodes.clear()
                prev = null
            }

        }

        return root
    }

    fun poorPigs(buckets: Int, minutesToDie: Int, minutesToTest: Int): Int {
        val states = minutesToTest / minutesToDie + 1
        return ceil(Math.log(buckets.toDouble()) / Math.log(states.toDouble())).toInt()
    }

    open fun removeInterval(intervals: Array<IntArray>, toBeRemoved: IntArray): List<List<Int>>? {
        val res: MutableList<List<Int>> = mutableListOf()
        for (interval in intervals) {

            if (toBeRemoved[0] > interval[0] && toBeRemoved[0] < interval[1]) {
                val list: MutableList<Int> = ArrayList()
                list.add(interval[0])
                list.add(toBeRemoved[0])
                res.add(list)
            }

            if (toBeRemoved[1] < interval[1] && toBeRemoved[1] > interval[0]) {
                val list: MutableList<Int> = ArrayList()
                list.add(toBeRemoved[1])
                list.add(interval[1])
                res.add(list)
            }

            if (toBeRemoved[0] >= interval[1] || interval[0] >= toBeRemoved[1]) {
                val list: MutableList<Int> = ArrayList()
                list.add(interval[0])
                list.add(interval[1])
                res.add(list)
            }
        }
        return res
    }

    fun longestMountain(A: IntArray): Int {
        val N = A.size
        var ans = 0
        var base = 0
        while (base < N) {
            var end = base
            // if base is a left-boundary
            if (end + 1 < N && A[end] < A[end + 1]) {
                // set end to the peak of this potential mountain
                while (end + 1 < N && A[end] < A[end + 1]) end++

                // if end is really a peak..
                if (end + 1 < N && A[end] > A[end + 1]) {
                    // set end to the right-boundary of mountain
                    while (end + 1 < N && A[end] > A[end + 1]) end++
                    // record candidate answer
                    ans = Math.max(ans, end - base + 1)
                }
            }
            base = Math.max(end, base + 1)
        }
        return ans
    }

    open fun longestArithSeqLength(A: IntArray): Int {
        val hash = Array(A.size) { IntArray(20000) }
        var max = 0
        for (i in 1 until A.size) for (j in i - 1 downTo 0) {
            val diff = A!![i] - A!![j] + 10000
            val counttillnow = hash[j][diff]
            if (hash[i][diff] > counttillnow) continue else {
                hash[i][diff] = counttillnow + 1
                max = Math.max(max, counttillnow + 1)
            }
        }
        return max + 1
    }

    fun search(nums: IntArray, target: Int): Boolean {

        // returns true if we can reduce the search space in current binary search space
        fun isBinarySearchHelpful(arr: IntArray, start: Int, element: Int): Boolean {
            return arr[start] != element
        }

        // returns true if element exists in first array, false if it exists in second
        fun existsInFirst(arr: IntArray, start: Int, element: Int): Boolean {
            return arr[start] <= element
        }

        val n = nums.size
        if (n == 0) return false
        var end = n - 1
        var start = 0
        while (start <= end) {
            val mid = start + (end - start) / 2
            if (nums[mid] == target) {
                return true
            }
            if (!isBinarySearchHelpful(nums, start, nums[mid])) {
                start++
                continue
            }
            // which array does pivot belong to.
            val pivotArray = existsInFirst(nums, start, nums[mid])

            // which array does target belong to.
            val targetArray = existsInFirst(nums, start, target)
            if (pivotArray xor targetArray) { // If pivot and target exist in different sorted arrays, recall that xor is true when both operands are distinct
                if (pivotArray) {
                    start = mid + 1 // pivot in the first, target in the second
                } else {
                    end = mid - 1 // target in the first, pivot in the second
                }
            } else { // If pivot and target exist in same sorted array
                if (nums[mid] < target) {
                    start = mid + 1
                } else {
                    end = mid - 1
                }
            }
        }
        return false
    }

    fun atMostNGivenDigitSet(digits: Array<String>, n: Int): Int {
        val S = n.toString()
        val K = S.length
        val dp = IntArray(K + 1)
        dp[K] = 1

        for (i in K - 1 downTo 0) {
            // compute dp[i]
            val Si = S[i] - '0'
            for (d in digits) {
                if (Integer.valueOf(d) < Si)
                    dp[i] += Math.pow(digits.size.toDouble(), K - i - 1.toDouble()).toInt()
                else if (Integer.valueOf(d) == Si)
                    dp[i] += dp[i + 1]
            }
        }

        for (i in 1 until K)
            dp[0] += Math.pow(digits.size.toDouble(), i.toDouble()).toInt()
        return dp[0]
    }

    open fun lengthOfLongestSubstringTwoDistinct(s: String?): Int {
        if (s == null || s.isEmpty()) return 0
        var left = 0
        var right = 0
        var max = 0
        val map: MutableMap<Char, Int> = HashMap()
        while (right < s.length) {
            if (map.size <= 2) {
                map[s[right]] = right
            }
            if (map.size > 2) {
                var leftMost = s.length
                for (index in map.values) {
                    leftMost = Math.min(index, leftMost)
                }
                map.remove(s[leftMost])
                left = leftMost + 1
            }
            max = Math.max(right - left + 1, max)
            right++
        }
        return max
    }

    fun uniqueMorseRepresentations(words: Array<String>): Int {
        val morse = arrayOf(".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
                "....", "..", ".---", "-.-", ".-..", "--", "-.",
                "---", ".--.", "--.-", ".-.", "...", "-", "..-",
                "...-", ".--", "-..-", "-.--", "--..")
        val seen: MutableSet<String> = HashSet()
        for (word in words) {
            val code = StringBuilder()
            for (c in word.toCharArray()) code.append(morse[c - 'a'])
            seen.add(code.toString())
        }
        return seen.size
    }

}
