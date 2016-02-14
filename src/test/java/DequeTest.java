import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import static org.junit.Assert.*;


public class DequeTest
{
    @Test
    public void testIsEmpty() throws Exception
    {
        Deque<Integer> deque = new Deque<>();
        assertTrue(deque.isEmpty());
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
        deque.removeFirst();
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testSize() throws Exception
    {
        Deque<Integer> deque = new Deque<>();
        assertEquals(0, deque.size());
        deque.addFirst(1);
        assertEquals(1, deque.size());
        deque.addFirst(2);
        assertEquals(2, deque.size());
        deque.addFirst(3);
        assertEquals(3, deque.size());
        deque.addFirst(4);
        assertEquals(4, deque.size());
        deque.removeFirst();
        assertEquals(3, deque.size());
        deque.removeFirst();
        assertEquals(2, deque.size());
        deque.removeFirst();
        assertEquals(1, deque.size());
        deque.removeFirst();
        assertEquals(0, deque.size());
    }

    @Test
    public void testAddFirst() throws Exception
    {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);

        assertEquals(4, deque.size());
        assertEquals(new Integer(4), deque.removeFirst());
        assertEquals(3, deque.size());
        assertEquals(new Integer(1), deque.removeLast());
        assertEquals(2, deque.size());
        assertEquals(new Integer(3), deque.removeFirst());
        assertEquals(1, deque.size());
        assertEquals(new Integer(2), deque.removeFirst());
        assertEquals(0, deque.size());
        try
        {
            deque.addFirst(null);
            fail();
        }
        catch (NullPointerException npe)
        {
            //
        }

        deque.addFirst(5);
        assertEquals(1, deque.size());
        assertEquals(new Integer(5), deque.removeFirst());
        assertEquals(0, deque.size());
    }

    @Test
    public void testAddLast() throws Exception
    {
        Deque<Integer> deque = new Deque<>();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);

        assertEquals(4, deque.size());
        assertEquals(new Integer(1), deque.removeFirst());
        assertEquals(3, deque.size());
        assertEquals(new Integer(4), deque.removeLast());
        assertEquals(2, deque.size());
        assertEquals(new Integer(2), deque.removeFirst());
        assertEquals(1, deque.size());
        assertEquals(new Integer(3), deque.removeFirst());
        assertEquals(0, deque.size());

        try
        {
            deque.addLast(null);
            fail();
        }
        catch (NullPointerException npe)
        {
            //
        }

        deque.addLast(5);
        assertEquals(1, deque.size());
        assertEquals(new Integer(5), deque.removeFirst());
        assertEquals(0, deque.size());
    }

    @Test
    public void testRemoveFirst() throws Exception
    {
        Deque<Integer> deque = new Deque<>();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);

        assertEquals(4, deque.size());
        assertEquals(new Integer(1), deque.removeFirst());
        assertEquals(3, deque.size());
        assertEquals(new Integer(2), deque.removeFirst());
        assertEquals(2, deque.size());
        assertEquals(new Integer(3), deque.removeFirst());
        assertEquals(1, deque.size());
        assertEquals(new Integer(4), deque.removeFirst());
        assertEquals(0, deque.size());

        try
        {
            deque.removeFirst();
            fail();
        }
        catch (NoSuchElementException nse)
        {
            //
        }
    }

    @Test
    public void testRemoveLast() throws Exception
    {
        Deque<Integer> deque = new Deque<>();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);

        assertEquals(4, deque.size());
        assertEquals(new Integer(4), deque.removeLast());
        assertEquals(3, deque.size());
        assertEquals(new Integer(3), deque.removeLast());
        assertEquals(2, deque.size());
        assertEquals(new Integer(2), deque.removeLast());
        assertEquals(1, deque.size());
        assertEquals(new Integer(1), deque.removeLast());
        assertEquals(0, deque.size());

        try
        {
            deque.removeLast();
            fail();
        }
        catch (NoSuchElementException nse)
        {
            //
        }
    }

    @Test
    public void testIterator() throws Exception
    {
        Deque<Integer> deque = new Deque<>();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);

        Iterator<Integer> iterator = deque.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(new Integer(1), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(new Integer(2), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(new Integer(3), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(new Integer(4), iterator.next());
        assertFalse(iterator.hasNext());

        try
        {
            iterator.next();
            fail();
        }
        catch (NoSuchElementException nse)
        {
            //
        }

        deque.removeFirst();
        deque.removeLast();

        try
        {
            iterator.hasNext();
            fail();
        }
        catch (ConcurrentModificationException cme)
        {
            //
        }

        Iterator<Integer> iterator2 = deque.iterator();

        assertTrue(iterator2.hasNext());
        assertEquals(new Integer(2), iterator2.next());
        assertTrue(iterator2.hasNext());
        assertEquals(new Integer(3), iterator2.next());
        assertFalse(iterator2.hasNext());
    }
}
