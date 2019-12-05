package com.yl.ds;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    @Test
    public void testHashMap(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("1","a");
        map.put("2","b");

        System.out.println(map.get("1"));
    }
}
