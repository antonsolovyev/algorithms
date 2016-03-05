import edu.princeton.cs.algs4.StdDraw;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;


public class RectHVTest
{
    @Test
    public void testIntersects() throws Exception
    {
        RectHV rectHV;
        RectHV rectHV2;

        //
        rectHV = new RectHV(0.25, 0.25, 1.25, 1.25);
        assertTrue(rectHV.intersects(rectHV));

        //
        rectHV = new RectHV(0.25, 0.25, 1.25, 1.25);
        rectHV2 = new RectHV(1.5, 0.5, 2.5, 1.0);
        assertFalse(rectHV.intersects(rectHV2));
        assertFalse(rectHV2.intersects(rectHV));

        //
        rectHV = new RectHV(0.25, 0.25, 1.25, 1.25);
        rectHV2 = new RectHV(1.0, 1.0, 1.5, 1.5);
        assertTrue(rectHV.intersects(rectHV2));
        assertTrue(rectHV2.intersects(rectHV));

        //
        rectHV = new RectHV(0.25, 0.25, 1.25, 1.25);
        rectHV2 = new RectHV(0.0, 0.0, 0.5, 0.5);
        assertTrue(rectHV.intersects(rectHV2));
        assertTrue(rectHV2.intersects(rectHV));

        //
        rectHV = new RectHV(0.25, 0.25, 1.25, 1.25);
        rectHV2 = new RectHV(0.5, 0.5, 0.75, 0.75);
        assertTrue(rectHV.intersects(rectHV2));
        assertTrue(rectHV2.intersects(rectHV));

        //
        rectHV = new RectHV(0.25, 0.25, 1.25, 1.25);
        rectHV2 = new RectHV(-0.25, -0.25, 0.0, 0.0);
        assertFalse(rectHV.intersects(rectHV2));
        assertFalse(rectHV2.intersects(rectHV));
    }

    @Test
    public void testContains() throws Exception
    {
        Point2D point2D;
        RectHV rectHV;

        //
        rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        point2D = new Point2D(0.5, 0.5);
        assertTrue(rectHV.contains(point2D));

        //
        rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        point2D = new Point2D(0.0, 0.0);
        assertFalse(rectHV.contains(point2D));

        //
        rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        point2D = new Point2D(1.0, 1.0);
        assertFalse(rectHV.contains(point2D));
    }

    @Ignore
    @Test
    public void testDraw() throws Exception
    {

        double start = 0.02;
        double curr = start;
        while (true)
        {
            if (curr >= (1 - start))
            {
                break;
            }

            RectHV rectHV = new RectHV(start, start, curr, curr);
            StdDraw.clear();
            rectHV.draw();
            Thread.sleep(5);

            curr += 0.001;
        }
    }

    @Test
    public void testDistance() throws Exception
    {
        Point2D point2D;
        RectHV rectHV;

        //
        rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        point2D = new Point2D(0.5, 0.5);
        assertEquals(0, rectHV.distanceTo(point2D), 0);

        //
        rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        point2D = new Point2D(0.25, 0.25);
        assertEquals(0, rectHV.distanceTo(point2D), 0);

        //
        rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        point2D = new Point2D(0.25, 0.5);
        assertEquals(0, rectHV.distanceTo(point2D), 0);

        //
        rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        point2D = new Point2D(0.0, 0.0);
        assertEquals(0.25 * Math.sqrt(2), rectHV.distanceTo(point2D), 0);

        //
        rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        point2D = new Point2D(1.0, 1.0);
        assertEquals(0.25 * Math.sqrt(2), rectHV.distanceTo(point2D), 0);

        //
        rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        point2D = new Point2D(1.0, 0.0);
        assertEquals(0.25 * Math.sqrt(2), rectHV.distanceTo(point2D), 0);

        //
        rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        point2D = new Point2D(0.0, 1.0);
        assertEquals(0.25 * Math.sqrt(2), rectHV.distanceTo(point2D), 0);

        //
        rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        point2D = new Point2D(1.0, 0.5);
        assertEquals(0.25, rectHV.distanceTo(point2D), 0);

        //
        rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        point2D = new Point2D(0.5, 1.0);
        assertEquals(0.25, rectHV.distanceTo(point2D), 0);

        //
        rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        point2D = new Point2D(0.0, 0.5);
        assertEquals(0.25, rectHV.distanceTo(point2D), 0);

        //
        rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        point2D = new Point2D(0.5, 0.0);
        assertEquals(0.25, rectHV.distanceTo(point2D), 0);
    }
}
