package problem.facebook.fb_1

import generateIntArray
import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 13/11/20
 * Time: 10:41 am
 */
class ToeplitzMatrixTest {
    private val obj = ToeplitzMatrix()

    @Test
    fun isToeplitzMatrix() {
        Assert.assertEquals(false, obj.isToeplitzMatrix(generateIntArray("[[11,74,0,93],[40,11,74,7]]")))
    }
}