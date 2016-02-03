package com.solovyev.algorithms;

import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;


@Ignore
public class UnionFindTest
{
    public static final int SIZE = 10000000;
    public static final int TIMES = 1;

    @Test
    public void testQuickFindCorrectness() throws Exception
    {
        UnionFind unionFind = new QuickFind(10);

        testCorrectness(unionFind);
    }

    @Test
    public void testQuickFindSpeed() throws Exception
    {
        UnionFind unionFind = new QuickFind(SIZE);

        System.out.println("time used: " + testSpeed(unionFind, TIMES));
    }

    @Test
    public void testQuickUnionFindCorrectness() throws Exception
    {
        UnionFind unionFind = new QuickUnionFind(10);

        testCorrectness(unionFind);
    }

    @Test
    public void testQuickUnionFindSpeed() throws Exception
    {
        UnionFind unionFind = new QuickUnionFind(SIZE);

        System.out.println("time used: " + testSpeed(unionFind, TIMES));
    }

    @Test
    public void testWeightedQuickUnionFindCorrectness() throws Exception
    {
        UnionFind unionFind = new WeightedQuickUnionFind(10);

        testCorrectness(unionFind);
    }

    @Test
    public void testWeightedQuickUnionFindSpeed() throws Exception
    {
        UnionFind unionFind = new WeightedQuickUnionFind(SIZE);

        System.out.println("time used: " + testSpeed(unionFind, TIMES));
    }

    @Test
    public void testPrincetonUnionFindCorrectness() throws Exception
    {
        UnionFind unionFind = new PrincetonUnionFind(10);

        testCorrectness(unionFind);
    }

    @Test
    public void testPrincetonUnionFindSpeed() throws Exception
    {
        UnionFind unionFind = new PrincetonUnionFind(SIZE);

        System.out.println("time used: " + testSpeed(unionFind, TIMES));
    }

    private void testCorrectness(UnionFind unionFind)
    {
        for (int i = 0; i < 10; i++)
        {
            unionFind.union(1, 2);
            unionFind.union(1, 3);
            unionFind.union(3, 5);
            unionFind.union(8, 9);
            unionFind.union(7, 6);
            unionFind.union(4, 7);
        }

        assertTrue(unionFind.connected(1, 2));
        assertTrue(unionFind.connected(1, 3));
        assertTrue(unionFind.connected(3, 5));
        assertTrue(unionFind.connected(1, 5));
        assertTrue(unionFind.connected(5, 1));
        assertTrue(unionFind.connected(7, 6));
        assertTrue(unionFind.connected(4, 7));
        assertTrue(unionFind.connected(4, 6));
        assertFalse(unionFind.connected(4, 1));
        assertFalse(unionFind.connected(1, 4));
        assertFalse(unionFind.connected(5, 7));
        assertFalse(unionFind.connected(1, 7));
    }

    private long testSpeed(UnionFind unionFind, long times)
    {
        Random random = new Random(System.currentTimeMillis());

        long start = System.currentTimeMillis();
        for (int n = 0; n < times; n++)
        {
            for (int i = 0; i < unionFind.getSize(); i++)
            {
                unionFind.union(random.nextInt(unionFind.getSize()), random.nextInt(unionFind.getSize()));
            }

            for (int i = 0; i < unionFind.getSize(); i++)
            {
                unionFind.connected(random.nextInt(unionFind.getSize()), random.nextInt(unionFind.getSize()));
            }
        }

        return System.currentTimeMillis() - start;
    }
}
