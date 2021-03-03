import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class provides a solution for a modified N-Queen problem,
 * with additional constraint that no three queens can be placed in a straight line at any angle.
 *
 * It uses a backtracking algorithm and <i>availability</i> map, to mark which field can be used to place a new queen.
 */
public class NQueens {

    enum Action {
        RELEASE,
        BLOCK
    }

    private int[] cols;
    private int n;
    private int[][] availability;
    private final List<int[]> result = new LinkedList<>();
    private final char QUEEN = 'X';
    private final char FIELD = '.';

    /**
     * Solves and prints the solution for modified N-Queen problem.
     * @param n the chessboard size
     */
    public void solveNQueens(int n) {
        init(n);
        solve(0);
        printResults();
    }

    private void init(int n) {
        this.n = n;
        result.clear();
        cols = new int[n];
        availability = new int[n][n];
        for (int[] row : availability) {
            Arrays.fill(row, 0);
        }
        Arrays.fill(cols, -1);
    }

    private void solve(int x) {
        if (x >= n) {
            addResult();
            return;
        }
        for (int y = 0; y < n; y++) {
            if (availability[y][x] != 0) {
                continue;
            }
            releaseFields(x, cols[x]);
            cols[x] = y;
            updateFreeFields(x, cols[x]);

            solve(x + 1);
        }
        releaseFields(x, cols[x]);
        cols[x] = -1;
    }

    private void addResult() {
        int[] r = new int[n];
        System.arraycopy(cols, 0, r, 0, n);
        result.add(r);
    }

    private void updateFreeFields(int x, int y) {
        updateFields(x, y, Action.BLOCK);
    }

    private void releaseFields(int x, int y) {
        updateFields(x, y, Action.RELEASE);
    }

    /**
     * Method responsible for updating <i>availability</i> map.
     * The map is always updated in a forward directions only (x+1, x+2, ..., n)
     * @param x      a column number
     * @param y      a row number
     * @param action the indicator, if the field should be blocked or released
     */
    private void updateFields(int x, int y, Action action) {
        if (y < 0) {
            return ;
        }
        int value = action == Action.RELEASE ? -1 : 1;
        updateStraightLines(x, y, value);
        updateHorizontalAndDiagonals(x, y, value);
    }

    private void updateHorizontalAndDiagonals(int x, int y, int value) {
        int offset = 1;
        while (x + offset < availability.length) {
            availability[y][x + offset] += value;
            if (y - offset>= 0) {
                availability[y - offset][x + offset] += value;
            }
            if (y + offset < availability.length) {
                availability[y + offset][x + offset] += value;
            }
            offset++;
        }
    }

    private void updateStraightLines(int x, int y, int value) {
        for (int i = 0; i < x; i++) {
            int numerator = y - cols[i];
            int denominator = x - i;
            int highestCommonDivisor = Math.abs(Helpers.GCD(numerator, denominator));
            numerator /= highestCommonDivisor;
            denominator /= highestCommonDivisor;
            int yy = y + numerator;
            int xx = x + denominator;
            while (xx < cols.length && xx >= 0
                && yy < cols.length && yy >= 0) {
                availability[yy][xx] += value;
                yy += numerator;
                xx += denominator;
            }
        }
    }

    private void printResults() {
        int counter = 1;
        char[][] chessboard = new char[n][n];
        for (char[] row: chessboard) {
            Arrays.fill(row, FIELD);
        }

        for (int[] r: result) {
            System.out.println(counter + ")");
            for (int i = 0; i < n; i++) {
                chessboard[i][r[i]] = QUEEN;
            }
            for (char[] row: chessboard) {
                System.out.println(row);
            }
            for (int i = 0; i < n; i++) {
                chessboard[i][r[i]] = FIELD;
            }

            System.out.println();
            counter++;
        }
        System.out.println("Total solutions: " + result.size());
    }
}
