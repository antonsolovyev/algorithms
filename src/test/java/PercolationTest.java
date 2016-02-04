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

        for (int i = 1; i <= 100; i++)
        {
            for (int j = 1; j <= 100; j++)
            {
                percolation.open(i, j);
            }
        }

        for (int i = 1; i <= 100; i++)
        {
            for (int j = 1; j <= 100; j++)
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
            percolation.open(random.nextInt(size) + 1, random.nextInt(size) + 1);
        }
    }

    @Test
    public void testIsFull() throws Exception
    {
        Percolation percolation = makePercolates();

        assertFalse(percolation.isFull(1, 1));
        assertTrue(percolation.isFull(1, 2));
        assertFalse(percolation.isFull(1, 3));
        assertTrue(percolation.isFull(2, 1));
        assertTrue(percolation.isFull(2, 2));
        assertTrue(percolation.isFull(2, 3));
        assertFalse(percolation.isFull(3, 1));
        assertTrue(percolation.isFull(3, 2));
        assertFalse(percolation.isFull(3, 3));
    }

    @Test
    public void testIsFull2() throws Exception
    {
        Percolation percolation = makePercolates2();

        assertTrue(percolation.isFull(3, 3));
        assertFalse(percolation.isFull(3, 1));
    }

    @Test
    public void testOne()
    {
        Percolation percolation = new Percolation(1);
        assertFalse(percolation.percolates());
        percolation.open(1, 1);
        assertTrue(percolation.percolates());
    }

    @Test
    public void testTwo()
    {
        Percolation percolation = new Percolation(2);
        assertFalse(percolation.percolates());
        percolation.open(1, 1);
        assertFalse(percolation.percolates());
        percolation.open(1, 2);
        assertFalse(percolation.percolates());
        percolation.open(2, 1);
        assertTrue(percolation.percolates());
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
        res.open(1, 2);
        res.open(2, 1);
        res.open(2, 2);
        res.open(2, 3);
        res.open(3, 2);

        return res;
    }

    private Percolation makePercolates2()
    {
        Percolation res = new Percolation(3);
        res.open(1, 3);
        res.open(2, 3);
        res.open(3, 3);
        res.open(3, 1);

        return res;
    }

    private Percolation makeNotPercolates()
    {
        Percolation res = new Percolation(3);
        res.open(1, 2);
        res.open(2, 1);
        res.open(2, 2);
        res.open(2, 3);

        return res;
    }
}
