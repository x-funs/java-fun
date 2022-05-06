package io.github.xfuns.java.fun;

import org.junit.Test;


public class RandomFunTest {

    @Test
    public void testRandomInt() {
        System.out.println(RandomFun.randomInt());
        System.out.println(RandomFun.randomInt(100));
        System.out.println(RandomFun.randomInt(100, 200));
    }

    @Test
    public void testRandomLong() {
        System.out.println(RandomFun.randomLong());
        System.out.println(RandomFun.randomLong(100));
        System.out.println(RandomFun.randomLong(100, 200));
    }

    @Test
    public void testRandomString() {
        System.out.println(RandomFun.randomString(10));
        System.out.println(RandomFun.randomLetter(10));
        System.out.println(RandomFun.randomString(10));
        System.out.println(RandomFun.randomStringExcept(32, "o", "0"));
    }
}
