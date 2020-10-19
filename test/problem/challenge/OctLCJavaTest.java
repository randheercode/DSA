package problem.challenge;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: randheercode
 * Date: 17/10/20
 * Time: 4:58 pm
 */
public class OctLCJavaTest {

    OctLCJava obj = new OctLCJava();

    @Test
    public void findRepeatedDnaSequences() {
        Assert.assertEquals("[AAAAACCCCC, CCCCCAAAAA]", obj.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT").toString());
        Assert.assertEquals("[AAAAAAAAAA]", obj.findRepeatedDnaSequences("AAAAAAAAAAAAA").toString());
        Assert.assertEquals("[AAAAAAAAAA]", obj.findRepeatedDnaSequences("AAAAAAAAAAA").toString());
    }

    @Test
    public void longestDupSubstring() {
        Assert.assertEquals("ana", obj.longestDupSubstring("banana"));
        Assert.assertEquals("", obj.longestDupSubstring("abcd"));
    }

    @Test
    public void longestRepeatingSubstring() {
        Assert.assertEquals(3, obj.longestRepeatingSubstring("banana"));
        Assert.assertEquals(0, obj.longestRepeatingSubstring("abcd"));
    }

    @Test
    public void maxProfit() {
        Assert.assertEquals(2, obj.maxProfit(2, new int[]{2, 4, 1}));
        Assert.assertEquals(7, obj.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
    }

    @Test
    public void minDominoRotations() {
        Assert.assertEquals(2, obj.minDominoRotations(new int[]{2, 1, 2, 4, 2, 2}, new int[]{5, 2, 6, 2, 3, 2}));
        Assert.assertEquals(-1, obj.minDominoRotations(new int[]{3, 5, 1, 2, 3}, new int[]{3, 6, 3, 3, 4}));
    }

}
