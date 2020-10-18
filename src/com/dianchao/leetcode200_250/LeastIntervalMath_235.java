package com.dianchao.leetcode200_250;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 *  描述
 * 这个题目说的是，给你一个字符数组和一个非负整数 n。字符数组表示等待 CPU 处理的任务，每个任务用 A 到 Z 中的一个字符表示，
 * 并且每个任务都可以在一个时间单位内完成；n 表示冷却时间，即相同任务之间需要间隔至少 n 个时间单位才能再次执行，冷却时间
 * 内的每个时间单位，可以选择执行不同的任务或是让 CPU 处于闲置状态。
 *
 * 现在你要重新组织任务的执行顺序，并计算出最少需要多少个时间单位才能完成所有任务。
 *
 * 比如说，给你的任务数组是：
 *
 * A, A, A, B, B, B, D
 *
 * 给你的冷却时间是：
 *
 * n = 2
 *
 * 完成所有任务最少需要 8 个时间单位，一种执行序列是：
 *
 * A, B, D, A, B, _, A, B
 *
 * 序列中的 '_' 表示 CPU 处于闲置状态。
 *
 * 因此，对于这个例子，你要返回 8。
 *
 * 思路：
 * 关键约束：相同任务之间需要间隔，至少n个时间单位才能再次执行。
 * AAA
 * A__A__A
 * 由于两个A之间间隔2个时间单位，因此执行序列可以看成是以3个时间单位为一组，或者通用的表示成n+1个时间单位为一组，每一组里的
 * n+1个时间单位可以填充n+1个不同的任务，如果不同的任务不足n+1个，那么剩下的时间单位让CPU闲置，我们已经知道每一种类有n+1个
 * 时间单位，那么总共有多少组呢？这个数量由出现次数最多的任务决定。比如接着刚才的例子，增加一个任务B，毫无疑问我们还是以A划
 * 分组，而新增的B随便填充到闲置的时间单位里即可，因为为了得到正确的分组，我们需要对数组中的任务做次数统计，并使用维护次数
 * 从大到小的排列，而做这件事最合适的数据结构就是最大堆。接下来我们在题目给出的例子中计算出完成任务的最少时间单位，首先统计
 * 每个任务出现次数，A：3 B：3 D：1 然后把出现次数放入最大堆中，由于我们只要计算最少的时间单位，并不关心具体的任务执行序列，
 * 因此我们只需要将任务出现的次数加入到堆中即可，接着我们就来统计所需的时间单位，定义一个变量result累加时间单位，初始为0，
 * 定义一个变量idle表示一个分组内闲置的时间单位，开始为0，idle的真正作用是记录最后一个分组的闲置时间，因为最后一个分组的闲
 * 置时间是在最后一个任务后面，因此不需要计算到result中，最终要从result中减掉，接下来只要最大堆不为空，就需要一个分组来填充
 * 任务，因此result + n + 1，在这个例子中n=2,因此result每次加3，这个分组内idle等于分组长度n+1减去堆中不同任务的数量，也就
 * 是堆的大小
 *
 * idle = (n+1) - 堆的大小
 * A：3
 * B: 3
 * D: 1
 *
 * result   idle
 * 0        0
 * 3        3-3=0
 * 6        3-2=1
 * 9        3-2=1
 *
 * 9 - 1 = 8
 * 3 * 3 - 1 = 8
 *
 * AAABC  3  ABC_A  A
 * 3    1   1
 * 0    0
 * 4    1
 * 8    3
 * 12   3
 * 分组长度由出现次数最多的任务决定，假设我们任务出现次数最多次数为maxFreq, 那么最终的任务执行序列前面一定时maxFreq-1个完整的分组
 * 而单个分组的长度是n+1，因此这部分的长度是(maxFreq-1)*(n+1), 接着我们分析最后一个分组的长度，由于出现次数最多的任务可能不止1个
 *
 *
 *
 *
 */
public class LeastIntervalMath_235 {
    // Time: O(m), Space: O(1)
    public static int leastIntervalMaxHeap(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;
        int[] freqs = new int[26];
        //统计任务次数
        for (char t: tasks) ++freqs[t - 'A'];
        //最大堆, 优先队列即可
        Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());

        //如果出现次数不为0，则将出现次数入最大堆
        for (int freq: freqs){
            if (freq != 0) {
                q.add(freq);
            }
        }

        int result = 0, idle = 0;
        while (!q.isEmpty()) {
            result += (n + 1);
            //在中间过程中，由于n+1可能小于堆的大小，导致idle为负数，但是这没有关系，因为我们只需要最后一个分组的idle
            idle = n + 1 - q.size();

            //要从堆中取出的任务数量
            int size = Math.min(n+1, q.size());
            for (int i = 0; i < size; ++i){
                freqs[i] = q.poll() - 1;
            }
            //如果刚才临时保存的次数不为0，就将值放入最大堆
            for (int i = 0; i < size; ++i){
                if (freqs[i] != 0) {
                    q.add(freqs[i]);
                }
            }
        }
        //循环结束后返回result-idle的值即可
        return result - idle;
    }

    // Time: O(m), Space: O(1)
    public static int leastIntervalMath(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;
        int[] freqs = new int[26];
        //计算不同任务出现的次数
        for (char t: tasks) ++freqs[t - 'A'];
        //maxFreq表示出现次数最多的任务所出现的次数
        //cnt表示出现次数等于maxFreq的任务有多少个
        int maxFreq = 0, cnt = 0;
        for (int freq: freqs) {
            if (freq > maxFreq) {
                maxFreq = freq;
                cnt = 1;
            } else if (freq == maxFreq) {
                ++cnt;
            }
        }
        int result = (n + 1) * (maxFreq - 1) + cnt;
        //返回result和数组长度最大值即可
        return Math.max(result, tasks.length);
    }

    public static void main(String[] args){
        char [] a = {'A', 'A', 'A', 'B', 'C'};
        System.out.println(leastIntervalMaxHeap(a, 3));
    }
}
