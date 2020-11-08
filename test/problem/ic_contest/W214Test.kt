package problem.ic_contest

import org.junit.Assert
import org.junit.Test
import problem.lc_contest.W214

class W214Test {

    private val obj = W214()

    @Test
    fun getMaximumGenerated() {
        Assert.assertEquals(3, obj.getMaximumGenerated(7))
        Assert.assertEquals(1, obj.getMaximumGenerated(1))
        Assert.assertEquals(2, obj.getMaximumGenerated(3))
    }

    @Test
    fun minDeletions() {
        Assert.assertEquals(0, obj.minDeletions("aab"))
        Assert.assertEquals(2, obj.minDeletions("aaabbbcc"))
    }

}

