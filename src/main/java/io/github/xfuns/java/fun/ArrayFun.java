package io.github.xfuns.java.fun;

import io.github.xfuns.java.Fun;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * ArrayFun
 *
 * @author smallmenu
 */
public class ArrayFun extends ArrayBaseFun {
    private ArrayFun() {
        throw new AssertionError();
    }

    /**
     * 检测对象是否为数组对象
     *
     * @param obj 对象
     * @return boolean
     */
    public static boolean isArray(Object obj) {
        if (null == obj) {
            return false;
        }

        return obj.getClass().isArray();
    }

    /**
     * 获取数组长度，null 返回 0
     *
     * @param array 对象
     * @return int
     * @throws IllegalArgumentException 如果参数不为数组，抛出异常
     */
    public static int length(Object array) throws IllegalArgumentException {
        if (null == array) {
            return 0;
        }

        return Array.getLength(array);
    }

    /**
     * 数组中是否包含元素
     *
     * @param <T>   数组元素类型
     * @param array 数组
     * @param value 被检查的元素
     * @return 是否包含
     */
    public static <T> boolean contains(T[] array, T value) {
        if (array != null) {
            return indexOf(array, value) > INDEX_NOT_FOUND;
        }

        return false;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @param <T>   数组范型
     * @return int
     */
    public static <T> int indexOf(T[] array, Object value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (ObjectFun.equal(value, array[i])) {
                    return i;
                }
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 字符串数组转整型数组，忽略错误的值
     *
     * @param strings 字符串数组
     * @return int []
     */
    public static int[] stringToIntArray(String[] strings) {
        if (strings == null) {
            return EMPTY_INT;
        }

        int[] numbers = new int[strings.length];
        int index = 0;
        for (String string : strings) {
            try {
                numbers[index] = Integer.parseInt(string);
                index++;
            } catch (NumberFormatException ignored) {
            }
        }

        return Arrays.copyOf(numbers, index);
    }

    /**
     * 字符串数组转整型数组，忽略错误的值
     *
     * @param strings 字符串数组
     * @return long []
     */
    public static long[] stringToLongArray(String[] strings) {
        if (strings == null) {
            return EMPTY_LONG;
        }

        long[] numbers = new long[strings.length];
        int index = 0;
        for (String string : strings) {
            try {
                numbers[index] = Long.parseLong(string);
                index++;
            } catch (NumberFormatException ignored) {
            }
        }

        return Arrays.copyOf(numbers, index);
    }

    @SafeVarargs
    public static <T> boolean hasNull(T... array) {
        if (Fun.empty(array)) {
            for (T element : array) {
                if (null == element) {
                    return true;
                }
            }
        }
        return false;
    }
}
