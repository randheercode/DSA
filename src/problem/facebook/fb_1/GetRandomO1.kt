package problem.facebook.fb_1

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 11:28 am
 */
class GetRandomO1 {
    var lst: ArrayList<Int> = ArrayList()
    var idx: HashMap<Int, MutableSet<Int>> = HashMap()
    var rand = Random()

    fun insert(`val`: Int): Boolean {
        if (!idx.containsKey(`val`)) idx[`val`] = LinkedHashSet()
        idx[`val`]!!.add(lst.size)
        lst.add(`val`)
        return idx[`val`]!!.size == 1
    }

    fun remove(`val`: Int): Boolean {
        if (!idx.containsKey(`val`) || idx[`val`]!!.size == 0) return false
        val removeIdx = idx[`val`]!!.iterator().next()
        idx[`val`]!!.remove(removeIdx)
        val last = lst[lst.size - 1]
        lst[removeIdx] = last
        idx[last]!!.add(removeIdx)
        idx[last]!!.remove(lst.size - 1)
        lst.removeAt(lst.size - 1)
        return true
    }

    fun getRandom(): Int {
        return lst[rand.nextInt(lst.size)]
    }
}