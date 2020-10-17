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
        Assert.assertEquals(obj.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT").toString(),
                "[AAAAACCCCC, CCCCCAAAAA]");
        Assert.assertEquals(obj.findRepeatedDnaSequences("AAAAAAAAAAAAA").toString(),
                "[AAAAAAAAAA]");
        Assert.assertEquals(obj.findRepeatedDnaSequences("AAAAAAAAAAA").toString(),
                "[AAAAAAAAAA]");
    }

    @Test
    public void longestDupSubstring() {
        Assert.assertEquals(obj.longestDupSubstring("banana"), "ana");
        Assert.assertEquals(obj.longestDupSubstring("abcd"), "");
    }

    @Test
    public void longestRepeatingSubstring() {
        Assert.assertEquals(obj.longestRepeatingSubstring("banana"), 3);
        Assert.assertEquals(obj.longestRepeatingSubstring("abcd"), 0);
    }

}
