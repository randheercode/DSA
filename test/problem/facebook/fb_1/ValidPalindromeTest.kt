package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 4/11/20
 * Time: 5:00 pm
 */
class ValidPalindromeTest {
    private val obj = ValidPalindrome()

    @Test
    fun isPalindrome() {
        Assert.assertTrue(obj.isPalindrome("A man, a plan, a canal: Panama"))
        Assert.assertFalse(obj.isPalindrome("race a car"))
    }
}