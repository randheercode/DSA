package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 2/11/20
 * Time: 8:02 pm
 */
class ValidPalindromeIITest {

    private val obj = ValidPalindromeII()

    @Test
    fun validPalindrome() {
        Assert.assertTrue(obj.validPalindrome("aba"))
        Assert.assertTrue(obj.validPalindrome("abca"))
        Assert.assertFalse(obj.validPalindrome("abbcca"))
    }
}