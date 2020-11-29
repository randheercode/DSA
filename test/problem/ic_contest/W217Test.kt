package problem.ic_contest

import generateIntArray
import org.junit.Assert
import org.junit.Test
import problem.lc_contest.W217


class W217Test {

    private val obj = W217()


    @Test
    fun maximumWealth() {
        Assert.assertEquals(17, obj.maximumWealth(generateIntArray("[[2,8,7],[7,1,3],[1,9,5]]")))
    }

    @Test
    fun mostCompetitive() {
        Assert.assertEquals("[1, 2]", obj.mostCompetitive(intArrayOf(1, 2, 3), 2).toList().toString())
        Assert.assertEquals("[2, 3, 3, 4]", obj.mostCompetitive(intArrayOf(2, 4, 3, 3, 5, 4, 9, 6), 4).toList().toString())
        Assert.assertEquals("[2, 6]", obj.mostCompetitive(intArrayOf(3, 5, 2, 6), 2).toList().toString())
    }

    @Test
    fun minMoves() {
        Assert.assertEquals(1, obj.minMoves(intArrayOf(1, 2, 4, 3), 4))
        Assert.assertEquals(2, obj.minMoves(intArrayOf(1, 2, 2, 1), 2))
        Assert.assertEquals(0, obj.minMoves(intArrayOf(1, 2, 1, 2), 2))
        Assert.assertEquals(4, obj.minMoves(intArrayOf(1, 3, 1, 1, 1, 2, 3, 2, 3, 1, 3, 2, 1, 3), 3))
    }

}
