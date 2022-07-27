package io.github.xfuns.java.fun;

import static io.github.xfuns.java.Fun.removeSign;

/**
 * SimilarFun
 *
 * @author smallmenu
 */
public class SimilarityFun {
    /**
     * 禁止实例化
     */
    private SimilarityFun() {
        throw new AssertionError();
    }

    /**
     * 计算相似度，两个都是空串相似度为1，被认为是相同的串
     *
     * @param strA 字符串1
     * @param strB 字符串2
     * @return 相似度
     */
    public static double similarity(String strA, String strB) {
        String newStrA, newStrB;
        if (strA.length() < strB.length()) {
            newStrA = removeSign(strB);
            newStrB = removeSign(strA);
        } else {
            newStrA = removeSign(strA);
            newStrB = removeSign(strB);
        }

        // 用较大的字符串长度作为分母，相似子串作为分子计算出字串相似度
        int temp = Math.max(newStrA.length(), newStrB.length());

        if (0 == temp) {
            // 两个都是空串相似度为1，被认为是相同的串
            return 1;
        }

        int temp2 = longestCommonSubstring(newStrA, newStrB).length();

        return NumberFun.div(temp2, temp);
    }

    /**
     * 计算相似度百分比
     *
     * @param strA  字符串1
     * @param strB  字符串2
     * @param scale 保留小数
     * @return 百分比
     */
    public static String similarity(String strA, String strB, int scale) {
        return NumberFun.formatPercent(similarity(strA, strB), scale);
    }

    /**
     * 求公共子串，采用动态规划算法。 其不要求所求得的字符在所给的字符串中是连续的。
     *
     * @param strA 字符串1
     * @param strB 字符串2
     * @return 公共子串
     */
    private static String longestCommonSubstring(String strA, String strB) {
        char[] charsStrA = strA.toCharArray();
        char[] charsStrB = strB.toCharArray();
        int m = charsStrA.length;
        int n = charsStrB.length;

        int[][] matrix = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (charsStrA[i - 1] == charsStrB[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
                }
            }
        }

        char[] result = new char[matrix[m][n]];
        int currentIndex = result.length - 1;
        while (matrix[m][n] != 0) {
            if (matrix[m][n] == matrix[m][n - 1]) {
                n--;
            } else if (matrix[m][n] == matrix[m - 1][n]) {
                m--;
            } else {
                result[currentIndex] = charsStrA[m - 1];
                currentIndex--;
                n--;
                m--;
            }
        }
        return new String(result);
    }

    public static double strSim(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        if (len1 == 0 || len2 == 0) {
            return 1;
        }

        double lcs = lcs(a, b) + 0.0;
        double max = Math.max(len1, len2) + 0.0;

        return lcs / max;
    }

    public static int lcs(String x, String y) {

        int m = x.length();
        int n = y.length();
        if (m == 0 || n == 0) {
            return 0;
        }
        int[][] opt = new int[m + 1][n + 1];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (x.charAt(i) == y.charAt(j)) {
                    opt[i][j] = opt[i + 1][j + 1] + 1;
                } else {
                    opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);
                }
            }
        }

        return opt[0][0];
    }
}
