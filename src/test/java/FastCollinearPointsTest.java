import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;


public class FastCollinearPointsTest
{
    @Test
    public void testNullArray() throws Exception
    {
        try
        {
            FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(null);
            fail("should have thrown");
        }
        catch (NullPointerException npe)
        {
        }
    }

    @Test
    public void testNullPoint() throws Exception
    {
        try
        {
            Point[] points = new Point[] { null, null };
            FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
            fail("should have thrown");
        }
        catch (NullPointerException npe)
        {
        }
    }

    @Test
    public void testRepeatingPoints() throws Exception
    {
        try
        {
            Point[] points = new Point[] { new Point(1, 2), new Point(12, 31), new Point(30, 40), new Point(1, 2) };
            FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
            fail("should have thrown");
        }
        catch (IllegalArgumentException iae)
        {
        }
    }

    @Test
    public void testNone()
    {
        Point[] points = new Point[] {};
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(0, fastCollinearPoints.numberOfSegments());
        assertEquals(0, fastCollinearPoints.segments().length);
    }

    @Test
    public void testOne()
    {
        Point[] points = new Point[] { new Point(1, 2) };
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(0, fastCollinearPoints.numberOfSegments());
        assertEquals(0, fastCollinearPoints.segments().length);
    }

    @Test
    public void testTwo()
    {
        Point[] points = new Point[] { new Point(1, 1), new Point(2, 2) };
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(0, fastCollinearPoints.numberOfSegments());
        assertEquals(0, fastCollinearPoints.segments().length);
    }

    @Test
    public void testThree()
    {
        Point[] points = new Point[] { new Point(1, 1), new Point(2, 2), new Point(3, 3) };
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(0, fastCollinearPoints.numberOfSegments());
        assertEquals(0, fastCollinearPoints.segments().length);
    }

