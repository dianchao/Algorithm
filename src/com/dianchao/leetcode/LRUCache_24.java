package com.dianchao.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LRUCache：Least Recently used 将最久没有使用数替换掉
 * 这个题目说的是，你要实现一个 LRU 缓存，提供 get 和 put 两个操作，并且要求两个操作的时间复杂度都是 O(1)。另外为了简单起见，
 * 在这个题目中，key 和 value 都是整数值，并且 value 只为正整数。因此在 get 操作中，当 key 不存在时，返回 -1 即可。
 *
 * Map+双向链表
 * 思路：常见方法是使用头尾两个指针指向双向链表的头尾节点，当访问完一个节点后将它放到头节点，当要替换最久未使用的数据时，将尾指针
 * 指向的节点移除。更新节点上的数据后再移动到头节点。
 *
 * 优化：将双向链表的头尾节点连接起来，这样只需要保存头指针即可。
 *
 * 缓存容量是4，首先初始化双向链表和hash表，双向链表上的四个节点分别为A、B、C、D，然后依次插入4个键值对：
 * (1,1)、(2,2)、(3,3)、(4,4) 插入数据后head总是逆时针移动一个节点，最后还是指向节点A。同时为了使get操作的时间复杂度为O(1),
 * 我们需要将key和它所在的节点存在Hash表中，这时候如果再插入数据(5,5),header直接指向它更新的节点即可，因为那个节点（1,1）是
 * 最久未访问的节点。然后将hash表中key为1的节点删除掉，插入key为5，value为A的数据，这样一来新插入的数据总会覆盖最久未使用的数
 * 据。通过get操作获取key为3的数据，从Hash表可知key为3的节点存放在节点C上，因此需要返回节点C上的值，在返回C之前需要将节点C
 * 移到header节点前面表示它是最新访问过的节点：
 *
 * 将C前一个节点的next域指向C的后一个节点
 * C.prev.next = C.next
 * C.next.prev = C.prev
 *
 * 将C移动到header的后面：
 * C.next = header.next;
 * C.next.prev = C;
 * head.next = C;
 * C.prev = head;
 *
 * 这样一来，只要节点被访问就会被移动到链表头部，而链表尾部表示就是最久未访问的数据，未访问的数据总会被新插入的数据覆盖
 *
 */
public class LRUCache_24 {
    public class LRUCache {

        private class Node {
            int key, val;
            Node prev, next;

            Node(int key, int val, Node prev, Node next) {
                this.key = key;
                this.val = val;
                this.prev = prev;
                this.next = next;
            }
        }

        //定义头节点
        private Node head = new Node(-1, -1, null, null);

        //定义Map
        private Map<Integer, Node> map = new HashMap<>();

        //将一个节点移动到头节点
        private void move2Head(Node cur) {
            //如果当前节点就是头节点，则不需要移动，将头节点逆时针移动到它的上一个节点即可
            if (cur == head) {
                head = head.prev;
                return;
            }

            //如果当前节点不是头节点，则：
            //① 将当前节点从链表中取下
            // detach
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
            //② 将当前插入到head节点后面
            // attach
            cur.next = head.next;
            cur.next.prev = cur;
            head.next = cur;
            cur.prev = head;
        }

        //构造容量为capacity的双向循环链表
        public LRUCache(int capacity) {
            Node cur = head;
            for (int i = 0; i < capacity-1; ++i) {
                cur.next = new Node(-1, -1, cur, null);
                cur = cur.next;
            }
            cur.next = head;
            head.prev = cur;
        }

        public int get(int key) {
            //当hash表中不存指定的key时，直接返回-1。
            if (!map.containsKey(key)) return -1;
            Node cur = map.get(key);
            //将访问的节点移动到head前面
            move2Head(cur);
            //返回节点的value
            return cur.val;
        }


        public void put(int key, int value) {
            //如果hash表中已经存在key
            if (map.containsKey(key)) {
                //取出节点
                Node cur = map.get(key);
                //更新节点的值
                cur.val = value;
                //将该节点移动到head节点前面
                move2Head(cur);
            } else {
                //否则，表示插入的是一个新的key，检查head节点的value是否是-1，如果不是-1，则表示该节点已经存储了数据，
                //需要从hash表中移除
                if (head.val != -1) map.remove(head.key);
                //将key-value存入到头节点中
                head.key = key;
                head.val = value;
                //将key和头节点存入hash表
                map.put(key, head);
                //head指向前一个节点，即指向最久未使用的节点
                head = head.prev;
            }
        }
    }
}
