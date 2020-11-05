package algorithm

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 5/11/20
 * Time: 11:38 pm
 */
class CountingSortTest {
    @Test
    fun sort() {
        Assert.assertEquals("[1, 1, 2, 2, 4, 5, 7]", CountingSort().sort(intArrayOf(1, 4, 1, 2, 7, 5, 2)).toList().toString())
    }
}