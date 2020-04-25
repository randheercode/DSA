package algorithm.lrucache

/**
 * Created by randheercode
 * Date: 25/4/20
 * Time: 9:40 pm
 * Implementation of LRU Cache using HashMap.
 * The key to solve this problem is using a double linked list which enables us to quickly move nodes.
 * The LRU cache is a hash map of keys and double linked nodes. The hash map makes the time of get() to be O(1).
 * The list of double linked nodes make the nodes adding/removal operations O(1).
 */
internal class Node(var key: Int, var value: Int) {
    var pre: Node? = null
    var next: Node? = null
}

internal class LRUCache(private val capacity: Int) {
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
    operator fun get(key: Int): Int? {
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
    operator fun set(key: Int, value: Int) {
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

fun main() {
    println("Going to test the LRU Cache Implementation")
    val cache = LRUCache(2)

    // it will store a key (1) with value
    // 10 in the cache.
    cache[1] = 10

    // it will store a key (1) with value 10 in the cache.
    cache[2] = 20
    println("Value for the key: 1 is " + cache[1]) // returns 10

    // evicts key 2 and store a key (3) with
    // value 30 in the cache.
    cache[3] = 30
    println("Value for the key: 2 is " + cache[2]) // returns -1 (not found)

    // evicts key 1 and store a key (4) with
    // value 40 in the cache.
    cache[4] = 40
    println("Value for the key: 1 is " + cache[1]) // returns -1 (not found)
    println("Value for the key: 3 is " + cache[3]) // returns 30
    println("Value for the key: 4 is " + cache[4]) // return 40
}