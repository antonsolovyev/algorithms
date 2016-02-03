package com.solovyev.algorithms;

public class WeightedQuickUnionFind implements UnionFind
{
    private final int[] data;
    private final int[] weight;

    public WeightedQuickUnionFind(int size)
    {
        this.data = new int[size];
        this.weight = new int[size];

        for (int i = 0; i < data.length; i++)
        {
            data[i] = i;
            weight[i] = 1;
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
        int rootP = getRoot(p);
        int rootQ = getRoot(q);

        if (rootP == rootQ)
        {
            return;
        }

        if (weight[rootQ] >= weight[rootP])
        {
            data[rootP] = rootQ;
            weight[rootQ] += weight[rootP];
        }
        else
        {
            data[rootQ] = rootP;
            weight[rootP] += weight[rootQ];
        }
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
