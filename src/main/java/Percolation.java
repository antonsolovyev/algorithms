import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation
{
    private final boolean[][] isOpen;
    private final int size;
    private WeightedQuickUnionUF weightedQuickUnionUF;

    public Percolation(int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("Non-positive lattice size");
        }

        this.size = size;

        isOpen = new boolean[size][size];

        weightedQuickUnionUF = new WeightedQuickUnionUF((size * size) + 2);
        for (int i = 0; i < size; i++)
        {
            weightedQuickUnionUF.union(getLinearAddress(0, i), getTopPseudoSiteLinearAddress());
        }
        for (int i = 0; i < size; i++)
        {
            weightedQuickUnionUF.union(getLinearAddress(size - 1, i), getBottomPseudoSiteLinearAddress());
        }
    }

    public void open(int row, int column)
    {
        if (!validRowColumn(row, column))
        {
            throw new IndexOutOfBoundsException("Row or column is out of bounds");
        }

        if (isOpen(row, column))
        {
            return;
        }

        isOpen[row][column] = true;

        connect(row, column, row, column - 1);
        connect(row, column, row, column + 1);
        connect(row, column, row - 1, column);
        connect(row, column, row + 1, column);
    }

    private void connect(int row, int column, int row2, int column2)
    {
        if (!validRowColumn(row, column) || !validRowColumn(row2, column2))
        {
            return;
        }

        if (isOpen(row, column) && isOpen(row2, column2))
        {
            weightedQuickUnionUF.union(getLinearAddress(row, column), getLinearAddress(row2, column2));
        }
    }

    private int getLinearAddress(int row, int column)
    {
        return (row * size) + column;
    }

    private int getTopPseudoSiteLinearAddress()
    {
        return size * size;
    }

    private int getBottomPseudoSiteLinearAddress()
    {
        return (size * size) + 1;
    }

    public boolean isOpen(int row, int column)
    {
        if (!validRowColumn(row, column))
        {
            throw new IndexOutOfBoundsException("Row or column is out of bounds");
        }

        return isOpen[row][column];
    }

    public boolean isFull(int row, int column)
    {
        if (!validRowColumn(row, column))
        {
            throw new IndexOutOfBoundsException("Row or column is out of bounds");
        }

        return isOpen(row, column) &&
            weightedQuickUnionUF.connected(getLinearAddress(row, column), getTopPseudoSiteLinearAddress());
    }

    public boolean percolates()
    {
        return weightedQuickUnionUF.connected(getBottomPseudoSiteLinearAddress(), getTopPseudoSiteLinearAddress());
    }

    private boolean validRowColumn(int row, int column)
    {
        return (row >= 0) && (row < size) && (column >= 0) && (column < size);
    }
}
