import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Pavel.Pontryagin on 21.03.2015.
 */
public class BoardTest {

    Board board;
    Board boardRev;


    @Before
    public void setUp() {
        board = new Board(makeTiles(3));
        boardRev = new Board(makeTilesReverse(3));
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

    @Ignore
    public void soutIsCorrect() {
        StdOut.println(board);
        StdOut.println(boardRev);
    }

    private int[][] makeTiles(int N) {
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = i * N + j + 1;

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
