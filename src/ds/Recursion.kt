package ds

import problem.old.ListNode
import utils.TreeNode

/**
 * Created by randheercode
 * Date: 22/6/20
 * Time: 9:41 pm
 * https://leetcode.com/explore/learn/card/recursion-i/
 */
class Recursion {

    fun reverseString(s: CharArray): Unit {
        var start = 0
        var end = s.lastIndex
        var temp: Char
        while (start < end) {
            temp = s[start]
            s[start] = s[end]
            s[end] = temp
            start++
            end--
        }
    }

    fun swapPairs(head: ListNode?): ListNode? {
        // 1->2->3->4
        // 0:- 1->2->3->4
        val dummy: ListNode? = ListNode(0)
        dummy?.next = head
        var curr = dummy

        fun swap(node: ListNode?) {
            if (node?.next?.next == null) return
            val temp = node.next // 1
            node.next = node.next?.next // 0:- 2
            temp?.next = node.next?.next // 1 -> 3
            node.next?.next = temp // 2->1
        }

        while (curr != null) {
            swap(curr)
            curr = curr.next?.next
        }
        return dummy?.next
    }

    fun reverseList(head: ListNode?): ListNode? {
        var currHead: ListNode? = null
        var curr = head
        while (curr != null) {
            val nextTemp = curr.next
            curr.next = currHead
            currHead = curr
            curr = nextTemp
        }
        return currHead
    }

    fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
        if (root == null) return null
        if (root.`val` == `val`) return root
        return if (root.`val` > `val`) searchBST(root.left, `val`)
        else searchBST(root.right, `val`)
    }

    fun getRow(rowIndex: Int): List<Int> {
        val result = ArrayList<Int>(rowIndex + 1).apply {
            repeat(rowIndex + 1) { add(0) }
        }

        repeat(rowIndex + 1) { i ->
            if (i == 0) {
                result[i] = 1
            } else {
                for (j in i downTo 0) {
                    result[j] = result[j] + if (j == 0) 0 else result[j - 1]
                }
            }
        }

        return result
    }
}