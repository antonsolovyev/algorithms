import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;


public class KdTreeTest
{
    @Ignore
    @Test
    public void testDraw() throws Exception
    {
        Random random = new Random();

        KdTree kdTree = new KdTree();
        for (int i = 0; i < 10; i++)
        {
            kdTree.insert(new Point2D(random.nextDouble(), random.nextDouble()));
        }

        kdTree.draw();
        Thread.sleep(1000000);
    }

    @Ignore
    @Test
    public void testDraw2() throws Exception
    {
        KdTree kdTree = new KdTree();
        for (Point2D point2D : readPoints("circle10.txt"))
        {
            kdTree.insert(point2D);
        }

        kdTree.draw();
        Thread.sleep(1000000);
    }

    @Test
    public void testSize() throws Exception
    {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.25, 0.25));
        kdTree.insert(new Point2D(0.25, 0.25));
        kdTree.insert(new Point2D(0.75, 0.75));
        kdTree.insert(new Point2D(0.75, 0.75));
        kdTree.insert(new Point2D(0.5, 0.5));
        kdTree.insert(new Point2D(0.5, 0.5));

        assertEquals(3, kdTree.size());
    }

    @Test
    public void testNearest() throws Exception
    {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.25, 0.25));
        kdTree.insert(new Point2D(0.75, 0.75));
        kdTree.insert(new Point2D(0.5, 0.5));

        assertEquals(new Point2D(0.25, 0.25), kdTree.nearest(new Point2D(0.25, 0.25)));
        assertEquals(new Point2D(0.25, 0.25), kdTree.nearest(new Point2D(0, 0)));

        assertEquals(new Point2D(0.75, 0.75), kdTree.nearest(new Point2D(0.75, 0.75)));
        assertEquals(new Point2D(0.75, 0.75), kdTree.nearest(new Point2D(1, 1)));

        assertEquals(new Point2D(0.5, 0.5), kdTree.nearest(new Point2D(0.5, 0.5)));
        assertEquals(new Point2D(0.5, 0.5), kdTree.nearest(new Point2D(0.51, 0.51)));
    }

    @Test
    public void testNearest2() throws Exception
    {
        KdTree kdTree = new KdTree();

        for (Point2D point2D : readPoints("input800K.txt"))
        {
            kdTree.insert(point2D);
        }

        long start = System.currentTimeMillis();
        Point2D nearest = kdTree.nearest(new Point2D(0.5, 0.5));
        assertEquals(new Point2D(0.49981, 0.499247), nearest);

        long delta = System.currentTimeMillis() - start;
        System.out.println("time: " + delta + ", nearest:" + nearest);

        Point2D nearest2 = kdTree.nearest(new Point2D(0.0, 0.0));
        assertEquals(new Point2D(0.001711, 2.19E-4), nearest2);
    }

    @Test
    public void testNearest3() throws Exception
    {
        KdTree kdTree = new KdTree();

        for (Point2D point2D : readPoints("circle10000.txt"))
        {
            kdTree.insert(point2D);
        }

        long start = System.currentTimeMillis();
        Point2D nearest = kdTree.nearest(new Point2D(0.5, 0.5));
//        assertEquals(new Point2D(0.099217, 0.201048), nearest);

        long delta = System.currentTimeMillis() - start;
        System.out.println("time: " + delta + ", nearest:" + nearest);
    }

    @Test
    public void testNearest4() throws Exception
    {
        KdTree kdTree = new KdTree();

        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 5000000; i++)
        {
            kdTree.insert(new Point2D(random.nextDouble(), random.nextDouble()));
        }

        long start = System.currentTimeMillis();
        Point2D nearest = kdTree.nearest(new Point2D(0.5, 0.5));
        long delta = System.currentTimeMillis() - start;
        System.out.println("time: " + delta + ", nearest:" + nearest);
    }

    @Test
    public void testContains() throws Exception
    {
        List<Point2D> point2Ds = readPoints("input10K.txt");
        KdTree kdTree = new KdTree();
        for (Point2D point2D : point2Ds)
        {
            kdTree.insert(point2D);
        }

        for (Point2D point2D : point2Ds)
        {
            assertTrue(kdTree.contains(point2D));
        }

        assertFalse(kdTree.contains(new Point2D(0, 0)));
    }

    @Test
    public void testRange() throws Exception
    {
        KdTree kdTree;
        RectHV rectHV;
        List<Point2D> point2Ds;

        //
        rectHV = new RectHV(0, 0, 1, 1);
        kdTree = new KdTree();
        kdTree.insert(new Point2D(0.25, 0.25));
        kdTree.insert(new Point2D(0.75, 0.75));
        kdTree.insert(new Point2D(0.0, 0.0));
        kdTree.insert(new Point2D(1.0, 1.0));

        int count = 0;
        for (Point2D p : kdTree.range(rectHV))
        {
            count++;
        }
        assertEquals(4, count);

        // all in
        rectHV = new RectHV(0, 0, 0.5, 0.5);
        kdTree = new KdTree();
        kdTree.insert(new Point2D(0.25, 0.25));
        kdTree.insert(new Point2D(0.75, 0.75));
        kdTree.insert(new Point2D(0.0, 0.0));
        kdTree.insert(new Point2D(1.0, 1.0));

        count = 0;
        for (Point2D p : kdTree.range(rectHV))
        {
            count++;
        }
        assertEquals(2, count);

        // 2 in 2 out
        rectHV = new RectHV(0.25, 0.25, 0.75, 0.75);
        kdTree = new KdTree();

        int npoints = 1000000;
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < npoints; i++)
        {
            kdTree.insert(new Point2D(random.nextDouble(), random.nextDouble()));
        }
        count = 0;
        for (Point2D p : kdTree.range(rectHV))
        {
            count++;
        }
        // Should be ~1/4 points within rectangle
        assertEquals(count * 4.0d, npoints, npoints * 0.01);
    }

    private List<Point2D> readPoints(String filename) throws IOException
    {
        List<Point2D> res = new ArrayList<>();

        List<String> contents = IOUtils.readLines(KdTreeTest.class.getResourceAsStream(new File("kdtree", filename).getPath()));
        for (String s : contents)
        {
            s = s.trim();
            if (StringUtils.isBlank(s))
            {
                continue;
            }

            String[] coords = s.split("\\s+");
            Double x = Double.parseDouble(coords[0]);
            Double y = Double.parseDouble(coords[1]);
            res.add(new Point2D(x, y));
        }

        return res;
    }
}
