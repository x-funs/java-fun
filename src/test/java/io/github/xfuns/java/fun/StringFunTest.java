package io.github.xfuns.java.fun;

import org.junit.Assert;
import org.junit.Test;

public class StringFunTest {

    @Test
    public void isString() {
        String a = "";
        String b = null;
        Integer c = 1;
        int d = 3;
        Assert.assertTrue(StringFun.isString(a));
        Assert.assertFalse(StringFun.isString(b));
        Assert.assertFalse(StringFun.isString(c));
        Assert.assertFalse(StringFun.isString(d));
    }
}
