package problem.ic_contest;

import org.junit.Assert;
import org.junit.Test;
import problem.lc_contest.W212;

public class W212Test {

    W212 obj = new W212();

    @Test
    public void slowestKey() {
        Assert.assertEquals('c', obj.slowestKey(new int[]{9, 29, 49, 50}, "cbcd"));
        Assert.assertEquals('a', obj.slowestKey(new int[]{12, 23, 36, 46, 62}, "spuda"));
    }

    @Test
    public void checkArithmeticSubarrays() {
        Assert.assertEquals("[true, false, true]", obj.checkArithmeticSubarrays(new int[]{4, 6, 5, 9, 3, 7}, new int[]{0, 0, 2}, new int[]{2, 3, 5}).toString());
        Assert.assertEquals("[false, true, false, false, true, true]", obj.checkArithmeticSubarrays(new int[]{-12, -9, -3, -12, -6, 15, 20, -25, -20, -15, -10}, new int[]{0, 1, 6, 4, 8, 7}, new int[]{4, 4, 9, 7, 9, 10}).toString());
    }

    @Test
    public void minimumEffortPath() {
        Assert.assertEquals(2, obj.minimumEffortPath(
                new int[][]{
                        new int[]{1, 2, 2},
                        new int[]{3, 8, 3},
                        new int[]{5, 3, 5}
                }
        ));
        Assert.assertEquals(1, obj.minimumEffortPath(
                new int[][]{
                        new int[]{1, 2, 3},
                        new int[]{3, 8, 4},
                        new int[]{5, 3, 5}
                }
        ));
        Assert.assertEquals(0, obj.minimumEffortPath(
                new int[][]{
                        new int[]{1, 2, 1, 1, 1},
                        new int[]{1, 2, 1, 2, 1},
                        new int[]{1, 2, 1, 2, 1},
                        new int[]{1, 2, 1, 2, 1},
                        new int[]{1, 1, 1, 2, 1}
                }
        ));
        Assert.assertEquals(5, obj.minimumEffortPath(
                new int[][]{
                        new int[]{4, 3, 4, 10, 5, 5, 9, 2},
                        new int[]{10, 8, 2, 10, 9, 7, 5, 6},
                        new int[]{5, 8, 10, 10, 10, 7, 4, 2},
                        new int[]{5, 1, 3, 1, 1, 3, 1, 9},
                        new int[]{6, 4, 10, 6, 10, 9, 4, 6}
                }
        ));

    }

}
