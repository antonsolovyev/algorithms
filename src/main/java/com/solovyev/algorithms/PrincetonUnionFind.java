package com.solovyev.algorithms;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


/**
 * Created by antonsolovyev on 2/2/16.
 */
public class PrincetonUnionFind implements UnionFind
{
    private final int size;
    private final WeightedQuickUnionUF weightedQuickUnionUF;

    public PrincetonUnionFind(int size)
    {
        this.size = size;
        weightedQuickUnionUF = new WeightedQuickUnionUF(size);
    }

    @Override
    public boolean connected(int p, int q)
    {
        return weightedQuickUnionUF.connected(p, q);
    }

    @Override
    public void union(int p, int q)
    {
        weightedQuickUnionUF.union(p, q);

    }

    @Override
    public int getSize()
    {
        return size;
    }
}
