import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;


public class BoardTest
{
    @Test
    public void testConstructor() throws Exception
    {
        try
        {
            new Board(null);
        }
        catch (NullPointerException npe)
        {
        }

        try
        {
            new Board(new int[1][1]);
        }
        catch (IllegalArgumentException iae)
        {
        }

        try
        {
            new Board(new int[129][129]);
        }
        catch (IllegalArgumentException iae)
        {
        }

        try
        {
            int[][] blocks = new int[][]
                {
                    { 1, 2, 3 },
                    { 4, 5, 6 },
                    { 7, 8, 9 }
                };
            new Board(blocks);
        }
        catch (IllegalArgumentException iae)
        {
        }

        try
        {
            int[][] blocks = new int[][]
                {
                    { 1, 1, 3 },
                    { 4, 5, 6 },
                    { 7, 8, 0 }
                };
            new Board(blocks);
        }
        catch (IllegalArgumentException iae)
        {
        }

        try
        {
            int[][] blocks = new int[][]
                {
                    { 1, 10, 3 },
                    { 4, 5, 6 },
                    { 7, 8, 0 }
                };
            new Board(blocks);
        }
        catch (IllegalArgumentException iae)
        {
        }

        int[][] blocks = new int[][]
            {
                { 2, 1, 3 },
                { 4, 5, 6 },
                { 7, 0, 8 }
            };
        new Board(blocks);
    }

    @Test
    public void testDimension() throws Exception
    {
        int[][] blocks = new int[][]
            {
                { 0, 1 },
                { 2, 3 }
            };
        Board board = new Board(blocks);

        assertEquals(2, board.dimension());
    }

    @Test
    public void testManhattan() throws Exception
    {
        int[][] blocks;

        blocks = new int[][]
            {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
            };
        assertEquals(0, new Board(blocks).manhattan());

        blocks = new int[][]
            {
                { 2, 1, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
            };
        assertEquals(2, new Board(blocks).manhattan());

        blocks = new int[][]
            {
                { 5, 2, 3 },
                { 4, 1, 6 },
                { 7, 8, 0 }
            };
        assertEquals(4, new Board(blocks).manhattan());

        blocks = new int[][]
            {
                { 5, 2, 8 },
                { 4, 1, 6 },
                { 7, 3, 0 }
            };
        assertEquals(10, new Board(blocks).manhattan());

        blocks = new int[][]
            {
                { 1, 2, 7 },
                { 4, 5, 6 },
                { 3, 8, 0 }
            };
        assertEquals(8, new Board(blocks).manhattan());

        blocks = new int[][]
            {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 0, 8 }
            };
        assertEquals(1, new Board(blocks).manhattan());
    }

    @Test
    public void testHamming() throws Exception
    {
        int[][] blocks;

        blocks = new int[][]
            {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
            };
        assertEquals(0, new Board(blocks).hamming());

        blocks = new int[][]
            {
                { 2, 1, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
            };
        assertEquals(2, new Board(blocks).hamming());

        blocks = new int[][]
            {
                { 3, 2, 1 },
                { 4, 5, 6 },
                { 7, 8, 0 }
            };
        assertEquals(2, new Board(blocks).hamming());

        blocks = new int[][]
            {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 0, 8 }
            };
        assertEquals(1, new Board(blocks).hamming());

        blocks = new int[][]
            {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 0, 7, 8 }
            };
        assertEquals(2, new Board(blocks).hamming());

