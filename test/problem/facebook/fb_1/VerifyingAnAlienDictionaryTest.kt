package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 30/10/20
 * Time: 10:42 am
 */
class VerifyingAnAlienDictionaryTest {
    private val obj = VerifyingAnAlienDictionary()

    @Test
    fun isAlienSorted() {
        Assert.assertTrue(obj.isAlienSorted(arrayOf("hello", "leetcode"), "hlabcdefgijkmnopqrstuvwxyz"))
        Assert.assertFalse(obj.isAlienSorted(arrayOf("word", "world", "row"), "worldabcefghijkmnpqstuvxyz"))
        Assert.assertFalse(obj.isAlienSorted(arrayOf("apple", "app"), "abcdefghijklmnopqrstuvwxyz"))
    }

}