package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 8:23 am
 */
class SimplifyPathTest {
    private val obj = SimplifyPath()

    @Test
    fun simplifyPath() {
        Assert.assertEquals("/home", obj.simplifyPath("/home/"))
        Assert.assertEquals("/", obj.simplifyPath("/../"))
        Assert.assertEquals("/home/foo", obj.simplifyPath("/home//foo/"))
        Assert.assertEquals("/c", obj.simplifyPath("/a/./b/../../c/"))
    }
}