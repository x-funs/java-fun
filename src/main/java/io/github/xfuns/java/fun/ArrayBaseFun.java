package io.github.xfuns.java.fun;

/**
 * ArrayBaseFun
 *
 * @author smallmenu
 */
public class ArrayBaseFun {
    /**
     * boolean[]
     */
    public static final boolean[] EMPTY_BOOLEAN = new boolean[0];

    /**
     * Boolean[]
     */
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT = new Boolean[0];

    /**
     * byte[]
     */
    public static final byte[] EMPTY_BYTE = new byte[0];

    /**
     * Byte[]
     */
    public static final Byte[] EMPTY_BYTE_OBJECT = new Byte[0];

    /**
     * char[]
     */
    public static final char[] EMPTY_CHAR = new char[0];

    /**
     * Character[]
     */
    public static final Character[] EMPTY_CHARACTER_OBJECT = new Character[0];

    /**
     * double[]
     */
    public static final double[] EMPTY_DOUBLE = new double[0];

    /**
     * Double[]
     */
    public static final Double[] EMPTY_DOUBLE_OBJECT = new Double[0];

    /**
     * String[]
     */
    public static final String[] EMPTY_STRING = new String[0];

    /**
     * float[]
     */
    public static final float[] EMPTY_FLOAT = new float[0];

    /**
     * Float[]
     */
    public static final Float[] EMPTY_FLOAT_OBJECT = new Float[0];

    /**
     * int[]
     */
    public static final int[] EMPTY_INT = new int[0];

    /**
     * Integer[]
     */
    public static final Integer[] EMPTY_INTEGER_OBJECT = new Integer[0];

    /**
     * long[]
     */
    public static final long[] EMPTY_LONG = new long[0];

    /**
     * Long[]
     */
    public static final Long[] EMPTY_LONG_OBJECT = new Long[0];

    /**
     * Class[]
     */
    public static final Class<?>[] EMPTY_CLASS = new Class[0];

    /**
     * Object[]
     */
    public static final Object[] EMPTY_OBJECT = new Object[0];

    /**
     * short[]
     */
    public static final short[] EMPTY_SHORT = new short[0];

    /**
     * Short[]
     */
    public static final Short[] EMPTY_SHORT_OBJECT = new Short[0];

    /**
     * INDEX_NOT_FOUND
     */
    public static final int INDEX_NOT_FOUND = -1;

    /**
     * 禁止实例化
     */
    protected ArrayBaseFun() {
        throw new AssertionError();
    }

    /**
     * 检测数组中是否包含元素
     *
     * @param array 数组
     * @param value 被检查的元素
     * @return boolean
     */
    public static boolean contains(byte[] array, byte value) {
        if (array != null) {
            return indexOf(array, value) > INDEX_NOT_FOUND;
        }

        return false;
    }

    /**
     * 检测数组中是否包含元素
     *
     * @param array 数组
     * @param value 被检查的元素
     * @return boolean
     */
    public static boolean contains(char[] array, char value) {
        if (array != null) {
            return indexOf(array, value) > INDEX_NOT_FOUND;
        }

        return false;
    }

    /**
     * 检测数组中是否包含元素
     *
     * @param array 数组
     * @param value 被检查的元素
     * @return boolean
     */
    public static boolean contains(int[] array, int value) {
        if (array != null) {
            return indexOf(array, value) > INDEX_NOT_FOUND;
        }

        return false;
    }

    /**
     * 检测数组中是否包含元素
     *
     * @param array 数组
     * @param value 被检查的元素
     * @return boolean
     */
    public static boolean contains(long[] array, long value) {
        if (array != null) {
            return indexOf(array, value) > INDEX_NOT_FOUND;
        }

        return false;
    }

    /**
     * 检测数组中是否包含元素
     *
     * @param array 数组
     * @param value 被检查的元素
     * @return boolean
     */
    public static boolean contains(float[] array, float value) {
        if (array != null) {
            return indexOf(array, value) > INDEX_NOT_FOUND;
        }

        return false;
    }

    /**
     * 检测数组中是否包含元素
     *
     * @param array 数组
     * @param value 被检查的元素
     * @return boolean
     */
    public static boolean contains(double[] array, double value) {
        if (array != null) {
            return indexOf(array, value) > INDEX_NOT_FOUND;
        }

        return false;
    }

    /**
     * 检测数组中是否包含元素
     *
     * @param array 数组
     * @param value 被检查的元素
     * @return boolean
     */
    public static boolean contains(short[] array, short value) {
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
     * @return int
     */
    public static int indexOf(boolean[] array, boolean value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @return int
     */
    public static int indexOf(byte[] array, byte value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @return int
     */
    public static int indexOf(char[] array, char value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @return int
     */
    public static int indexOf(double[] array, double value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (Double.doubleToLongBits(value) == Double.doubleToRawLongBits(array[i])) {
                    return i;
                }
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @return int
     */
    public static int indexOf(float[] array, float value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (Float.floatToIntBits(value) == Float.floatToIntBits(array[i])) {
                    return i;
                }
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @return int
     */
    public static int indexOf(int[] array, int value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @return int
     */
    public static int indexOf(long[] array, long value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @return int
     */
    public static int indexOf(short[] array, short value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }

        return INDEX_NOT_FOUND;
    }
}
