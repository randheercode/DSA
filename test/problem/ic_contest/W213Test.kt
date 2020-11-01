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

    @Test
    fun countVowelStrings() {
        Assert.assertEquals(5, obj.countVowelStrings(1))
        Assert.assertEquals(15, obj.countVowelStrings(2))
        Assert.assertEquals(66045, obj.countVowelStrings(33))
    }

    @Test
    fun furthestBuilding() {
//        Assert.assertEquals(4, obj.furthestBuilding(intArrayOf(4, 2, 7, 6, 9, 14, 12), 5, 1))
//        Assert.assertEquals(7, obj.furthestBuilding(intArrayOf(4, 12, 2, 7, 3, 18, 20, 3, 19), 10, 2))
//        Assert.assertEquals(3, obj.furthestBuilding(intArrayOf(14, 3, 19, 3), 17, 0))
        Assert.assertEquals(3, obj.furthestBuilding(intArrayOf(3, 19), 87, 1))
    }


}

