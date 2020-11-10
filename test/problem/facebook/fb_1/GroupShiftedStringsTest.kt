package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 10/11/20
 * Time: 10:01 am
 */
class GroupShiftedStringsTest {

    private val obj = GroupShiftedStrings()

    @Test
    fun groupStrings() {
        Assert.assertEquals(4, obj.groupStrings(arrayOf("abc", "bcd", "acef", "xyz", "az", "ba", "a", "z")).size)
    }
}