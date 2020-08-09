package algorithm.lrucache

/**
 * Created by randheercode
 * Date: 25/4/20
 * Time: 9:48 pm
 * Implementation of LRU Cache using HashMap.
 * Another implementation in Java using LinkedHashMap:removeEldestEntry() is overridden to impose a
 * policy for removing problem.old mappings when size goes beyond capacity.
 */
internal class CustomLinkedHashMap<K, V>(private val initialCapacity: Int, loadFactor: Float, accessOrder: Boolean) : LinkedHashMap<K, V>(initialCapacity, loadFactor, accessOrder) {

    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
        return size > initialCapacity
    }

}

internal class LruCacheLinkedHashmap(capacity: Int) {
    private val map: CustomLinkedHashMap<Int, Int> = CustomLinkedHashMap(capacity, 0.75f, true)

    // This method works in O(1)
    operator fun get(key: Int): Int {
        println("Going to get the value for the key : $key")
        return map.getOrDefault(key, -1)
    }

    // This method works in O(1)
    operator fun set(key: Int, value: Int) {
        println("Going to set the (key, value) : ($key, $value)")
        map[key] = value
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
