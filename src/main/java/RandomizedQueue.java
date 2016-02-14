import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item>
{
    private static final int INITIAL_SIZE = 1;
    private Item[] store;
    private int size;
    private int nextIndex;

    public RandomizedQueue() // construct an empty randomized queue
    {
        store = (Item[]) new Object[INITIAL_SIZE];
        size = 0;
    }

    public boolean isEmpty() // is the queue empty?
    {
        return size == 0;
    }

    public int size() // return the number of items on the queue
    {
        return size;
    }

    public void enqueue(Item item) // add the item
    {
        if (item == null)
        {
            throw new NullPointerException("nulls are not supported");
        }

        if (nextIndex >= store.length)
        {
            reallocate(store.length * 2);
        }

        store[nextIndex++] = item;

        size++;
    }

    private void reallocate(int newSize)
    {
        Item[] newStore = (Item[]) new Object[newSize];
        nextIndex = 0;
        for (int i = 0; i < store.length; i++)
        {
            if (store[i] != null)
            {
                newStore[nextIndex++] = store[i];
            }
        }
        store = newStore;
    }

    public Item dequeue() // remove and return a random item
    {
        int randomIndex = getRandomNonEmptyIndex();

        Item res = store[randomIndex];

        store[randomIndex] = null;

        size--;

        if ((size <= (store.length / 4)) && (size >= INITIAL_SIZE))
        {
            reallocate(store.length / 2);
        }

        return res;
    }

    private int getRandomNonEmptyIndex()
    {
        if (size == 0)
        {
            throw new NoSuchElementException("queue is empty");
        }

        while (true)
        {
            int randomIndex = StdRandom.uniform(store.length);
            if (store[randomIndex] != null)
            {
                return randomIndex;
            }
        }
    }

    public Item sample() // return (but do not remove) a random item
    {
        return store[getRandomNonEmptyIndex()];
    }

    public Iterator<Item> iterator() // return an independent iterator over items in random order
    {
        return new Iterator<Item>()
            {
                private Item[] snapshot;
                private int nextIndex;
                private int size;

                {
                    snapshot = (Item[]) new Object[store.length];

                    size = 0;
                    for (int i = 0; i < store.length; i++)
                    {
                        if (store[i] != null)
                        {
                            snapshot[size++] = store[i];
                        }
                    }

                    if (size > 0)
                    {
                        StdRandom.shuffle(snapshot, 0, size - 1);
                    }

                    nextIndex = 0;
                }

                @Override
                public boolean hasNext()
                {
                    return nextIndex < size;
                }

                @Override
                public Item next()
                {
                    if (!hasNext())
                    {
                        throw new NoSuchElementException("at the last element");
                    }

                    return snapshot[nextIndex++];
                }

                @Override
                public void remove()
                {
                    throw new UnsupportedOperationException();
                }
            };
    }
}
