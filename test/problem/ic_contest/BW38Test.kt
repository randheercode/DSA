package problem.ic_contest

import generateIntArray
import org.junit.Assert
import org.junit.Test
import problem.lc_contest.BW38

class BW38Test {
    private val obj = BW38()

    @Test
    fun frequencySort() {
        Assert.assertEquals("[3, 1, 1, 2, 2, 2]", obj.frequencySort(intArrayOf(1, 1, 2, 2, 2, 3)).toList().toString())
        Assert.assertEquals("[1, 3, 3, 2, 2]", obj.frequencySort(intArrayOf(2, 3, 1, 3, 2)).toList().toString())
        Assert.assertEquals("[5, -1, 4, 4, -6, -6, 1, 1, 1]", obj.frequencySort(intArrayOf(-1, 1, -6, 4, 5, -6, 1, 4, 1)).toList().toString())
    }

    @Test
    fun maxWidthOfVerticalArea() {
        Assert.assertEquals(1, obj.maxWidthOfVerticalArea(generateIntArray("[[8,7],[9,9],[7,4],[9,7]]")))
        Assert.assertEquals(3, obj.maxWidthOfVerticalArea(generateIntArray("[[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]")))
    }

    @Test
    fun countSubstrings() {
        Assert.assertEquals(6, obj.countSubstrings("aba", "baba"))
    }
}

