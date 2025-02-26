/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null)
            return head;

        ListNode end = head;
        int listSize = 1;
        while(end.next != null){
            end = end.next;
            listSize++;
        }
        
        int rotateUnits = k % listSize;
        if(rotateUnits == 0)
            return head;
            
        ListNode rotateNode = head;
        ListNode prev = null;
        for(int i = 0; i<listSize-rotateUnits; i++){
            prev = rotateNode;
            rotateNode = rotateNode.next;
        }

        end.next = head;
        prev.next = null;

        return rotateNode;
    }
}