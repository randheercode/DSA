package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 13/11/20
 * Time: 10:15 am
 */
class FriendsOfAppropriateAgesTest {
    private val obj = FriendsOfAppropriateAges()

    @Test
    fun numFriendRequests() {
        Assert.assertEquals(2, obj.numFriendRequests(intArrayOf(16, 16)))
        Assert.assertEquals(2, obj.numFriendRequests(intArrayOf(16, 17, 18)))
        Assert.assertEquals(3, obj.numFriendRequests(intArrayOf(20, 30, 100, 110, 120)))
    }
}