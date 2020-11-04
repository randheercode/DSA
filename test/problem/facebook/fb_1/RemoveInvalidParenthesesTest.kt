package problem.facebook.fb_1


import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 4/11/20
 * Time: 4:16 pm
 */
class RemoveInvalidParenthesesTest {
    private val obj = RemoveInvalidParentheses()

    @Test
    fun removeInvalidParentheses() {
        var res = obj.removeInvalidParentheses("()())()")
        Assert.assertEquals("[()()(), (())()]", res.toString())
        res = obj.removeInvalidParentheses("(a)())()")
        Assert.assertEquals("[(a)()(), (a())()]", res.toString())
        res = obj.removeInvalidParentheses(")(")
        Assert.assertEquals("[]", res.toString())
    }
}