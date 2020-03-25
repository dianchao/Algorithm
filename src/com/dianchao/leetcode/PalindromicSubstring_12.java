package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你一个字符串，你要计算出它所包含的回文子串个数。只要起始下标或终止下标不同，即使子串相同，我们也认为是不同的回文子串。
 *
 * 比如说，给你的字符串是：
 *
 * abc
 *
 * 这个字符串中总共有 3 个回文子串，分别是 a， b 和 c。因此你要返回的个数是 3。
 *
 * 再比如说，给你的字符串是：
 *
 * aba
 *
 * 这个字符串中总共有 4 个回文子串，分别是 a，b，a，和 aba。因此你要返回的个数是 4。
 *
 * 思路：
 * 方法1：暴力法
 * 使用两层for循环取出任意的字串，然后再判断这个字符是否是回文字符串，如果是则回文子串的个数加1.
 * 回文子串S(i,j):
 * 1）当i=j时，只有一个字符，该子串一定是回文字符串
 * 2）当i+1==j时，有两个字符，判断s(i)==s(j)
 * 3）i和j不相邻时，除了判断s(i)==s(j) 还需要判断s(i+1, j-1)子串是否回文
 * 因此在两层循环中i：n-1-> 0遍历  j：i->n-1
 * 这种方法的关注点是拿来一个子串，然后判断它是否是回文字符串，我们还可以换种思路遍历子串每次都将当前字符作为回文串的中心
 * 然后向两边扩展当两边的字符串相等时，则增加一个回文子串计数。
 *
 */
public class PalindromicSubstring_12 {
    //动态规划方法
    // Time: O(n^2), Space: O(n^2)
    public int countPalindromicSubstringsDP(String s) {
        //当空串或空白串时返回0
        if (s == null || s.length() == 0) return 0;

        int n = s.length(), count = 0;

        //定义二维数组，来标识s(i，j)是否是回文子串
        boolean[][] d = new boolean[n][n];

        //i要从大到小遍历
        for (int i = n - 1; i >= 0; --i) {
            //j是从i开始，从小到大遍历
            for (int j = i; j < n; ++j) {
                //说明当前子串只有一个字符，因此是回文子串
                if (i == j) d[i][j] = true;
                //只需要判断i和j指向的字符是否相等
                else if (i+1 == j) d[i][j] = s.charAt(i) == s.charAt(j);
                //其他情况表示i和j不相邻，此时除了比较i和j的字符是否相等还有确定S(i+1, j-1)是都是回文子串
                else d[i][j] = s.charAt(i) == s.charAt(j) && d[i+1][j-1];

                if (d[i][j]) ++count;
            }
        }

        return count;
    }

    /**
     * 向两边扩展的左右游标
     * @param s：字符串
     * @param left
     * @param right
     * @return 扩展过程中统计的回文子串的个数
     */
    int expand(String s, int left, int right) {
        int count = 0;
        //当左右游标分别不超过边界且它们指向的字符相等时，说明两个游标之间的子串是回文子串
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            ++count;
            //将游标向两边移动
            --left; ++right;
        }
        return count;
    }

    //解法2：从中心想两边扩展，遍历字符串，然后将当前元素作为回文串的中心向两边扩展
    // Time: O(n^2), Space: O(1)
    public int countPalindromicSubstringsExpand(String s) {
        //当字符串为空，或者是空白子串串时返回为0
        if (s == null || s.length() == 0) return 0;

        //初始化为0
        int count = 0;

        //遍历子串
        for (int i = 0; i < s.length(); ++i) {
            //以i为中心，向两边扩展
            count += expand(s, i, i);
            //以i和i+1字符为中心，向两边扩展
            count += expand(s, i, i+1);
        }

        return count;
    }
}
