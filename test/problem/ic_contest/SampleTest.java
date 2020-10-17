package problem.ic_contest;

import org.junit.Assert;
import org.junit.Test;
import problem.lc_contest.Sample;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SampleTest {

    Sample obj = new Sample();

    @Test
    public void trimMean() {
        double deviation = Math.pow(10, -5);
        Assert.assertEquals(2.00000, obj.trimMean(new int[]{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3}), deviation);
        Assert.assertEquals(4.00000, obj.trimMean(new int[]{6, 2, 7, 5, 1, 2, 0, 3, 10, 2, 5, 0, 5, 5, 0, 8, 7, 6, 8, 0}), deviation);
        Assert.assertEquals(4.77778, obj.trimMean(new int[]{6, 0, 7, 0, 7, 5, 7, 8, 3, 4, 0, 7, 8, 1, 6, 8, 1, 1, 2, 4, 8, 1, 9, 5, 4, 3, 8, 5, 10, 8, 6, 6, 1, 0, 6, 10, 8, 2, 3, 4}), deviation);
        Assert.assertEquals(5.27778, obj.trimMean(new int[]{9, 7, 8, 7, 7, 8, 4, 4, 6, 8, 8, 7, 6, 8, 8, 9, 2, 6, 0, 0, 1, 10, 8, 6, 3, 3, 5, 1, 10, 9, 0, 7, 10, 0, 10, 4, 1, 10, 6, 9, 3, 6, 0, 0, 2, 7, 0, 6, 7, 2, 9, 7, 7, 3, 0, 1, 6, 1, 10, 3}), deviation);
        Assert.assertEquals(5.29167, obj.trimMean(new int[]{4, 8, 4, 10, 0, 7, 1, 3, 7, 8, 8, 3, 4, 1, 6, 2, 1, 1, 8, 0, 9, 8, 0, 3, 9, 10, 3, 10, 1, 10, 7, 3, 2, 1, 4, 9, 10, 7, 6, 4, 0, 8, 5, 1, 2, 1, 6, 2, 5, 0, 7, 10, 9, 10, 3, 7, 10, 5, 8, 5, 7, 6, 7, 6, 10, 9, 5, 10, 5, 5, 7, 2, 10, 7, 7, 8, 2, 0, 1, 1}), deviation);
    }

    @Test
    public void bestCoordinate() {
        List<Integer> list;
        list = Arrays.stream(obj.bestCoordinate(new int[][]{{1, 2, 5}, {2, 1, 7}, {3, 1, 9}}, 2)).boxed().collect(Collectors.toList());
        Assert.assertEquals("[2, 1]", list.toString());
        list = Arrays.stream(obj.bestCoordinate(new int[][]{{23, 11, 21}}, 9)).boxed().collect(Collectors.toList());
        Assert.assertEquals("[23, 11]", list.toString());
        list = Arrays.stream(obj.bestCoordinate(new int[][]{{1, 2, 13}, {2, 1, 7}, {0, 1, 9}}, 2)).boxed().collect(Collectors.toList());
        Assert.assertEquals("[1, 2]", list.toString());
        list = Arrays.stream(obj.bestCoordinate(new int[][]{{2, 1, 9}, {0, 1, 9}}, 2)).boxed().collect(Collectors.toList());
        Assert.assertEquals("[0, 1]", list.toString());
        list = Arrays.stream(obj.bestCoordinate(new int[][]{{13, 50, 45}, {0, 5, 31}, {47, 34, 24}, {37, 14, 9}, {45, 21, 40}}, 34)).boxed().collect(Collectors.toList());
        Assert.assertEquals("[13, 50]", list.toString());
    }

    @Test
    public void numberOfSets() {
        Assert.assertEquals(5, obj.numberOfSets(4, 2));
        Assert.assertEquals(3, obj.numberOfSets(3, 1));
        Assert.assertEquals(796297179, obj.numberOfSets(30, 7));
        Assert.assertEquals(7, obj.numberOfSets(5, 3));
        Assert.assertEquals(1, obj.numberOfSets(3, 2));
    }

}
