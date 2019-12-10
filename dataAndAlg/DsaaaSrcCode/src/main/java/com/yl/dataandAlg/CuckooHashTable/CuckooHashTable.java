package com.yl.dataandAlg.CuckooHashTable;

import java.util.Random;

/**
 * 050702
 * CuckooHash（布谷鸟散列）
 * https://www.jianshu.com/p/68220564f341
 */
public class CuckooHashTable<AnyType> {
    public CuckooHashTable(HashFamily<? super AnyType> hf){
        this(hf, DEFAULT_TABLE_SIZE);
    }
    //初始化操作
    public CuckooHashTable(HashFamily<? super AnyType> hf, int size){
        allocateArray(nextPrime(size));
        doClear();
        hashFunctions = hf;
        numHashFunctions = hf.getNumberOfFunctions();
    }

    public void makeEmpty(){
        doClear();
    }

    public boolean contains(AnyType x){
        return findPos(x) != -1;
    }

    /**
     *
     * @param x 当前的元素
     * @param which 选取的散列函数对应的位置
     * @return
     */
    private int myHash(AnyType x, int which){
        //调用散列函数集合中的hash方法获取到hash值
        int hashVal = hashFunctions.hash(x, which);
        //再做一定的处理
        hashVal %= array.length;
        if (hashVal < 0){
            hashVal += array.length;
        }
        return hashVal;
    }

    /**
     * 查询元素的位置，若找到元素，则返回其当前位置，否则返回-1
     * @param x
     * @return
     */
    private int findPos(AnyType x){
        //遍历散列函数集合，因为不确定元素所用的散列函数为哪个
        for (int i = 0; i < numHashFunctions; i ++){
            //获取到当前hash值
            int pos = myHash(x, i);
            //判断表中是否存在当前元素
            if (array[pos] != null && array[pos].equals(x)){
                return pos;
            }
        }
        return -1;
    }

    /**
     * 删除元素：先查询表中是否存在该元素，若存在，则进行删除该元素
     * @param x
     * @return
     */
    public boolean remove(AnyType x){
        int pos = findPos(x);
        if (pos != -1){
            array[pos] = null;
            currentSize --;
        }
        return pos != -1;
    }

    /**
     * 插入：先判断该元素是否存在，若存在，在判断表的大小是否达到最大负载，
     * 若达到，则进行扩展，最后调用insertHelper方法进行插入元素
     * @param x
     * @return
     */
    public boolean insert(AnyType x){
        if (contains(x)){
            return false;
        }
        if (currentSize >= array.length * MAX_LOAD){
            expand();
        }
        return insertHelper(x);
    }

    /**
     * 具体的插入过程：
     * a. 先遍历散列函数集合，找出元素所有的可存放的位置，若找到的位置为空，则放入即可，完成插入
     * b. 若没有找到空闲位置，随机产生一个位置
     * c. 将插入的元素替换随机产生的位置，并将要插入的元素更新为被替换的元素
     * d. 替换后，回到步骤a.
     * e. 若超过查找次数，还是没有找到空闲位置，那么根据rehash的次数，
     * 判断是否需要进行扩展表，若超过rehash的最大次数，则进行扩展表，
     * 否则进行rehash操作，并更新散列函数集合
     * @param x
     * @return
     */
    private boolean insertHelper(AnyType x) {
        //记录循环的最大次数
        final int COUNT_LIMIT = 100;
        while (true){
            //记录上一个元素位置
            int lastPos = -1;
            int pos;
            //进行查找插入
            for (int count = 0; count < COUNT_LIMIT; count ++){
                for (int i = 0; i < numHashFunctions; i ++){
                    pos = myHash(x, i);
                    //查找成功，直接返回
                    if (array[pos] == null){
                        array[pos] = x;
                        currentSize ++;
                        return true;
                    }
                }
                //查找失败，进行替换操作，产生随机数位置，当产生的位置不能与原来的位置相同
                int i = 0;
                do {
                    pos = myHash(x, r.nextInt(numHashFunctions));
                } while (pos == lastPos && i ++ < 5);
                //进行替换操作
                AnyType temp = array[lastPos = pos];
                array[pos] = x;
                x = temp;
            }
            //超过次数，还是插入失败，则进行扩表或rehash操作
            if (++ rehashes > ALLOWED_REHASHES){
                expand();
                rehashes = 0;
            } else {
                rehash();
            }
        }
    }

    private void expand(){
        rehash((int) (array.length / MAX_LOAD));
    }

    private void rehash(){
        hashFunctions.generateNewFunctions();
        rehash(array.length);
    }

    private void rehash(int newLength){
        AnyType [] oldArray = array;
        allocateArray(nextPrime(newLength));
        currentSize = 0;
        for (AnyType str : oldArray){
            if (str != null){
                insert(str);
            }
        }
    }
    //清空操作
    private void doClear(){
        currentSize = 0;
        for (int i = 0; i < array.length; i ++){
            array[i] = null;
        }
    }
    //初始化表
    private void allocateArray(int arraySize){
        array = (AnyType[]) new Object[arraySize];
    }

    public void printArray(){
        System.out.println("当前散列表如下：");
        System.out.println("表的大小为：" + array.length);
        for (int i = 0; i < array.length; i ++){
            if (array[i] != null)
                System.out.println("current pos: " + i + " current value: " + array[i]);
        }
    }

    //定义最大装填因子为0.4
    private static final double MAX_LOAD = 0.4;
    //定义rehash次数达到一定时，进行
    private static final int ALLOWED_REHASHES = 1;
    //定义默认表的大小
    private static final int DEFAULT_TABLE_SIZE = 101;
    //定义散列函数集合
    private final HashFamily<? super AnyType> hashFunctions;
    //定义散列函数个数
    private final int numHashFunctions;
    //定义当前表
    private AnyType[] array;
    //定义当前表的大小
    private int currentSize;
    //定义rehash的次数
    private int rehashes = 0;
    //定义一个随机数
    private Random r = new Random();

    //返回下一个素数
    private static int nextPrime(int n){
        while (!isPrime(n)){
            n ++;
        }
        return n;
    }
    //判断是否为素数
    //1 不是素数，最小的素数是2。此方法再传入1时会返回true，有问题。
    //至于为什么只用除到其平方根？因为如果一个数不是素数是合数，那么一定可以由两个自然数相乘得到，其中一个大于或等于它的平方根，一个小于或等于它的平方根。
    private static boolean isPrime(int n){
        for (int i = 2; i <= Math.sqrt(n); i ++){
            if (n % i == 0 && n != 2){
                return false;
            }
        }
        return true;
    }

}
