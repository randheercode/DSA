package problem.challenge

import utils.TreeNode

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
}