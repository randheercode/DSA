package problem.lc_contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class W212 {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        char slowest = keysPressed.charAt(0);
        int maxTime = releaseTimes[0];
        for (int i = 1; i < releaseTimes.length; i++) {
            int diff = releaseTimes[i] - releaseTimes[i - 1];
            if (diff > maxTime) {
                slowest = keysPressed.charAt(i);
                maxTime = diff;
            } else if (diff == maxTime) {
                slowest = (char) Math.max((int) slowest, (int) keysPressed.charAt(i));
            }
        }
        return slowest;
    }

    private boolean isArithmetic(int[] nums) {
        Arrays.sort(nums);
        if (nums.length < 2) return false;
        int diff = Math.abs(nums[1] - nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (diff != Math.abs(nums[i] - nums[i - 1])) return false;
        }
        return true;
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            result.add(isArithmetic(Arrays.copyOfRange(nums, l[i], r[i] + 1)));
        }
        return result;
    }

    public int minimumEffortPath(int[][] heights) {
        //strategy: dijkstra
        int r = heights.length;
        int c = heights[0].length;
        int[][] data = new int[r][c];
        for (int[] d : data) {
            //fill w/ max value so we can find smallest effor
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        int[][] d = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        //sort by increasing effort
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            //pull out current
            int[] curr = pq.poll();
            int x = curr[0];
            int y = curr[1];
            int h = curr[2];
            //reached end so return smallest effort
            if (x == r - 1 && y == c - 1) {
                return h;
            }
            //check 4 directions
            for (int i = 0; i < 4; i++) {
                int nx = x + d[i][0];
                int ny = y + d[i][1];
                int nh = h;
                //need to be in bounds
                if (inBounds(nx, ny, r, c)) {
                    //find route's largest height
                    nh = Math.max(nh, Math.abs(heights[nx][ny] - heights[x][y]));
                    //swap if effort is smaller for this cell
                    if (nh < data[nx][ny]) {
                        data[nx][ny] = nh;
                        //add to queue
                        pq.add(new int[]{nx, ny, nh});
                    }
                }
            }
        }
        return -1;
    }

    private boolean inBounds(int x, int y, int r, int c) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }
}
