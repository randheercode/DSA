package problems

import kotlin.random.Random

/**
 * Created by randheercode
 * Date: 12/6/20
 * Time: 6:02 pm
 */
class RandomizedSet() {

    private val set = HashSet<Int>()
    private val random = Random.Default


    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    fun insert(`val`: Int): Boolean {
        return set.add(`val`)
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    fun remove(`val`: Int): Boolean {
        return set.remove(`val`)
    }

    /** Get a random element from the set. */
    fun getRandom(): Int {
        return set.toList()[random.nextInt(set.size)]
    }

}