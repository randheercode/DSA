package problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: randheercode
 * Date:
 * Time:
 */
public class Sep20LC {
    public int lengthOfLastWord(String s) {
        if (s == null) return 0;
        s = s.trim();
        int idx = s.lastIndexOf(" ");
        return s.length() - 1 - idx;
    }

    public int findMaximumXOR(int[] nums) {
        int maxNum = nums[0];
        for (int num : nums) maxNum = Math.max(maxNum, num);
        int L = (Integer.toBinaryString(maxNum)).length();

        int maxXor = 0, currXor;
        Set<Integer> prefixes = new HashSet<>();
        for (int i = L - 1; i > -1; --i) {
            maxXor <<= 1;
            currXor = maxXor | 1;
            prefixes.clear();
            for (int num : nums) prefixes.add(num >> i);
            for (int p : prefixes) {
                if (prefixes.contains(currXor ^ p)) {
                    maxXor = currXor;
                    break;
                }
            }
        }
        return maxXor;
    }

    public boolean isRobotBounded(String instructions) {
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0;
        int dir = 0;
        for (char i : instructions.toCharArray()) {
            if (i == 'L')
                dir = (dir + 3) % 4;
            else if (i == 'R')
                dir = (dir + 1) % 4;
            else {
                x += directions[dir][0];
                y += directions[dir][1];
            }
        }
        return (x == 0 && y == 0) || (dir != 0);
    }

    public int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }

    public List<Integer> sequentialDigits(int low, int high) {
        String sample = "123456789";
        int n = 10;
        List<Integer> nums = new ArrayList();

        int lowLen = String.valueOf(low).length();
        int highLen = String.valueOf(high).length();
        for (int length = lowLen; length < highLen + 1; ++length) {
            for (int start = 0; start < n - length; ++start) {
                int num = Integer.parseInt(sample.substring(start, start + length));
                if (num >= low && num <= high) nums.add(num);
            }
        }
        return nums;
    }

}

class UniquePath3  {
    int rows, cols;
    int[][] grid;
    int path_count;

    protected void backtrack(int row, int col, int remain) {
        // base case for the termination of backtracking
        if (this.grid[row][col] == 2 && remain == 1) {
            // reach the destination
            this.path_count += 1;
            return;
        }

        // mark the square as visited. case: 0, 1, 2
        int temp = grid[row][col];
        grid[row][col] = -4;
        remain -= 1; // we now have one less square to visit

        // explore the 4 potential directions around
        int[] row_offsets = {0, 0, 1, -1};
        int[] col_offsets = {1, -1, 0, 0};
        for (int i = 0; i < 4; ++i) {
            int next_row = row + row_offsets[i];
            int next_col = col + col_offsets[i];

            if (0 > next_row || next_row >= this.rows ||
                    0 > next_col || next_col >= this.cols)
                // invalid coordinate
                continue;

            if (grid[next_row][next_col] < 0)
                // either obstacle or visited square
                continue;

            backtrack(next_row, next_col, remain);
        }

        // unmark the square after the visit
        grid[row][col] = temp;
    }

    public int uniquePathsIII(int[][] grid) {
        int non_obstacles = 0, start_row = 0, start_col = 0;

        this.rows = grid.length;
        this.cols = grid[0].length;

        // step 1). initialize the conditions for backtracking
        //   i.e. initial state and final state
        for (int row = 0; row < rows; ++row)
            for (int col = 0; col < cols; ++col) {
                int cell = grid[row][col];
                if (cell >= 0)
                    non_obstacles += 1;
                if (cell == 1) {
                    start_row = row;
                    start_col = col;
                }
            }

        this.path_count = 0;
        this.grid = grid;

        // kick-off the backtracking
        backtrack(start_row, start_col, non_obstacles);

        return this.path_count;
    }
}
