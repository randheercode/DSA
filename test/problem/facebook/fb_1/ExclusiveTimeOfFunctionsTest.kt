package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 5/11/20
 * Time: 3:13 pm
 */
class ExclusiveTimeOfFunctionsTest {
    private val obj = ExclusiveTimeOfFunctions()

    @Test
    fun exclusiveTime() {
        Assert.assertEquals("[3, 4]", obj.exclusiveTime(2, listOf("0:start:0", "1:start:2", "1:end:5", "0:end:6")).toList().toString())
    }
}