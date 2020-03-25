package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你一个整数，你要判断它是否是一个回文数字。所谓回文数字就是，你正着读和反着读都是同一个数字。
 *
 * 比如，给你的数字是：
 *
 * 12321
 *
 * 无论你从左向右读，还是从右向左读，都是 12321，所以它是一个回文数字，你要返回 true。
 *
 * 再比如说：
 *
 * -232
 *
 * 你从左向右读是 -232，但从右向左读则是 232-，和 -232 不一样，因此它不是一个回文数字，你要返回 false。
 *
 * 思路：
 * 最简单的方法是将整数转换为字符串，然后使用游标分别指向字符串的第一个字符和最后一个字符，不断对别i和j字符，如果
 * 出现不相同则返回false。如果循环结束都没有返回false，则表示是回文字符串。
 *
 * 如果不能将整数转换为字符串，则构造出从右向左读的数字然后比较是否相等，比如123
 * y = y*10 + num
 * 0 * 10 + 3 = 3
 * 3 * 10 + 2 = 32
 * 32 * 10 + 1 = 321
 * 321
 */
public class IsPalindromeNumber_13 {

    // Time: O(m), Space: O(1)
    public boolean isPalindromeString(int x) {
        //将整数转为字符串
        String str = String.valueOf(x);
        //定义游标i和j
        int i = 0, j = str.length() - 1;
        //当i<j时循环
        while (i < j) {
            //如果i和j所指向的字符不相等则返回false
            if (str.charAt(i) != str.charAt(j)) return false;
            //更新游标值
            ++i;
            --j;
        }
        //循环结束返回true
        return true;
    }

    // Time: O(m), Space: O(1)
    public boolean isPalindrome(int x) {
        //如果是负数直接返回false
        if (x < 0) return false;

        int tmp = x;
        long y = 0;
        while (tmp != 0) {
            //将tmp当前的个位数字提取出来
            int num = tmp % 10;
            //更新y值
            y = y * 10 + num;
            //tmp通过除以10向右移动一位
            tmp = tmp / 10;
        }
        return y == x;
    }
}
