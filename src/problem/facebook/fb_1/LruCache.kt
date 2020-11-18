package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 25/4/20
 * Time: 9:40 pm
 * Implementation of LRU Cache using HashMap.
 * The key to solve this problem is using a double linked list which enables us to quickly move nodes.
 * The LRU cache is a hash map of keys and double linked nodes. The hash map makes the time of get() to be O(1).
 * The list of double linked nodes make the nodes adding/removal operations O(1).
 */


internal class LRUCache(private val capacity: Int) {
    internal class Node(var key: Int, var value: Int) {
        var pre: Node? = null
        var next: Node? = null
    }

    private val map: HashMap<Int, Node?> = HashMap()
    private var count: Int
    private val head: Node = Node(0, 0)
    private val tail: Node = Node(0, 0)

    init {
        head.next = tail
        tail.pre = head
        head.pre = null
        tail.next = null
        count = 0
    }

    private fun deleteNode(node: Node?) {
        node?.pre?.next = node?.next
        node?.next?.pre = node?.pre
    }

    private fun addToHead(node: Node?) {
        node?.next = head.next
        node?.next?.pre = node
        node?.pre = head
        head.next = node
    }

    // This method works in O(1)
    fun get(key: Int): Int? {
        if (map[key] != null) {
            val node = map[key]
            val result = node?.value
            deleteNode(node)
            addToHead(node)
            println("Got the value : $result for the key: $key")
            return result
        }
        println("Did not get any value for the key: $key")
        return -1
    }

    // This method works in O(1)
    fun put(key: Int, value: Int) {
        println("Going to set the (key, value) : ($key, $value)")
        if (map[key] != null) {
            val node = map[key]
            node?.value = value
            deleteNode(node)
            addToHead(node)
        } else {
            val node = Node(key, value)
            map[key] = node
            if (count < capacity) {
                count++
                addToHead(node)
            } else {
                map.remove(tail.pre?.key)
                deleteNode(tail.pre)
                addToHead(node)
            }
        }
    }
}
