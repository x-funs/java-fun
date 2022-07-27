package io.github.xfuns.java;

import io.github.xfuns.java.constant.DatePattern;
import io.github.xfuns.java.constant.RegexPattern;
import io.github.xfuns.java.crypto.Hashids;
import io.github.xfuns.java.date.Strtotime;
import io.github.xfuns.java.expection.FunException;
import io.github.xfuns.java.fun.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Java With Fun(ctions)
 *
 * @author smallmenu
 */
public class Fun extends FunBase {
    /**
     * 禁止实例化
     */
    private Fun() {
        throw new AssertionError();
    }

    /**
     * 返回当前 Unix 时间戳（秒）
     *
     * @return long
     */
    public static long timestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 返回当前 Unix 时间戳
     *
     * @param milliseconds 是否返回毫秒
     * @return long
     */
    public static long timestamp(boolean milliseconds) {
        if (milliseconds) {
            return System.currentTimeMillis();
        }
        return timestamp();
    }

    /**
     * 返回系统空闲堆内存，单位 MB
     *
     * @return long
     */
    public static long freeMemoryMb() {
        return freeMemory(DataSizeFun.MB);
    }

    /**
     * 返回系统最大堆内存（-Xmx），单位 MB
     *
     * @return long
     */
    public static long maxMemoryMb() {
        return maxMemory(DataSizeFun.MB);
    }

    /**
     * 返回系统当前已使用堆内存，单位 MB
     *
     * @return long
     */
    public static long usedMemoryMb() {
        return usedMemory(DataSizeFun.MB);
    }

    /**
     * 返回系统当前已申请堆内存，单位 MB
     *
     * @return long
     */
    public static long totalMemoryMb() {
        return totalMemory(DataSizeFun.MB);
    }

    /**
     * 字符串转换为字节数组 Bytes
     *
     * @param str 字符串
     * @return byte[]
     */
    public static byte[] bytes(CharSequence str) {
        return bytes(str, Charset.defaultCharset());
    }

    /**
     * 字符串转换为字节数组 Bytes
     *
     * @param str     字符串
     * @param charset Charset 字符集
     * @return byte[]
     */
    public static byte[] bytes(CharSequence str, Charset charset) {
        if (str == null) {
            return null;
        }

        if (null == charset) {
            return str.toString().getBytes();
        }

        return str.toString().getBytes(charset);
    }

