package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你一个字符串，你要在它所有的回文子串中，找到长度最长的子串，并返回它。
 *
 * 比如说，给你的字符串是：
 *
 * abcbab
 *
 * 你要返回的最长回文子串是：
 *
 * abcba
 *
 * 解法1：暴力法，使用两层for循环可以取出任意的一个子串，然后再判断这个字符串是否是一个回文字符串，在求解过程中，保存最大的回文子串
 * 或是它的下标和长度即可。
 *
 * 解法2：动态规划，通过子问题的解求解原问题的解，使用S(i,j)表示子串，显然：
 * 当i==j时，这时只有一个字符，这时一定是一个回文字符串；
 * 当i和j相邻时即i+1=j,这是只有两个字符，S(i,j) = S(i)==S(j)；
 * 当i与j不相邻时，S(i,j) = S(i)==S(j) && S(i+1, j-1)
 * 因此我们只需要一个二维数组来子串是否回文这个信息。
 *
 * 另外，我们在判断s(i,j)是否回文时，需要先知道S(i+1, j-1)是否回文，因此在两层循环中i需要从大到小进行遍历（n-1->0）,j要从小
 * 到大进行遍历（i->n-1）
 *
 * 解法3：从中心向两边扩展，遍历这个字符串，每次都把当前字符作为回文串的中心，然后向两边扩展，最终得到以这个字符为中心的回文串，比如
 * 以abcbab为例：
 * 以a为中心向两边扩展
 * 以b为中心向两边扩展
 * 以c为中心向两边扩展
 * ...
 * 以b为中心向两边扩展
 *
 * 是不是这样就可以了呢？No，上面只考虑了回文字符串是奇数的情况，也就是回文字符串的正中心只有一个字符，但是还有一种情况，回文字符串的
 * 中心是两个相等的字符，因此这种情况也要考虑，比如当我们访问到字符c时，一种情况是游标i和j都指向c，以它为中心然后向两边扩展；另一种
 * 情况是i指向c,j指向它的下一位b，并向两边扩展，需要注意在向两边扩展对比字符是否相等时，初始的c和b也是需要对比的。
 */
public class LongestPalindromeString_26 {
    // Time: O(n^2), Space: O(n^2)
    public String longestPalindromeDP(String s) {
        //边界：当字符串为null，或者长度为0时，返回空串
        if (s == null || s.length() == 0) return "";

        //初始化字符串长度
        int n = s.length();

        //最长回文子串下标start和它的长度
        int start = 0, maxLen = 0;

        //定义一个二维数组来保存S(i，j)是否是回文子串
        boolean[][] d = new boolean[n][n];

        //i需要从大到小进行遍历
        for (int i = n - 1; i >= 0; --i) {
            //j则是从i开始，从小到大进行遍历
            for (int j = i; j < n; ++j) {
                //i==j只有一个字符
                if (i == j) d[i][j] = true;
                //i+1==j，i和j相邻
                else if (i+1 == j) d[i][j] = s.charAt(i) == s.charAt(j);
                //i和j不相邻
                else d[i][j] = s.charAt(i) == s.charAt(j) && d[i+1][j-1];


                //判断，如果S(i,j)是回文字符串，并且j-i+1是大于最大回文子串长度，则更新最长回文子串开始下标start和它的长度
                if (d[i][j] && (j-i+1) > maxLen) {
                    start = i;
                    maxLen = j - i + 1;
                }
            }
        }

        return s.substring(start, start+maxLen);
    }

    int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left; ++right;
        }
        // (right-1) - (left+1) + 1
        return right - left - 1;
    }

    // Time: O(n^2), Space: O(1)
    public String longestPalindromeExpand(String s) {
        if (s == null || s.length() == 0) return "";
        int start = 0, maxLen = 0;
        for (int i = 0; i < s.length(); ++i) {
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i+1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                start = i - (len-1)/2;
                maxLen = len;
            }
        }
        return s.substring(start, start+maxLen);
    }
}
