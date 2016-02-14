import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.*;


public class RandomizedQueueTest
{
    @Test
    public void testIsEmpty() throws Exception
    {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        assertTrue(randomizedQueue.isEmpty());
        for (int i = 0; i < 256; i++)
        {
            randomizedQueue.enqueue(i);
        }
        assertFalse(randomizedQueue.isEmpty());
        for (int i = 0; i < 256; i++)
        {
            randomizedQueue.dequeue();
        }
        assertTrue(randomizedQueue.isEmpty());
    }

    @Test
    public void testSize() throws Exception
    {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        assertEquals(0, randomizedQueue.size());
        for (int i = 0; i < 256; i++)
        {
            randomizedQueue.enqueue(i);
        }
        assertEquals(256, randomizedQueue.size());
        for (int i = 0; i < 256; i++)
        {
            randomizedQueue.dequeue();
        }
        assertEquals(0, randomizedQueue.size());
    }

    @Test
    public void testEnqueueDequeue() throws Exception
    {
        Set<Integer> integerSet = new HashSet<>();

        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

        for (int i = 0; i < 256; i++)
        {
            randomizedQueue.enqueue(i);
        }
        assertEquals(256, randomizedQueue.size());

        for (int i = 0; i < 256; i++)
        {
            integerSet.add(randomizedQueue.dequeue());
        }
        assertEquals(0, randomizedQueue.size());

        for (int i = 0; i < 256; i++)
        {
            assertTrue(integerSet.contains(i));
        }

        try
        {
            randomizedQueue.dequeue();
            fail();
        }
        catch (NoSuchElementException nse)
        {
            //
        }

        try
        {
            randomizedQueue.enqueue(null);
            fail();
        }
        catch (NullPointerException npe)
        {
            //
        }
    }

    @Test
    public void testSample() throws Exception
    {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

        for (int i = 0; i < 256; i++)
        {
            randomizedQueue.enqueue(i);
        }

        for (int i = 0; i < 1024; i++)
        {
            int sample = randomizedQueue.sample();

            assertTrue((sample >= 0) && (sample < 256));
        }
    }

    @Test
    public void testIterator() throws Exception
    {
        Set<Integer> integerSet = new HashSet<>();

        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

        for (int i = 0; i < 256; i++)
        {
            randomizedQueue.enqueue(i);
        }

        Iterator<Integer> iterator = randomizedQueue.iterator();
        while (iterator.hasNext())
        {
            int sample = iterator.next();
            integerSet.add(sample);
        }

        assertEquals(256, integerSet.size());

        for (int i = 0; i < 256; i++)
        {
            assertTrue(integerSet.contains(i));
        }

        try
        {
            iterator.next();
            fail();
        }
        catch (NoSuchElementException nse)
        {
            //
        }
    }
}
