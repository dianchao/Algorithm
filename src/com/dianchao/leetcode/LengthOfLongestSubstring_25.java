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
        //定义一个数组来记录字符出现的次数
        int[] counts = new int[256];

        //定义滑动窗口左右指针，初始值都为0
        int i = 0, j = 0;

        //记录字符最大长度
        int maxLen = 0;

        //定义两层for循环，注意内循环每次不会都从0开始，而是不断向前移动
        for (; i < s.length(); ++i) {
            for (; j < s.length(); ++j) {
                //如果游标j指向的字符出现过，则内循环结束
                if (counts[s.charAt(j)] != 0) break;
                //否则将当前字符在count数组出现的次数加1
                counts[s.charAt(j)] += 1;
            }
            //内循环结束后更新maxLen
            maxLen = Math.max(maxLen, j - i); // j - i is current length
            //在i向前移动一位前，将i指向的字符出现的次数减1
            counts[s.charAt(i)] -= 1;
        }

        return maxLen;
    }

    // 优化，比如字符串：abcbadfe
    // Time: O(n), Space: O(m)，m 是字符集大小
    public int lengthOfLongestSubstring1N(String s) {
        //使用index数组记录出现过的字符下标，由于数组默认值是0，而0是一个有意义的下标
        int[] index = new int[256];
        //初始化数组元素值为-1
        Arrays.fill(index, -1);

        //定义maxLen，初始化为0
        int maxLen = 0;

        //初始化i和j为0
        for (int i=0, j=0; j < s.length(); ++j) {
            //首先更新i的值，如果当前j所指向的字符在前面已经出现过，则i直接跳到出现过的字符的下一位，否则保持不变
            i = Math.max(index[s.charAt(j)] + 1, i);

            //更新maxLen,j - i + 1表示当前不包括重复字符的子串长度
            maxLen = Math.max(maxLen, j - i + 1);

            //将j指向的字符和下标j保存起来
            index[s.charAt(j)] = j;
        }
        return maxLen;
    }
}