    /**
     * 字符串获取 Bytes
     *
     * @param str     字符串
     * @param charset 字符串字符集
     * @return byte[]
     */
    public static byte[] bytes(CharSequence str, String charset) {
        return bytes(str, blank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    /**
     * 检测是否为空，（可匹配单一对象）
     * <p>
     * 如果对象为 null，返回 true
     * 如果对象为数组，判断数组长度是否为 0
     * 如果对象为字符串，判断字符串长度是否为 0
     * 如果对象为数值类型，判断是否为 0
     * 如果对象为布尔类型，判断是否为 false
     * 其他情况，默认返回 false
     *
     * @param object 数组
     * @return boolean
     */
    public static boolean empty(Object object) {
        if (object != null) {
            if (ArrayFun.isArray(object)) {
                return 0 == Array.getLength(object);
            }

            if (StringFun.isString(object)) {
                return object.toString().length() == 0;
            }

            if (object instanceof Integer) {
                return 0 == (int) object;
            }

            if (object instanceof Long) {
                return 0 == (long) object;
            }

            if (object instanceof Short) {
                return 0 == (short) object;
            }

            if (object instanceof Boolean) {
                return !((boolean) object);
            }

            return false;
        }

        return true;
    }

    /**
     * 检测字符串是否为空
     * <p>
     * 空包含 null、空字符串 ""
     *
     * @param str 待检测字符串
     * @return boolean
     */
    public static boolean empty(final CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 检测数组为空（范型）
     *
     * @param array 数组
     * @param <T>   T
     * @return boolean
     */
    public static <T> boolean empty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 检测 Map 是否为空
     *
     * @param map 列表
     * @return boolean
     */
    public static boolean empty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 检测 Collection 是否为空
     *
     * @param collection 集合
     * @return boolean
     */
    public static boolean empty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 检测字符串列表，是否包含有空值，包含任意一个返回 true
     *
     * @param strs 字符串列表
     * @return boolean
     */
    public static boolean emptyAny(CharSequence... strs) {
        if (empty(strs)) {
            return true;
        }

        for (CharSequence str : strs) {
            if (empty(str)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 检测字符串列表，是否全部为空
     *
     * @param strs 字符串列表
     * @return boolean
     */
    public static boolean emptyAll(final CharSequence... strs) {
        if (empty(strs)) {
            return true;
        }

        for (CharSequence str : strs) {
            if (!empty(str)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 检测字符串是否为空白
     * <p>
     * 空白包含：null、""、" "、以及不可见字符如：缩进符
     *
     * @param str 待检测字符串
     * @return boolean
     */
    public static boolean blank(final CharSequence str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!CharFun.isBlankChar(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 检测字符串列表，是否包含空白，包含任意一个空白返回 true
     * <p>
     * 空白包含：null、""、" "、以及不可见字符如：缩进符
     *
     * @param strs 字符串列表
     * @return boolean
     */
    public static boolean blankAny(CharSequence... strs) {
        if (empty(strs)) {
            return true;
        }

        for (CharSequence str : strs) {
            if (blank(str)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 检测字符串列表，是否全部为空白
     *
     * @param strs 字符串列表
     * @return boolean
     */
    public static boolean blankAll(final CharSequence... strs) {
        if (empty(strs)) {
            return true;
        }

        for (CharSequence str : strs) {
            if (!blank(str)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 字符串是否匹配正则表达式
     *
     * @param regex   正则表达式字符串
     * @param content 字符串
     * @return boolean
     */
    public static boolean matches(String regex, CharSequence content) {
        if (content == null) {
            return false;
        }

        if (empty(regex)) {
            return true;
        }

        return content.toString().matches(regex);
    }

    /**
     * 字符串是否匹配正则表达式
     *
     * @param pattern 正则表达式
     * @param content 字符串
     * @return boolean
     */
    public static boolean matches(Pattern pattern, CharSequence content) {
        if (content == null || pattern == null) {
            return false;
        }

        return pattern.matcher(content).matches();
    }

    /**
     * 除去字符串头尾部的空白，如果字符串是 null，依然返回 null
     * <p>
     * 和原生的 String.trim 不同，此方法使用 CharUtils.isBlankChar() 来判定空白
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String trim(final CharSequence str) {
        return (null == str) ? null : StringFun.trim(str);
    }

    /**
     * 除去字符串左侧的空白
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String ltrim(final CharSequence str) {
        return (null == str) ? null : StringFun.trim(str, null, -1);
    }

    /**
     * 除去字符串右侧的空白
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String rtrim(final CharSequence str) {
        return (null == str) ? null : StringFun.trim(str, null, 1);
    }

    /**
     * 除去字符串两侧的指定字符串集合
     * <p>
     * 如果 trimStr 为空，则默认使用空白符号
     *
     * @param str     待处理字符串
     * @param trimStr 需要处理的字符串集合
     * @return String
     */
    public static String trim(final CharSequence str, final CharSequence trimStr) {
        return (null == str) ? null : StringFun.trim(str, trimStr, 0);
    }


    /**
     * 除去字符串头尾部的空白，并处理 null 值
     * <p>
     * 如果是 null，返回 ""
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String trimToEmpty(final CharSequence str) {
        return str == null ? StringFun.EMPTY : trim(str);
    }

    /**
     * 除去字符串头尾部的空白，并处理 null 值。如果结果为 empty，则返回指定的默认值
     *
     * @param str        待处理字符串
     * @param defaultStr 指定默认值
     * @return String
     */
    public static String trimToDefault(final CharSequence str, String defaultStr) {
        if (str == null) {
            return defaultStr;
        }

        String s = trim(str);
        return empty(s) ? defaultStr : s;
    }

    /**
     * 除去字符串头尾部的空白，并处理 null 和空字符串 ""
     * <p>
     * 如果字符串是 null 或者 "" ，返回  null
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String trimToNull(final CharSequence str) {
        final String trimStr = trim(str);
        return StringFun.EMPTY.equals(trimStr) ? null : trimStr;
    }

    /**
     * 字符串转整型
     * <p>
     * 如果是 null 或者 "" 或者异常，返回 0
     * 如果是小数，返回 0
     *
     * @param str 字符串
     * @return int
     */
    public static int toInt(final String str) {
        return toInt(toStr(str), 0);
    }

    /**
     * 字符串转整型
     * <p>
     * 如果是 null 或者 "" 或者异常，返回指定的默认值
     *
     * @param str          字符串
     * @param defaultValue 指定默认值
     * @return int
     */
    public static int toInt(final String str, final int defaultValue) {
        if (str == null) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 字符串转浮点型
     * <p>
     * 如果是 null 或者 "" 或者异常，返回 0.0F
     *
     * @param str 字符串
     * @return float
     */
    public static float toFloat(final String str) {
        return toFloat(str, 0.0F);
    }

    /**
     * 字符串转浮点型
     * <p>
     * 如果是 null 或者 "" 或者异常，返回指定默认值
     *
     * @param str          字符串
     * @param defaultValue 指定默认值
     * @return float
     */
    public static float toFloat(final String str, float defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Float.parseFloat(str);
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        }
    }

    /**
     * 字符串转浮点型
     * <p>
     * 如果是 null 或者 "" 或者异常，返回 0.0D
     *
     * @param str 字符串
     * @return double
     */
    public static double toDouble(String str) {
        return toDouble(str, 0.0D);
    }

    /**
     * 字符串转浮点型
     * <p>
     * 如果是 null 或者 "" 或者异常，返回指定默认值
     *
     * @param str          字符串
     * @param defaultValue 指定默认值
     * @return double
     */
    public static double toDouble(String str, double defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException var4) {
                return defaultValue;
            }
        }
    }

    /**
     * 字符串转整型
     * <p>
     * 如果是  null 或者 "" 或异常，返回 0L
     *
     * @param str 字符串
     * @return int
     */
    public static long toLong(final String str) {
        return toLong(str, 0L);
    }

    /**
     * Boolean 转 boolean
     * <p>
     * 如果是  null 返回 false, 如果是 true 返回 true, 如果是 false 返回 false
     *
     * @param bool Boolean
     * @return boolean
     */
    public static boolean toBoolean(final Boolean bool) {
        return bool != null && bool;
    }

    /**
     * int 转 boolean
     *
     * @param value int
     * @return boolean
     */
    public static boolean toBoolean(final int value) {
        return value != 0;
    }

    /**
     * 字符串转整型
     * <p>
     * 如果是  null 或者 "" 或者异常，返回指定默认值
     *
     * @param str          字符串
     * @param defaultValue 指定默认值
     * @return long
     */
    public static long toLong(final String str, final long defaultValue) {
        if (str == null) {
            return defaultValue;
        }

        try {
            return Long.parseLong(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 查找字符串是否包含指定字符串
     *
     * @param str       字符串
     * @param searchStr 查找字符串
     * @return boolean
     */
    public static boolean contains(final CharSequence str, final CharSequence searchStr) {
        if (null == str || null == searchStr) {
            return false;
        }

        return str.toString().contains(searchStr);
    }

    /**
     * 查找字符串是否包含指定字符串，忽略大小写
     *
     * @param str       字符串
     * @param searchStr 查找字符串
     * @return boolean
     */
    public static boolean containsCase(final CharSequence str, final CharSequence searchStr) {
        if (null == str || null == searchStr) {
            return false;
        }

        return str.toString().toLowerCase().contains(searchStr.toString().toLowerCase());
    }

    /**
     * 查找指定字符串，是否包含指定字符列表中的任意一个字符串
     *
     * @param str        指定字符串
     * @param searchStrs 查找字符串列表
     * @return boolean
     */
    public static boolean containsAny(final CharSequence str, final CharSequence... searchStrs) {
        if (empty(str) || empty(searchStrs)) {
            return false;
        }

        for (CharSequence checkStr : searchStrs) {
            if (str.toString().contains(checkStr)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 比较两个字符串
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @return boolean
     */
    public static boolean equals(final CharSequence str1, final CharSequence str2) {
        return StringFun.equals(str1, str2, false);
    }

    /**
     * 比较两个字符串，忽略大小写
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @return boolean
     */
    public static boolean equalsCase(final CharSequence str1, final CharSequence str2) {
        return StringFun.equals(str1, str2, true);
    }

    /**
     * 检测字符串是否以指定字符串开头
     * <p>
     * 如果给定的字符串和开头字符串都为 null 则返回 true ，否则任意一个值为 null 返回 false
     * 如果给定的字符串不为 null，prefix 字符串为 "" 空字符串，返回 true
     *
     * @param str    待检测字符串
     * @param prefix 开头字符串
     * @return boolean
     */
    public static boolean startsWith(final CharSequence str, final CharSequence prefix) {
        return StringFun.startsWith(str, prefix, false);
    }

    /**
     * 检测字符串是否以指定字符串开头，忽略大小写
     *
     * @param str    待检测字符串
     * @param prefix 开头字符串
     * @return boolean
     */
    public static boolean startsWithCase(final CharSequence str, final CharSequence prefix) {
        return StringFun.startsWith(str, prefix, true);
    }

    /**
     * 检测字符串是否以任意指定字符串开头
     *
     * @param str           待检测字符串
     * @param searchStrings 开头字符串数组
     * @return boolean
     */
    public static boolean startsWithAny(final CharSequence str, final CharSequence... searchStrings) {
        if (empty(str) || empty(searchStrings)) {
            return false;
        }

        for (CharSequence searchString : searchStrings) {
            if (startsWith(str, searchString)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 检测字符串是否以指定字符串结尾
     * <p>
     * 如果给定的字符串和开头字符串都为 null 则返回 true ，否则任意一个值为 null 返回 false
     * 如果给定的字符串不为 null，suffix 字符串为 "" 空字符串，返回 true
     *
     * @param str    待检测字符串
     * @param suffix 结尾字符串
     * @return boolean
     */
    public static boolean endsWith(final CharSequence str, final CharSequence suffix) {
        return StringFun.endsWith(str, suffix, false);
    }

    /**
     * 检测字符串是否以指定字符串结尾，忽略大小写
     *
     * @param str    待检测字符串
     * @param suffix 结尾字符串
     * @return boolean
     */
    public static boolean endsWithCase(final CharSequence str, final CharSequence suffix) {
        return StringFun.endsWith(str, suffix, true);
    }

    /**
     * 检测字符串是否以任意指定字符串结尾
     *
     * @param str           待检测字符串
     * @param searchStrings 查找字符串
     * @return boolean
     */
    public static boolean endWithAny(final CharSequence str, final CharSequence... searchStrings) {
        if (empty(str) || empty(searchStrings)) {
            return false;
        }

        for (CharSequence searchString : searchStrings) {
            if (endsWith(str, searchString)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 获取字符串的字符长度，如果为 null 返回 0
     * <p>
     * 使用 String.length 方法， 中英文数字均是 1
     *
     * @param str 字符串
     * @return int
     */
    public static int length(final CharSequence str) {
        return str == null ? 0 : str.length();
    }

    /**
     * 字符串转小写
     *
     * @param str 字符串
     * @return String
     */
    public static String toLowerCase(final CharSequence str) {
        return str == null ? null : str.toString().toLowerCase();
    }

    /**
     * 字符串转大写
     *
     * @param str 字符串
     * @return String
     */
    public static String toUpperCase(final CharSequence str) {
        return str == null ? null : str.toString().toUpperCase();
    }

    /**
     * 截取字符串左侧指定长度的子串
     *
     * @param str    字符串
     * @param length 长度
     * @return String
     */
    public static String left(final String str, final int length) {
        if (str == null) {
            return null;
        }

        if (length < 0) {
            return StringFun.EMPTY;
        }

        if (str.length() <= length) {
            return str;
        }

        return str.substring(0, length);
    }

    /**
     * 截取字符串右侧指定长度的子串
     *
     * @param str    字符串
     * @param length 长度
     * @return String
     */
    public static String right(final String str, final int length) {
        if (str == null) {
            return null;
        }

        if (length < 0) {
            return StringFun.EMPTY;
        }

        if (str.length() <= length) {
            return str;
        }

        return str.substring(str.length() - length);
    }

    /**
     * 查找字符串，并返回索引位置。（从后向前查找）
     *
     * @param str       字符串
     * @param searchStr 查找字符串
     * @return int
     */
    public static int lastIndexOf(final CharSequence str, final CharSequence searchStr) {
        if (str == null || searchStr == null) {
            return StringFun.INDEX_NOT_FOUND;
        }
        return StringFun.lastIndexOf(str, searchStr, str.length());
    }

    /**
     * 查找字符串，并返回索引位置。（从后向前查找）
     *
     * @param str       字符串
     * @param searchStr 查找字符串
     * @param start     起始位置
     * @return int
     */
    public static int lastIndexOf(final CharSequence str, final CharSequence searchStr, final int start) {
        if (str == null || searchStr == null) {
            return StringFun.INDEX_NOT_FOUND;
        }
        return StringFun.lastIndexOf(str, searchStr, start);
    }

    /**
     * 查找字符串，并返回索引位置
     *
     * @param str       字符串
     * @param searchStr 查找字符串
     * @return int
     */
    public static int indexOf(final CharSequence str, final CharSequence searchStr) {
        if (str == null || searchStr == null) {
            return StringFun.INDEX_NOT_FOUND;
        }

        return StringFun.indexOf(str, searchStr, 0, false);
    }

    /**
     * 查找字符串，并返回索引位置
     *
     * @param str       字符串
     * @param searchStr 查找字符串
     * @param start     起始位置
     * @return int
     */
    public static int indexOf(final CharSequence str, final CharSequence searchStr, int start) {
        if (str == null || searchStr == null) {
            return StringFun.INDEX_NOT_FOUND;
        }

        return StringFun.indexOf(str, searchStr, start, false);
    }

    /**
     * 查找字符串，并返回索引位置，忽略大小写
     *
     * @param str       字符串
     * @param searchStr 查找字符串
     * @return int
     */
    public static int indexOfCase(final CharSequence str, final CharSequence searchStr) {
        if (str == null || searchStr == null) {
            return StringFun.INDEX_NOT_FOUND;
        }

        return StringFun.indexOf(str, searchStr, 0, true);
    }

    /**
     * 查找字符串，并返回索引位置，忽略大小写
     *
     * @param str       字符串
     * @param searchStr 查找字符串
     * @param start     起始位置
     * @return int
     */
    public static int indexOfCase(final CharSequence str, final CharSequence searchStr, int start) {
        if (str == null || searchStr == null) {
            return StringFun.INDEX_NOT_FOUND;
        }

        return StringFun.indexOf(str, searchStr, start, true);
    }

    /**
     * 反转字符串
     *
     * @param str 字符串
     * @return String
     */
    public static String reverse(final String str) {
        if (str == null) {
            return null;
        }

        return new StringBuilder(str).reverse().toString();
    }

    /**
     * CharSequence 转 String
     *
     * @param cs CharSequence 类型字符串
     * @return String
     */
    public static String toStr(final CharSequence cs) {
        return null == cs ? null : cs.toString();
    }

    /**
     * 数组或对象转 String
     *
     * @param obj 数组或对象
     * @return String
     */
    public static String toStr(Object obj) {
        if (null == obj) {
            return null;
        }

        if (obj instanceof long[]) {
            return Arrays.toString((long[]) obj);
        } else if (obj instanceof int[]) {
            return Arrays.toString((int[]) obj);
        } else if (obj instanceof short[]) {
            return Arrays.toString((short[]) obj);
        } else if (obj instanceof char[]) {
            return Arrays.toString((char[]) obj);
        } else if (obj instanceof byte[]) {
            return Arrays.toString((byte[]) obj);
        } else if (obj instanceof boolean[]) {
            return Arrays.toString((boolean[]) obj);
        } else if (obj instanceof float[]) {
            return Arrays.toString((float[]) obj);
        } else if (obj instanceof double[]) {
            return Arrays.toString((double[]) obj);
        } else if (ArrayFun.isArray(obj)) {
            try {
                return Arrays.deepToString((Object[]) obj);
            } catch (Exception ignore) {
            }
        }

        return obj.toString();
    }

    /**
     * 蛇形转大驼峰
     *
     * @param input String
     * @return String
     */
    public static String snakeToCamel(String input) {
        return snakeToCamel(input, true);
    }

    /**
     * 蛇形转驼峰
     *
     * @param input String
     * @param bigCamel boolean
     * @return String
     */
    public static String snakeToCamel(String input, boolean bigCamel) {
        if (input == null) {
            return StringFun.EMPTY;
        }

        if (!contains(input, StringFun.UNDERSCORE)) {
            return input;
        }

        input = input.toLowerCase();
        int length = input.length();

        StringBuilder sb = new StringBuilder(length);
        boolean upperCase = false;
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (i == 0) {
                if (bigCamel) {
                    sb.append(Character.toUpperCase(c));
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            } else if (c == CharFun.UNDESCORE) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰转蛇形
     *
     * @param input String
     * @return String
     */
    public static String camelToSanke(String input) {
        if (input == null) {
            return StringFun.EMPTY;
        }

        String str = input;
        if (Character.isUpperCase(input.charAt(0))) {
            char[] chars = str.toCharArray();
            chars[0] += 32;
            str = String.valueOf(chars);
        }

        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(CharFun.UNDESCORE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 重复指定字符到指定次数
     *
     * @param ch     字符
     * @param repeat 次数
     * @return String
     */
    public static String repeat(final char ch, final int repeat) {
        if (repeat <= 0) {
            return StringFun.EMPTY;
        }

        final char[] buf = new char[repeat];
        for (int i = repeat - 1; i >= 0; i--) {
            buf[i] = ch;
        }

        return new String(buf);
    }

    /**
     * 重复指定字符串到指定次数
     *
     * @param str    字符串
     * @param repeat 次数
     * @return String
     */
    public static String repeat(final CharSequence str, final int repeat) {
        if (str == null) {
            return null;
        }

        if (repeat <= 0) {
            return StringFun.EMPTY;
        }

        final int inputLength = str.length();
        if (repeat == 1 || inputLength == 0) {
            return toStr(str);
        }

        if (inputLength == 1 && repeat <= StringFun.PAD_LIMIT) {
            return repeat(str.charAt(0), repeat);
        }

        final int outputLength = inputLength * repeat;
        switch (inputLength) {
            case 1:
                return repeat(str.charAt(0), repeat);
            case 2:
                final char ch0 = str.charAt(0);
                final char ch1 = str.charAt(1);
                final char[] output2 = new char[outputLength];
                for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }
                return new String(output2);
            default:
                final StringBuilder buf = new StringBuilder(outputLength);
                for (int i = 0; i < repeat; i++) {
                    buf.append(str);
                }
                return buf.toString();
        }
    }

    /**
     * 左侧填充字符串到最小长度，使用单一字符填充
     *
     * @param str     待填充字符串
     * @param length  长度
     * @param padChar 填充字符
     * @return String
     */
    public static String padLeft(final CharSequence str, final int length, final char padChar) {
        if (str == null) {
            return null;
        }

        final int pads = length - str.length();
        if (pads <= 0) {
            return str.toString();
        }

        if (pads > StringFun.PAD_LIMIT) {
            return padLeft(str, length, String.valueOf(padChar));
        }

        return repeat(padChar, pads).concat(str.toString());
    }


    /**
     * 左侧填充字符串到指定长度，使用默认空格填充
     *
     * @param str    待填充字符串
     * @param length 长度
     * @return String
     */
    public static String padLeft(final CharSequence str, final int length) {
        return padLeft(str, length, ' ');
    }

    /**
     * 左侧填充字符串到指定长度，使用字符串填充
     *
     * @param str    待填充字符串
     * @param length 长度
     * @param padStr 填充字符串
     * @return String
     */
    public static String padLeft(final CharSequence str, final int length, String padStr) {
        if (str == null) {
            return null;
        }

        if (empty(padStr)) {
            padStr = StringFun.SPACE;
        }

        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = length - strLen;
        if (pads <= 0) {
            return toStr(str);
        }

        if (padLen == 1 && pads <= StringFun.PAD_LIMIT) {
            return padLeft(str, length, padStr.charAt(0));
        }

        if (pads == padLen) {
            return padStr.concat(str.toString());
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str.toString());
        } else {
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str.toString());
        }
    }

    /**
     * 右侧填充字符串到指定长度。使用单一字符填充
     *
     * @param str     待填充字符
     * @param length  长度
     * @param padChar 填充字符
     * @return String
     */
    public static String padRight(final CharSequence str, final int length, final char padChar) {
        if (str == null) {
            return null;
        }

        final int pads = length - str.length();
        if (pads <= 0) {
            return toStr(str);
        }

        if (pads > StringFun.PAD_LIMIT) {
            return padRight(str, length, String.valueOf(padChar));
        }

        return str.toString().concat(repeat(padChar, pads));
    }

    /**
     * 右侧填充字符串到指定长度。使用默认空格填充
     *
     * @param str    待填充字符串
     * @param length 长度
     * @return String
     */
    public static String padRight(final CharSequence str, final int length) {
        return padRight(str, length, ' ');
    }

    /**
     * 右侧填充字符串到指定长度，使用字符串填充
     *
     * @param str    待填充字符串
     * @param length 长度
     * @param padStr 填充字符串
     * @return String
     */
    public static String padRight(final CharSequence str, final int length, String padStr) {
        if (str == null) {
            return null;
        }

        if (empty(padStr)) {
            padStr = StringFun.SPACE;
        }

        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = length - strLen;
        if (pads <= 0) {
            return toStr(str);
        }

        if (padLen == 1 && pads <= StringFun.PAD_LIMIT) {
            return padRight(str, length, padStr.charAt(0));
        }

        if (pads == padLen) {
            return str.toString().concat(padStr);
        } else if (pads < padLen) {
            return str.toString().concat(padStr.substring(0, pads));
        } else {
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return str.toString().concat(new String(padding));
        }
    }

    /**
     * 分割字符串为数组
     *
     * @param str 字符串
     * @return String[]
     */
    public static String[] split(final CharSequence str) {
        if (str == null) {
            return ArrayFun.EMPTY_STRING;
        }

        return StringFun.split(str.toString(), 0, false, false);
    }

    /**
     * 分割字符串为数组，指定分割字符
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return String[]
     */
    public static String[] split(final CharSequence str, final char separator) {
        if (str == null) {
            return ArrayFun.EMPTY_STRING;
        }

        return StringFun.split(str.toString(), separator, 0, false, false, false);
    }

    /**
     * 分割字符串为数组，指定分割字符串
     *
     * @param str       字符串
     * @param separator 分割字符串
     * @return String[]
     */
    public static String[] split(final CharSequence str, final CharSequence separator) {
        if (str == null) {
            return ArrayFun.EMPTY_STRING;
        }

        if (separator == null) {
            return StringFun.split(str.toString(), 0, false, false);
        } else {
            return StringFun.split(str.toString(), separator.toString(), 0, false, false, false);
        }
    }

    /**
     * 分割字符串为数组
     * <p>
     * 默认以空白字符分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str 字符串
     * @return String[]
     */
    public static String[] splitTrim(final CharSequence str) {
        if (str == null) {
            return ArrayFun.EMPTY_STRING;
        }

        return StringFun.split(str.toString(), 0, true, true);
    }

    /**
     * 分割字符串为数组
     * <p>
     * 指定单一字符分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return String[]
     */
    public static String[] splitTrim(final CharSequence str, final char separator) {
        if (str == null) {
            return ArrayFun.EMPTY_STRING;
        }

        return StringFun.split(str.toString(), separator, 0, true, true, false);
    }

    /**
     * 分割字符串为数组
     * <p>
     * 指定字符串分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str       字符串
     * @param separator 分割字符串
     * @return String[]
     */
    public static String[] splitTrim(final CharSequence str, final CharSequence separator) {
        if (str == null) {
            return ArrayFun.EMPTY_STRING;
        }

        if (separator == null) {
            return StringFun.split(str.toString(), 0, true, true);
        } else {
            return StringFun.split(str.toString(), separator.toString(), 0, true, true, false);
        }
    }

    /**
     * 分割字符串为列表
     * <p>
     * 默认以空白字符分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str 字符串
     * @return String[]
     */
    public static List<String> splitToList(final CharSequence str) {
        if (str == null) {
            return Collections.emptyList();
        }

        return StringFun.splitToList(str.toString(), 0, false, false);
    }

    /**
     * 分割字符串为列表
     * <p>
     * 指定单一字符分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return String[]
     */
    public static List<String> splitToList(final CharSequence str, final char separator) {
        if (str == null) {
            return Collections.emptyList();
        }

        return StringFun.splitToList(str.toString(), separator, 0, false, false, false);
    }

    /**
     * 分割字符串为列表
     * <p>
     * 指定字符串分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str       字符串
     * @param separator 分割字符串
     * @return String[]
     */
    public static List<String> splitToList(final CharSequence str, final CharSequence separator) {
        if (str == null) {
            return Collections.emptyList();
        }

        if (separator == null) {
            return StringFun.splitToList(str.toString(), 0, false, false);
        } else {
            return StringFun.splitToList(str.toString(), separator.toString(), 0, false, false, false);
        }
    }

    /**
     * 分割字符串为整型数组
     * <p>
     * 默认以空白字符分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str 字符串
     * @return int[]
     */
    public static int[] splitTrimToInt(final CharSequence str) {
        if (str == null) {
            return ArrayFun.EMPTY_INT;
        }

        String[] strings = StringFun.split(str.toString(), 0, true, true);
        return Arrays.stream(strings).mapToInt(Integer::parseInt).toArray();
    }

    /**
     * 分割字符串为数组
     * <p>
     * 指定字符串分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return int[]
     */
    public static int[] splitTrimToInt(final CharSequence str, final CharSequence separator) {
        if (str == null) {
            return ArrayFun.EMPTY_INT;
        }

        String[] strings;
        if (separator == null) {
            strings = StringFun.split(str.toString(), 0, true, true);
        } else {
            strings = StringFun.split(str.toString(), separator.toString(), 0, true, true, false);
        }

        return ArrayFun.stringToIntArray(strings);
    }

    /**
     * 分割字符串为整型数组
     * <p>
     * 默认以空白字符分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str 字符串
     * @return long[]
     */
    public static long[] splitTrimToLong(final CharSequence str) {
        if (str == null) {
            return ArrayFun.EMPTY_LONG;
        }

        String[] strings = StringFun.split(str.toString(), 0, true, true);
        return Arrays.stream(strings).mapToLong(Long::parseLong).toArray();
    }

    /**
     * 分割字符串为数组
     * <p>
     * 指定字符串分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return long[]
     */
    public static long[] splitTrimToLong(final CharSequence str, final CharSequence separator) {
        if (str == null) {
            return ArrayFun.EMPTY_LONG;
        }

        String[] strings;
        if (separator == null) {
            strings = StringFun.split(str.toString(), 0, true, true);
        } else {
            strings = StringFun.split(str.toString(), separator.toString(), 0, true, true, false);
        }

        return ArrayFun.stringToLongArray(strings);
    }

    /**
     * 分割字符串为列表
     * <p>
     * 默认以空白字符分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str 字符串
     * @return String[]
     */
    public static List<String> splitTrimToList(final CharSequence str) {
        if (str == null) {
            return Collections.emptyList();
        }

        return StringFun.splitToList(str.toString(), 0, true, true);
    }

    /**
     * 分割字符串为列表
     * <p>
     * 指定单一字符分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return String[]
     */
    public static List<String> splitTrimToList(final CharSequence str, final char separator) {
        if (str == null) {
            return Collections.emptyList();
        }

        return StringFun.splitToList(str.toString(), separator, 0, true, true, false);
    }

    /**
     * 分割字符串为列表
     * <p>
     * 指定字符串分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str       字符串
     * @param separator 分割字符串
     * @return String[]
     */
    public static List<String> splitTrimToList(final CharSequence str, final CharSequence separator) {
        if (str == null) {
            return Collections.emptyList();
        }

        if (separator == null) {
            return StringFun.splitToList(str.toString(), 0, true, true);
        } else {
            return StringFun.splitToList(str.toString(), separator.toString(), 0, true, true, false);
        }
    }

    /**
     * 字符串截取
     *
     * @param str   字符串
     * @param start 起始索引
     * @return String
     */
    public static String substring(final CharSequence str, int start) {
        if (str == null) {
            return null;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return StringFun.EMPTY;
        }

        return str.toString().substring(start);
    }

    /**
     * 字符串截取
     *
     * @param str   字符串
     * @param start 起始索引
     * @param end   结束索引
     * @return String
     */
    public static String substring(final CharSequence str, int start, int end) {
        if (str == null) {
            return null;
        }

        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return StringFun.EMPTY;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.toString().substring(start, end);
    }

    /**
     * 字符串截取，等同于 substring
     *
     * @param str   字符串
     * @param start 起始索引
     * @return String
     */
    public static String substr(final CharSequence str, int start) {
        return substring(str, start);
    }

    /**
     * 字符串截取
     *
     * @param str    字符串
     * @param start  起始索引
     * @param length 截取长度
     * @return String
     */
    public static String substr(final CharSequence str, int start, int length) {
        if (str == null) {
            return null;
        }

        int len = str.length();
        int end;

        if (start < 0) {
            start = str.length() + start;
        }

        if (length < 0) {
            end = len + length;
        } else {
            end = Math.min(start + length, len);
        }

        if (start > end) {
            return StringFun.EMPTY;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.toString().substring(start, end);
    }

    /**
     * 以 separator 为分隔符将数组转换为字符串（范型）
     *
     * @param array     数组
     * @param separator 分隔符
     * @param <T>       范型
     * @return String
     */
    public static <T> String join(T[] array, CharSequence separator) {
        if (array == null) {
            return null;
        }

        if (separator == null) {
            separator = StringFun.EMPTY;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (T item : array) {
            if (item != null) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(separator);
                }
                sb.append(item);
            }
        }

        return sb.toString();
    }

    /**
     * 以 separator 为分隔符将数组转换为字符串，支持字符串类容器
     *
     * @param iterable  字符串容器
     * @param separator 分隔符
     * @return String
     */
    public static String join(final Iterable<?> iterable, String separator) {
        if (iterable == null) {
            return null;
        }

        return join(iterable.iterator(), separator);
    }

    /**
     * 以 separator 为分隔符转换为字符串，支持字符串容器
     *
     * @param iterator  迭代器
     * @param separator 分隔符
     * @return String
     */
    public static String join(final Iterator<?> iterator, String separator) {
        if (iterator == null) {
            return null;
        }

        if (separator == null) {
            separator = StringFun.EMPTY;
        }

        if (!iterator.hasNext()) {
            return StringFun.EMPTY;
        }

        final Object first = iterator.next();
        if (!iterator.hasNext()) {
            return Objects.toString(first, "");
        }

        boolean isFirst = true;
        final StringBuilder sb = new StringBuilder();
        if (first != null) {
            sb.append(first);
            isFirst = false;
        }

        while (iterator.hasNext()) {
            final Object obj = iterator.next();
            if (obj != null) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(separator);
                }
                sb.append(obj);
            }
        }

        return sb.toString();
    }

    /**
     * 获取正则匹配字符串后的指定分组
     * <p>
     * 分组 0，表示全匹配的信息
     *
     * @param pattern    正则表达式模式
     * @param content    字符串
     * @param groupIndex 分组
     * @return String
     */
    public static String regexMatch(Pattern pattern, CharSequence content, int groupIndex) {
        if (pattern == null || content == null) {
            return null;
        }

        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(groupIndex);
        }

        return null;
    }

    /**
     * 获取正则匹配字符串后的所有分组
     * <p>
     * 分组 0，表示全匹配的信息
     *
     * @param pattern 正则表达式模式
     * @param content 字符串
     * @return List
     */
    public static List<String> regexMatch(Pattern pattern, CharSequence content) {
        if (pattern == null || content == null) {
            return null;
        }

        ArrayList<String> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            final int groupCount = matcher.groupCount();
            for (int i = 0; i <= groupCount; i++) {
                result.add(matcher.group(i));
            }
        }

        return result;
    }

    /**
     * 获取正则匹配字符串后的指定分组
     * <p>
     * 分组 0，表示全匹配的信息
     *
     * @param regex      正则表达式字符串
     * @param content    字符串
     * @param groupIndex 分组
     * @return String
     */
    public static String regexMatch(String regex, CharSequence content, int groupIndex) {
        if (regex == null || content == null) {
            return null;
        }

        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        return regexMatch(pattern, content, groupIndex);
    }

    /**
     * 获取正则匹配字符串后的所有分组
     *
     * @param regex   正则表达式字符串
     * @param content 字符串
     * @return List
     */
    public static List<String> regexMatch(String regex, CharSequence content) {
        if (regex == null || content == null) {
            return null;
        }

        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        return regexMatch(pattern, content);
    }

    /**
     * 字符串替换
     *
     * @param str        待替换的字符串
     * @param searchStr  查找字符串
     * @param replaceStr 替换字符串
     * @return String
     */
    public static String replace(final CharSequence str, final CharSequence searchStr, final CharSequence replaceStr) {
        return StringFun.replace(str, searchStr, replaceStr, -1, false);
    }

    /**
     * 字符串替换，忽略大小写
     *
     * @param str        待替换的字符串
     * @param searchStr  查找字符串
     * @param replaceStr 替换字符串
     * @return String
     */
    public static String replaceCase(final CharSequence str, final CharSequence searchStr, final CharSequence replaceStr) {
        return StringFun.replace(str, searchStr, replaceStr, -1, true);
    }

    /**
     * 移除指定字符串中所有给定字符串
     *
     * @param str    字符串
     * @param remove 被移除的字符串
     * @return String
     */
    public static String remove(final CharSequence str, final CharSequence remove) {
        if (empty(str) || empty(remove)) {
            return toStr(str);
        }

        return str.toString().replace(remove, StringFun.EMPTY);
    }

    /**
     * 移除指定字符串左侧中所有给定字符串
     *
     * @param str    字符串
     * @param remove 被移除的字符串
     * @return String
     */
    public static String removePrefix(final CharSequence str, final CharSequence remove) {
        if (empty(str) || empty(remove)) {
            return toStr(str);
        }

        if (str.toString().startsWith(remove.toString())) {
            return str.toString().substring(remove.length());
        }

        return str.toString();
    }

    /**
     * 完整的移除指定字符串左侧中所有给定字符串（多次）
     *
     * @param str    字符串
     * @param remove 被移除的字符串
     * @return String
     */
    public static String removePrefixComplete(final CharSequence str, final CharSequence remove) {
        if (empty(str) || empty(remove)) {
            return toStr(str);
        }

        String removedStr = str.toString();

        do {
            if (removedStr.startsWith(remove.toString())) {
                removedStr = removePrefix(removedStr, remove);
            } else {
                break;
            }

        } while (true);

        return removedStr;
    }

    /**
     * 移除指定字符串右侧中所有给定字符串
     *
     * @param str    字符串
     * @param remove 被移除的字符串
     * @return String
     */
    public static String removeSuffix(final CharSequence str, final CharSequence remove) {
        if (empty(str) || empty(remove)) {
            return toStr(str);
        }

        if (str.toString().endsWith(remove.toString())) {
            return str.toString().substring(0, str.length() - remove.length());
        }

        return str.toString();
    }

    /**
     * 完整的移除指定字符串右侧中所有给定字符串（多次）
     *
     * @param str    字符串
     * @param remove 被移除的字符串
     * @return String
     */
    public static String removeSuffixComplete(final CharSequence str, final CharSequence remove) {
        if (empty(str) || empty(remove)) {
            return toStr(str);
        }

        String removedStr = str.toString();

        do {
            if (removedStr.endsWith(remove.toString())) {
                removedStr = removeSuffix(removedStr, remove);
            } else {
                break;
            }

        } while (true);

        return removedStr;
    }

    /**
     * 移除指定字符串中所有给定字符串列表
     *
     * @param str     字符串
     * @param removes 被移除的字符串数组，一个或多个
     * @return String
     */
    public static String removeAny(final CharSequence str, final CharSequence... removes) {
        String result = toStr(str);

        if (!empty(str)) {
            for (CharSequence remove : removes) {
                result = remove(result, remove);
            }
        }

        return result;
    }

    /**
     * 去除指定字符串中指定的多个字符集
     *
     * @param str   字符串
     * @param chars 字符列表
     * @return String
     */
    public static String removeAny(final CharSequence str, char... chars) {
        if (null == str || empty(chars)) {
            return toStr(str);
        }

        final int len = str.length();
        if (0 == len) {
            return toStr(str);
        }

        final StringBuilder builder = new StringBuilder(len);
        char c;
        for (int i = 0; i < len; i++) {
            c = str.charAt(i);
            if (!ArrayFun.contains(chars, c)) {
                builder.append(c);
            }
        }

        return builder.toString();
    }

    /**
     * 去除指定字符串中换行符
     *
     * @param str 字符串
     * @return String
     */
    public static String removeLines(final CharSequence str) {
        return removeAny(str, CharFun.CR, CharFun.LF);
    }

    /**
     * 返回当前日期时间默认格式字符串
     * <p>
     * 默认格式：yyyy-MM-dd HH:mm:ss
     *
     * @return String
     */
    public static String date() {
        return date(DatePattern.DATETIME_PATTERN);
    }

    /**
     * 根据时间戳，返回指定日期时间默认格式字符串
     * <p>
     * 默认格式：yyyy-MM-dd HH:mm:ss
     *
     * @param timestamp 时间戳
     * @return String
     */
    public static String date(final long timestamp) {
        return date(DatePattern.DATETIME_PATTERN, timestamp);
    }

    /**
     * 根据格式化参数返回当前日期时间字符串
     *
     * @param pattern 日期时间格式
     * @return String
     */
    public static String date(final String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 根据格式化参数，时间戳，返回指定日期时间字符串
     *
     * @param pattern   日期时间格式
     * @param timestamp 时间戳
     * @return String
     */
    public static String date(final String pattern, final long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(Math.abs(timestamp)), ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 返回当前 Unix 时间戳
     *
     * @return long
     */
    public static long strtotime() {
        return Instant.now().getEpochSecond();
    }

    /**
     * 将通用格式的日期时间字符串，自动为转换时间戳
     *
     * @param dateString 日期时间字符串
     * @return long
     */
    public static long strtotime(final String dateString) {
        return Strtotime.parse(dateString);
    }

    /**
     * 以提供的时间戳为基准，返回简单表达式计算后的时间戳
     *
     * @param expString 时期时间字符串
     * @param timestamp 时间戳
     * @return long
     */
    public static long strtotime(final String expString, final Long timestamp) {
        return Strtotime.parse(expString, timestamp);
    }

    /**
     * 获得指定范围内的随机数
     * <p>
     * 默认 [0,Integer.MAX_VALUE)
     *
     * @return int
     */
    public static int random() {
        return RandomFun.randomInt(Integer.MAX_VALUE);
    }

    /**
     * 获得指定范围内的随机数 [0, max)
     *
     * @param max 限制随机数的范围
     * @return int
     */
    public static int randomInt(int max) {
        return RandomFun.randomInt(max);
    }

    /**
     * 获得指定范围内的随机数 [0, max)
     *
     * @param max 限制随机数的范围
     * @return 随机数
     */
    public static long randomLong(long max) {
        return RandomFun.randomLong(max);
    }

    /**
     * 获得一个指定长度随机的字符串（只包含数字和字符）
     *
     * @param length 字符串的长度
     * @return String
     */
    public static String randomString(int length) {
        return RandomFun.randomString(length);
    }

    /**
     * 获得一个指定长度随机的字符串，排除指定的字符串列表集
     *
     * @param length  字符串的长度
     * @param excepts 待排除列表
     * @return String
     */
    public static String randomStringExcepts(int length, String... excepts) {
        return RandomFun.randomStringExcept(length, excepts);
    }

    /**
     * 获得一个指定长度只包含数字的随机字符串
     *
     * @param length 字符串的长度
     * @return String
     */
    public static String randomNumber(int length) {
        return RandomFun.randomNumber(length);
    }

    /**
     * 获得一个指定长度只包含字符的随机字符串
     *
     * @param length 字符串的长度
     * @return String
     */
    public static String randomLetter(int length) {
        return RandomFun.randomLetter(length);
    }

    /**
     * 获得一个指定长度只包含字符池中的随机字符串
     *
     * @param pool String
     * @param length 字符串的长度
     * @return String
     */
    public static String randomPool(String pool, int length) {
        return RandomFun.randomPool(pool, length);
    }

    /**
     * base64Encode
     *
     * @param str 字符串
     * @return String
     */
    public static String base64Encode(final String str) {
        return base64Encode(bytes(str));
    }

    /**
     * base64Decode
     *
     * @param str Base64字符串
     * @return String
     */
    public static String base64Decode(final String str) {
        return new String(Base64.getDecoder().decode(str));
    }

    /**
     * base64URLEncode
     * <p>
     * 会将 +、/ 替换为 -、_
     *
     * @param str 字符串
     * @return String
     */
    public static String base64UrlEncode(final String str) {
        return base64UrlSafeEncode(bytes(str));
    }

    /**
     * base64URLDecode
     *
     * @param str Base64字符串
     * @return String
     */
    public static String base64UrlDecode(final String str) {
        return new String(Base64.getUrlDecoder().decode(str));
    }


    /**
     * MD5
     *
     * @param str 字符串
     * @return String
     */
    public static String md5(final String str) {
        return DigestFun.md5Hex(str);
    }

    /**
     * SHA1
     *
     * @param str 字符串
     * @return String
     */
    public static String sha1(final String str) {
        return DigestFun.sha1Hex(str);
    }

    /**
     * SHA256
     *
     * @param str 字符串
     * @return String
     */
    public static String sha256(final String str) {
        return DigestFun.sha256Hex(str);
    }

    /**
     * SHA384
     *
     * @param str 字符串
     * @return String
     */
    public static String sha384(final String str) {
        return DigestFun.sha384Hex(str);
    }

    /**
     * SHA512
     *
     * @param str 字符串
     * @return String
     */
    public static String sha512(final String str) {
        return DigestFun.sha512Hex(str);
    }

    /**
     * Hashid 加密
     *
     * @param id ID
     * @return String
     */
    public static String idEncode(long id) {
        Hashids hashids = new Hashids();
        return hashids.encode(id);
    }

    /**
     * Hashid 加密
     *
     * @param id        ID
     * @param minLength 最小长度
     * @return String
     */
    public static String idEncode(long id, int minLength) {
        Hashids hashids = new Hashids(minLength);
        return hashids.encode(id);
    }

    /**
     * Hashid 加密
     *
     * @param id   ID
     * @param salt 盐
     * @return String
     */
    public static String idEncode(long id, String salt) {
        Hashids hashids = new Hashids(salt);
        return hashids.encode(id);
    }

    /**
     * Hashid 加密
     *
     * @param id        ID
     * @param minLength 最小长度
     * @param salt      盐
     * @return String
     */
    public static String idEncode(long id, String salt, int minLength) {
        Hashids hashids = new Hashids(salt, minLength);
        return hashids.encode(id);

    }

    /**
     * Hashid 解密
     *
     * @param hash Hash字符串
     * @return long
     */
    public static long idDecode(String hash) {
        Hashids hashids = new Hashids();
        long[] ids = hashids.decode(hash);
        if (!empty(ids)) {
            return ids[0];
        }

        return 0L;
    }

    /**
     * Hashid 解密
     *
     * @param hash      Hash 字符串
     * @param minLength 最小长度
     * @return long
     */
    public static long idDecode(String hash, int minLength) {
        Hashids hashids = new Hashids(minLength);
        long[] ids = hashids.decode(hash);
        if (!empty(ids)) {
            return ids[0];
        }

        return 0L;
    }

    /**
     * Hashid 解密
     *
     * @param hash Hash 字符串
     * @param salt 盐
     * @return long
     */
    public static long idDecode(String hash, String salt) {
        Hashids hashids = new Hashids(salt);
        long[] ids = hashids.decode(hash);
        if (!empty(ids)) {
            return ids[0];
        }

        return 0L;
    }

    /**
     * Hashid 解密
     *
     * @param hash      Hash 字符串
     * @param salt      盐
     * @param minLength 最小长度
     * @return long
     */
    public static long idDecode(String hash, String salt, int minLength) {
        Hashids hashids = new Hashids(salt, minLength);
        long[] ids = hashids.decode(hash);
        if (!empty(ids)) {
            return ids[0];
        }

        return 0L;
    }

    /**
     * 计算两个字符串的相似度
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return double
     */
    public static double similarity(String str1, String str2) {
        return SimilarityFun.similarity(str1, str2);
    }

    /**
     * 计算两个字符串的相似度
     *
     * @param str1  字符串1
     * @param str2  字符串2
     * @param scale 保留小数
     * @return double
     */
    public static String similarity(String str1, String str2, int scale) {
        return SimilarityFun.similarity(str1, str2, scale);
    }

    /**
     * 按照给定的算术运算符做计算
     *
     * @param firstValue  字符串1
     * @param secondValue 字符串2
     * @param ops 操作数
     * @return BigDecimal
     */
    public static BigDecimal calculate(String firstValue, String secondValue, char ops) {
        return NumberFun.calculate(firstValue, secondValue, ops);
    }

    /**
     * 安全的解析 HTTP(s) URL，会对 URL 进行验证与格式化，返回 URL 实例
     *
     * @param url 字符串
     * @return URL
     */
    public static URL urlParse(final String url) {
        String formatUrl = urlNormalize(url);

        if (!empty(formatUrl)) {
            try {
                return new URL(formatUrl);
            } catch (MalformedURLException e) {
                throw new FunException(e);
            }
        }

        return null;
    }

    /**
     * 验证 HTTP(s) 格式
     *
     * @param url URL 字符串
     * @return boolean
     */
    public static boolean isUrl(final String url) {
        if (!blank(url)) {
            return matches(RegexPattern.URL, url);
        }

        return false;
    }

    /**
     * 自动格式化补齐 HTTP(s) URL，如缺少协议头
     *
     * @param url URL字符串
     * @return String
     */
    public static String urlNormalize(final String url) {
        if (isUrl(url)) {
            if (startsWith(url, UrlFun.NO_PROTOCOL)) {
                String formatUrl = removePrefix(url, UrlFun.NO_PROTOCOL);
                return UrlFun.HTTP_PROTOCOL + UrlFun.PROTOCOL_BREAK + formatUrl;
            }

            if (!contains(url, UrlFun.PROTOCOL_BREAK)) {
                return UrlFun.HTTP_PROTOCOL + UrlFun.PROTOCOL_BREAK + url;
            }

            return url;
        }

        return StringFun.EMPTY;
    }

    // 判断字符串是否全部为数字
    public static boolean isNumber(String str) {
        if (str == null) {
            return false;
        }
        return matches(RegexPattern.NUMBER, str);
    }

    // 判断字符串是否全部为字母
    public static boolean isLetter(String str) {
        if (str == null) {
            return false;
        }
        return matches(RegexPattern.LETTER, str);
    }

    /**
     * 将字符串的所有数据依次写成一行，去除无意义字符串
     * 去除标点符号、符号、分隔符、其他
     *
     * @param str 字符串
     * @return 处理后的字符串
     */
    public static String removeSign(String str) {
        str = str.replaceAll("\\n|\\r|\\s", "");
        return str.replaceAll("[\\pP\\pS]", "");
    }
}
