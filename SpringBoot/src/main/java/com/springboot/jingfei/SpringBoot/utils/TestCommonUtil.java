package com.springboot.jingfei.SpringBoot.utils;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Author xf
 * @Date Created in 17:44 2018/11/29
 */
public class TestCommonUtil {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l = l1;
        l.next = new ListNode(5);
        l = l.next;
        l.next = new ListNode(5);


        ListNode l2 = new ListNode(5);
        ListNode s = l2;
        s.next = new ListNode(5);
        s = s.next;
        s.next = new ListNode(5);

        ListNode next = addTwoNumbers(l1, l2);
        String str = "";
        while(next != null){
            str += next.val;
            next = next.next;
        }
        System.out.println(new StringBuilder(str).reverse().toString());

    }
}
