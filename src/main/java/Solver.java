public class Solver {

    private Node currNode;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new NullPointerException();

        MinPQ<Node> minPQ = new MinPQ<Node>();
        MinPQ<Node> twinPQ = new MinPQ<Node>();

        currNode = new Node(initial, null);
        Node twinNode = new Node(initial.twin(), null);

        minPQ.insert(currNode);
        twinPQ.insert(twinNode);

        while (true) {
            currNode = minPQ.delMin();
            twinNode = twinPQ.delMin();

            if (currNode.isGoal() || twinNode.isGoal())
                break;

            for (Board board : currNode.getBoard().neighbors()) {
                if (currNode.prevNode == null || !board.equals(currNode.prevNode.getBoard())) {
                    minPQ.insert(new Node(board, currNode));
                }
            }
            for (Board board : twinNode.getBoard().neighbors()) {
                if (twinNode.prevNode == null || !board.equals(twinNode.prevNode.getBoard())) {
                    twinPQ.insert(new Node(board, twinNode));
                }
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return currNode.isGoal();
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable())
            return currNode.moves;
        else
            return -1;
    }

    // sequence of boards in a intest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable())
            return null;

        Stack<Board> stack = new Stack<Board>();
        Node current = currNode;
        while (current != null) {
            stack.push(current.getBoard());
            current = current.prevNode;
        }
        return stack;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private class Node implements Comparable<Node> {

        private Board current;
        private Node prevNode;
        private int moves;
        private int priority;
        private int manhattan;

        public Node(Board board, Node prev) {
            this.current = board;
            prevNode = prev;
            if (prev == null)
                moves = 0;
            else
                moves = prevNode.moves + 1;

            manhattan = current.manhattan();
            priority = moves + manhattan;

            assert (prevNode == null || this.priority >= prevNode.priority);
        }

        public boolean isGoal() {
            return current.isGoal();
        }

        public Board getBoard() {
            return current;
        }

        @Override
        public int compareTo(Node that) {

            if (that.priority > this.priority)
                return -1;
            else if (that.priority == this.priority) {
                return this.manhattan - that.manhattan;
            } else
                return 1;
        }
    }
}
