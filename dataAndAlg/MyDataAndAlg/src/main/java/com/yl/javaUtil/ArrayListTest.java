package com.yl.javaUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {

    @Test
    public void testArrayList(){
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(1);
        arrayList.add("22");

        arrayList.get(1);

        arrayList.remove("22");
    }

    /**
     * 不执行iterator.next()而直接iterator.remove()抛出java.lang.IllegalStateException
     */
    @Test
    public void testIterator(){
        ArrayList arrayList = new ArrayList(10);
        arrayList.add("22");
        arrayList.add("33");

        Iterator iterator = arrayList.iterator();
        iterator.remove();
    }

    /**
     * iterator遍历过程中，集合结构发生改变(add,remove或clear),抛出 java.util.ConcurrentModificationException
     */
    @Test
    public void testIterator2(){
        List<String> arrayList = new ArrayList<>();
        arrayList.add("22");
        arrayList.add("33");

        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            arrayList.remove(0);
            String s = iterator.next(); //抛出 java.util.ConcurrentModificationException
        }
    }

    /**
     * 测试 ensureCapacity()
     * 设置容量为一个足够大的量以避免数组容量以后的扩展 TODO: 未完成
     */
    @Test
    public void testEnsureCapacity(){
        ArrayList<String> //ensureCapacity()为ArrayList的特有方法，不是List的方法，所以类型应声明为ArrayList
                arrayList = new ArrayList<>();
        arrayList.add("22");
        arrayList.add("33");

        System.out.println("before ensureCapacity:" + arrayList.size());
        arrayList.ensureCapacity(2);
        System.out.println("after ensureCapacity:" + arrayList.size());
    }

    /**
     * 测试 trimToSize()
     * 在所有的ArrayList添加操作完成之后使用以避免浪费空间 TODO: 未完成
     */
    @Test
    public void testTrimToSize(){
        ArrayList<String> //trimToSize()为ArrayList的特有方法，不是List的方法，所以类型应声明为ArrayList
                arrayList = new ArrayList<>();
        arrayList.add("22");
        arrayList.add("33");

        System.out.println("before trimToSize:" + arrayList.size());
        arrayList.trimToSize();
        System.out.println("after trimToSize:" + arrayList.size());
    }
}
