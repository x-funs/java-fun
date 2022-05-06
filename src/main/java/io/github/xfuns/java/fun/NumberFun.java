package io.github.xfuns.java.fun;

import io.github.xfuns.java.Fun;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class NumberFun {

    /**
     * 默认除法运算精度
     */
    private static final int DEFAULT_DIV_SCALE = 10;

    /**
     * 0-20对应的阶乘，超过20的阶乘会超过Long.MAX_VALUE
     */
    private static final long[] FACTORIALS = new long[]{
            1L, 1L, 2L, 6L, 24L, 120L, 720L, 5040L, 40320L, 362880L, 3628800L, 39916800L, 479001600L, 6227020800L,
            87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L,
            2432902008176640000L};

    /**
     * 提供精确的加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 和
     */
    public static double add(float v1, float v2) {
        return add(Float.toString(v1), Float.toString(v2)).doubleValue();
    }

    /**
     * 提供精确的加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 和
     */
    public static double add(double v1, double v2) {
        return add(Double.toString(v1), Double.toString(v2)).doubleValue();
    }

    /**
     * 提供精确的加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 和
     * @since 3.1.1
     */
    public static double add(Double v1, Double v2) {
        return add(v1, (Number) v2).doubleValue();
    }

    /**
     * 提供精确的加法运算
     * 如果传入多个值为null或者空，则返回0
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 和
     */
    public static BigDecimal add(Number v1, Number v2) {
        return add(new Number[]{v1, v2});
    }

    /**
     * 提供精确的加法运算
     * 如果传入多个值为null或者空，则返回0
     *
     * @param values 多个被加值
     * @return 和
     * @since 4.0.0
     */
    public static BigDecimal add(Number... values) {
        if (Fun.empty(values)) {
            return BigDecimal.ZERO;
        }

        Number value = values[0];
        BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value.toString());
        for (int i = 1; i < values.length; i++) {
            value = values[i];
            if (null != value) {
                result = result.add(new BigDecimal(value.toString()));
            }
        }
        return result;
    }

    /**
     * 提供精确的加法运算
     * 如果传入多个值为null或者空，则返回0
     *
     * @param values 多个被加值
     * @return 和
     * @since 4.0.0
     */
    public static BigDecimal add(String... values) {
        if (Fun.empty(values)) {
            return BigDecimal.ZERO;
        }

        String value = values[0];
        BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);
        for (int i = 1; i < values.length; i++) {
            value = values[i];
            if (null != value) {
                result = result.add(new BigDecimal(value));
            }
        }
        return result;
    }

    /**
     * 提供精确的加法运算
     * 如果传入多个值为null或者空，则返回0
     *
     * @param values 多个被加值
     * @return 和
     * @since 4.0.0
     */
    public static BigDecimal add(BigDecimal... values) {
        if (Fun.empty(values)) {
            return BigDecimal.ZERO;
        }

        BigDecimal value = values[0];
        BigDecimal result = null == value ? BigDecimal.ZERO : value;
        for (int i = 1; i < values.length; i++) {
            value = values[i];
            if (null != value) {
                result = result.add(value);
            }
        }
        return result;
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 差
     */
    public static double sub(float v1, float v2) {
        return sub(Float.toString(v1), Float.toString(v2)).doubleValue();
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 差
     */
    public static double sub(double v1, double v2) {
        return sub(Double.toString(v1), Double.toString(v2)).doubleValue();
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 差
     */
    public static double sub(Double v1, Double v2) {
        //noinspection RedundantCast
        return sub((Number) v1, (Number) v2).doubleValue();
    }

    /**
     * 提供精确的减法运算
     * 如果传入多个值为null或者空，则返回0
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 差
     */
    public static BigDecimal sub(Number v1, Number v2) {
        return sub(new Number[]{v1, v2});
    }

    /**
     * 提供精确的减法运算
     * 如果传入多个值为null或者空，则返回0
     *
     * @param values 多个被减值
     * @return 差
     * @since 4.0.0
     */
    public static BigDecimal sub(Number... values) {
        if (Fun.empty(values)) {
            return BigDecimal.ZERO;
        }

        Number value = values[0];
        BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value.toString());
        for (int i = 1; i < values.length; i++) {
            value = values[i];
            if (null != value) {
                result = result.subtract(new BigDecimal(value.toString()));
            }
        }
        return result;
    }

    /**
     * 提供精确的减法运算
     * 如果传入多个值为null或者空，则返回0
     *
     * @param values 多个被减值
     * @return 差
     * @since 4.0.0
     */
    public static BigDecimal sub(String... values) {
        if (Fun.empty(values)) {
            return BigDecimal.ZERO;
        }

        String value = values[0];
        BigDecimal result = null == value ? BigDecimal.ZERO : new BigDecimal(value);
        for (int i = 1; i < values.length; i++) {
            value = values[i];
            if (null != value) {
                result = result.subtract(new BigDecimal(value));
            }
        }
        return result;
    }

    /**
     * 提供精确的减法运算
     * 如果传入多个值为null或者空，则返回0
     *
     * @param values 多个被减值
     * @return 差
     * @since 4.0.0
     */
    public static BigDecimal sub(BigDecimal... values) {
        if (Fun.empty(values)) {
            return BigDecimal.ZERO;
        }

        BigDecimal value = values[0];
        BigDecimal result = null == value ? BigDecimal.ZERO : value;
        for (int i = 1; i < values.length; i++) {
            value = values[i];
            if (null != value) {
                result = result.subtract(value);
            }
        }
        return result;
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 积
     */
    public static double mul(float v1, float v2) {
        return mul(Float.toString(v1), Float.toString(v2)).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 积
     */
    public static double mul(double v1, double v2) {
        return mul(Double.toString(v1), Double.toString(v2)).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     * 如果传入多个值为null或者空，则返回0
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 积
     */
    public static double mul(Double v1, Double v2) {
        //noinspection RedundantCast
        return mul((Number) v1, (Number) v2).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     * 如果传入多个值为null或者空，则返回0
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 积
     */
    public static BigDecimal mul(Number v1, Number v2) {
        return mul(new Number[]{v1, v2});
    }

    /**
     * 提供精确的乘法运算
     * 如果传入多个值为null或者空，则返回0
     *
     * @param values 多个被乘值
     * @return 积
     * @since 4.0.0
     */
    public static BigDecimal mul(Number... values) {
        if (Fun.empty(values) || ArrayFun.hasNull(values)) {
            return BigDecimal.ZERO;
        }

        Number value = values[0];
        BigDecimal result = new BigDecimal(value.toString());
        for (int i = 1; i < values.length; i++) {
            value = values[i];
            result = result.multiply(new BigDecimal(value.toString()));
        }
        return result;
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 积
     * @since 3.0.8
     */
    public static BigDecimal mul(String v1, String v2) {
        return mul(new BigDecimal(v1), new BigDecimal(v2));
    }

    /**
     * 提供精确的乘法运算
     * 如果传入多个值为null或者空，则返回0
     *
     * @param values 多个被乘值
     * @return 积
     * @since 4.0.0
     */
    public static BigDecimal mul(String... values) {
        if (Fun.empty(values) || ArrayFun.hasNull(values)) {
            return BigDecimal.ZERO;
        }

        BigDecimal result = new BigDecimal(values[0]);
        for (int i = 1; i < values.length; i++) {
            result = result.multiply(new BigDecimal(values[i]));
        }

        return result;
    }

    /**
     * 提供精确的乘法运算
     * 如果传入多个值为null或者空，则返回0
     *
     * @param values 多个被乘值
     * @return 积
     * @since 4.0.0
     */
    public static BigDecimal mul(BigDecimal... values) {
        if (Fun.empty(values) || ArrayFun.hasNull(values)) {
            return BigDecimal.ZERO;
        }

        BigDecimal result = values[0];
        for (int i = 1; i < values.length; i++) {
            result = result.multiply(values[i]);
        }
        return result;
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况的时候,精确到小数点后10位,后面的四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(float v1, float v2) {
        return div(v1, v2, DEFAULT_DIV_SCALE);
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况的时候,精确到小数点后10位,后面的四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEFAULT_DIV_SCALE);
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况的时候,精确到小数点后10位,后面的四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(Double v1, Double v2) {
        return div(v1, v2, DEFAULT_DIV_SCALE);
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况的时候,精确到小数点后10位,后面的四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     * @since 3.1.0
     */
    public static BigDecimal div(Number v1, Number v2) {
        return div(v1, v2, DEFAULT_DIV_SCALE);
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况的时候,精确到小数点后10位,后面的四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static BigDecimal div(String v1, String v2) {
        return div(v1, v2, DEFAULT_DIV_SCALE);
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况时,由scale指定精确度,后面的四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 精确度，如果为负值，取绝对值
     * @return 两个参数的商
     */
    public static double div(float v1, float v2, int scale) {
        return div(v1, v2, scale, RoundingMode.HALF_UP);
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况时,由scale指定精确度,后面的四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 精确度，如果为负值，取绝对值
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        return div(v1, v2, scale, RoundingMode.HALF_UP);
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况时,由scale指定精确度,后面的四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 精确度，如果为负值，取绝对值
     * @return 两个参数的商
     */
    public static double div(Double v1, Double v2, int scale) {
        return div(v1, v2, scale, RoundingMode.HALF_UP);
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况时,由scale指定精确度,后面的四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 精确度，如果为负值，取绝对值
     * @return 两个参数的商
     * @since 3.1.0
     */
    public static BigDecimal div(Number v1, Number v2, int scale) {
        return div(v1, v2, scale, RoundingMode.HALF_UP);
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况时,由scale指定精确度,后面的四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 精确度，如果为负值，取绝对值
     * @return 两个参数的商
     */
    public static BigDecimal div(String v1, String v2, int scale) {
        return div(v1, v2, scale, RoundingMode.HALF_UP);
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况时,由scale指定精确度
     *
     * @param v1           被除数
     * @param v2           除数
     * @param scale        精确度，如果为负值，取绝对值
     * @param roundingMode 保留小数的模式 {@link RoundingMode}
     * @return 两个参数的商
     */
    public static double div(float v1, float v2, int scale, RoundingMode roundingMode) {
        return div(Float.toString(v1), Float.toString(v2), scale, roundingMode).doubleValue();
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况时,由scale指定精确度
     *
     * @param v1           被除数
     * @param v2           除数
     * @param scale        精确度，如果为负值，取绝对值
     * @param roundingMode 保留小数的模式 {@link RoundingMode}
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale, RoundingMode roundingMode) {
        return div(Double.toString(v1), Double.toString(v2), scale, roundingMode).doubleValue();
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况时,由scale指定精确度
     *
     * @param v1           被除数
     * @param v2           除数
     * @param scale        精确度，如果为负值，取绝对值
     * @param roundingMode 保留小数的模式 {@link RoundingMode}
     * @return 两个参数的商
     */
    public static double div(Double v1, Double v2, int scale, RoundingMode roundingMode) {
        //noinspection RedundantCast
        return div((Number) v1, (Number) v2, scale, roundingMode).doubleValue();
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况时,由scale指定精确度
     *
     * @param v1           被除数
     * @param v2           除数
     * @param scale        精确度，如果为负值，取绝对值
     * @param roundingMode 保留小数的模式 {@link RoundingMode}
     * @return 两个参数的商
     * @since 3.1.0
     */
    public static BigDecimal div(Number v1, Number v2, int scale, RoundingMode roundingMode) {
        return div(v1.toString(), v2.toString(), scale, roundingMode);
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况时,由scale指定精确度
     *
     * @param v1           被除数
     * @param v2           除数
     * @param scale        精确度，如果为负值，取绝对值
     * @param roundingMode 保留小数的模式 {@link RoundingMode}
     * @return 两个参数的商
     */
    public static BigDecimal div(String v1, String v2, int scale, RoundingMode roundingMode) {
        return div(new BigDecimal(v1), new BigDecimal(v2), scale, roundingMode);
    }

    /**
     * 提供(相对)精确的除法运算,当发生除不尽的情况时,由scale指定精确度
     *
     * @param v1           被除数
     * @param v2           除数
     * @param scale        精确度，如果为负值，取绝对值
     * @param roundingMode 保留小数的模式 {@link RoundingMode}
     * @return 两个参数的商
     * @since 3.0.9
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale, RoundingMode roundingMode) {
        assert v2 != null;
        if (null == v1) {
            return BigDecimal.ZERO;
        }
        if (scale < 0) {
            scale = -scale;
        }
        return v1.divide(v2, scale, roundingMode);
    }

    /**
     * 从0开始给定范围内的整数列表，步进为1
     *
     * @param stop 结束（包含）
     * @return 整数列表
     * @since 3.3.1
     */
    public static int[] range(int stop) {
        return range(0, stop);
    }

    /**
     * 给定范围内的整数列表，步进为1
     *
     * @param start 开始（包含）
     * @param stop  结束（包含）
     * @return 整数列表
     */
    public static int[] range(int start, int stop) {
        return range(start, stop, 1);
    }

    /**
     * 给定范围内的整数列表
     *
     * @param start 开始（包含）
     * @param stop  结束（包含）
     * @param step  步进
     * @return 整数列表
     */
    public static int[] range(int start, int stop, int step) {
        if (start < stop) {
            step = Math.abs(step);
        } else if (start > stop) {
            step = -Math.abs(step);
        } else {// start == end
            return new int[]{start};
        }

        int size = Math.abs((stop - start) / step) + 1;
        int[] values = new int[size];
        int index = 0;
        for (int i = start; (step > 0) ? i <= stop : i >= stop; i += step) {
            values[index] = i;
            index++;
        }
        return values;
    }

    /**
     * 计算范围阶乘
     * <p>
     * factorial(start, end) = start * (start - 1) * ... * (end - 1)
     * </p>
     *
     * @param start 阶乘起始（包含）
     * @param end   阶乘结束，必须小于起始（不包括）
     * @return 结果
     * @since 4.1.0
     */
    public static long factorial(long start, long end) {
        // 负数没有阶乘
        if (start < 0 || end < 0) {
            throw new IllegalArgumentException("Factorial start and end both must be >= 0");
        }
        if (0L == start || start == end) {
            return 1L;
        }
        if (start < end) {
            return 0L;
        }
        return factorialMultiplyAndCheck(start, factorial(start - 1, end));
    }

    /**
     * 计算范围阶乘中校验中间的计算是否存在溢出，factorial提前做了负数和0的校验，因此这里没有校验数字的正负
     *
     * @param a 乘数
     * @param b 被乘数
     * @return 如果 a * b的结果没有溢出直接返回，否则抛出异常
     */
    private static long factorialMultiplyAndCheck(long a, long b) {
        if (a <= Long.MAX_VALUE / b) {
            return a * b;
        }
        throw new IllegalArgumentException("Overflow in multiplication");
    }

    /**
     * 计算阶乘
     * <p>
     * n! = n * (n-1) * ... * 2 * 1
     * </p>
     *
     * @param n 阶乘起始
     * @return 结果
     */
    public static long factorial(long n) {
        if (n < 0 || n > 20) {
            throw new IllegalArgumentException("Factorial must have n >= 0 and n <= 20");
        }
        return FACTORIALS[(int) n];
    }

    /**
     * 最大公约数
     *
     * @param m 第一个值
     * @param n 第二个值
     * @return 最大公约数
     */
    public static int divisor(int m, int n) {
        while (m % n != 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        return n;
    }

    /**
     * 最小公倍数
     *
     * @param m 第一个值
     * @param n 第二个值
     * @return 最小公倍数
     */
    public static int multiple(int m, int n) {
        return m * n / divisor(m, n);
    }

    /**
     * 格式化百分比，小数采用四舍五入方式
     *
     * @param number 值
     * @param scale  保留小数位数
     * @return 百分比
     * @since 3.2.3
     */
    public static String formatPercent(double number, int scale) {
        final NumberFormat format = NumberFormat.getPercentInstance();
        format.setMaximumFractionDigits(scale);
        return format.format(number);
    }

    /**
     * 按照给定的算术运算符做计算
     *
     * @param firstValue  第一个值
     * @param secondValue 第二个值
     * @param ops         算数符，只支持'+'、'-'、'*'、'/'
     * @return 结果
     */
    public static BigDecimal calculate(String firstValue, String secondValue, char ops) {
        BigDecimal result;
        switch (ops) {
            case '+':
                result = add(firstValue, secondValue);
                break;
            case '-':
                result = sub(firstValue, secondValue);
                break;
            case '*':
                result = mul(firstValue, secondValue);
                break;
            case '/':
                result = div(firstValue, secondValue);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + ops);
        }
        return result;
    }
}
