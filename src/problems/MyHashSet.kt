package problems

import java.util.*


/**
 * Created by randheercode
 * Date: 3/8/20
 * Time: 10:15 am
 */


class MyHashSet {
    private val bucketArray: Array<Bucket?>?
    private val keyRange: Int = 769
    fun hash(key: Int): Int {
        return key % keyRange
    }

    fun add(key: Int) {
        val bucketIndex = hash(key)
        bucketArray!![bucketIndex]!!.insert(key)
    }

    fun remove(key: Int) {
        val bucketIndex = hash(key)
        bucketArray!![bucketIndex]!!.delete(key)
    }

    /** Returns true if this set contains the specified element  */
    operator fun contains(key: Int): Boolean {
        val bucketIndex = hash(key)
        return bucketArray!![bucketIndex]!!.exists(key)
    }

    /** Initialize your data structure here.  */
    init {
        bucketArray = arrayOfNulls<Bucket?>(keyRange)
        for (i in 0 until keyRange) bucketArray[i] = Bucket()
    }
}


class Bucket {
    private val container: LinkedList<Int?> = LinkedList()
    fun insert(key: Int?) {
        val index: Int = container.indexOf(key)
        if (index == -1) {
            container.addFirst(key)
        }
    }

    fun delete(key: Int?) {
        container.remove(key)
    }

    fun exists(key: Int?): Boolean {
        val index: Int = container.indexOf(key)
        return index != -1
    }

}


