package ex.code;
/*
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.

Example 1:
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted linked list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []
* */

import java.util.PriorityQueue;

public class MergeKSortedLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode list : lists) {
            minHeap.offer(list);
        }

        ListNode root = new ListNode(0);
        ListNode cur = root;
        while (!minHeap.isEmpty()) {
            ListNode minimum = minHeap.poll();
            cur.next = minimum;
            cur = cur.next;
            if (minimum.next != null) {
                minHeap.offer(minimum.next);
            }

        }
        return root.next;
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode list3 = new ListNode(2, new ListNode(6));
        ListNode list4 = new ListNode(3, new ListNode(4, new ListNode(5)));
        ListNode list5 = new ListNode(4, new ListNode(5, new ListNode(6)));

        MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
        ListNode result = mergeKSortedLists.mergeKLists(new ListNode[]{list1, list2, list3, list4, list5});
        ListNode print = result;
        while (print != null) {
            System.out.print(print.val + " -> ");
            print = print.next;
        }
        System.out.println("null");    }
}
