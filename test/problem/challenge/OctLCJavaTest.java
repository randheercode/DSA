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

}
