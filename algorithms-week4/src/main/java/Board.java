import java.util.ArrayList;
import java.util.List;


public class Board
{

    private static void swap(Board board, int i, int j, int k, int l)
    {
        int t = board.blocks[i][j];
        board.blocks[i][j] = board.blocks[k][l];
        board.blocks[k][l] = t;
    }

    private int[][] blocks;

    public Board(int[][] blocks) // construct a board from an N-by-N array of blocks
    {
        if (blocks == null)
        {
            throw new NullPointerException();
        }

        if ((blocks.length < 2) || (blocks.length > 128))
        {
            throw new IllegalArgumentException();
        }

        this.blocks = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++)
        {
            for (int j = 0; j < blocks.length; j++)
            {
                this.blocks[i][j] = blocks[i][j];
            }
        }

        if (!isValid())
        {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValid()
    {
        boolean[] seen = new boolean[blocks.length * blocks.length];

        for (int i = 0; i < blocks.length; i++)
        {
            for (int j = 0; j < blocks.length; j++)
            {
                if ((blocks[i][j] < 0) || (blocks[i][j] >= seen.length))
                {
                    throw new IllegalArgumentException();
                }

                seen[blocks[i][j]] = true;
            }
        }

        for (int i = 0; i < seen.length; i++)
        {
            if (!seen[i])
            {
                return false;
            }
        }

        return true;
    }

    // (where blocks[i][j] = block in row i, column j)
    public int dimension() // board dimension N
    {
        return blocks.length;
    }

    public int hamming() // number of blocks out of place
    {
        int res = 0;
        for (int i = 0; i < blocks.length; i++)
        {
            for (int j = 0; j < blocks.length; j++)
            {
                // Do not count gap as being out of place
                if ((i == (blocks.length - 1)) && (j == (blocks.length - 1)))
                {
                    continue;
                }

                if (blocks[i][j] != goalValueByRowCol(i, j))
                {
                    res++;
                }
            }
        }

        return res;
    }

    public int manhattan() // sum of Manhattan distances between blocks and goal
    {
        int res = 0;
        for (int i = 0; i < blocks.length; i++)
        {
            for (int j = 0; j < blocks.length; j++)
            {
                // Do not count gap as being out of place
                if (blocks[i][j] == 0)
                {
                    continue;
                }

                int goalRow = goalRowByValue(blocks[i][j]);
                int goalCol = goalColByValue(blocks[i][j]);

                res = res + Math.abs(i - goalRow) + Math.abs(j - goalCol);
            }
        }

        return res;
    }

    private int goalValueByRowCol(int i, int j)
    {
        if ((i == (blocks.length - 1)) && (j == (blocks.length - 1)))
        {
            return 0;
        }

        return (i * blocks.length) + j + 1;
    }

    private int goalRowByValue(int value)
    {
        if (value == 0)
        {
            return blocks.length - 1;
        }

        return (value - 1) / blocks.length;
    }

    private int goalColByValue(int value)
    {
        if (value == 0)
        {
            return blocks.length - 1;
        }

        return (value - 1) % blocks.length;
    }

    public boolean isGoal() // is this board the goal board?
    {
        return hamming() == 0;
    }

    public Board twin() // a board that is obtained by exchanging any pair of blocks
    {
        Board res = new Board(blocks);

        int i = ((blocks[0][0] == 0) || (blocks[0][1] == 0)) ? 1 : 0;
        int j = 0;

        swap(res, i, j, i, j + 1);

        return res;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass()))
        {
            return false;
        }

        Board board = (Board) o;

        if (blocks.length != board.blocks.length)
        {
            return false;
        }

        for (int i = 0; i < blocks.length; i++)
        {
            for (int j = 0; j < blocks.length; j++)
            {
                if (blocks[i][j] != board.blocks[i][j])
                {
                    return false;
                }
            }
        }

        return true;
    }

    public Iterable<Board> neighbors() // all neighboring boards
    {
        List<Board> res = new ArrayList<>();

        int gapRow = 0;
        int gapCol = 0;
        for (int i = 0; i < blocks.length; i++)
        {
            for (int j = 0; j < blocks.length; j++)
            {
                if (blocks[i][j] == 0)
                {
                    gapRow = i;
                    gapCol = j;
                }
            }
        }

        if ((gapCol - 1) >= 0)
        {
            Board board = new Board(blocks);
            swap(board, gapRow, gapCol, gapRow, gapCol - 1);
            res.add(board);
        }

        if ((gapCol + 1) < blocks.length)
        {
            Board board = new Board(blocks);
            swap(board, gapRow, gapCol, gapRow, gapCol + 1);
            res.add(board);
        }

        if ((gapRow - 1) >= 0)
        {
            Board board = new Board(blocks);
            swap(board, gapRow, gapCol, gapRow - 1, gapCol);
            res.add(board);
        }

        if ((gapRow + 1) < blocks.length)
        {
            Board board = new Board(blocks);
            swap(board, gapRow, gapCol, gapRow + 1, gapCol);
            res.add(board);
        }

        return res;
    }

    public String toString() // string representation of this board (in the output format specified below)
    {
        StringBuilder res = new StringBuilder();

        res.append(blocks.length);
        res.append("\n");
        for (int i = 0; i < blocks.length; i++)
        {
            for (int j = 0; j < blocks.length; j++)
            {
                res.append(blocks[i][j]);
                if (j != (blocks.length - 1))
                {
                    res.append("  ");
                }
            }
            res.append("\n");
        }

        return res.toString();
    }
}
