import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;


public class Solver
{
    public static void main(String[] args)
    {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                blocks[i][j] = in.readInt();
            }
        }

        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
        {
            StdOut.println("No solution possible");
        }
        else
        {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
            {
                StdOut.println(board);
            }
        }
    }

    private MinPQ<BoardWithMoves> minPQ;
    private MinPQ<BoardWithMoves> minPQ2;
    private List<Board> solution;
    private boolean isSolvable;

    public Solver(Board initial) // find a solution to the initial board (using the A* algorithm)
    {
        minPQ = new MinPQ<>();
        minPQ2 = new MinPQ<>();
        solution = new ArrayList<>();
        isSolvable = true;

        minPQ.insert(new BoardWithMoves(0, initial, null));
        minPQ2.insert(new BoardWithMoves(0, initial.twin(), null));

        BoardWithMoves boardWithMoves;
        while (true)
        {
            boardWithMoves = step(minPQ);
            if (boardWithMoves != null)
            {
                break;
            }

            if (step(minPQ2) != null)
            {
                isSolvable = false;

                break;
            }
        }

        for (BoardWithMoves b = boardWithMoves; b != null; b = b.getParent())
        {
            solution.add(0, b.getBoard());
        }
    }

    private BoardWithMoves step(MinPQ<BoardWithMoves> minPQ)
    {
        BoardWithMoves boardWithMoves = minPQ.delMin();

        if (boardWithMoves.getBoard().isGoal())
        {
            return boardWithMoves;
        }

        Iterable<Board> boards = boardWithMoves.getBoard().neighbors();
        for (Board b : boards)
        {
            if ((boardWithMoves.getParent() == null) || !b.equals(boardWithMoves.getParent().getBoard()))
            {
                minPQ.insert(new BoardWithMoves(boardWithMoves.getMoves() + 1, b,
                        boardWithMoves));
            }
        }

        return null;
    }

    public boolean isSolvable() // is the initial board solvable?
    {
        return isSolvable;
    }

    public int moves() // min number of moves to solve initial board; -1 if unsolvable
    {
        return isSolvable() ? (solution.size() - 1) : -1;
    }

    public Iterable<Board> solution() // sequence of boards in a shortest solution; null if unsolvable
    {
        return isSolvable() ? solution : null;
    }

    private static class BoardWithMoves implements Comparable<BoardWithMoves>
    {
        private int moves;
        private Board board;
        private BoardWithMoves parent;

        public BoardWithMoves(int moves, Board board, BoardWithMoves parent)
        {
            this.moves = moves;
            this.board = board;
            this.parent = parent;
        }

        public int getMoves()
        {
            return moves;
        }

        public Board getBoard()
        {
            return board;
        }

        public BoardWithMoves getParent()
        {
            return parent;
        }

        @Override
        public int compareTo(BoardWithMoves o)
        {
            return new Integer(moves + board.manhattan()).compareTo(o.getMoves() + o.getBoard().manhattan());
        }

        @Override
        public String toString()
        {
            return "BoardWithMoves{" +
                "moves=" + moves +
                ", board=" + board +
                ", parent=" + parent +
                '}';
        }
    }
}
