package problem.lc_contest;

import java.util.Arrays;

public class W211 {
    public int maxLengthBetweenEqualCharacters(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int[] ints : dp) {
            Arrays.fill(ints, -2);
        }
        return maxLenBw(s, 0, s.length() - 1, dp);
    }

    private int maxLenBw(String s, int start, int end, int[][] dp) {
        if (start >= s.length() || end < 0 || start > end) return -1;
        if (s.length() <= 1) return -1;
        if (s.charAt(start) == s.charAt(end)) {
            dp[start][end] = end - start - 1;
        } else if (dp[start][end] == -2) {
            dp[start][end] = Math.max(maxLenBw(s, start + 1, end, dp), maxLenBw(s, start, end - 1, dp));
        }
        return dp[start][end];
    }

    public String findLexSmallestString(String s, int a, int b) {
        int[] nums = new int[s.length()];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = s.charAt(i) - '0';
        }
        int[][] mat = new int[11][11];
        for (int i = 0; i < 11; i++) {
            mat[i][1] = i + 1;
            for (int j = 2; j < 11; j++) {
                mat[i][j] = (mat[i][j - 1] + (i + 1)) % 10;
            }
        }
        int firstIdx = 0;
        for (int i = 0; i < nums.length; i += b) {

        }
        return "";
    }
}
