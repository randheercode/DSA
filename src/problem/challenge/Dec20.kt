package problem.challenge

import utils.TreeNode
import java.util.*
import kotlin.collections.HashSet


class Dec20 {
    private fun reverseLinkedList(head: ListNode?): ListNode? {
        val result = ListNode(0)
        var curr = head
        while (curr != null) {
            val temp = result.next
            result.next = curr
            val next = curr.next
            curr.next = temp
            curr = next
        }
        return result.next
    }

    fun plusOne(head: ListNode?): ListNode? {
        if (head == null) return null
        val reversed = reverseLinkedList(head)
        var curr = reversed
        var carry = 1
        while (curr != null && carry > 0) {
            curr.`val` += carry
            carry = curr.`val`.div(10)
            curr.`val` = curr.`val`.rem(10)
            curr = curr.next
        }
        val result = reverseLinkedList(reversed)
        if (carry > 0) {
            val newNode = ListNode(carry)
            newNode.next = result
            return newNode
        }
        return result
    }

    fun sortedSquares(nums: IntArray): IntArray {
        val result = IntArray(nums.size)
        var start = 0
        var end = nums.lastIndex
        var idx = nums.lastIndex
        while (start <= end) {
            val fromStart = nums[start] * nums[start]
            val fromEnd = nums[end] * nums[end]
            result[idx--] = if (fromEnd >= fromStart) {
                end--
                fromEnd
            } else {
                start++
                fromStart
            }
        }
        return result
    }

    private fun validateBST(root: TreeNode?, low: Int?, high: Int?): Boolean {

        if (root == null) {
            return true
        }
        return if (low != null && root.`val` <= low || high != null && root.`val` >= high) {
            false
        } else validateBST(root.right, root.`val`, high) && validateBST(root.left, low, root.`val`)
    }

    fun isValidBST(root: TreeNode?): Boolean {
        return validateBST(root, null, null)
    }

    fun increasingTriplet(nums: IntArray): Boolean {
        var firstNum = Int.MAX_VALUE
        var secondNum = Int.MAX_VALUE
        for (n in nums) {
            when {
                n <= firstNum -> {
                    firstNum = n
                }
                n <= secondNum -> {
                    secondNum = n
                }
                else -> {
                    return true
                }
            }
        }
        return false
    }

    fun cherryPickup(grid: Array<IntArray>): Int {
        val m = grid.size
        val n: Int = grid[0].size
        val dp = Array(m) { Array(n) { IntArray(n) } }
        for (row in m - 1 downTo 0) {
            for (col1 in 0 until n) {
                for (col2 in 0 until n) {
                    var result = 0
                    // current cell
                    result += grid[row][col1]
                    if (col1 != col2) {
                        result += grid[row][col2]
                    }
                    // transition
                    if (row != m - 1) {
                        var max = 0
                        for (newCol1 in col1 - 1..col1 + 1) {
                            for (newCol2 in col2 - 1..col2 + 1) {
                                if (newCol1 in 0 until n && newCol2 >= 0 && newCol2 < n) {
                                    max = Math.max(max, dp[row + 1][newCol1][newCol2])
                                }
                            }
                        }
                        result += max
                    }
                    dp[row][col1][col2] = result
                }
            }
        }
        return dp[0][0][n - 1]
    }

    fun smallestRangeII(A: IntArray, K: Int): Int {
        val N = A.size
        Arrays.sort(A)
        var ans = A[N - 1] - A[0]
        for (i in 0 until A.size - 1) {
            val a = A[i]
            val b = A[i + 1]
            val high = maxOf(A[N - 1] - K, a + K)
            val low = minOf(A[0] + K, b - K)
            ans = minOf(ans, high - low)
        }
        return ans
    }

    fun findNearestRightNode(root: TreeNode?, u: TreeNode?): TreeNode? {

        val stack = LinkedList<TreeNode?>()
        var nodeFound = true
        val list = mutableListOf<TreeNode?>()

        stack.push(root)

        while (stack.peek() != null) {

            val node = stack.peek()
            if (nodeFound) return node
            if (node?.`val` == u?.`val`) nodeFound = true
            if (nodeFound && stack.isEmpty()) return null

            if (node?.left != null) list.add(node.left)
            if (node?.right != null) list.add(node.right)

            if (stack.isEmpty()) {
                stack.addAll(list)
                list.clear()
            }
        }

        return null
    }

    private fun height(root: TreeNode?): Int {
        // An empty tree has height -1
        return if (root == null) {
            -1
        } else 1 + maxOf(height(root.left), height(root.right))
    }

    fun isBalanced(root: TreeNode?): Boolean {
        return if (root == null) {
            true
        } else Math.abs(height(root.left) - height(root.right)) < 2 && isBalanced(root.left) && isBalanced(root.right)
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        val lookup = HashSet<Int>()
        val result = IntArray(2) { -1 }
        for (i in nums.indices) {
            val num = target - nums[i]
            if (lookup.contains(num)) {
                result[0] = nums.indexOf(num)
                result[1] = i
                break
            } else {
                lookup.add(num)
            }
        }
        return result
    }

