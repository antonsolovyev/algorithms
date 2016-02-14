import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Subset
{
    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            throw new RuntimeException("need 1 command line argument");
        }

        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty())
        {
            String s = StdIn.readString();
            randomizedQueue.enqueue(s);
        }

        for (int i = 0; i < k; i++)
        {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}
