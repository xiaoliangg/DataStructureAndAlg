package com.yl.javaUtil;

import org.junit.Test;

import java.util.ArrayList;

public class ArrayListTest {

    @Test
    public void testArrayList(){
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(1);
        arrayList.add("22");

        arrayList.get(1);

        arrayList.remove("22");
    }
}
