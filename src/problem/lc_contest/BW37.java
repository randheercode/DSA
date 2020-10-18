package problem.lc_contest;

import java.util.Arrays;

public class BW37 {
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int toRemove = (int) (arr.length * 0.05);
        int count = 0;
        double sum = 0.0;
        for (int i = toRemove; i < arr.length - toRemove; i++) {
            sum += arr[i];
            count++;
        }
        return sum / count;
    }

    public int[] bestCoordinate(int[][] towers, int radius) {
        int[] result = new int[2];
        int maxQuality = 0;
        for (int[] tower : towers) {
            int qty = 0;
            for (int[] ints : towers) {
                qty += compute(tower, ints, radius);
            }
            if (qty > maxQuality) {
                maxQuality = qty;
                result[0] = tower[0];
                result[1] = tower[1];
            } else if (qty == maxQuality) {
                if (tower[0] < result[0]) {
                    result[0] = tower[0];
                    result[1] = tower[1];
                }
            }
        }
        return result;
    }

    private double compute(int[] target, int[] tower, int radius) {
        int dx = tower[0] - target[0];
        int dy = tower[1] - target[1];
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance > radius) return 0;
        return Math.floor(tower[2] / (1 + distance));
    }

    public int numberOfSets(int n, int k) {
        long mod = 1000000007;
        if (k == 1) {
            return n * (n - 1) / 2;
        }
        long[][] dp = new long[n][k];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + (i * (i + 1)) / 2;
        }
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i && j < k; j++) {
                dp[i][j] = dp[i - 1][j] - dp[i - 2][j] + dp[i - 1][j - 1] + mod;
                dp[i][j] %= mod;
                dp[i][j] += dp[i - 1][j];
            }
        }
        return (int) (dp[n - 1][k - 1] - dp[n - 2][k - 1]);
    }
}
