package com.dianchao.leetcode51_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 比如说，给你的区间集合是：
 *
 * [1, 8]
 * [2, 4]
 * [9, 10]
 * [10, 16]
 *
 * 这 4 个区间里，[1, 8] 区间包含了 [2, 4] 区间，于是它们合并后是 [1, 8]。
 *
 * [9, 10] 区间和 [10, 16] 区间相邻，合并起来后是 [9, 16]。最后得到合并后的区间有两个：
 *
 * [1, 8]
 * [9, 16]
 *
 * 思路：
 * 第一步我们要把区间排序，注意是按照区间的开始下标进行排序，给的例子已经是按照开始区间进行了排序,
 * 先将区间[1,8]入结果中，然后[2,4]我们发现[1,8]的结束下标是大于[2,4]的开始下标的，因此这两个区间可以合并，
 * 而合并后的结束下标是两个区间结束下标中较大的值，4和8较大的是8，[1,8]的结束区间小于9，没有重叠，因此我们可以把
 * 区间[9,10]加入到结果中，然后我们看下一个区间[10,16],结果区间中最后的值是10和[10,16]它们是相邻的，因此它们
 * 可以合并，开始下标是9，结束下标是两个区间中较大的值。
 * res
 * 1    8
 * 9    16
 *
 */
public class MergeInterval_60 {
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    /**
     * 输入是一组区间，输出是合并后的区间
     * @param intervals
     * @return
     */
    // Time: O(n*log(n)), Space: O(1)
    public List<Interval> merge(List<Interval> intervals) {
        //定义结果List
        List<Interval> result = new ArrayList<>();

        //按照区间下标从小到大进行排序
        intervals.sort((a, b) -> a.start - b.start);

        //遍历区间数组
        for (Interval in: intervals) {
            //取出结果数组的大小，便于取结果数组的最后一个元素
            int n = result.size();

            //如果结果数组为空，或者结果数组的最后一个区间的结束下标小于当前区间的开始下标，就将当前区间加入结果数组
            if (result.isEmpty() || result.get(n-1).end < in.start) {
                result.add(in);
            //否则，说明结果数组的最后区间和当前区间有重叠，更新这个区间的结束下标为两个区间结束下标的较大者
            } else {
                result.get(n-1).end = Math.max(result.get(n-1).end, in.end);
            }
        }

        //返回结果数组即可
        return result;
    }

    // Just another method signature
    public int[][] merge(int[][] intervals) {
        int[][] result = new int[intervals.length][2];
        int size = 0;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for (int[] in: intervals) {
            if (size == 0 || result[size-1][1] < in[0]) {
                result[size][0] = in[0];
                result[size][1] = in[1];
                ++size;
            } else {
                result[size-1][1] = Math.max(result[size-1][1], in[1]);
            }
        }
        return Arrays.copyOf(result, size);
    }

}
