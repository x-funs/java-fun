package io.github.xfuns.java.fun;

import io.github.xfuns.java.Fun;
import io.github.xfuns.java.digest.DigestAlgorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static io.github.xfuns.java.Fun.bytes;

/**
 * DigestFun 摘要算法库
 *
 * @author smallmenu
 */
public class DigestFun {

    /**
     * 禁止实例化
     */
    private DigestFun() {
        throw new AssertionError();
    }

    /**
     * 摘要算法工厂
     *
     * @param algorithm 摘要算法字符串表示
     * @return MessageDigest
     */
    public static MessageDigest getDigest(final String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * MD5 MessageDigest
     *
     * @return MessageDigest
     */
    public static MessageDigest getMd5Digest() {
        return getDigest(DigestAlgorithm.MD5.value());
    }

    /**
     * SHA1 MessageDigest
     *
     * @return MessageDigest
     */
    public static MessageDigest getSha1Digest() {
        return getDigest(DigestAlgorithm.SHA1.value());
    }

    /**
     * SHA256 MessageDigest
     *
     * @return MessageDigest
     */
    public static MessageDigest getSha256Digest() {
        return getDigest(DigestAlgorithm.SHA256.value());
    }

    /**
     * SHA384 MessageDigest
     *
     * @return MessageDigest
     */
    public static MessageDigest getSha384Digest() {
        return getDigest(DigestAlgorithm.SHA384.value());
    }

    /**
     * SHA512 MessageDigest
     *
     * @return MessageDigest
     */
    public static MessageDigest getSha512Digest() {
        return getDigest(DigestAlgorithm.SHA512.value());
    }

    /**
     * 生成摘要
     *
     * @param messageDigest MessageDigest
     * @param data          字节数组
     * @return byte[]
     */
    public static byte[] digest(final MessageDigest messageDigest, final byte[] data) {
        return messageDigest.digest(data);
    }

    /**
     * md5
     *
     * @param data 数据
     * @return byte[]
     */
    public static byte[] md5(final byte[] data) {
        return getMd5Digest().digest(data);
    }

    /**
     * MD5
     *
     * @param str 数据
     * @return byte[]
     */
    public static byte[] md5(final String str) {
        return md5(Fun.bytes(str));
    }

    /**
     * MD5
     *
     * @param data 数据
     * @return String
     */
    public static String md5Hex(final byte[] data) {
        return HexFun.encodeHexString(md5(data));
    }

    /**
     * MD5
     *
     * @param str 数据
     * @return String
     */
    public static String md5Hex(final String str) {
        return HexFun.encodeHexString(md5(str));
    }

    /**
     * SHA1
     *
     * @param data 数据
     * @return byte[]
     */
    public static byte[] sha1(final byte[] data) {
        return getSha1Digest().digest(data);
    }

    /**
     * SHA1
     *
     * @param str 数据
     * @return byte[]
     */
    public static byte[] sha1(final String str) {
        return sha1(Fun.bytes(str));
    }

    /**
     * SHA1
     *
     * @param data 数据
     * @return String
     */
    public static String sha1Hex(final byte[] data) {
        return HexFun.encodeHexString(sha1(data));
    }

    /**
     * SHA1
     *
     * @param str 数据
     * @return String
     */
    public static String sha1Hex(final String str) {
        return HexFun.encodeHexString(sha1(str));
    }

    /**
     * SHA256
     *
     * @param data 数据
     * @return byte[]
     */
    public static byte[] sha256(final byte[] data) {
        return getSha256Digest().digest(data);
    }

    /**
     * SHA256
     *
     * @param str 数据
     * @return byte[]
     */
    public static byte[] sha256(final String str) {
        return sha256(Fun.bytes(str));
    }

    /**
     * SHA256
     *
     * @param data 数据
     * @return String
     */
    public static String sha256Hex(final byte[] data) {
        return HexFun.encodeHexString(sha256(data));
    }

    /**
     * SHA256
     *
     * @param str 数据
     * @return String
     */
    public static String sha256Hex(final String str) {
        return HexFun.encodeHexString(sha256(str));
    }

    /**
     * SHA384
     *
     * @param data 数据
     * @return byte[]
     */
    public static byte[] sha384(final byte[] data) {
        return getSha384Digest().digest(data);
    }

    /**
     * SHA384
     *
     * @param str 数据
     * @return byte[]
     */
    public static byte[] sha384(final String str) {
        return sha384(Fun.bytes(str));
    }

    /**
     * SHA384
     *
     * @param data 数据
     * @return String
     */
    public static String sha384Hex(final byte[] data) {
        return HexFun.encodeHexString(sha384(data));
    }

    /**
     * SHA384
     *
     * @param str 数据
     * @return String
     */
    public static String sha384Hex(final String str) {
        return HexFun.encodeHexString(sha384(str));
    }

    /**
     * SHA512
     *
     * @param data 数据
     * @return byte[]
     */
    public static byte[] sha512(final byte[] data) {
        return getSha512Digest().digest(data);
    }

    /**
     * SHA512
     *
     * @param str 数据
     * @return byte[]
     */
    public static byte[] sha512(final String str) {
        return sha512(Fun.bytes(str));
    }

    /**
     * SHA512
     *
     * @param data 数据
     * @return String
     */
    public static String sha512Hex(final byte[] data) {
        return HexFun.encodeHexString(sha512(data));
    }

    /**
     * SHA512
     *
     * @param str 数据
     * @return String
     */
    public static String sha512Hex(final String str) {
        return HexFun.encodeHexString(sha512(str));
    }
}
