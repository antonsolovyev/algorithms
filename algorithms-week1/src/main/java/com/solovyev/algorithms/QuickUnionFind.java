package com.solovyev.algorithms;

/**
 * Created by antonsolovyev on 2/2/16.
 */
public class QuickUnionFind implements UnionFind
{
    private final int[] data;

    public QuickUnionFind(int size)
    {
        this.data = new int[size];

        for (int i = 0; i < data.length; i++)
        {
            data[i] = i;
        }
    }

    @Override
    public boolean connected(int p, int q)
    {
        return getRoot(p) == getRoot(q);
    }

    @Override
    public void union(int p, int q)
    {
        data[getRoot(p)] = getRoot(q);
    }

    private int getRoot(int n)
    {
        int res = data[n];
        while (true)
        {
            if (res == data[res])
            {
                return res;
            }
            res = data[res];
        }
    }

    @Override
    public int getSize()
    {
        return data.length;
    }
}
