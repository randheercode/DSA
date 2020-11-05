package algorithm

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 5/11/20
 * Time: 11:38 pm
 */
class RadixSortTest {
    @Test
    fun sort() {
        Assert.assertEquals("[2, 24, 45, 66, 75, 90, 170, 802]", RadixSort().sort(intArrayOf(170, 45, 75, 90, 802, 24, 2, 66)).toList().toString())
    }
}