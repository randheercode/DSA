package problem.ic_contest

import org.junit.Assert
import org.junit.Test
import problem.lc_contest.W215

class W215Test {

    private val obj = W215()

    @Test
    fun closeStrings() {
        Assert.assertTrue(obj.closeStrings("abc", "bca"))
        Assert.assertFalse(obj.closeStrings("a", "aa"))
        Assert.assertTrue(obj.closeStrings("cabbba", "abbccc"))
        Assert.assertFalse(obj.closeStrings("cabbba", "aabbss"))
        Assert.assertFalse(obj.closeStrings("abbzzca", "babzzcz"))
    }

    @Test
    fun minOps() {
        Assert.assertEquals(2, obj.minOperations(intArrayOf(1, 1, 4, 2, 3), 5))
        Assert.assertEquals(-1, obj.minOperations(intArrayOf(5, 6, 7, 8, 9), 4))
        Assert.assertEquals(5, obj.minOperations(intArrayOf(3, 2, 20, 1, 1, 3), 10))
        Assert.assertEquals(-1, obj.minOperations(intArrayOf(1, 1), 3))
        Assert.assertEquals(16, obj.minOperations(intArrayOf(8828, 9581, 49, 9818, 9974, 9869, 9991, 10000, 10000, 10000, 9999, 9993, 9904, 8819, 1231, 6309), 134365))
    }

}

