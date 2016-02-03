import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.*;


public class PercolationTest
{
    @Test
    public void testOpen() throws Exception
    {
        Percolation percolation = new Percolation(100);

        populateRandom(percolation, 100, 500);

        for (int i = 0; i < 100; i++)
        {
            for (int j = 0; j < 100; j++)
            {
                percolation.open(i, j);
            }
        }

        for (int i = 0; i < 100; i++)
        {
            for (int j = 0; j < 100; j++)
            {
                assertTrue(percolation.isOpen(i, j));
            }
        }
    }

    private void populateRandom(Percolation percolation, int size, int n)
    {
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < n; i++)
        {
            percolation.open(random.nextInt(size), random.nextInt(size));
        }
    }

    @Test
    public void testIsFull() throws Exception
    {
        Percolation percolation = makePercolates();

        assertFalse(percolation.isFull(0, 0));
        assertTrue(percolation.isFull(0, 1));
        assertFalse(percolation.isFull(0, 2));
        assertTrue(percolation.isFull(1, 0));
        assertTrue(percolation.isFull(1, 1));
        assertTrue(percolation.isFull(1, 2));
        assertFalse(percolation.isFull(2, 0));
        assertTrue(percolation.isFull(2, 1));
        assertFalse(percolation.isFull(2, 2));
    }

    @Test
    public void testIsFull2() throws Exception
    {
        Percolation percolation = makePercolates2();

        assertTrue(percolation.isFull(2, 2));
        assertFalse(percolation.isFull(2, 0));
    }

    @Test
    public void testPercolates() throws Exception
    {
        Percolation percolation = makePercolates();

        assertTrue(percolation.percolates());

        Percolation percolation2 = makeNotPercolates();

        assertFalse(percolation2.percolates());
    }

    private Percolation makePercolates()
    {
        Percolation res = new Percolation(3);
        res.open(0, 1);
        res.open(1, 0);
        res.open(1, 1);
        res.open(1, 2);
        res.open(2, 1);

        return res;
    }

    private Percolation makePercolates2()
    {
        Percolation res = new Percolation(3);
        res.open(0, 2);
        res.open(1, 2);
        res.open(2, 2);
        res.open(2, 0);

        return res;
    }

    private Percolation makeNotPercolates()
    {
        Percolation res = new Percolation(3);
        res.open(0, 1);
        res.open(1, 0);
        res.open(1, 1);
        res.open(1, 2);

        return res;
    }
}
