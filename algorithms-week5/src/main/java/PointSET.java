import java.util.*;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;


public class PointSET
{
    private static final double POINT_RADIUS = 0.005;

    private Set<Point2D> points;

    public PointSET() // construct an empty set of points
    {
        points = new TreeSet<>();
    }

    public boolean isEmpty() // is the set empty?
    {
        return points.isEmpty();
    }

    public int size() // number of points in the set
    {
        return points.size();
    }

    public void insert(Point2D p) // add the point to the set (if it is not already in the set)
    {
        if (p == null)
        {
            throw new NullPointerException();
        }

        if ((p.x() < 0) || (p.x() > 1) || (p.y() < 0) || (p.y() > 1))
        {
            throw new IllegalArgumentException();
        }

        points.add(p);
    }

    public boolean contains(Point2D p) // does the set contain point p?
    {
        if (p == null)
        {
            throw new NullPointerException();
        }

        return points.contains(p);
    }

    public void draw() // draw all points to standard draw
    {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.RED);
        for (Point2D point2D : points)
        {
            StdDraw.filledCircle(point2D.x(), point2D.y(), POINT_RADIUS);
        }
    }

    public Iterable<Point2D> range(RectHV rect) // all points that are inside the rectangle
    {
        if (rect == null)
        {
            throw new NullPointerException();
        }

        Set<Point2D> res = new TreeSet<>();
        for (Point2D point2D : points)
        {
            if (rect.contains(point2D))
            {
                res.add(point2D);
            }
        }

        return res;
    }

    public Point2D nearest(final Point2D p) // a nearest neighbor in the set to point p; null if the set is empty
    {
        if (p == null)
        {
            throw new NullPointerException();
        }

        if (points.isEmpty())
        {
            return null;
        }

        return Collections.min(points, new Comparator<Point2D>()
                {
                    @Override
                    public int compare(Point2D o1, Point2D o2)
                    {
                        return new Double(p.distanceSquaredTo(o1)).compareTo(p.distanceSquaredTo(o2));
                    }
                });
    }
}