    @Test
    public void testFourPositive()
    {
        Point[] points = new Point[] { new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4) };
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(1, fastCollinearPoints.numberOfSegments());
        assertEquals(1, fastCollinearPoints.segments().length);
        assertEquals("(1, 1) -> (4, 4)", fastCollinearPoints.segments()[0].toString());
    }

    @Test
    public void testFourOpposite()
    {
        Point[] points = new Point[] { new Point(-1, -1), new Point(-2, -2), new Point(3, 3), new Point(4, 4) };
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(1, fastCollinearPoints.numberOfSegments());
        assertEquals(1, fastCollinearPoints.segments().length);
        assertEquals("(-2, -2) -> (4, 4)", fastCollinearPoints.segments()[0].toString());
    }

    @Test
    public void testTwoSegments()
    {
        Point[] points = new Point[]
            {
                new Point(-1, -2), new Point(0, 0), new Point(1, 2), new Point(2, 4),
                new Point(2, -1), new Point(-2, 1), new Point(-6, 3)
            };
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(2, fastCollinearPoints.numberOfSegments());
        assertEquals(2, fastCollinearPoints.segments().length);
        assertEquals("(-1, -2) -> (2, 4)", fastCollinearPoints.segments()[0].toString());
        assertEquals("(2, -1) -> (-6, 3)", fastCollinearPoints.segments()[1].toString());
    }

    @Test
    public void testThreeSegments()
    {
        Point[] points = new Point[]
            {
                new Point(-1, -2), new Point(0, 0), new Point(1, 2), new Point(2, 4),
                new Point(2, -1), new Point(-2, 1), new Point(-6, 3),
                new Point(1, -2), new Point(-1, 2), new Point(-3, 6)
            };
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(3, fastCollinearPoints.numberOfSegments());
        assertEquals(3, fastCollinearPoints.segments().length);
        assertEquals("(-1, -2) -> (2, 4)", fastCollinearPoints.segments()[0].toString());
        assertEquals("(2, -1) -> (-6, 3)", fastCollinearPoints.segments()[2].toString());
        assertEquals("(1, -2) -> (-3, 6)", fastCollinearPoints.segments()[1].toString());
    }

    @Test
    public void test40()
    {
        Point[] points = new Point[]
            {
                new Point(1000, 17000),
                new Point(14000, 24000),
                new Point(26000, 8000),
                new Point(10000, 28000),
                new Point(18000, 5000),
                new Point(1000, 27000),
                new Point(14000, 14000),
                new Point(11000, 16000),
                new Point(29000, 17000),
                new Point(5000, 21000),
                new Point(19000, 26000),
                new Point(28000, 21000),
                new Point(25000, 24000),
                new Point(30000, 10000),
                new Point(25000, 14000),
                new Point(31000, 16000),
                new Point(5000, 12000),
                new Point(1000, 31000),
                new Point(2000, 24000),
                new Point(13000, 17000),
                new Point(1000, 28000),
                new Point(14000, 16000),
                new Point(26000, 26000),
                new Point(10000, 31000),
                new Point(12000, 4000),
                new Point(9000, 24000),
                new Point(28000, 29000),
                new Point(12000, 20000),
                new Point(13000, 11000),
                new Point(4000, 26000),
                new Point(8000, 10000),
                new Point(15000, 12000),
                new Point(22000, 29000),
                new Point(7000, 15000),
                new Point(10000, 4000),
                new Point(2000, 29000),
                new Point(17000, 17000),
                new Point(3000, 15000),
                new Point(4000, 29000),
                new Point(19000, 2000)
            };
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(4, fastCollinearPoints.numberOfSegments());

        assertEquals("(1000, 17000) -> (29000, 17000)", fastCollinearPoints.segments()[0].toString());
        assertEquals("(1000, 17000) -> (1000, 31000)", fastCollinearPoints.segments()[1].toString());
        assertEquals("(2000, 24000) -> (25000, 24000)", fastCollinearPoints.segments()[2].toString());
        assertEquals("(2000, 29000) -> (28000, 29000)", fastCollinearPoints.segments()[3].toString());
    }

    @Test
    public void test8()
    {
        Point[] points = new Point[]
            {
                new Point(10000, 0),
                new Point(0, 10000),
                new Point(3000, 7000),
                new Point(7000, 3000),
                new Point(20000, 21000),
                new Point(3000, 4000),
                new Point(14000, 15000),
                new Point(6000, 7000)
            };
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(2, fastCollinearPoints.numberOfSegments());
        assertEquals("(10000, 0) -> (0, 10000)", fastCollinearPoints.segments()[0].toString());
        assertEquals("(3000, 4000) -> (20000, 21000)", fastCollinearPoints.segments()[1].toString());
    }

    @Test
    public void testHorizontal() throws Exception
    {
        Point[] points = readPoints("horizontal25.txt");
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(25, fastCollinearPoints.numberOfSegments());

        points = readPoints("horizontal50.txt");
        fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(50, fastCollinearPoints.numberOfSegments());

        points = readPoints("horizontal75.txt");
        fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(75, fastCollinearPoints.numberOfSegments());

        points = readPoints("horizontal100.txt");
        fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(100, fastCollinearPoints.numberOfSegments());
    }

    @Test
    public void testVertical() throws Exception
    {
        Point[] points = readPoints("vertical25.txt");
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(25, fastCollinearPoints.numberOfSegments());

        points = readPoints("vertical50.txt");
        fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(50, fastCollinearPoints.numberOfSegments());

        points = readPoints("vertical75.txt");
        fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(75, fastCollinearPoints.numberOfSegments());

        points = readPoints("vertical100.txt");
        fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(100, fastCollinearPoints.numberOfSegments());
    }

    @Test
    public void testInput9() throws Exception
    {
        Point[] points = readPoints("input9.txt");
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        System.out.println(Arrays.asList(fastCollinearPoints.segments()));
    }

//    @Test
//    public void test1000() throws Exception
//    {
//        Point[] points = readPoints("input1000.txt");
//        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
//        System.out.println(fastCollinearPoints.numberOfSegments());
//    }
//
//    @Test
//    public void testGrid4x4() throws Exception
//    {
//        Point[] points = readPoints("grid4x4.txt");
//        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
//        System.out.println(fastCollinearPoints.numberOfSegments());
//    }
//
//    @Test
//    public void testMystery() throws Exception
//    {
//        Point[] points = readPoints("mystery10089.txt");
//        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
//        System.out.println(fastCollinearPoints.numberOfSegments());
//    }

    private Point[] readPoints(String filename) throws IOException
    {
        List<String> contents = IOUtils.readLines(FastCollinearPointsTest.class.getResourceAsStream(new File("collinear", filename).getPath()));
        int size = Integer.parseInt(contents.get(0).trim());
        Point[] points = new Point[size];
        for (int i = 0; i < points.length; i++)
        {
            String s = contents.get(i + 1);
            s = s.trim();
            if (StringUtils.isBlank(s))
            {
                continue;
            }

            String[] coords = s.split("\\s+");
            points[i] = new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
        }

        return points;
    }
}
