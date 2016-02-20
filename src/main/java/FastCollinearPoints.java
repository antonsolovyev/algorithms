import java.util.Arrays;
import java.util.Comparator;


public class FastCollinearPoints
{
    private static final int INITIAL_SEGMENTS = 1;

    private LineSegment[] segments;
    private int nextSegment;

    public FastCollinearPoints(Point[] points) // finds all line segments containing 4 points
    {
        if (points == null)
        {
            throw new NullPointerException("null input");
        }

        segments = new LineSegment[INITIAL_SEGMENTS];
        nextSegment = 0;

        processInput(Arrays.copyOf(points, points.length));
    }

    private void processInput(Point[] points)
    {
        for (int i = 0; i < points.length; i++)
        {
            Point p = points[i];

            if (p == null)
            {
                throw new NullPointerException("null point");
            }

            Comparator<Point> comparator = p.slopeOrder();

            Arrays.sort(points, i + 1, points.length, comparator);

            for (int j = i + 1, length = 1; j < points.length; j++)
            {
                if (points[i].compareTo(points[j]) == 0)
                {
                    throw new IllegalArgumentException("repeating points");
                }

                if ((j == (points.length - 1)) || (comparator.compare(points[j], points[j + 1]) != 0))
                {
                    if (length >= 3)
                    {
                        Point[] collinear = new Point[length + 1];
                        collinear[0] = points[i];
                        for (int k = 0; k < length; k++)
                        {
                            collinear[k + 1] = points[j - k];
                        }
                        Arrays.sort(collinear);
                        addSegment(new LineSegment(collinear[0], collinear[collinear.length - 1]));
                    }
                    length = 1;
                }
                else
                {
                    length++;
                }
            }
        }
        adjustSize();
    }

    private void adjustSize()
    {
        reallocSegments(nextSegment);
    }

    private void addSegment(LineSegment lineSegment)
    {
        if (nextSegment == segments.length)
        {
            reallocSegments(segments.length * 2);
        }

        segments[nextSegment++] = lineSegment;
    }

    private void reallocSegments(int newLength)
    {
        LineSegment[] newSegments = new LineSegment[newLength];

        int copyCount = (segments.length > newLength) ? newLength : segments.length;

        for (int i = 0; i < copyCount; i++)
        {
            newSegments[i] = segments[i];
        }
        segments = newSegments;
    }

    public int numberOfSegments() // the number of line segments
    {
        return segments.length;
    }

    public LineSegment[] segments() // the line segments
    {
        return Arrays.copyOf(segments, segments.length);
    }
}
