package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 4/11/20
 * Time: 5:12 pm
 */
class FindAllAnagramsInAStringTest {
    private val obj = FindAllAnagramsInAString()

    @Test
    fun findAnagrams() {
        Assert.assertEquals("[0, 6]", obj.findAnagrams("cbaebabacd", "abc").toString())
        Assert.assertEquals("[0, 1, 2]", obj.findAnagrams("abab", "ab").toString())
    }
}