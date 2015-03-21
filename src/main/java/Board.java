/**
 * Created by Pavel.Pontryagin on 21.03.2015.
 */
public class Board {

    private int[][] tiles;
    private int N;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.tiles = blocks;
        this.N = blocks.length;
    }

    // board dimension N
    public int dimension() {
        return tiles.length;
    }

    //TODO number of blocks out of place
    public int hamming() {
        return 0;
    }

    //TODO  sum of Manhattan distances between blocks and goal
    public int manhattan() {
        return 0;
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
}
