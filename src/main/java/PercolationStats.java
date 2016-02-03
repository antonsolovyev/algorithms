import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats
{
    public static void main(String[] args)
    {
        if (args.length < 2)
        {
            throw new IllegalArgumentException("Two parameters required, lattice size and experiment count");
        }

        Integer size = Integer.parseInt(args[0]);
        Integer count = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(size, count);

        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev);
        StdOut.println("95% confidence interval = " + percolationStats.confidenceLo() + ", " +
            percolationStats.confidenceHi());
    }

    private final int size;
    private final int count;
    private double[] samples;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    public PercolationStats(int size, int count)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("Non-positive lattice size");
        }
        if (count <= 0)
        {
            throw new IllegalArgumentException("Non-positive experiment count");
        }

        this.size = size;
        this.count = count;
        this.samples = new double[count];

        run();
    }

    private void run()
    {
        for (int i = 0; i < count; i++)
        {
            samples[i] = getSample();
        }

        stddev = StdStats.stddev(samples);
        mean = StdStats.mean(samples);
        confidenceHi = mean + getHalf95ConfidenceInterval(stddev);
        confidenceLo = mean - getHalf95ConfidenceInterval(stddev);
    }

    private double getHalf95ConfidenceInterval(double stddev)
    {
        return stddev * 1.96 / Math.sqrt(count);
    }

    private double getSample()
    {
        Percolation percolation = new Percolation(size);
        for (int count = 0; count < (size * size);)
        {
            int row = StdRandom.uniform(size);
            int column = StdRandom.uniform(size);
            if (!percolation.isOpen(row, column))
            {
                percolation.open(row, column);
                count++;
                if (percolation.percolates())
                {
                    return ((double) count) / (size * size);
                }
            }
        }

        return 1.0;
    }

    public double mean()
    {
        return mean;
    }

    public double stddev()
    {
        return stddev;
    }

    public double confidenceLo()
    {
        return confidenceLo;
    }

    public double confidenceHi()
    {
        return confidenceHi;
    }
}
