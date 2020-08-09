package problem.old

/**
 * Created by randheercode
 * Date: 2/6/20
 * Time: 4:06 pm
 */
class DeleteNode {
    fun deleteNode(node: ListNode?) {
        node?.`val` = node?.next?.`val` ?: 0
        node?.next = node?.next?.next
    }
}