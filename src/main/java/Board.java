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

    //TODO  is this board the goal board?

    public boolean isGoal() {
        return false;
    }

    //TODO  a boadr that is obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        return null;
    }

    //TODO  does this board equal y?
    public boolean equals(Object y) {
        return false;
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
