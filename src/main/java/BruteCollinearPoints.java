import java.util.Arrays;


public class BruteCollinearPoints
{
    private static final int INITIAL_SEGMENTS = 1;

    private LineSegment[] segments;
    private int nextSegment;

    public BruteCollinearPoints(Point[] points) // finds all line segments containing 4 points
    {
        if (points == null)
        {
            throw new NullPointerException("null input");
        }

        segments = new LineSegment[INITIAL_SEGMENTS];
        nextSegment = 0;

        for (int i = 0; i < points.length; i++)
        {
            Point p = points[i];
            if (p == null)
            {
                throw new NullPointerException("null point");
            }

            for (int j = i + 1; j < points.length; j++)
            {
                //J-
                Point q = points[j];
                if (p.compareTo(q) == 0)
                {
                    throw new IllegalArgumentException("repeating points");
                }
                double slopePQ = p.slopeTo(q);
                //J+

                for (int k = j + 1; k < points.length; k++)
                {
                    Point r = points[k];
                    double slopePR = p.slopeTo(r);

                    if (slopePQ != slopePR)
                    {
                        continue;
                    }

                    for (int l = k + 1; l < points.length; l++)
                    {
                        Point s = points[l];
                        double slopePS = p.slopeTo(s);
                        if (slopePQ == slopePS)
                        {
                            Point[] collinearPoints = new Point[] { p, q, r, s };
                            Arrays.sort(collinearPoints);
                            addSegment(new LineSegment(collinearPoints[0], collinearPoints[3]));
                        }
                    }
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
