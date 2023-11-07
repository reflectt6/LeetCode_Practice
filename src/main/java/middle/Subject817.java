package middle;

import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Arrays;
import java.util.HashSet;


//  Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public int numComponents(ListNode head, int[] nums) {
        boolean subStart = false;
        int cnt = 0;
        HashSet<Integer> set = new HashSet();
        for (int num : nums) {
            set.add(num);
        }
        if (set.contains(head.val)) {
            subStart = !subStart;
        }
        while (head.next != null) {
            head = head.next;
            if (set.contains(head.val) && !subStart) {
                subStart = !subStart;
            } else if (!set.contains(head.val) && subStart) {
                subStart = !subStart;
                cnt++;
            }
        }
        if (subStart) {
            cnt++;
        }
        return cnt;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

public class Subject817 {
    public static void main(String[] args) {


    }
}
