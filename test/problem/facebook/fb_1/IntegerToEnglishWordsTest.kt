package problem.facebook.fb_1

import org.junit.Assert
import org.junit.Test

/**
 * Created by randheercode
 * Date: 2/11/20
 * Time: 8:44 pm
 */
class IntegerToEnglishWordsTest {

    private val obj = IntegerToEnglishWords()

    @Test
    fun numberToWords() {
        Assert.assertEquals("One Hundred Twenty Three", obj.numberToWords(123))
        Assert.assertEquals("Twelve Thousand Three Hundred Forty Five", obj.numberToWords(12345))
        Assert.assertEquals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven", obj.numberToWords(1234567))
        Assert.assertEquals("One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One", obj.numberToWords(1234567891))
    }
}