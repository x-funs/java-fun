package io.github.xfuns.java.fun;

import io.github.xfuns.java.Fun;

/**
 * DataSizeFun
 *
 * @author smallmenu
 */
final public class DataSizeFun {
    /**
     * SIZE_FORMAT
     */
    public static final String[] DATASIZE_FORMAT = {"B", "KB", "MB", "GB", "TB", "PB"};

    /**
     * B
     */
    public static final String B = "B";

    /**
     * KB
     */
    public static final String KB = "KB";

    /**
     * MB
     */
    public static final String MB = "MB";

    /**
     * GB
     */
    public static final String GB = "GB";

    /**
     * TB
     */
    public static final String TB = "TB";

    /**
     * PB
     */
    public static final String PB = "PB";

    /**
     * Bytes per Kilobyte(KB).
     */
    public static final long BYTES_PER_KB = 1024;

    /**
     * Bytes per Megabyte(MB).
     */
    public static final long BYTES_PER_MB = BYTES_PER_KB * 1024;

    /**
     * Bytes per Gigabyte(GB).
     */
    public static final long BYTES_PER_GB = BYTES_PER_MB * 1024;

    /**
     * Bytes per Terabyte(TB).
     */
    public static final long BYTES_PER_TB = BYTES_PER_GB * 1024;

    /**
     * Bytes per Terabyte(PB).
     */
    public static final long BYTES_PER_PB = BYTES_PER_TB * 1024;

    /**
     * 禁止实例化
     */
    private DataSizeFun() {
        throw new AssertionError();
    }

    /**
     * 字节数格式化转换大小（自动）
     *
     * @param bytes 字节大小
     * @return String
     */
    public static String formatString(long bytes) {
        int index = 0;
        while (bytes >= BYTES_PER_KB) {
            bytes /= BYTES_PER_KB;
            index++;
        }

        if (index < DATASIZE_FORMAT.length) {
            return bytes + DATASIZE_FORMAT[index];
        }

        return Fun.EMPTY;
    }

    /**
     * 字节数格式化转换大小（指定单位）
     *
     * @param bytes  字节大小
     * @param suffix 后缀标识
     * @return String
     */
    public static String formatString(long bytes, final String suffix) {
        if (ArrayFun.contains(DATASIZE_FORMAT, suffix)) {
            long size = format(bytes, suffix);

            return size + suffix;
        }

        return Fun.EMPTY;
    }

    /**
     * 字节数格式化转换大小（指定单位）
     *
     * @param bytes  字节大小
     * @param suffix 后缀标识
     * @return long
     */
    public static long format(long bytes, final String suffix) {
        if (bytes > 0) {
            switch (suffix.toUpperCase()) {
                case KB:
                    return (bytes / BYTES_PER_KB);
                case MB:
                    return (bytes / BYTES_PER_MB);
                case GB:
                    return (bytes / BYTES_PER_GB);
                case TB:
                    return (bytes / BYTES_PER_TB);
                case PB:
                    return (bytes / BYTES_PER_PB);
                default:
                    return bytes;
            }
        }

        return bytes;
    }
}
