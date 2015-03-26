import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardTest {

    Board board;
    Board boardRev;
    Board boardTwin;

    @Before
    public void setUp() {
        board = new Board(makeTiles((int)3));
        boardRev = new Board(makeTilesReverse(3));

        int[][] twin = makeTiles((int)3);
        twin[0][0] = 2;
        twin[0][1] = 1;

        boardTwin = new Board(twin);
    }

    @Test
    public void immutableTest() {
        board = new Board(makeTiles20Txt(3));
        System.out.println("orig: " + board);
        System.out.println(board.twin());
        System.out.println(board.twin());
        System.out.println(board.twin());
        System.out.println(board.twin());

    }

    @Ignore
    public void neighboursAreCorrect() {

        int[][] twin = makeTiles((int)3);

        twin[2][1] = 0;
        twin[2][2] = 8;

        board = new Board(twin);

        System.out.println(board.toString());

        for (Board b: board.neighbors()) {
            System.out.println(b.toString());
        }
    }

    @Test
    public void twinIsCorrect() {
        assertThat(board.twin().equals(boardTwin), is(true));
    }

    @Test
    public void testHamming() {
        assertThat(board.hamming(), is(0));
        assertThat(boardRev.hamming(), is(8));
    }

    @Test
    public void testManhattan() {
        assertThat(board.manhattan(), is(0));
        assertThat(boardRev.manhattan(), is(16));
    }

    @Test
    public void boardIsGoal() {
        assertThat(board.isGoal(), is(true));
    }

    @Test
    public void twoEqualBoardAreEqual() {
        int n = 3;
        Board boardEqual = new Board(makeTiles(n));
        assertThat(board.equals(boardEqual), is(true));
    }

    @Test
    public void twoUnequalBoardAreUnequal() {
        assertThat(board.equals(boardRev), is(false));
    }

    @Ignore
    public void soutIsCorrect() {
        StdOut.println(board);
        StdOut.println(boardRev);
    }

    private int[][] makeTiles20Txt(int N) {
        int[][] blocks = new int[N][N];

//        1  6  4
//        7  0  8
//        2  3  5

        blocks[0][0] = 1;
        blocks[0][1] = 6;
        blocks[0][2] = 4;
        blocks[1][0] = 7;
        blocks[1][1] = 0;
        blocks[1][2] = 8;
        blocks[2][0] = 2;
        blocks[2][1] = 3;
        blocks[2][2] = 5;


        return blocks;
    }

    private int[][] makeTiles(int N) {
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = (int)i * (int)N + (int)j + (int)1;

        blocks[N - 1][N - 1] = 0;

        return blocks;
    }

    private int[][] makeTilesReverse(int N) {
        int[][] blocks = new int[N][N];
        int k = 8;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = k--;

        return blocks;
    }
}
