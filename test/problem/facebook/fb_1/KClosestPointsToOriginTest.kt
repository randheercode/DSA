package problem.facebook.fb_1

import generateIntArray
import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 2/11/20
 * Time: 8:25 pm
 */
class KClosestPointsToOriginTest {
    private val obj = KClosestPointsToOrigin()

    @Test
    fun kClosest() {
        val result = obj.kClosest(generateIntArray("[[-5,4],[-6,-5],[4,6]]"), 2)
        val actual = result.map { it.toList() }.toList().toString()
        Assert.assertEquals("[[-5, 4], [4, 6]]", actual)
    }
}