import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;


public class PercolationStatsTest
{

    @Test
    public void test()
    {
        PercolationStats percolationStats = new PercolationStats(1, 1);
        assertEquals(1.0, percolationStats.mean(), 0.0);
    }

    @Test
    public void test2()
    {
        long start = System.currentTimeMillis();
        PercolationStats percolationStats2 = new PercolationStats(200, 100);
        System.out.println("mean: " + percolationStats2.mean());
        System.out.println("sdtddev: " + percolationStats2.stddev());
        System.out.println("confidenceLo: " + percolationStats2.confidenceLo());
        System.out.println("confidenceHi: " + percolationStats2.confidenceHi());
        System.out.println("time: " + (System.currentTimeMillis() - start) + "ms");
        assertEquals(0.59, percolationStats2.mean(), 0.01);
    }
}
