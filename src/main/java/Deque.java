import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item>
{
    private Node<Item> first = null;
    private Node<Item> last = null;
    private int size = 0;
    private int modcount = 0;

    public boolean isEmpty() // is the deque empty?
    {
        return size == 0;
    }

    public int size() // return the number of items on the deque
    {
        return size;
    }

    public void addFirst(Item item) // add the item to the front
    {
        if (item == null)
        {
            throw new NullPointerException("nulls not allowed");
        }

        Node<Item> node = new Node<>(item);

        node.next = first;

        if (first != null)
        {
            first.prev = node;
        }
        else
        {
            last = node;
        }

        first = node;

        size += 1;

        modcount += 1;
    }

    public void addLast(Item item) // add the item to the end
    {
        if (item == null)
        {
            throw new NullPointerException("nulls not allowed");
        }

        Node<Item> node = new Node<>(item);

        node.prev = last;

        if (last != null)
        {
            last.next = node;
        }
        else
        {
            first = node;
        }

        last = node;

        size += 1;

        modcount += 1;
    }

    public Item removeFirst() // remove and return the item from the front
    {
        if (size == 0)
        {
            throw new NoSuchElementException("queue is empty");
        }

        Node<Item> res = first;

        first = first.next;

        if (first != null)
        {
            first.prev = null;
        }
        else
        {
            last = null;
        }

        size -= 1;

        modcount += 1;

        return res.item;
    }

    public Item removeLast() // remove and return the item from the end
    {
        if (size == 0)
        {
            throw new NoSuchElementException("queue is empty");
        }

        Node<Item> res = last;

        last = last.prev;

        if (last != null)
        {
            last.next = null;
        }
        else
        {
            first = null;
        }

        size -= 1;

        modcount += 1;

        return res.item;
    }

    public Iterator<Item> iterator() // return an iterator over items in order from front to end
    {
        return new Iterator<Item>()
            {
                private Node<Item> next;
                private final int expectedModcount;

                {
                    next = first;
                    expectedModcount = modcount;
                }

                @Override
                public boolean hasNext()
                {
                    if (expectedModcount != modcount)
                    {
                        throw new ConcurrentModificationException();
                    }

                    return next != null;
                }

                @Override
                public Item next()
                {
                    if (expectedModcount != modcount)
                    {
                        throw new ConcurrentModificationException();
                    }

                    if (next == null)
                    {
                        throw new NoSuchElementException("already at last element");
                    }

                    Item res = next.item;

                    next = next.next;

                    return res;
                }

                @Override
                public void remove()
                {
                    throw new UnsupportedOperationException();
                }
            };
    }

    private static class Node<Item>
    {
        final Item item;
        Node<Item> next = null;
        Node<Item> prev = null;

        public Node(Item item)
        {
            this.item = item;
        }
    }
}
