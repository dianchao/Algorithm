package com.dianchao.leetcode;

import java.util.Arrays;

/**
 * 这个题目说的是，给你一个字符串，你要找到没有重复字符的最长子串，然后返回它的长度。
 *
 * 比如说给你的字符串 s 是：
 *
 * abcabcbb
 *
 * 没有重复字符的最长子串是 abc，这里再往下的字符是 a，和前面这个 a 重复了。
 *
 * 后面满足条件的子串还有 bca，cab，abc 等，不过它们的长度都是 3 ，因此返回的长度为 3。
 *
 * 再比如说 ddd，没有重复字符的最长子串就是一个 d，因此你要返回的长度是 1。
 *
 * 解法1：暴力
 * 两层for循环，取出这个字符串中任意一个子串，然后这要这个子串中不存在重复的字母，就可以记录下它的当前长度，并且和当前的最大长度对比
 *
 * 解法2：滑动窗口
 * 使用双指针i和j，区别是它们只向前移动，还需要的一个hash表counts记录出现过的字符的个数，同时需要定义一个变量maxLen，初始值为0
 *
 */
public class LengthOfLongestSubstring_25 {
    // Time: O(n), Space: O(m), m 是字符集大小
    public int lengthOfLongestSubstring2N(String s) {
        int[] counts = new int[256];
        int i = 0, j = 0, maxLen = 0;
        for (; i < s.length(); ++i) {
            for (; j < s.length(); ++j) {
                if (counts[s.charAt(j)] != 0) break;
                counts[s.charAt(j)] += 1;
            }
            maxLen = Math.max(maxLen, j - i); // j - i is current length
            counts[s.charAt(i)] -= 1;
        }
        return maxLen;
    }

    // Time: O(n), Space: O(m)，m 是字符集大小
    public int lengthOfLongestSubstring1N(String s) {
        int[] index = new int[256];
        Arrays.fill(index, -1);
        int maxLen = 0;
        for (int i=0, j=0; j < s.length(); ++j) {
            i = Math.max(index[s.charAt(j)] + 1, i);
            maxLen = Math.max(maxLen, j - i + 1);
            index[s.charAt(j)] = j;
        }
        return maxLen;
    }
}
