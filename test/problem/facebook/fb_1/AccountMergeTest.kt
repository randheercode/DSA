package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test


/**
 * Created by randheercode
 * Date: 5/11/20
 * Time: 2:06 pm
 */
class AccountMergeTest {
    private val obj = AccountsMerge()

    @Test
    fun accountsMerge() {
        Assert.assertEquals("[[John, john00@mail.com, john_newyork@mail.com, johnsmith@mail.com], [John, johnnybravo@mail.com], [Mary, mary@mail.com]]",
                obj.accountsMerge(listOf(
                        listOf("John", "johnsmith@mail.com", "john00@mail.com"),
                        listOf("John", "johnnybravo@mail.com"),
                        listOf("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                        listOf("Mary", "mary@mail.com")
                )).toString())
    }

    @Test
    fun accountsMergeDSU() {
        Assert.assertEquals("[[John, john00@mail.com, john_newyork@mail.com, johnsmith@mail.com], [John, johnnybravo@mail.com], [Mary, mary@mail.com]]",
                obj.accountsMergeDSU(listOf(
                        listOf("John", "johnsmith@mail.com", "john00@mail.com"),
                        listOf("John", "johnnybravo@mail.com"),
                        listOf("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                        listOf("Mary", "mary@mail.com")
                )).toString())
    }
}