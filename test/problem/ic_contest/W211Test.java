package problem.ic_contest;

import org.junit.Assert;
import org.junit.Test;
import problem.lc_contest.W211;

public class W211Test {

    W211 obj = new W211();

    @Test
    public void maxLengthBetweenEqualCharacters() {
        Assert.assertEquals(0, obj.maxLengthBetweenEqualCharacters("aa"));
        Assert.assertEquals(2, obj.maxLengthBetweenEqualCharacters("abca"));
        Assert.assertEquals(-1, obj.maxLengthBetweenEqualCharacters("cbzxy"));
        Assert.assertEquals(4, obj.maxLengthBetweenEqualCharacters("cabbac"));
        Assert.assertEquals(-1, obj.maxLengthBetweenEqualCharacters("x"));
        Assert.assertEquals(19, obj.maxLengthBetweenEqualCharacters("aydsicwrfybunpqsdsnenvrfirr"));
        Assert.assertEquals(38, obj.maxLengthBetweenEqualCharacters("pfwftcwdbiodyoojbebtwoyqemmsgmsryugkkcwykhtaczj"));
    }

    @Test
    public void findLexSmallestString() {
        Assert.assertEquals("2050", obj.findLexSmallestString("5525", 9, 2));
    }


}
