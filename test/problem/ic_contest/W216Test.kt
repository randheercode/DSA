package problem.ic_contest

import org.junit.Assert
import org.junit.Test
import problem.lc_contest.W216


class W216Test {
    private val obj = W216()

    @Test
    fun arrayStringsAreEqual() {
        Assert.assertTrue(obj.arrayStringsAreEqual(arrayOf("ab", "c"), arrayOf("a", "bc")))
    }

    @Test
    fun getSmallestString() {
        Assert.assertEquals("aay", obj.getSmallestString(3, 27))
        Assert.assertEquals("aaszz", obj.getSmallestString(5, 73))
    }

    @Test
    fun waysToMakeFair() {
        Assert.assertEquals(1, obj.waysToMakeFair(intArrayOf(2, 1, 6, 4)))
        Assert.assertEquals(3, obj.waysToMakeFair(intArrayOf(1, 1, 1)))
        Assert.assertEquals(0, obj.waysToMakeFair(intArrayOf(1, 2, 3)))
        Assert.assertEquals(0, obj.waysToMakeFair(intArrayOf(1, 1, 1, 1, 1, 1)))
        Assert.assertEquals(1, obj.waysToMakeFair(intArrayOf(1)))
        Assert.assertEquals(0, obj.waysToMakeFair(intArrayOf(1, 1)))
    }

}
