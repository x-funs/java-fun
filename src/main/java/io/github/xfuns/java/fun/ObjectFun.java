package io.github.xfuns.java.fun;

import java.util.Objects;

/**
 * ObjectFun
 *
 * @author smallmenu
 */
public class ObjectFun {
    private ObjectFun() {
        throw new AssertionError();
    }

    /**
     * 对象比较
     *
     * @param obj1 对象
     * @param obj2 对象
     * @return boolean
     */
    public static boolean equal(Object obj1, Object obj2) {
        return Objects.equals(obj1, obj2);
    }
}
