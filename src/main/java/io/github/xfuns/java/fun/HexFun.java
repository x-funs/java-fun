package io.github.xfuns.java.fun;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * HexFun Hex 库
 *
 * @author smallmenu
 */
public class HexFun {

    /**
     * DEFAULT_CHARSET
     */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /**
     * Hex Code
     */
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    /**
     * Hex Code
     */
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * charset
     */
    private final Charset charset;

    /**
     * Construct
     */
    public HexFun() {
        charset = DEFAULT_CHARSET;
    }

    /**
     * Construct
     *
     * @param charset Charset
     */
    public HexFun(final Charset charset) {
        this.charset = charset;
    }

    /**
     * Get charset
     *
     * @return Charset
     */
    public Charset getCharset() {
        return this.charset;
    }

    /**
     * Get charset
     *
     * @return String
     */
    public String getCharsetName() {
        return this.charset.name();
    }

    /**
     * Construct
     *
     * @param charsetName 字符集
     */
    public HexFun(final String charsetName) {
        this(Charset.forName(charsetName));
    }

    /**
     * toDigit
     *
     * @param ch    char
     * @param index int
     * @return int
     * @throws Exception e
     */
    protected static int toDigit(final char ch, final int index) throws Exception {
        final int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new Exception("Illegal hexadecimal character " + ch + " at index " + index);
        }

        return digit;
    }

    /**
     * decodeHex
     *
     * @param data 字节数组
     * @return byte[]
     * @throws Exception e
     */
    public static byte[] decodeHex(final char[] data) throws Exception {
        final byte[] out = new byte[data.length >> 1];
        decodeHex(data, out, 0);

        return out;
    }

    /**
     * decodeHex
     *
     * @param data      字节数组
     * @param out       byte[]
     * @param outOffset int
     * @return int
     * @throws Exception e
     */
    public static int decodeHex(final char[] data, final byte[] out, final int outOffset) throws Exception {
        final int len = data.length;

        if ((len & 0x01) != 0) {
            throw new Exception("Odd number of characters.");
        }

        final int outLen = len >> 1;
        if (out.length - outOffset < outLen) {
            throw new Exception("Output array is not large enough to accommodate decoded data.");
        }

        for (int i = outOffset, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }

        return outLen;
    }

    /**
     * decodeHex
     *
     * @param data 字节数组
     * @return byte[]
     * @throws Exception e
     */
    public static byte[] decodeHex(final String data) throws Exception {
        return decodeHex(data.toCharArray());
    }

    /**
     * encodeHexString
     *
     * @param data 字节数组
     * @return String
     */
    public static String encodeHexString(final byte[] data) {
        return new String(encodeHex(data));
    }

    /**
     * encodeHexString
     *
     * @param data        字节数组
     * @param toLowerCase boolean
     * @return String
     */
    public static String encodeHexString(final byte[] data, final boolean toLowerCase) {
        return new String(encodeHex(data, toLowerCase));
    }


    /**
     * encodeHex
     *
     * @param data 字节数组
     * @return char[]
     */
    public static char[] encodeHex(final byte[] data) {
        return encodeHex(data, true);
    }

    /**
     * encodeHex
     *
     * @param data        字节数组
     * @param toLowerCase 大小写
     * @return char[]
     */
    public static char[] encodeHex(final byte[] data, final boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    /**
     * encodeHex
     *
     * @param data     字节数组
     * @param toDigits char[]
     * @return char[]
     */
    protected static char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        encodeHex(data, 0, data.length, toDigits, out, 0);

        return out;
    }

    /**
     * encodeHex
     *
     * @param data        字节数组
     * @param dataOffset  int
     * @param dataLen     int
     * @param toLowerCase boolean
     * @return char[]
     */
    public static char[] encodeHex(final byte[] data, final int dataOffset, final int dataLen, final boolean toLowerCase) {
        final char[] out = new char[dataLen << 1];
        encodeHex(data, dataOffset, dataLen, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER, out, 0);

        return out;
    }

    /**
     * encodeHex
     *
     * @param data        字节数组
     * @param dataOffset  int
     * @param dataLen     int
     * @param toLowerCase boolean
     * @param out         char[]
     * @param outOffset   int
     */
    public static void encodeHex(final byte[] data, final int dataOffset, final int dataLen, final boolean toLowerCase, final char[] out, final int outOffset) {
        encodeHex(data, dataOffset, dataLen, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER, out, outOffset);
    }

    /**
     * encodeHex
     *
     * @param data       字节数组
     * @param dataOffset int
     * @param dataLen    int
     * @param toDigits   char[]
     * @param out        char[]
     * @param outOffset  int
     */
    private static void encodeHex(final byte[] data, final int dataOffset, final int dataLen, final char[] toDigits, final char[] out, final int outOffset) {
        for (int i = dataOffset, j = outOffset; i < dataOffset + dataLen; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
    }
}
