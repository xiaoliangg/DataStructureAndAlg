package com.yl.javaUtil;

import org.junit.Test;

import java.util.LinkedList;

public class LinkedListTest {

    @Test
    public void testArrayList(){
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add("22");

        linkedList.get(1);

        linkedList.remove("22");
        System.out.println(linkedList);
    }
}
