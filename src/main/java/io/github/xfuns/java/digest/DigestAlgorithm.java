package io.github.xfuns.java.digest;

/**
 * DigestAlgorithm 摘要算法
 *
 * @author smallmenu
 */
public enum DigestAlgorithm {
    /**
     * MD5
     */
    MD5("MD5"),

    /**
     * SHA1
     */
    SHA1("SHA-1"),

    /**
     * SHA256
     */
    SHA256("SHA-256"),

    /**
     * SHA384
     */
    SHA384("SHA-384"),

    /**
     * SHA512
     */
    SHA512("SHA-512");

    private final String value;

    /**
     * Construct
     *
     * @param value 字符串表示
     */
    DigestAlgorithm(String value) {
        this.value = value;
    }

    /**
     * 字符串表示
     *
     * @return String
     */
    public String value() {
        return this.value;
    }
}
