package problem;

import java.util.*;

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

    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) return Collections.singletonList(1);
        if (rowIndex == 1) return new ArrayList<>(Arrays.asList(1, 1));

        List<Integer> last = getRow(rowIndex - 1);
        int prev = last.get(0);
        for (int i = 1; i < last.size(); i++) {
            int next = prev + last.get(i);
            prev = last.get(i);
            last.set(i, next);
        }
        last.set(0, 1);
        last.add(1);
        return last;
    }

    public int longestPalindrome(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray())
            count[c]++;

        int ans = 0;
        for (int v : count) {
            ans += v / 2 * 2;
            if (ans % 2 == 0 && v % 2 == 1)
                ans++;
        }
        return ans;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[1]));
        int end = intervals[0][1];

        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                count++;
            } else {
                end = intervals[i][1];
            }
        }

        return count;
    }

    public int[] findPermutation(String s) {
        int[] res = new int[s.length() + 1];
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i - 1) == 'I') {
                stack.push(i);
                while (!stack.isEmpty())
                    res[j++] = stack.pop();
            } else
                stack.push(i);
        }
        stack.push(s.length() + 1);
        while (!stack.isEmpty())
            res[j++] = stack.pop();
        return res;
    }

    public static void main(String[] args) {
        Aug20LCJava obj = new Aug20LCJava();
    }
}

class CombinationIterator {
    public Deque<String> combinations = new ArrayDeque<>();

    public CombinationIterator(String characters, int combinationLength) {
        int n = characters.length(), k = combinationLength;

        // init the first combination
        int[] nums = new int[k + 1];
        for (int i = 0; i < k; ++i) {
            nums[i] = i;
        }
        nums[k] = n;

        int j = 0;
        while (j < k) {
            // add current combination
            StringBuilder sb = new StringBuilder();
            for (int i = k - 1; i > -1; i--) {
                sb.append(characters.charAt(n - 1 - nums[i]));
            }
            combinations.push(sb.toString());
            // Generate next combination.
            // Find the first j such that nums[j] + 1 != nums[j + 1].
            // Increase nums[j] by one.
            j = 0;
            while ((j < k) && (nums[j + 1] == nums[j] + 1)) {
                nums[j] = j;
                j++;
            }
            nums[j]++;
        }
    }

    public String next() {
        return combinations.pop();
    }

    public boolean hasNext() {
        return (!combinations.isEmpty());
    }
}
