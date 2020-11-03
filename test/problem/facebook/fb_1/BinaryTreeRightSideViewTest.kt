package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 3/11/20
 * Time: 10:20 am
 */
class BinaryTreeRightSideViewTest {
    private val obj = BinaryTreeRightSideView()

    @Test
    fun rightSideView() {
        Assert.assertEquals("[]", obj.rightSideView(null).toString())
    }
}