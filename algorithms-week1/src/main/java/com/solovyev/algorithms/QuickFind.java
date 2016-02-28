package com.solovyev.algorithms;

import java.util.Arrays;


public class QuickFind implements UnionFind
{
    private final int[] data;

    public QuickFind(int size)
    {
        data = new int[size];
        for (int i = 0; i < data.length; i++)
        {
            data[i] = i;
        }
    }

    @Override
    public boolean connected(int p, int q)
    {
        if ((p > data.length) || (q > data.length))
        {
            throw new IllegalArgumentException();
        }

        return data[p] == data[q];
    }

    @Override
    public void union(int p, int q)
    {
        if ((p > data.length) || (q > data.length))
        {
            throw new IllegalArgumentException();
        }

        int pValue = data[p];
        for (int i = 0; i < data.length; i++)
        {
            if (data[i] == pValue)
            {
                data[i] = data[q];
            }
        }
    }

    @Override
    public int getSize()
    {
        return data.length;
    }
}
