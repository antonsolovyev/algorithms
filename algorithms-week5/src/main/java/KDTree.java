import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;


public class KdTree
{
    private static final double POINT_RADIUS = 0.005;
    private static final long DRAW_DELAY = 0;

    private int size;
    private Node root;

    public KdTree() // construct an empty set of points
    {
    }

    public boolean isEmpty() // is the set empty?
    {
        return size == 0;
    }

    public int size() // number of points in the set
    {
        return size;
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

        root = insert(root, p, Node.Bisection.HORIZONTAL);
    }

    private Node insert(Node n, Point2D p, Node.Bisection bisection)
    {
        if (n == null)
        {
            size++;

            return new Node(p, (bisection == Node.Bisection.HORIZONTAL) ? Node.Bisection.VERTICAL : Node.Bisection.HORIZONTAL);
        }

        if (n.point.equals(p))
        {
            return n;
        }

        if (((n.bisection == Node.Bisection.HORIZONTAL) && (p.y() < n.point.y())) ||
                ((n.bisection == Node.Bisection.VERTICAL) && (p.x() < n.point.x())))
        {
            n.left = insert(n.left, p, n.bisection);
        }
        else
        {
            n.right = insert(n.right, p, n.bisection);
        }

        return n;
    }

    public boolean contains(Point2D p) // does the set contain point p?
    {
        if (p == null)
        {
            throw new NullPointerException();
        }

        return contains(root, p);
    }

    private boolean contains(Node n, Point2D p)
    {
        if (n == null)
        {
            return false;
        }

        if (n.point.equals(p))
        {
            return true;
        }

        if (((n.bisection == Node.Bisection.HORIZONTAL) && (p.y() < n.point.y())) ||
                ((n.bisection == Node.Bisection.VERTICAL) && (p.x() < n.point.x())))
        {
            return contains(n.left, p);
        }
        else
        {
            return contains(n.right, p);
        }
    }

    public void draw() // draw all points to standard draw
    {
        StdDraw.clear();
        draw(root, new RectHV(0, 0, 1, 1));
    }

    private void draw(Node n, RectHV current)
    {
        if (n == null)
        {
            return;
        }

        switch (n.bisection)
        {
        case VERTICAL:
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(n.point.x(), current.ymin(), n.point.x(), current.ymax());
            drawPoint(n.point);
            sleep(DRAW_DELAY);
            break;
        case HORIZONTAL:
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(current.xmin(), n.point.y(), current.xmax(), n.point.y());
            drawPoint(n.point);
            sleep(DRAW_DELAY);
            break;
        default:
            throw new IllegalStateException();
        }

        draw(n.left, getLeftRect(n, current));
        draw(n.right, getRightRect(n, current));
    }

    private void drawPoint(Point2D point2D)
    {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(point2D.x(), point2D.y(), POINT_RADIUS);
    }

    private void sleep(long millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException ie)
        {
        }
    }

    public Iterable<Point2D> range(RectHV rect) // all points that are inside the rectangle
    {
        if (rect == null)
        {
            throw new NullPointerException();
        }

        return range(root, rect, new RectHV(0, 0, 1, 1));
    }

    private List<Point2D> range(Node n, RectHV target, RectHV current)
    {
        List<Point2D> res = new ArrayList<>();

        if (n == null)
        {
            return res;
        }

        if (!target.intersects(current))
        {
            return res;
        }

        if (target.contains(n.point))
        {
            res.add(n.point);
        }

        res.addAll(range(n.left, target, getLeftRect(n, current)));
        res.addAll(range(n.right, target, getRightRect(n, current)));

        return res;
    }

    private RectHV getLeftRect(Node n, RectHV current)
    {
        switch (n.bisection)
        {
        case VERTICAL:
            return new RectHV(current.xmin(), current.ymin(), n.point.x(), current.ymax());
        case HORIZONTAL:
            return new RectHV(current.xmin(), current.ymin(), current.xmax(), n.point.y());
        default:
            throw new IllegalArgumentException();
        }
    }

    private RectHV getRightRect(Node n, RectHV current)
    {
        switch (n.bisection)
        {
        case VERTICAL:
            return new RectHV(n.point.x(), current.ymin(), current.xmax(), current.ymax());
        case HORIZONTAL:
            return new RectHV(current.xmin(), n.point.y(), current.xmax(), current.ymax());
        default:
            throw new IllegalArgumentException();
        }
    }

    public Point2D nearest(final Point2D p) // a nearest neighbor in the set to point p; null if the set is empty
    {
        if (p == null)
        {
            throw new NullPointerException();
        }

        if (isEmpty())
        {
            return null;
        }

        return nearest(root, new RectHV(0, 0, 1, 1), p, root.point);
    }

