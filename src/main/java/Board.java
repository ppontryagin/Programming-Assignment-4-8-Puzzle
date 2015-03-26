import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private final int[][] tiles;
    private final int N;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.N = blocks.length;
        tiles = new int[N][N];

        for (int i = 0; i < blocks.length; i++) {
            System.arraycopy(blocks[i], 0, tiles[i], 0, blocks[0].length);
        }
    }

    // board dimension N
    public int dimension() {
        return N;
    }

    // number of blocks out of place
    public int hamming() {
        int n = 0;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if ((i != toI(tiles[i][j]) || j != toJ(tiles[i][j])) && tiles[i][j] != 0) {
                    n++;
                }
            }
        return n;
    }

    private int toI(int i) {
        return (i - 1) / N;
    }

    private int toJ(int i) {
        return (i - 1) % N;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int n = 0;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != 0)
                    n += Math.abs(toJ(tiles[i][j]) - j) + Math.abs(toI(tiles[i][j]) - i);
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

        for (int i = 0; i < tiles.length; i++) {
            System.arraycopy(tiles[i], 0, clone[i], 0, tiles[0].length);
        }

        //swap values
        if (clone[0][0] == 0 || clone[0][1] == 0) {
            int buf = clone[1][1];
            clone[1][1] = clone[1][0];
            clone[1][0] = buf;
        } else {
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

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> mates = new ArrayList<Board>();
        int[][] mate;
        Point2D emptyTile = null;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0)
                    emptyTile = new Point2D(i, j);
            }

        for (Point2D point : emptyTile.neighbours()) {
            mate = new int[N][N];

            for (int i = 0; i < tiles.length; i++) {
                System.arraycopy(tiles[i], 0, mate[i], 0, tiles[0].length);
            }

            mate[emptyTile.getX()][emptyTile.getY()] = mate[point.getX()][point.getY()];
            mate[point.getX()][point.getY()] = 0;

            mates.add(new Board(mate));
        }
        return mates;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N).append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }

    private class Point2D {
        private int x;
        private int y;

        private Point2D(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Iterable<Point2D> neighbours() {
            List<Point2D> mates = new LinkedList<Point2D>();

            if (x - 1 >= 0)
                mates.add(new Point2D(x - 1, y));
            if (x + 1 < N)
                mates.add(new Point2D(x + 1, y));
            if (y - 1 >= 0)
                mates.add(new Point2D(x, y - 1));
            if (y + 1 < N)
                mates.add(new Point2D(x, y + 1));

            return mates;
        }

        @Override
        public String toString() {
            return "Point2D{" + "x=" + x + ", y=" + y + '}';
        }
    }
}
