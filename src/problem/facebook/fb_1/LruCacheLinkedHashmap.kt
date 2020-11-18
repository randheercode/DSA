package problem.facebook.fb_1

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

