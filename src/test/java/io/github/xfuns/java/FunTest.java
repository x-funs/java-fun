package io.github.xfuns.java;

import io.github.xfuns.java.constant.DatePattern;
import io.github.xfuns.java.fun.DataSizeFun;
import io.github.xfuns.java.fun.RandomFun;
import io.github.xfuns.java.fun.SimilarityFun;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

import static io.github.xfuns.java.Fun.*;


public class FunTest {

    public static class ObjectClass {
        private Integer id;
        private String name;

        public ObjectClass() {
        }

        public ObjectClass(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "ObjectClass{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Test
    public void testTime() {
        Assert.assertEquals(Fun.timestamp(), Fun.strtotime());
        Assert.assertEquals(Fun.timestamp(), Fun.strtotime());
        Assert.assertEquals(Fun.timestamp(true) / 1000, Fun.strtotime());
    }

    @Test
    public void testMemory() {
        System.out.println(maxMemoryByte());
        System.out.println(totalMemoryByte());
        System.out.println(freeMemoryByte());
        System.out.println(usedMemoryByte());
        System.out.println(Fun.maxMemoryMb());
        System.out.println(Fun.totalMemoryMb());
        System.out.println(Fun.freeMemoryMb());
        System.out.println(Fun.usedMemoryMb());
        System.out.println(maxMemory(DataSizeFun.MB) + "MB");
        System.out.println(totalMemory(DataSizeFun.MB) + "MB");
        System.out.println(freeMemory(DataSizeFun.MB) + "MB");
        System.out.println(usedMemory(DataSizeFun.MB) + "MB");
    }

    @Test
    public void testBlank() {
        Assert.assertTrue(Fun.blank(""));
        Assert.assertTrue(Fun.blank(null));
        Assert.assertTrue(Fun.blank(" "));
        Assert.assertTrue(Fun.blank("   "));

        Assert.assertTrue(Fun.blankAny("a", "b", " "));
        Assert.assertTrue(Fun.blankAny("a", "b", ""));
        Assert.assertTrue(Fun.blankAny("a", "b", null));
        Assert.assertFalse(Fun.blankAny("a", "b", "c"));

        Assert.assertTrue(Fun.blankAll("", " ", "   ", null));
    }

    @Test
    public void testEmpty() {
        Object obj = null;
        String str1 = null;
        String str2 = "";
        String[] strs1 = {};
        long[] long1 = {};
        Integer[] ints1 = {};
        ObjectClass testObj1 = null;
        int inta = 0;
        boolean boola = false;
        Integer integera = 0;
        Boolean booleanA = false;
        List<String> strList1 = new ArrayList<>();
        Set<String> strSet1 = new HashSet<>();
        HashMap<String, String> strMap1 = new HashMap<>();

        Assert.assertTrue(Fun.empty(obj));
        Assert.assertTrue(Fun.empty(str1));
        Assert.assertTrue(Fun.empty(str2));
        Assert.assertTrue(Fun.empty(strs1));
        Assert.assertTrue(Fun.empty(long1));
        Assert.assertTrue(Fun.empty(ints1));
        Assert.assertTrue(Fun.empty(testObj1));
        Assert.assertTrue(Fun.empty(inta));
        Assert.assertTrue(Fun.empty(integera));
        Assert.assertTrue(Fun.empty(boola));
        Assert.assertTrue(Fun.empty(booleanA));
        Assert.assertTrue(Fun.empty(""));
        Assert.assertTrue(Fun.empty(strList1));
        Assert.assertTrue(Fun.empty(strSet1));
        Assert.assertTrue(Fun.empty(strMap1));

        String str3 = "a";
        String[] strs2 = {"a", "b"};
        ObjectClass testObj2 = new ObjectClass();
        long[] long2 = {1, 2};
        int intb = 1;
        boolean boolb = true;
        Integer integerb = 1;
        Integer[] ints2 = {1, 2};
        List<String> strList2 = new ArrayList<>();
        Set<String> strSet2 = new HashSet<>();
        HashMap<String, String> strMap2 = new HashMap<>();
        strList2.add("a");
        strSet2.add("a");
        strMap2.put("a", "b");

        Assert.assertFalse(Fun.empty(str3));
        Assert.assertFalse(Fun.empty(strs2));
        Assert.assertFalse(Fun.empty(long2));
        Assert.assertFalse(Fun.empty(ints2));
        Assert.assertFalse(Fun.empty(testObj2));
        Assert.assertFalse(Fun.empty(intb));
        Assert.assertFalse(Fun.empty(boolb));
        Assert.assertFalse(Fun.empty(integerb));
        Assert.assertFalse(Fun.empty(strList2));
        Assert.assertFalse(Fun.empty(strSet2));
        Assert.assertFalse(Fun.empty(strMap2));
        Assert.assertFalse(Fun.empty(" "));
        Assert.assertFalse(Fun.empty("   "));

        Assert.assertTrue(Fun.emptyAny("a", "b", ""));
        Assert.assertFalse(Fun.emptyAny("a", "b", "c"));

        Assert.assertFalse(Fun.emptyAll("a", ""));
        Assert.assertTrue(Fun.emptyAll("", "", ""));
    }

    @Test
    public void testTrim() {
        String str1 = "  HelloWorld  ";
        String str2 = "HelloWorld";
        String str3 = "\t\tHelloWorld\t\t";
        Assert.assertEquals("HelloWorld", Fun.trim(str1));
        Assert.assertEquals("HelloWorld  ", Fun.ltrim(str1));
        Assert.assertEquals("  HelloWorld", Fun.rtrim(str1));
        Assert.assertEquals("HelloWorld", Fun.trim(str3));

        Assert.assertEquals("  HelloWorld  ", Fun.trim(str1, ""));
        Assert.assertEquals("HelloWorld", Fun.trim(str1, " "));
        Assert.assertEquals("HelloWorld", Fun.trim(str1, null));
        Assert.assertEquals("elloWorl", Fun.trim(str1, " Hd"));
        Assert.assertEquals("oWor", Fun.trim(str2, "Hdle"));
    }

    @Test
    public void testTrimToEmpty() {
        String str1 = " HelloWorld ";
        String str2 = null;
        Assert.assertEquals("HelloWorld", Fun.trimToEmpty(str1));
        Assert.assertEquals("", Fun.trimToEmpty(str2));

        Assert.assertEquals("abc", Fun.trimToDefault("\n", "abc"));
        Assert.assertEquals("abc", Fun.trimToDefault("      ", "abc"));
        Assert.assertEquals("abc", Fun.trimToDefault(null, "abc"));
        Assert.assertEquals("abc", Fun.trimToDefault(str2, "abc"));
        Assert.assertEquals("HelloWorld", Fun.trimToDefault(str1, "abc"));
    }

    @Test
    public void testTrimToDefault() {
        String str1 = " HelloWorld ";
        String str2 = null;

        Assert.assertEquals("abc", Fun.trimToDefault("\n", "abc"));
        Assert.assertEquals("abc", Fun.trimToDefault("      ", "abc"));
        Assert.assertEquals("abc", Fun.trimToDefault(null, "abc"));
        Assert.assertEquals("abc", Fun.trimToDefault(str2, "abc"));
        Assert.assertEquals("HelloWorld", Fun.trimToDefault(str1, "abc"));
    }

    @Test
    public void testTrimToNull() {
        String str1 = " HelloWorld ";
        String str2 = "";
        String str3 = null;
        Assert.assertEquals("HelloWorld", Fun.trimToNull(str1));
        Assert.assertNull(Fun.trimToNull(str2));
        Assert.assertNull(Fun.trimToNull(str3));
    }

    @Test
    public void testContains() {
        String s = "Hello";
        Assert.assertTrue(Fun.contains(s, "o"));
        Assert.assertFalse(Fun.contains(null, "o"));
        Assert.assertFalse(Fun.contains(null, ""));
        Assert.assertFalse(Fun.contains(null, null));
        Assert.assertTrue(Fun.containsCase(s, "h"));
        Assert.assertTrue(Fun.containsAny(s, "", "o"));
    }

    @Test
    public void testToInt() {
        Assert.assertEquals(0, Fun.toInt(""));
        Assert.assertEquals(0, Fun.toInt(""));
        Assert.assertEquals(0, Fun.toInt(" "));
        Assert.assertEquals(0, Fun.toInt(null));
        Assert.assertEquals(1, Fun.toInt("1"));
        Assert.assertEquals(1, Fun.toInt(Fun.trim(" 1 ")));

        Assert.assertEquals(0, Fun.toInt("1.1"));
        Assert.assertEquals(42, Fun.toInt("42"));
        Assert.assertEquals(0, Fun.toInt("4.2"));
        Assert.assertEquals(8, Fun.toInt("08"));
    }

    @Test
    public void testToLong() {
        Assert.assertEquals(0, Fun.toLong(""));
        Assert.assertEquals(0, Fun.toLong(""));
        Assert.assertEquals(0, Fun.toLong(" "));
        Assert.assertEquals(0, Fun.toLong(null));
        Assert.assertEquals(1, Fun.toLong("1"));
        Assert.assertEquals(1, Fun.toLong(Fun.trim(" 1 ")));

        Assert.assertEquals(0, Fun.toLong("1.1"));
    }

    @Test
    public void testToStr() {
        Assert.assertEquals(null, Fun.toStr(null));
    }

    @Test
    public void testToBoolean() {
        Assert.assertTrue(Fun.toBoolean(Boolean.TRUE));
        Assert.assertTrue(Fun.toBoolean(1));
        Assert.assertFalse(Fun.toBoolean(0));
    }

    @Test
    public void testToFloatDouble() {
        Assert.assertEquals(3.1415926F, Fun.toFloat("3.14159265"), 0.0001);
        Assert.assertEquals(3.14159265D, Fun.toDouble("3.14159265"), 0);
    }

    @Test
    public void testEquals() {
        String str1 = "HelloWorld";
        String str2 = null;
        Assert.assertTrue(Fun.equals(str1, "HelloWorld"));
        Assert.assertTrue(Fun.equalsCase(str1, "helloworld"));
        Assert.assertTrue(Fun.equals(str2, null));
    }

    @Test
    public void testStartEndWith() {
        Assert.assertFalse(Fun.startsWith("Hello", null));
        Assert.assertFalse(Fun.startsWith(null, ""));
        Assert.assertTrue(Fun.startsWith(null, null));
        Assert.assertTrue(Fun.startsWith("Hello", ""));
        Assert.assertTrue(Fun.startsWith("Hello", "H"));

        Assert.assertTrue(Fun.startsWithCase("Hello", "h"));

        Assert.assertFalse(Fun.endsWith("Hello", null));
        Assert.assertFalse(Fun.endsWith(null, ""));
        Assert.assertTrue(Fun.endsWith(null, null));
        Assert.assertTrue(Fun.endsWith("Hello", ""));
        Assert.assertTrue(Fun.endsWith("Hello", "o"));

        Assert.assertTrue(Fun.endsWithCase("Hello", "O"));


        Assert.assertFalse(Fun.startsWithAny(null, null));
        Assert.assertTrue(Fun.startsWithAny("a.zip", "b.", "a.", "c."));

        Assert.assertFalse(Fun.endWithAny(null, null));
        Assert.assertTrue(Fun.endWithAny("a.zip", ".doc", ".zip", ".xls"));
        Assert.assertFalse(Fun.endWithAny("a.png", ".doc", ".zip", ".xls"));
        Assert.assertTrue(Fun.endWithAny("a.png", new String[]{""}));
    }

    @Test
    public void testDate() {
        System.out.println(Fun.date());
        System.out.println(date(DatePattern.DATE_PATTERN_SLASH));
        System.out.println(date(DatePattern.DATETIME_PATTERN_CN));
        System.out.println(date(DatePattern.DATETIME_PATTERN_UTC));

        Assert.assertEquals("2015-04-06 16:03:03", Fun.date(1428307383));
        Assert.assertEquals("2015/04/06", Fun.date(DatePattern.DATE_PATTERN_SLASH, 1428307383));
    }

    @Test
    public void testStrtotime() {
        // 本地时间
        String dtString = "2015-04-06 16:03:03";

        long timeStamp = Fun.strtotime(dtString);

        Assert.assertEquals(Fun.strtotime("2015-04-06T16:03:03+08:00"), timeStamp);

        Assert.assertEquals(Fun.strtotime("2015-04-06T08:03:03Z"), timeStamp);
        Assert.assertEquals(Fun.strtotime("2015-04-06T08:03:03.123"), timeStamp);
        Assert.assertEquals(Fun.strtotime("2015-04-06T08:03:03"), timeStamp);
        Assert.assertEquals(Fun.strtotime("2015-04-06 16:03:03"), timeStamp);
        Assert.assertEquals(Fun.strtotime("2015-04-06 16:03"), timeStamp - 3);
        Assert.assertEquals(Fun.strtotime("2015-4-6 16:3:3"), timeStamp);
        Assert.assertEquals(Fun.strtotime("2015-4-06 16:03:3"), timeStamp);
        Assert.assertEquals(Fun.strtotime("2015-4-6 16:03:03"), timeStamp);
        Assert.assertEquals(Fun.strtotime("2015/04/06 16:03:03"), timeStamp);
        Assert.assertEquals(Fun.strtotime("2015年04月06日 16时03分03秒"), timeStamp);
        Assert.assertEquals(Fun.strtotime("2015年4月6日 16时03分3秒"), timeStamp);
        Assert.assertEquals(Fun.strtotime("2015年04月6日 16时03分3秒"), timeStamp);
        Assert.assertEquals(Fun.strtotime("2015年4月6日 16时03分"), timeStamp - 3);

        Assert.assertEquals("2014-04-06 16:03:03", Fun.date(Fun.strtotime("-1 year", timeStamp)));
        Assert.assertEquals("2015-04-04 16:03:03", Fun.date(Fun.strtotime("-2 days", timeStamp)));
        Assert.assertEquals("2015-04-06 18:03:03", Fun.date(Fun.strtotime("+2 hours", timeStamp)));
    }

    @Test
    public void testTimeParse() {
        String time = "2022-01-24T02:27:02Z";
        LocalDateTime ldt = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX"));
        System.out.println(ldt);
    }

    @Test
    public void testStrtotimeUTCParse() {

        String[] dtStrings = {
                "2022-01-24T02:27:02+08:00",    // "yyyy-MM-dd'T'HH:mm:ssX"
                "2022-01-24T02:27:02+02:00",    // "yyyy-MM-dd'T'HH:mm:ssX"
                "2022-01-24T02:27:02-0500",    // "yyyy-MM-dd'T'HH:mm:ssX"
                "2022-01-24T14:19:00Z",     // "yyyy-MM-dd'T'HH:mm:ssX"
                "2022-01-24T14:19:01z",   // "yyyy-MM-dd'T'HH:mm:ss.SSS'z'"
                "2022-01-24T14:19:01",   // "yyyy-MM-dd'T'HH:mm:ss"
                "2022-01-24 14:19:01"   // "yyyy-MM-dd HH:mm:ss"
        };

        for (String dtString : dtStrings) {
            long timestamp = Fun.strtotime(dtString);
            String date = Fun.date(timestamp);
            System.out.println(date);
        }

        System.out.println(Fun.date(Fun.strtotime()));
    }

    @Test
    public void testStrlen() {
        Assert.assertEquals(10, Fun.length("Hello, 中国！"));
    }

    @Test
    public void testStrLowerAndUpper() {
        String str = null;
        Assert.assertEquals("helloworld", Fun.toLowerCase("HelloWorld"));
        Assert.assertEquals("HELLOWORLD", Fun.toUpperCase("helloworld"));
    }

    @Test
    public void testRemove() {

        Assert.assertNull(Fun.remove(null, "o"));

        Assert.assertEquals("HelloWorld", Fun.remove("HelloWorld", null));
        Assert.assertEquals("HelloWorld", Fun.remove("HelloWorld", ""));
        Assert.assertEquals("HellWrld0", Fun.remove("HelloWorld0", "o"));
        Assert.assertEquals("HellWrld", Fun.removeAny("HelloWorld0", "o", "0"));

        Assert.assertEquals("com.abc.com", Fun.removePrefix("com.abc.com", ""));
        Assert.assertEquals(".abc.com", Fun.removePrefix("com.abc.com", "com"));
        Assert.assertEquals("com.abc.", Fun.removeSuffix("com.abc.com", "com"));

        Assert.assertEquals("//abc", Fun.removePrefix("////abc", "//"));
        Assert.assertEquals("abc", Fun.removePrefixComplete("////abc", "//"));

        Assert.assertEquals("abc##", Fun.removeSuffix("abc####", "##"));
        Assert.assertEquals("abc", Fun.removeSuffixComplete("abc####", "##"));
    }

    @Test
    public void testRemoveLines() {
        String str = "<html>\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\">\n";

        Assert.assertEquals("<html><head><meta charset=\"utf-8\">", Fun.removeLines(str));
    }

    @Test
    public void testRepeat() {
        Assert.assertEquals("", Fun.repeat('a', 0));
        Assert.assertEquals("", Fun.repeat('a', -2));
        Assert.assertEquals("aaa", Fun.repeat('a', 3));

        Assert.assertEquals("", Fun.repeat("ab", 0));
        Assert.assertEquals("ababab", Fun.repeat("ab", 3));
    }

    @Test
    public void testPad() {
        Assert.assertEquals("abc", Fun.padLeft("abc", 1));
        Assert.assertEquals("abc", Fun.padLeft("abc", -1));
        Assert.assertEquals("       abc", Fun.padLeft("abc", 10));
        Assert.assertEquals("*******abc", Fun.padLeft("abc", 10, '*'));
        Assert.assertEquals("0000000abc", Fun.padLeft("abc", 10, "0"));
        Assert.assertEquals("0101010abc", Fun.padLeft("abc", 10, "01"));
        Assert.assertEquals("0120120abc", Fun.padLeft("abc", 10, "012"));

        Assert.assertEquals("abc", Fun.padRight("abc", 1));
        Assert.assertEquals("abc", Fun.padRight("abc", -1));
        Assert.assertEquals("abc       ", Fun.padRight("abc", 10));
        Assert.assertEquals("abc*******", Fun.padRight("abc", 10, '*'));
        Assert.assertEquals("abc0000000", Fun.padRight("abc", 10, "0"));
        Assert.assertEquals("abc0101010", Fun.padRight("abc", 10, "01"));
        Assert.assertEquals("abc0120120", Fun.padRight("abc", 10, "012"));
    }

    @Test
    public void testSubstring() {
        Assert.assertEquals("f", Fun.substring("abcdef", -1));
        Assert.assertEquals("ef", Fun.substring("abcdef", -2));
        Assert.assertEquals("f", Fun.substring("abcdef", 5));
        Assert.assertEquals("", Fun.substring("abcdef", 6));

        Assert.assertEquals("bc", Fun.substring("abcdef", 1, 3));
        Assert.assertEquals("bcdef", Fun.substring("abcdef", 1, 6));
        Assert.assertEquals("bcde", Fun.substring("abcdef", 1, -1));
        Assert.assertEquals("", Fun.substring("abcdef", 4, -4));
    }

    @Test
    public void testSubstr() {
        Assert.assertEquals("f", Fun.substr("abcdef", -1));
        Assert.assertEquals("ef", Fun.substr("abcdef", -2));
        Assert.assertEquals("f", Fun.substr("abcdef", 5));
        Assert.assertEquals("", Fun.substr("abcdef", 6));

        Assert.assertEquals("abc", Fun.substr("abcdef", 0, 3));
        Assert.assertEquals("abcdef", Fun.substr("abcdef", 0, 6));
        Assert.assertEquals("abcdef", Fun.substr("abcdef", 0, 7));
        Assert.assertEquals("bcde", Fun.substr("abcdef", 1, -1));
        Assert.assertEquals("cde", Fun.substr("abcdef", 2, -1));
        Assert.assertEquals("", Fun.substr("abcdef", 4, -4));
    }

    @Test
    public void testSplit() {
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, "a b c".split(" "));
        Assert.assertArrayEquals(new String[]{"a", "", "b", "", "c"}, "a  b  c".split(" "));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, "a#b#c".split("#"));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, "a#b#c#".split("#"));
        Assert.assertArrayEquals(new String[]{"", "a", "b", "c"}, "#a#b#c#".split("#"));
        Assert.assertArrayEquals(new String[]{"", "a", "", "b", "", "c"}, "#a##b##c#".split("#"));
        Assert.assertArrayEquals(new String[]{"", "a", "", "b", "", "c"}, "#a##b##c#".split("#"));