        blocks = new int[][]
            {
                { 4, 5, 6 },
                { 1, 2, 3 },
                { 7, 8, 0 }
            };
        assertEquals(6, new Board(blocks).hamming());
    }

    @Test
    public void testIsGoal() throws Exception
    {
        int[][] blocks;

        blocks = new int[][]
            {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
            };
        assertTrue(new Board(blocks).isGoal());

        blocks = new int[][]
            {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 0, 8 }
            };
        assertFalse(new Board(blocks).isGoal());

        blocks = new int[][]
            {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 8, 7, 0 }
            };
        assertFalse(new Board(blocks).isGoal());
    }

    @Test
    public void testTwin() throws Exception
    {
        // 0,0 is 0
        int[][] blocks = new int[][]
            {
                { 0, 1 },
                { 2, 3 }
            };
        int[][] blocks2 = new int[][]
            {
                { 0, 1 },
                { 3, 2 }
            };
        Board board = new Board(blocks);
        Board twin = board.twin();
        assertEquals(new Board(blocks2), twin);

        // 0, 1 is 0
        blocks = new int[][]
            {
                { 1, 0 },
                { 2, 3 }
            };
        blocks2 = new int[][]
            {
                { 1, 0 },
                { 3, 2 }
            };
        board = new Board(blocks);
        twin = board.twin();
        assertEquals(new Board(blocks2), twin);

        // neither 0,0 not 0,1 is 0
        blocks = new int[][]
            {
                { 1, 2 },
                { 0, 3 }
            };
        blocks2 = new int[][]
            {
                { 2, 1 },
                { 0, 3 }
            };
        board = new Board(blocks);
        twin = board.twin();
        assertEquals(new Board(blocks2), twin);
    }

    @Test
    public void testEquals() throws Exception
    {
        int[][] blocks = new int[][]
            {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
            };
        Board board = new Board(blocks);

        // Not equal to null
        assertFalse(board.equals(null));

        // Not equal to another class' instance
        assertFalse(board.equals(new Object()));

        // Not equal if elements do not match
        int[][] blocks2 = new int[][]
            {
                { 2, 1, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
            };
        Board board2 = new Board(blocks2);
        assertFalse(board.equals(board2));

        // Equals when elements match
        int[][] blocks3 = new int[][]
            {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
            };
        Board board3 = new Board(blocks);
        assertTrue(board.equals(board3));

        // Equals to self
        assertTrue(board.equals(board));

        // Different dims
        int[][] blocks4 = new int[][]
            {
                { 1, 2 },
                { 3, 0 },
            };
        Board board4 = new Board(blocks4);
        assertFalse(board.equals(board4));
    }

    @Test
    public void testNeighbors() throws Exception
    {
        int[][] blocks;
        List<Board> neighbors;

        // case 1
        blocks = new int[][]
            {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
            };

        neighbors = (List<Board>) new Board(blocks).neighbors();

        assertTrue(neighbors.contains(new Board(new int[][]
                    {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 0, 8 }
                    })));
        assertTrue(neighbors.contains(new Board(new int[][]
                    {
                        { 1, 2, 3 },
                        { 4, 5, 0 },
                        { 7, 8, 6 }
                    })));

        // case 2
        blocks = new int[][]
            {
                { 0, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 1 }
            };

        neighbors = (List<Board>) new Board(blocks).neighbors();

        assertTrue(neighbors.contains(new Board(new int[][]
                    {
                        { 2, 0, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 1 }
                    })));
        assertTrue(neighbors.contains(new Board(new int[][]
                    {
                        { 4, 2, 3 },
                        { 0, 5, 6 },
                        { 7, 8, 1 }
                    })));

        // case 3
        blocks = new int[][]
            {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 0, 8 }
            };

        neighbors = (List<Board>) new Board(blocks).neighbors();

        assertTrue(neighbors.contains(new Board(new int[][]
                    {
                        { 1, 2, 3 },
                        { 4, 0, 6 },
                        { 7, 5, 8 }
                    })));
        assertTrue(neighbors.contains(new Board(new int[][]
                    {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 0, 7, 8 }
                    })));
        assertTrue(neighbors.contains(new Board(new int[][]
                    {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 0 }
                    })));

        // case 4
        blocks = new int[][]
            {
                { 1, 2, 3 },
                { 4, 0, 6 },
                { 7, 8, 5 }
            };

        neighbors = (List<Board>) new Board(blocks).neighbors();

        assertTrue(neighbors.contains(new Board(new int[][]
                    {
                        { 1, 0, 3 },
                        { 4, 2, 6 },
                        { 7, 8, 5 }
                    })));
        assertTrue(neighbors.contains(new Board(new int[][]
                    {
                        { 1, 2, 3 },
                        { 0, 4, 6 },
                        { 7, 8, 5 }
                    })));
        assertTrue(neighbors.contains(new Board(new int[][]
                    {
                        { 1, 2, 3 },
                        { 4, 6, 0 },
                        { 7, 8, 5 }
                    })));
        assertTrue(neighbors.contains(new Board(new int[][]
                    {
                        { 1, 2, 3 },
                        { 4, 8, 6 },
                        { 7, 0, 5 }
                    })));
    }

    @Test
    public void testToString() throws Exception
    {
        int[][] blocks = new int[][]
            {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
            };
        assertEquals("3\n" +
            "1  2  3\n" +
            "4  5  6\n" +
            "7  8  0\n", new Board(blocks).toString());
    }
}