//    private Point2D nearest(Node n, RectHV current, Point2D target, Point2D nearest)
//    {
//        if (n == null)
//        {
//            return nearest;
//        }
//
//        double mindist = nearest.distanceSquaredTo(target);
//
//        if (current.distanceSquaredTo(target) > mindist)
//        {
//            return nearest;
//        }
//
//        if (n.point.distanceSquaredTo(target) < mindist)
//        {
//            nearest = n.point;
//            mindist = nearest.distanceSquaredTo(target);
//        }
//
//        Point2D nearestLeft = nearest(n.left, getLeftRect(n, current), target, nearest);
//        double distanceLeft = nearestLeft.distanceSquaredTo(target);
//        if (mindist > distanceLeft)
//        {
//            nearest = nearestLeft;
//            mindist = distanceLeft;
//        }
//
//        Point2D nearestRight = nearest(n.right, getRightRect(n, current), target, nearest);
//        double distanceRight = nearestRight.distanceSquaredTo(target);
//        if (mindist > distanceRight)
//        {
//            nearest = nearestRight;
//            mindist = distanceRight;
//        }
//
//        return nearest;
//    }

    private Point2D nearest(Node n, RectHV current, Point2D target, Point2D nearest)
    {
        if (n == null)
        {
            return nearest;
        }

        double mindist = nearest.distanceSquaredTo(target);
        if (current.distanceSquaredTo(target) > mindist)
        {
            return nearest;
        }

        Point2D candidateNearest = n.point;
        double candidateMindist = candidateNearest.distanceSquaredTo(target);
        if (candidateMindist < mindist)
        {
            nearest = candidateNearest;
            mindist = candidateMindist;
        }

        if (((n.bisection == Node.Bisection.VERTICAL) && (target.x() < n.point.x())) ||
                ((n.bisection == Node.Bisection.HORIZONTAL) && (target.y() < n.point.y())))
        {
            candidateNearest = nearest(n.left, getLeftRect(n, current), target, nearest);
            candidateMindist = candidateNearest.distanceSquaredTo(target);
            if (candidateMindist < mindist)
            {
                nearest = candidateNearest;
                mindist = candidateMindist;
            }

            candidateNearest = nearest(n.right, getRightRect(n, current), target, nearest);
            candidateMindist = candidateNearest.distanceSquaredTo(target);
            if (candidateMindist < mindist)
            {
                nearest = candidateNearest;
                mindist = candidateMindist;
            }
        }
        else
        {
            candidateNearest = nearest(n.right, getRightRect(n, current), target, nearest);
            candidateMindist = candidateNearest.distanceSquaredTo(target);
            if (candidateMindist < mindist)
            {
                nearest = candidateNearest;
                mindist = candidateMindist;
            }

            candidateNearest = nearest(n.left, getLeftRect(n, current), target, nearest);
            candidateMindist = candidateNearest.distanceSquaredTo(target);
            if (candidateMindist < mindist)
            {
                nearest = candidateNearest;
                mindist = candidateMindist;
            }
        }

        return nearest;
    }

    private static class Node
    {
        enum Bisection
        {
            HORIZONTAL,
            VERTICAL;
        }

        Bisection bisection;
        Point2D point;
        Node left;
        Node right;

        public Node(Point2D point, Bisection bisection)
        {
            this.point = point;
            this.bisection = bisection;
        }
    }
}
