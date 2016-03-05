import edu.princeton.cs.algs4.StdDraw;


public class Point2D implements Comparable<Point2D>
{
    private final double x;
    private final double y;

    public Point2D(double x, double y) // construct the point (x, y)
    {
        this.x = x;
        this.y = y;
    }

    public double x() // x-coordinate
    {
        return x;
    }

    public double y() // y-coordinate
    {
        return y;
    }

    public double distanceTo(Point2D that) // Euclidean distance between two points
    {
        return Math.sqrt(distanceSquaredTo(that));
    }

    public double distanceSquaredTo(Point2D that) // square of Euclidean distance between two points
    {
        double deltaX = x - that.x;
        double deltaY = y - that.y;

        return (deltaX * deltaX) + (deltaY * deltaY);
    }

    public int compareTo(Point2D that) // for use in an ordered symbol table
    {
        return 0;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass()))
        {
            return false;
        }

        Point2D point2D = (Point2D) o;

        if (Double.compare(point2D.x, x) != 0)
        {
            return false;
        }

        return Double.compare(point2D.y, y) == 0;

    }

    public void draw() // draw to standard draw
    {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(x, y, 0.005);
    }

    @Override
    public String toString()
    {
        return "Point2D{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }
}
