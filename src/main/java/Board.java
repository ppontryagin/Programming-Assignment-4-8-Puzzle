/**
 * Created by Pavel.Pontryagin on 21.03.2015.
 */
public class Board {

    private int[] tiles;
    private int N;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.N = blocks.length;
        tiles = new int[N * N + 1];
        int n = 1;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                tiles[n] = blocks[i][j];
                n++;
            }
    }

    // board dimension N
    public int dimension() {
        return N;
    }

    // number of blocks out of place
    public int hamming() {
        int n = 0;

        for (int i = 1; i <= N * N; i++) {
            if (tiles[i] != i && tiles[i] != 0) {
                n++;
            }
        }
        return n;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int n = 0;
        Point2D curr;
        Point2D targ;

        for (int i = 1; i <= N * N; i++) {
            if (tiles[i] != 0) {
                curr = new Point2D(i, N);
                targ = new Point2D(tiles[i], N);
                n += targ.compare(curr);
            }
        }
        return n;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return (hamming() == 0);
    }

    // a board that is obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        int[][] clone = new int[N][N];
        int j = 0;
        int x = 0;
        for (int i = 1; i < N * N; i++) {
            if ((i % N) > 0) {
                j = i / N;
                x = i % N - 1;
            } else {
                j = i / N - 1;
                x = N - 1;
            }
            clone[j][x] = tiles[i];
        }

        if (clone[0][0] == 0 || clone[0][1] == 0) {
            int buf = clone[1][1];
            clone[1][1] = clone[1][0];
            clone[1][0] = buf;
        }
        else {
            int buf = clone[0][1];
            clone[0][1] = clone[0][0];
            clone[0][0] = buf;
        }

        return new Board(clone);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this)
            return true;
        if (y == null)
            return false;
        if (y.getClass() != this.getClass())
            return false;

        Board that = (Board) y;
        return this.toString().equals(that.toString());
    }

    //TODO  all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 1; i < N * N + 1; i++) {
            s.append(String.format("%2d ", tiles[i]));
            if (i % N == 0)
                s.append("\n");
        }

        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }

    private class Point2D {
        public int x;
        public int y;

        private Point2D(int p, int dim) {
            if ((p % dim) > 0) {
                y = p / dim + 1;
                x = p % dim;

            } else {
                y = p / dim;
                x = dim;
            }
        }

        @Override
        public String toString() {
            return "Point2D{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public boolean equals(Point2D that) {
            return compare(that) == 0;
        }

        public int compare(Point2D that) {
            return Math.abs(that.x - this.x) + Math.abs(that.y - this.y);
        }
    }
}