    fun nextGreaterElement(n: Int): Int {
        val a = ("" + n).toCharArray()
        var i = a.size - 2
        while (i >= 0 && a[i + 1] <= a[i]) {
            i--
        }
        if (i < 0) return -1
        var j = a.size - 1
        while (j >= 0 && a[j] <= a[i]) {
            j--
        }
        swap(a, i, j)
        reverse(a, i + 1)
        return try {
            String(a).toInt()
        } catch (e: Exception) {
            -1
        }
    }

    private fun reverse(a: CharArray, start: Int) {
        var i = start
        var j = a.size - 1
        while (i < j) {
            swap(a, i, j)
            i++
            j--
        }
    }

    private fun swap(a: CharArray, i: Int, j: Int) {
        val temp = a[i]
        a[i] = a[j]
        a[j] = temp
    }

    fun swapPairs(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }

        val firstNode: ListNode = head
        val secondNode = head.next

        firstNode.next = swapPairs(secondNode!!.next)
        secondNode.next = firstNode

        return secondNode
    }

    fun findDiagonalOrder(matrix: Array<IntArray>): IntArray {
        if (matrix.isEmpty()) return intArrayOf()
        var row = 0
        var col = 0
        val maxRow = matrix.size - 1
        val maxCol = matrix[0].size - 1
        val results = mutableListOf<Int>()
        var directionDown = false

        while (true) {
            results.add(matrix[row][col])
            if (row == maxRow && col == maxCol) {
                return results.toIntArray()
            }
            if (!directionDown) {
                when {
                    row == 0 -> {
                        if (col < maxCol) {
                            col++
                        } else {
                            row++
                        }
                        directionDown = true
                    }
                    col == maxCol -> {
                        row++
                        directionDown = true
                    }
                    else -> {
                        row--
                        col++
                    }
                }

            } else {
                when {
                    col == 0 -> {
                        if (row < maxRow) {
                            row++
                        } else {
                            col++
                        }
                        directionDown = false
                    }
                    row == maxRow -> {
                        col = minOf(maxCol, col + 1)
                        directionDown = false
                    }
                    else -> {
                        row++
                        col--
                    }
                }
            }
        }
    }

    fun numDecodings(s: String?): Int {
        val memo = HashMap<Int, Int>()

        fun recursiveWithMemo(index: Int, str: String): Int {

            if (index == str.length) {
                return 1
            }


            if (str[index] == '0') {
                return 0
            }
            if (index == str.length - 1) {
                return 1
            }

            if (memo.containsKey(index)) {
                return memo[index]!!
            }
            var ans = recursiveWithMemo(index + 1, str)
            if (str.substring(index, index + 2).toInt() <= 26) {
                ans += recursiveWithMemo(index + 2, str)
            }

            memo[index] = ans
            return ans
        }
        return if (s == null || s.isEmpty()) {
            0
        } else recursiveWithMemo(0, s)
    }

    fun countStudents(students: IntArray, sandwiches: IntArray): Int {
        val sw = LinkedList<Int>()
        val st = LinkedList<Int>()
        for (s in sandwiches) sw.offer(s)
        for (s in students) st.offer(s)

        var count = 0

        while (!sw.isEmpty() && count != st.size) {
            if (sw.peekFirst() == st.peekFirst()) {
                st.removeFirst()
                sw.removeFirst()
                count = 0
            } else {
                count++
                val d = st.removeFirst()
                st.offer(d)
            }
        }

        return st.size
    }

    fun averageWaitingTime(customers: Array<IntArray>): Double {
        var waitingTime = 0.0

        var chefBusyTill = 0

        for (c in customers) {
            waitingTime += maxOf(chefBusyTill, c[0]).plus(c[1]).minus(c[0])
            chefBusyTill = maxOf(chefBusyTill.plus(c[1]), c[0].plus(c[1]))
        }

        return waitingTime.div(customers.size)
    }

    fun halvesAreAlike(s: String): Boolean {
        val set = HashSet<Char>()
        set.add('a')
        set.add('e')
        set.add('i')
        set.add('o')
        set.add('u')
        set.add('A')
        set.add('E')
        set.add('I')
        set.add('O')
        set.add('U')

        var count = 0
        var fac = 1
        val half = s.length.div(2)
        for (i in s.indices) {
            fac = if (i >= half) -1 else 1
            if (set.contains(s[i])) {
                count += (1 * fac)
            }
        }

        return count == 0

    }

    fun eatenApples(apples: IntArray, days: IntArray): Int {
        val len: Int = apples.size

        val pq = PriorityQueue(Comparator { a: IntArray, b: IntArray -> a[1].compareTo(b[1]) })
        var startEatingDay = -1
        for (i in 0 until len) {
            if (apples[i] != 0) {
                pq.add(intArrayOf(apples[i], i + days[i]))
                if (startEatingDay == -1) startEatingDay = i
            }
        }

        var res = 0

        var i = startEatingDay
        while (!pq.isEmpty()) {
            while (!pq.isEmpty() && pq.peek()[1] <= i) {
                pq.remove()
            }
            if (!pq.isEmpty()) {
                res++
                pq.peek()[0]--
                if (pq.peek()[0] == 0) pq.remove()
            }
            i++
        }


        return res
    }

}