        Assert.assertArrayEquals(new String[]{}, Fun.split(null));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, Fun.split("a b c"));
        Assert.assertArrayEquals(new String[]{"a", "", "b", "", "c"}, Fun.split("a  b  c"));
        Assert.assertArrayEquals(new String[]{"a#b#c"}, Fun.split("a#b#c", null));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, Fun.split("a#b#c", '#'));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, Fun.split("a#b#c", "#"));
        Assert.assertArrayEquals(new String[]{"", "a", "b", "c", ""}, Fun.split("#a#b#c#", "#"));
        Assert.assertArrayEquals(new String[]{"", "a", "", "b", "", "c", ""}, Fun.split("#a##b##c#", "#"));

        Assert.assertArrayEquals(new String[]{}, Fun.splitTrim(null));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, Fun.splitTrim("a b c"));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, Fun.splitTrim("a  b  c"));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, Fun.splitTrim("a  b  c"));
        Assert.assertArrayEquals(new String[]{"a#b#c"}, Fun.splitTrim("a#b#c", null));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, Fun.splitTrim("a#b#c", '#'));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, Fun.splitTrim("a#b#c", "#"));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, Fun.splitTrim("#a#b#c#", "#"));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, Fun.splitTrim("#a##b##c#", "#"));
        Assert.assertArrayEquals(new String[]{"#a", "b", "c#"}, Fun.splitTrim("#a##b##c#", "##"));

        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        Assert.assertEquals(list, Fun.splitToList("a b c"));
        Assert.assertEquals(list, Fun.splitToList("a#b#c", '#'));
        Assert.assertEquals(list, Fun.splitToList("a#b#c", "#"));

        Assert.assertEquals(list, Fun.splitTrimToList("a b c"));
        Assert.assertEquals(list, Fun.splitTrimToList("a#b#c", '#'));
        Assert.assertEquals(list, Fun.splitTrimToList("a#b#c", "#"));
        Assert.assertEquals(list, Fun.splitTrimToList("#a#b#c#", "#"));
        Assert.assertEquals(list, Fun.splitTrimToList("#a##b##c#", "#"));

        Assert.assertArrayEquals(new int[]{3, 2, 0, 5}, Fun.splitTrimToInt("3 2 0 5"));
        Assert.assertArrayEquals(new long[]{3, 2, 0, 5}, Fun.splitTrimToLong("3 2 0 5"));
        Assert.assertArrayEquals(new int[]{3, 2, 0, 5}, Fun.splitTrimToInt("#3##2#0##5#", "#"));
        Assert.assertArrayEquals(new int[]{3, 1, 0, 5}, Fun.splitTrimToInt("#3##1#0#a#5#", "#"));
        Assert.assertArrayEquals(new long[]{3, 1, 0, 5}, Fun.splitTrimToLong("#3##1#0#a#5#", "#"));
    }

    @Test
    public void testJoin() {
        String a = null;
        short[] shorts = {};
        int[] ints = {2, 3, 4};
        long[] longs = {2, 3, 4};
        char[] chars = {'A', 'B', 'C'};
        float[] floats = {1.3F, 2.4F, 3.5F};
        double[] doubles = {1.3D, 2.4D, 3.5D};
        boolean[] booleans = {true, false};
        String[] strs = null;
        String[] strs1 = {"aa", "bb", "cc"};
        String[] strs2 = {"aa", null, "cc"};
        String[] strs3 = {null, "bb", "cc"};
        String[] strs4 = {};
        String[] strs5 = {null};
        String[] strs6 = {null, null, "cc", "dd"};
        String[] strs7 = {null, null, "", "dd"};

        List<Integer> strList1 = new ArrayList<>();
        strList1.add(2);
        strList1.add(3);
        strList1.add(4);

        List<String> strList2 = new ArrayList<>();

        List<String> strList3 = new ArrayList<>();
        strList3.add("aa");
        strList3.add("bb");
        strList3.add("cc");

        List<String> strList4 = new LinkedList<>();
        strList4.add("aa");
        strList4.add(null);
        strList4.add("cc");

        Set<String> strSets1 = new LinkedHashSet<>();
        strSets1.add(null);
        strSets1.add("bb");
        strSets1.add("cc");

        Set<String> strSets2 = new LinkedHashSet<>();
        strSets2.add(null);
        strSets2.add(null);
        strSets2.add("bb");
        strSets2.add("cc");

        Set<String> strSets3 = new LinkedHashSet<>();
        strSets3.add(null);
        strSets3.add(null);
        strSets3.add("bb");
        strSets3.add("cc");
        strSets3.add(null);
        strSets3.add("dd");

        Set<String> strSets4 = new LinkedHashSet<>();
        strSets4.add(null);
        strSets4.add(null);
        strSets4.add("bb");
        strSets4.add("");
        strSets4.add(null);
        strSets4.add("dd");

        Set<ObjectClass> objSets = new LinkedHashSet<>();
        objSets.add(new ObjectClass(1, "aa"));
        objSets.add(new ObjectClass(2, "bb"));

        Assert.assertNull(Fun.join(strs, ","));
        Assert.assertEquals("234", join(ints, a));
        Assert.assertEquals("234", join(ints, null));
        Assert.assertEquals("", join(shorts, ","));
        Assert.assertEquals("2,3,4", join(ints, ","));
        Assert.assertEquals("2,3,4", join(longs, ","));
        Assert.assertEquals("A,B,C", join(chars, ","));
        Assert.assertEquals("1.3,2.4,3.5", join(floats, ","));
        Assert.assertEquals("1.3,2.4,3.5", join(doubles, ","));
        Assert.assertEquals("true,false", join(booleans, ","));
        Assert.assertEquals("aa,bb,cc", Fun.join(strs1, ","));
        Assert.assertEquals("aa,cc", Fun.join(strs2, ","));
        Assert.assertEquals("bb,cc", Fun.join(strs3, ","));
        Assert.assertEquals("", Fun.join(strs4, ","));
        Assert.assertEquals("", Fun.join(strs5, ","));
        Assert.assertEquals("cc,dd", Fun.join(strs6, ","));
        Assert.assertEquals(",dd", Fun.join(strs7, ","));

        Assert.assertEquals("2,3,4", Fun.join(strList1, ","));
        Assert.assertEquals("", Fun.join(strList2, ","));
        Assert.assertEquals("aa,bb,cc", Fun.join(strList3, ","));
        Assert.assertEquals("aa,cc", Fun.join(strList4, ","));
        Assert.assertEquals("bb,cc", Fun.join(strSets1, ","));
        Assert.assertEquals("bb,cc", Fun.join(strSets2, ","));
        Assert.assertEquals("bb,cc,dd", Fun.join(strSets3, ","));
        Assert.assertEquals("bb,,dd", Fun.join(strSets4, ","));

        Assert.assertEquals("ObjectClass{id=1, name='aa'};ObjectClass{id=2, name='bb'}", Fun.join(objSets, ";"));
    }

    @Test
    public void testLeftRight() {
        String str1 = null;
        String str2 = "HelloWorld";
        Assert.assertNull(Fun.left(str1, 3));
        Assert.assertEquals("Hel", Fun.left(str2, 3));
        Assert.assertEquals("HelloWorld", Fun.left(str2, 15));
        Assert.assertEquals("rld", Fun.right(str2, 3));
        Assert.assertEquals("HelloWorld", Fun.right(str2, 15));
    }

    @Test
    public void testReverse() {
        Assert.assertEquals("dlroWolleH", Fun.reverse("HelloWorld"));
    }

    @Test
    public void testMatches() {
        Pattern pattern = Pattern.compile("(foo)(bar).*");
        Assert.assertTrue(Fun.matches("(foo)(bar)(baz)", "foobarbaz"));
        Assert.assertFalse(Fun.matches("(foo)(bar)(baa)", "foobarbaz"));

        Assert.assertTrue(Fun.matches(pattern, "foobarbaz"));
    }

    @Test
    public void testRegexMatch() {
        Pattern pattern = Pattern.compile("(foo)(bar)(baz)");
        List<String> results = new ArrayList<>();
        results.add("foobarbaz");
        results.add("foo");
        results.add("bar");
        results.add("baz");

        Assert.assertEquals("foobarbaz", Fun.regexMatch("(foo)(bar)(baz)", "foobarbaz", 0));
        Assert.assertEquals("bar", Fun.regexMatch("(foo)(bar)(baz)", "foobarbaz", 2));
        Assert.assertEquals("bar", Fun.regexMatch(pattern, "foobarbaz", 2));

        Assert.assertEquals(results, Fun.regexMatch("(foo)(bar)(baz)", "foobarbaz"));
        Assert.assertEquals(results, Fun.regexMatch(pattern, "foobarbaz"));
    }

    @Test
    public void testIndexOf() {
        String str1 = null;
        String str2 = "HelloWorld";
        String search = null;

        Assert.assertEquals(-1, Fun.indexOf(str1, "e"));
        Assert.assertEquals(-1, Fun.indexOf(str1, null));
        Assert.assertEquals(1, Fun.indexOf(str2, "e"));
        Assert.assertEquals(6, Fun.indexOf(str2, "o", 5));
        Assert.assertEquals(5, Fun.indexOfCase(str2, "w"));
        Assert.assertEquals(6, Fun.indexOfCase(str2, "O", 5));
        Assert.assertEquals(8, Fun.lastIndexOf(str2, "l"));
        Assert.assertEquals(3, Fun.lastIndexOf(str2, "l", 4));
    }

    @Test
    public void testReplace() {
        Assert.assertNull(Fun.replace(null, null, null));
        Assert.assertEquals("", Fun.replace("", null, null));
        Assert.assertEquals("HelloWorld", Fun.replace("HelloWorld", null, null));
        Assert.assertEquals("HellWrld", Fun.replace("HelloWorld", "o", ""));
        Assert.assertEquals("HellKWKrld", Fun.replace("HelloWorld", "o", "K"));
        Assert.assertEquals("Hello-orld", Fun.replaceCase("HelloWorld", "w", "-"));
    }

    @Test
    public void testRandom() {
        System.out.println(Fun.random());
        System.out.println(Fun.randomInt());
        System.out.println(Fun.randomInt(100));
        System.out.println(Fun.randomInt(100, 10000));
        System.out.println(Fun.randomLong());
        System.out.println(Fun.randomLong(100));
        System.out.println(Fun.randomLong(100, 10000));
        System.out.println(Fun.randomString(16));
        System.out.println(Fun.randomLetter(16));
        System.out.println(Fun.randomNumber(16));
        System.out.println(Fun.randomPool(RandomFun.RANDOM_LOWER_LETTER, 16));
        System.out.println(Fun.randomStringExcepts(16, "0"));
    }

    @Test
    public void testMd5() {
        Assert.assertEquals("d41d8cd98f00b204e9800998ecf8427e", Fun.md5(Fun.EMPTY));
        Assert.assertEquals("df10ef8509dc176d733d59549e7dbfaf", Fun.md5("123456abc"));
        Assert.assertEquals("c3fcd3d76192e4007dfb496cca67e13b", Fun.md5("abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testSha() {
        Assert.assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", Fun.sha1(Fun.EMPTY));
        Assert.assertEquals("a172ffc990129fe6f68b50f6037c54a1894ee3fd", Fun.sha1("123456abc"));
        Assert.assertEquals("32d10c7b8cf96570ca04ce37f2a19d84240d3a89", Fun.sha1("abcdefghijklmnopqrstuvwxyz"));
        Assert.assertEquals("931145d4ddd1811be545e4ac88a81f1fdbfaf0779c437efba16b884595274d11", Fun.sha256("123456abc"));
        Assert.assertEquals("71c480df93d6ae2f1efad1447c66c9525e316218cf51fc8d9ed832f2daf18b73", Fun.sha256("abcdefghijklmnopqrstuvwxyz"));
        Assert.assertEquals("2545507ada3a26999b11ec0324ae76e0cdef629c4a28b24be3965d24e1c406180a8ef79626c77fb3f556bfd59ab54920", Fun.sha384("123456abc"));
        Assert.assertEquals("feb67349df3db6f5924815d6c3dc133f091809213731fe5c7b5f4999e463479ff2877f5f2936fa63bb43784b12f3ebb4", Fun.sha384("abcdefghijklmnopqrstuvwxyz"));
        Assert.assertEquals("8756869d440a13e93979197b5d7839c823de87c2b115bce0dee62030af3b5b63114a517f1ab02509bfefa88527369ae0ad7946990f27dcb37711a7d6fb9bc893", Fun.sha512("123456abc"));
        Assert.assertEquals("4dbff86cc2ca1bae1e16468a05cb9881c97f1753bce3619034898faa1aabe429955a1bf8ec483d7421fe3c1646613a59ed5441fb0f321389f77f48a879c7b1f1", Fun.sha512("abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testBase64() {
        Assert.assertEquals("", Fun.base64Encode(Fun.EMPTY));
        Assert.assertEquals("MTIzNDU2YWJj", Fun.base64Encode(Fun.bytes("123456abc")));
        Assert.assertEquals("MTIzNDU2YWJj", Fun.base64Encode("123456abc"));
        Assert.assertEquals("YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXo=", Fun.base64Encode("abcdefghijklmnopqrstuvwxyz"));
        Assert.assertEquals("aHR0cHM6Ly93d3cuYmFpZHUuY29tL3M/aWU9dXRmLTgmZj04JnJzdl9icD0xJnRuPWJhaWR1", Fun.base64Encode("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu"));
        Assert.assertEquals("aHR0cHM6Ly93d3cuYmFpZHUuY29tL3M_aWU9dXRmLTgmZj04JnJzdl9icD0xJnRuPWJhaWR1", Fun.base64UrlEncode("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu"));

        Assert.assertEquals("123456abc", Fun.base64Decode("MTIzNDU2YWJj"));
        Assert.assertEquals("abcdefghijklmnopqrstuvwxyz", Fun.base64Decode("YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXo="));
        Assert.assertEquals("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu", Fun.base64Decode("aHR0cHM6Ly93d3cuYmFpZHUuY29tL3M/aWU9dXRmLTgmZj04JnJzdl9icD0xJnRuPWJhaWR1"));
        Assert.assertEquals("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu", Fun.base64UrlDecode("aHR0cHM6Ly93d3cuYmFpZHUuY29tL3M_aWU9dXRmLTgmZj04JnJzdl9icD0xJnRuPWJhaWR1"));
    }

    @Test
    public void testIdEncodeDecode() {
        Assert.assertEquals("j0gW", Fun.idEncode(12345L));
        Assert.assertEquals("NkK9", Fun.idEncode(12345L, "this is my salt"));
        Assert.assertEquals("lej0gWbj", Fun.idEncode(12345L, 8));
        Assert.assertEquals("EDngB0NkK9A5ev1W", Fun.idEncode(12345L, "this is my salt", 16));

        Assert.assertEquals(12345L, Fun.idDecode("j0gW"));
        Assert.assertEquals(12345L, Fun.idDecode("NkK9", "this is my salt"));
        Assert.assertEquals(12345L, Fun.idDecode("lej0gWbj", 8));
        Assert.assertEquals(12345L, Fun.idDecode("EDngB0NkK9A5ev1W", "this is my salt", 16));
    }

    @Test
    public void testUrlVerify() {
        Assert.assertFalse(Fun.isUrl("http://www"));

        Assert.assertTrue(Fun.isUrl("http://www.ss"));

        Assert.assertTrue(Fun.isUrl("https://www.baidu.com/s?wd=%E9%A9%AC%E4%BA%91"));
        Assert.assertTrue(Fun.isUrl("//www.zhihu.com/collection/150270125?page=3"));
        Assert.assertTrue(Fun.isUrl("//static.ws.126.net/www/logo/logo-ipad-icon.png"));
        Assert.assertTrue(Fun.isUrl("http://www.siyst.org.cn/portal/article/index/id/12/cid/1.html"));
        Assert.assertTrue(Fun.isUrl("https://www.cnblogs.com/hubingxu/archive/2012/02/17/2355516.html"));
    }

    @Test
    public void testUrlParse() {
        Assert.assertNull(Fun.urlParse(""));
        Assert.assertNull(Fun.urlParse(" "));
        Assert.assertNull(Fun.urlParse("http://www"));

        URL url1 = Fun.urlParse("https://www.baidu.com/s?wd=%E9%A9%AC%E4%BA%91");
        Assert.assertEquals("https", url1.getProtocol());
        Assert.assertEquals("www.baidu.com", url1.getHost());
        Assert.assertEquals("/s", url1.getPath());
        Assert.assertEquals("/s?wd=%E9%A9%AC%E4%BA%91", url1.getFile());
        Assert.assertEquals("wd=%E9%A9%AC%E4%BA%91", url1.getQuery());

        URL url2 = Fun.urlParse("http://username:password@www.abc.com:8080/path?arg=value&parameters=passed#with-anchor");
        Assert.assertEquals(8080, url2.getPort());
        Assert.assertEquals("http", url2.getProtocol());
        Assert.assertEquals("username:password", url2.getUserInfo());
        Assert.assertEquals("arg=value&parameters=passed", url2.getQuery());
        Assert.assertEquals("with-anchor", url2.getRef());

        URL url3 = Fun.urlParse("//www.example.com/path?googleguy=googley");
        Assert.assertEquals("http", url3.getProtocol());
        Assert.assertEquals("www.example.com", url3.getHost());
    }

    @Test
    public void testSimilar() {
        System.out.println(Fun.similarity("", ""));
        System.out.println(Fun.similarity("ABC", "ABCD"));
        System.out.println(Fun.similarity("ABC", "AB"));
        System.out.println(Fun.similarity("ABC", "AB", 2));
        System.out.println(Fun.similarity("我是中国人，我说中国话", "我说中国话"));
        System.out.println(Fun.similarity("我是中国人，我说中国话", "中国", 4));
        System.out.println(Fun.similarity("【一点温暖·陕西西安】“一分钟就到”网约车：你感动我 我温暖你 |唐博|核酸_网易政务", "【一点温暖·陕西西安】“一分钟就到”网约车：你感动我 我温暖你"));

        System.out.println("--");
        System.out.println(SimilarityFun.strSim("ABC", "AB"));
        System.out.println(SimilarityFun.strSim("【一点温暖·陕西西安】“一分钟就到”网约车：你感动我 我温暖你 |唐博|核酸_网易政务", "【一点温暖·陕西西安】“一分钟就到”网约车：你感动我 我温暖你"));
    }

    @Test
    public void testCalculate() {
        Assert.assertEquals("22222222222222222", Fun.calculate("11111111111111111", "11111111111111111", '+').toString());
    }

    @Test
    public void testCamel() {
        Assert.assertEquals("", Fun.underToCamel(null));
        Assert.assertEquals("", Fun.underToCamel(""));
        Assert.assertEquals("_abc", Fun.underToCamel("_abc"));
        Assert.assertEquals("AAbc", Fun.underToCamel("a_abc"));
        Assert.assertEquals("AAbc", Fun.underToCamel("a__abc"));
        Assert.assertEquals("TestAbc", Fun.underToCamel("test_abc_"));
        Assert.assertEquals("TestAbc", Fun.underToCamel("test_abc"));
        Assert.assertEquals("TestAbcDe", Fun.underToCamel("test_ABc_dE"));


        Assert.assertEquals("test_abc_de", Fun.camelToUnder("TestAbcDe"));
        Assert.assertEquals("test_abc_de", Fun.camelToUnder("testAbcDe"));
        Assert.assertEquals("a_b_c_d", Fun.camelToUnder("ABCD"));
    }

    @Test
    public void testIsNumber() {
        Assert.assertTrue(Fun.isNumber("123"));
        Assert.assertTrue(Fun.isNumber("0123"));
        Assert.assertFalse(Fun.isNumber(""));
        Assert.assertFalse(Fun.isNumber("a"));
        Assert.assertFalse(Fun.isNumber(null));
    }

    @Test
    public void testIsLetter() {
        Assert.assertTrue(Fun.isLetter("a"));
        Assert.assertFalse(Fun.isLetter(""));
        Assert.assertFalse(Fun.isLetter(null));
        Assert.assertFalse(Fun.isLetter("123"));
    }
}
