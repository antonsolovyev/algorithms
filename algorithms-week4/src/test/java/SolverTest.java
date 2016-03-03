import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;


public class SolverTest
{
    @Test
    public void testPuzzle3x3_00() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle3x3-00.txt");

        solver = new Solver(board);

        System.out.println(solver.solution());

        assertEquals(0, solver.moves());
    }

    @Test
    public void testPuzzle3x3_01() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle3x3-01.txt");

        solver = new Solver(board);

        System.out.println(solver.solution());

        assertEquals(1, solver.moves());
    }

    @Test
    public void testPuzzle3x3_02() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle3x3-02.txt");

        solver = new Solver(board);

        System.out.println(solver.solution());

        assertEquals(2, solver.moves());
    }

    @Test
    public void testPuzzle3x3_03() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle3x3-03.txt");

        solver = new Solver(board);

        System.out.println(solver.solution());

        assertEquals(3, solver.moves());
    }

    @Test
    public void testPuzzle3x3_04() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle3x3-04.txt");

        solver = new Solver(board);

        System.out.println(solver.solution());

        assertEquals(4, solver.moves());
    }

    @Test
    public void testPuzzle3x3_05() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle3x3-05.txt");

        solver = new Solver(board);

        System.out.println(solver.solution());

        assertEquals(5, solver.moves());
    }

    @Test
    public void testPuzzle3x3_06() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle3x3-06.txt");

        solver = new Solver(board);

        System.out.println(solver.solution());

        assertEquals(6, solver.moves());
    }

    @Test
    public void testPuzzle3x3_07() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle3x3-07.txt");

        solver = new Solver(board);

        System.out.println(solver.solution());

        assertEquals(7, solver.moves());
    }

    @Test
    public void testPuzzle3x3_08() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle3x3-08.txt");

        solver = new Solver(board);

        System.out.println(solver.solution());

        assertEquals(8, solver.moves());
    }

    // @Ignore
    @Test
    public void testPuzzle14() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle14.txt");

        solver = new Solver(board);

        System.out.println(solver.solution());

        assertEquals(14, solver.moves());
    }

    @Ignore
    @Test
    public void testPuzzle3x3_16() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle3x3-16.txt");

        solver = new Solver(board);

        System.out.println(solver.solution());

        assertEquals(16, solver.moves());
    }

    @Test
    public void testPuzzle2x2_unsolvable1() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle2x2-unsolvable1.txt");

        solver = new Solver(board);

        assertFalse(solver.isSolvable());
        assertEquals(-1, solver.moves());
        assertEquals(null, solver.solution());
    }

    @Test
    public void testPuzzle2x2_unsolvable2() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle2x2-unsolvable2.txt");

        solver = new Solver(board);

        assertFalse(solver.isSolvable());
        assertEquals(-1, solver.moves());
        assertEquals(null, solver.solution());
    }

    @Test
    public void testPuzzle2x2_unsolvable3() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle2x2-unsolvable3.txt");

        solver = new Solver(board);

        assertFalse(solver.isSolvable());
        assertEquals(-1, solver.moves());
        assertEquals(null, solver.solution());
    }

    @Test
    public void testPuzzle3x3_unsolvable() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle3x3-unsolvable.txt");

        solver = new Solver(board);

        assertFalse(solver.isSolvable());
        assertEquals(-1, solver.moves());
        assertEquals(null, solver.solution());
    }

    @Test
    public void testPuzzle3x3_unsolvable1() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle3x3-unsolvable1.txt");

        solver = new Solver(board);

        assertFalse(solver.isSolvable());
        assertEquals(-1, solver.moves());
        assertEquals(null, solver.solution());
    }

    @Test
    public void testPuzzle3x3_unsolvable2() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle3x3-unsolvable2.txt");

        solver = new Solver(board);

        assertFalse(solver.isSolvable());
        assertEquals(-1, solver.moves());
        assertEquals(null, solver.solution());
    }

    @Test
    public void testPuzzle4x4_unsolvable() throws Exception
    {
        Solver solver;

        Board board = readBoard("puzzle4x4-unsolvable.txt");

        solver = new Solver(board);

        assertFalse(solver.isSolvable());
        assertEquals(-1, solver.moves());
        assertEquals(null, solver.solution());
    }

    @Ignore
    @Test
    public void testAll() throws Exception
    {
        File dir = new File(SolverTest.class.getResource("8puzzle").getFile());
        for (File f : dir.listFiles())
        {
            String filename = f.getName();
            Board board = readBoard(filename);

            Solver solver = new Solver(board);
            System.out.println(filename + ": " + solver.moves());

            Thread.sleep(100);
        }
    }

    private Board readBoard(String filename) throws IOException
    {
        List<String> contents = IOUtils.readLines(SolverTest.class.getResourceAsStream(new File("8puzzle", filename).getPath()));
        int size = Integer.parseInt(contents.get(0).trim());
        int[][] blocks = new int[size][size];
        for (int i = 0; i < blocks.length; i++)
        {
            String s = contents.get(i + 1);
            s = s.trim();
            if (StringUtils.isBlank(s))
            {
                continue;
            }

            String[] numbers = s.split("\\s+");
            for (int j = 0; j < blocks.length; j++)
            {
                blocks[i][j] = Integer.parseInt(numbers[j]);
            }
        }

        return new Board(blocks);
    }
}
