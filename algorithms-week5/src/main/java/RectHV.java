import edu.princeton.cs.algs4.StdDraw;


public class RectHV
{
    private final double xmin;
    private final double ymin;
    private final double xmax;
    private final double ymax;

    public RectHV(double xmin, double ymin, // construct the rectangle [xmin, xmax] x [ymin, ymax]
        double xmax, double ymax) // throw a java.lang.IllegalArgumentException if (xmin > xmax) or (ymin > ymax)
    {
        if ((xmin > xmax) || (ymin > ymax))
        {
            throw new IllegalArgumentException();
        }

        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    public double xmin() // minimum x-coordinate of rectangle
    {
        return xmin;
    }

    public double ymin() // minimum y-coordinate of rectangle
    {
        return ymin;
    }

    public double xmax() // maximum x-coordinate of rectangle
    {
        return xmax;
    }

    public double ymax() // maximum y-coordinate of rectangle
    {
        return ymax;
    }

    public boolean contains(Point2D p) // does this rectangle contain the point p (either inside or on boundary)?
    {
        return (p.x() <= xmax) && (p.x() >= xmin) && (p.y() <= ymax) && (p.y() >= ymin);
    }

    public boolean intersects(RectHV that) // does this rectangle intersect that rectangle (at one or more points)?
    {
        boolean overlapX = false;
        if (Math.abs((xmax + xmin) - (that.xmax + that.xmin)) <= ((xmax - xmin) + (that.xmax - that.xmin)))
        {
            overlapX = true;
        }

        boolean overlapY = false;
        if (Math.abs((ymax + ymin) - (that.ymax + that.ymin)) <= ((ymax - ymin) + (that.ymax - that.ymin)))
        {
            overlapY = true;
        }

        return overlapX && overlapY;
    }

    public double distanceTo(Point2D p) // Euclidean distance from point p to closest point in rectangle
    {
        return Math.sqrt(distanceSquaredTo(p));
    }

    public double distanceSquaredTo(Point2D p) // square of Euclidean distance from point p to closest point in rectangle
    {
        if (contains(p))
        {
            return 0;
        }

        if ((p.x() < xmax) && (p.x() > xmin))
        {
            double distance = 0;

            if (p.y() < ymin)
            {
                distance = ymin - p.y();
            }

            if (p.y() > ymax)
            {
                distance = p.y() - ymax;
            }

            return distance * distance;
        }

        if ((p.y() < ymax) && (p.y() > ymin))
        {
            double distance = 0;

            if (p.x() < xmin)
            {
                distance = xmin - p.x();
            }

            if (p.x() > xmax)
            {
                distance = p.x() - xmax;
            }

            return distance * distance;
        }

        if ((p.y() < ymin) && (p.x() < xmin))
        {
            return p.distanceSquaredTo(new Point2D(xmin, ymin));
        }

        if ((p.y() > ymax) && (p.x() > xmax))
        {
            return p.distanceSquaredTo(new Point2D(xmax, ymax));
        }

        if ((p.y() > ymax) && (p.x() < xmin))
        {
            return p.distanceSquaredTo(new Point2D(xmin, ymax));
        }

        if ((p.y() < ymin) && (p.x() > xmax))
        {
            return p.distanceSquaredTo(new Point2D(xmax, ymin));
        }

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

        RectHV rectHV = (RectHV) o;

        if (Double.compare(rectHV.xmin, xmin) != 0)
        {
            return false;
        }
        if (Double.compare(rectHV.ymin, ymin) != 0)
        {
            return false;
        }
        if (Double.compare(rectHV.xmax, xmax) != 0)
        {
            return false;
        }

        return Double.compare(rectHV.ymax, ymax) == 0;
    }

    public void draw() // draw to standard draw
    {
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.rectangle(xmin + ((xmax - xmin) / 2), ymin + ((ymax - ymin) / 2),
            (xmax - xmin) / 2, (ymax - ymin) / 2);
    }

    @Override
    public String toString()
    {
        return "RectHV{" +
            "xmin=" + xmin +
            ", ymin=" + ymin +
            ", xmax=" + xmax +
            ", ymax=" + ymax +
            '}';
    }
}
