package problem.lc_contest;

import java.util.Arrays;

public class Sample {
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
}
