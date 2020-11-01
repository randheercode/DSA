package problem.ic_contest

import org.junit.Assert
import org.junit.Test
import problem.lc_contest.W213

class W213Test {

    private val obj = W213()

    @Test
    fun canFormArray() {
        Assert.assertTrue(obj.canFormArray(intArrayOf(85), arrayOf(
                intArrayOf(85)
        )))
        Assert.assertTrue(obj.canFormArray(intArrayOf(15, 88), arrayOf(
                intArrayOf(88),
                intArrayOf(15)
        )))
        Assert.assertFalse(obj.canFormArray(intArrayOf(49, 18, 16), arrayOf(
                intArrayOf(16, 18, 49)
        )))
        Assert.assertTrue(obj.canFormArray(intArrayOf(91, 4, 64, 78), arrayOf(
                intArrayOf(78),
                intArrayOf(4, 64),
                intArrayOf(91)
        )))
        Assert.assertFalse(obj.canFormArray(intArrayOf(1, 3, 5, 7), arrayOf(
                intArrayOf(2, 4, 6, 8)
        )))
        Assert.assertTrue(obj.canFormArray(intArrayOf(37, 69, 3, 74, 46), arrayOf(
                intArrayOf(37, 69, 3, 74, 46)
        )))
    }


}

