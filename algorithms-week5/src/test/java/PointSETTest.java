import java.util.Random;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;


public class PointSETTest
{
    @Ignore
    @Test
    public void testDraw() throws Exception
    {
        Random random = new Random();

        PointSET pointSET = new PointSET();
        for (int i = 0; i < 100; i++)
        {
            pointSET.insert(new Point2D(random.nextDouble(), random.nextDouble()));
        }

        pointSET.draw();
        Thread.sleep(10000);
    }

    @Ignore
    @Test
    public void testRange() throws Exception
    {
        Random random = new Random();

        PointSET pointSET = new PointSET();
        for (int i = 0; i < 5000; i++)
        {
            pointSET.insert(new Point2D(random.nextDouble(), random.nextDouble()));
        }

        RectHV rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);

        rectHV.draw();
        for (Point2D point2D : pointSET.range(rectHV))
        {
            point2D.draw();
        }
        Thread.sleep(10000);
    }

    @Ignore
    @Test
    public void testNearest() throws Exception
    {
        Random random = new Random();

        PointSET pointSET = new PointSET();
        for (int i = 0; i < 100; i++)
        {
            pointSET.insert(new Point2D(random.nextDouble(), random.nextDouble()));
        }

        Point2D nearest = pointSET.nearest(new Point2D(0, 0));

        StdDraw.setPenColor(StdDraw.BLUE);
        for (Point2D point2D : pointSET.range(new RectHV(0, 0, 1, 1)))
        {
            StdDraw.filledCircle(point2D.x(), point2D.y(), 0.003);
        }
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(nearest.x(), nearest.y(), 0.005);
        Thread.sleep(10000);
    }
}
