import org.junit.*;
import org.junit.Test;

/**
 * Created by Pavel.Pontryagin on 21.03.2015.
 */
public class BoardTest {

    @Test
    public void soutIsCorrect() {

        Board board = new Board(makeTiles(3));
        StdOut.println(board);
    }

    private int[][] makeTiles(int N) {
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = i + j;

        return blocks;
    }
}
