package io.github.xfuns.java.fun;

import org.junit.Assert;
import org.junit.Test;

public class ArrayFunTest {
    @Test
    public void testLength() {
        String[] strs1 = {"a", "b"};
        String[] strs2 = null;
        Assert.assertEquals(2, ArrayFun.length(strs1));
        Assert.assertEquals(0, ArrayFun.length(strs2));
    }

    @Test
    public void testIsArray() {
        int[] ints = {};
        Integer[] integers = new Integer[0];
        String[] strs1 = {""};
        String[] strs2 = null;

        Assert.assertTrue(ArrayFun.isArray(ints));
        Assert.assertTrue(ArrayFun.isArray(integers));
        Assert.assertTrue(ArrayFun.isArray(strs1));
        Assert.assertFalse(ArrayFun.isArray(strs2));
    }

    @Test
    public void testContains() {
        String[] strs1 = {"c", "a", "b"};
        String[] strs2 = null;
        int[] ints = {2, 3, 5};
        Assert.assertTrue(ArrayFun.contains(strs1, "a"));
        Assert.assertFalse(ArrayFun.contains(strs1, null));
        Assert.assertFalse(ArrayFun.contains(strs2, "a"));
        Assert.assertTrue(ArrayFun.contains(ints, 3));
    }

    @Test
    public void testIndexOf() {
        String[] str1 = null;
        String[] str2 = {"bb", "aa", "cc"};
        int[] ints = {5, 3, 7, 9};

        Assert.assertEquals(-1, ArrayFun.indexOf(str1, "aa"));
        Assert.assertEquals(1, ArrayFun.indexOf(str2, "aa"));
        Assert.assertEquals(2, ArrayFun.indexOf(ints, 7));
    }
}
