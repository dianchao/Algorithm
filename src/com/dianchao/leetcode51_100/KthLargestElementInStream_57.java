package com.dianchao.leetcode51_100;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 这个题目说的是，你要实现一个类，用来求数据流中第 K 大的元素。你需要实现这个类中的两个函数。第一个是构造函数，它接收一个整数数组以及一个整数 K，整数数组作为初始数据流。第二个是 add 函数，接收一个整数表示新流入的数据，然后返回当前第 K 大的元素。
 *
 * 比如说，给你的 K 是 3，初始的数组是：
 *
 * 1, 5, 2, 8
 *
 * 这时假如调用 add 函数增加一个数字 9，数据流变成：
 *
 * 1, 5, 2, 8, 9
 *
 * 你要返回第 3 大的元素是 5。
 *
 * 假如再调用 add 函数增加一个数字 0，数据流变成：
 *
 * 1, 5, 2, 8, 9, 0
 *
 * 这时你要返回的第 3 大元素仍然是 5。
 *
 * 思路：
 * 在类中维护一个topK元素的池子，当有新数字进来时，当数字与topK中最小的数字进行比较，如果新数字比它大，则把原来最小的数字去掉，
 * 把这个新数字加入到池子中，然后返回这个池子中最小的数字即可。在topK不断变化的过程中，我们要方便取出topK中的最小值，因此
 * 可以使用最小堆来保存这topK个元素，
 */
public class KthLargestElementInStream_57 {
    public class KthLargestElementInStream {
        //最小堆定义：在Java中使用优先权队列即可，它默认就是最小堆实现
        private Queue<Integer> minHeap = new PriorityQueue<>();
        private int k;

        // Time: O(n*log(k))
        public KthLargestElementInStream(int k, int[] nums) {
            this.k = k;
            //将数组中的元素一个个加入到最小堆中
            for (int num: nums) add(num);
        }

        // Time: O(log(k))
        public int add(int val) {
            //如果最小堆中不足k个元素，则将数字直接加入最小堆中即可
            if (minHeap.size() < k) {
                minHeap.add(val);
            //否则堆中有k个元素，我们需要判断新加入的元素是否大于堆中的最小值，如果大于最小值，则将堆中最小值出堆，
            //新元素入堆
            } else if (val > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(val);
            }

            //返回最小堆的最小值
            return minHeap.peek();
        }
    }
}
