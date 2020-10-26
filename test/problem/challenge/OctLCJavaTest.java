package problem.challenge;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    @Test
    public void cloneGraph() {
        Assert.assertNull(obj.cloneGraph(null));
    }

    @Test
    public void asteroidCollision() {
        Assert.assertEquals("510", Arrays.stream(obj.asteroidCollision(new int[]{5, 10, -5})).mapToObj(Integer::toString).collect(Collectors.joining()));
        Assert.assertEquals("", Arrays.stream(obj.asteroidCollision(new int[]{8, -8})).mapToObj(Integer::toString).collect(Collectors.joining()));
        Assert.assertEquals("10", Arrays.stream(obj.asteroidCollision(new int[]{10, 2, -5})).mapToObj(Integer::toString).collect(Collectors.joining()));
        Assert.assertEquals("-2-112", Arrays.stream(obj.asteroidCollision(new int[]{-2, -1, 1, 2})).mapToObj(Integer::toString).collect(Collectors.joining()));
        Assert.assertEquals("-5", Arrays.stream(obj.asteroidCollision(new int[]{4, 2, -5})).mapToObj(Integer::toString).collect(Collectors.joining()));
    }

    @Test
    public void find132pattern() {
        Assert.assertTrue(obj.find132pattern(new int[]{3, 1, 4, 2}));
        Assert.assertFalse(obj.find132pattern(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void bagOfTokensScore() {
        Assert.assertEquals(0, obj.bagOfTokensScore(new int[]{100}, 50));
        Assert.assertEquals(1, obj.bagOfTokensScore(new int[]{100, 200}, 150));
        Assert.assertEquals(2, obj.bagOfTokensScore(new int[]{100, 200, 300, 400}, 200));
    }

    @Test
    public void winnerSquareGame() {
        Assert.assertTrue(obj.winnerSquareGame(1));
        Assert.assertFalse(obj.winnerSquareGame(2));
        Assert.assertTrue(obj.winnerSquareGame(4));
    }

    @Test
    public void isAlienSorted() {
        Assert.assertTrue(obj.isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        Assert.assertFalse(obj.isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
        Assert.assertFalse(obj.isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
    }
}
