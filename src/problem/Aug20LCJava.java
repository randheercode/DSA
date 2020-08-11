package problem;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: randheercode
 * Date:
 * Time:
 */
public class Aug20LCJava {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int i = 0;
        int last = citations.length - 1;
        while (i < citations.length && citations[last - i] > i) {
            i++;
        }
        return i;
    }

    public int hIndexSecond(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n + 1];
        // counting papers for each citation number
        for (int c : citations)
            papers[Math.min(n, c)]++;
        // finding the h-index
        int k = n;
        for (int s = papers[n]; k > s; s += papers[k])
            k--;
        return k;
    }

    public static void hIndexTest(Aug20LCJava obj) {
        System.out.println(obj.hIndex(new int[]{3, 0, 6, 1, 5}));
    }

    public static void main(String[] args) {
        Aug20LCJava obj = new Aug20LCJava();
        Aug20LCJava.hIndexTest(obj);
    }
}
