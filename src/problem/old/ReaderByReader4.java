package problem.old;

/**
 * Created by IntelliJ IDEA.
 * User: randheercode
 * Date:
 * Time:
 */
class Reader4 {
    public int read4(char[] buf) {
        buf[0] = 'a';
        buf[1] = 'b';
        buf[2] = 'c';
        return 3;
    }
}

public class ReaderByReader4 extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int copiedChars = 0, readChars = 4;
        char[] buf4 = new char[4];

        while (copiedChars < n && readChars == 4) {
            readChars = read4(buf4);

            for (int i = 0; i < readChars; ++i) {
                if (copiedChars == n)
                    return copiedChars;
                buf[copiedChars] = buf4[i];
                ++copiedChars;
            }
        }
        return copiedChars;
    }
}

class TimeSolution {
    private int max_time = -1;

    public String largestTimeFromDigits(int[] A) {
        this.max_time = -1;
        permutate(A, 0);
        if (this.max_time == -1)
            return "";
        else
            return String.format("%02d:%02d", max_time / 60, max_time % 60);
    }

    protected void permutate(int[] array, int start) {
        if (start == array.length) {
            this.build_time(array);
            return;
        }
        for (int i = start; i < array.length; ++i) {
            this.swap(array, i, start);
            this.permutate(array, start + 1);
            this.swap(array, i, start);
        }
    }

    protected void build_time(int[] perm) {
        int hour = perm[0] * 10 + perm[1];
        int minute = perm[2] * 10 + perm[3];
        if (hour < 24 && minute < 60)
            this.max_time = Math.max(this.max_time, hour * 60 + minute);
    }

    protected void swap(int[] array, int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}