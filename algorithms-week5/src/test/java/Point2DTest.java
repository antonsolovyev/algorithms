import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;


public class Point2DTest
{
    @Test
    public void testDistanceTo() throws Exception
    {
        Point2D point2D = new Point2D(1, 1);
        Point2D point2D2 = new Point2D(0, 1);
        assertEquals(1, point2D.distanceTo(point2D2), 0.0);

        Point2D point2D3 = new Point2D(1, 0);
        Point2D point2D4 = new Point2D(-1, 0);
        assertEquals(2, point2D3.distanceTo(point2D4), 0.0);
    }

    @Ignore
    @Test
    public void testDraw() throws Exception
    {
        Point2D point2D = new Point2D(0.5, 0.5);

        point2D.draw();
        Thread.sleep(10000);
    }
}
