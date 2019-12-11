package com.yl.dataandAlg.binaryHeap;

/**
 * https://www.jianshu.com/p/316a4b4ee715
 */
public class LeftHeap {
    public HeapNode root;
    public LeftHeap(int val){
        root= new HeapNode(val);
    }

    public void merge(LeftHeap heap){
        if(heap!=null){
            root=internalMerge(root,heap.root);
        }
    }

    //TODO ！！！ 递归
    //理解该方法的递归，尝试使用封装的思想理解。
    //对于调用 internalMerge() 方法的程序而言,需求是:
    // 1、通过比较，得到 h1和h2中的较小的一个HeapNode作为result，并调节较小的一个的结构使其满足左式堆性质 2、调节result和子节点的结构，使其满足左式堆性质 3、返回 result
    // 而获取较小的一个是递归实现的。
    // 调节result的结构可以看成包含两个步骤，一个是result的右子节点先保证满足左式堆性质,第二是调节result和子节点，保证满足左式堆性质。第一步骤是从底层向上逐步调节的，也是递归实现的。第二步骤是在当前方法实现
    // 此处的基准情形是，直到两棵树中有一棵的右子节点为空，则把不为空的那一个添加

    //递归的条件是每一次递归应该要把上一个问题简化，并且有结束递归的条件。  既有递又有归。  google搜索的递归有递无归
    private HeapNode internalMerge(HeapNode h1,HeapNode h2){
        if(h1==null) return h2;
        if(h2==null) return h1;
        HeapNode result =null;
        if(h1.val>=h2.val){
            h2.right = internalMerge(h2.right,h1);
            result =h2;
        }else{
            h1.right = internalMerge(h1.right,h2);
            result =h1;
        }
        //如果不满足结构性，则调整
        int leftNPL = result.left==null?-1:result.left.npl;
        int rightNPL = result.right==null?-1:result.right.npl;
        if(leftNPL<rightNPL){
            HeapNode temp = result.right;
            result.right = result.left;
            result.left = temp;
        }
        //更新npl值
        result.npl = Math.min(leftNPL,rightNPL)+1;
        return result;
    }
    //对外暴露的插入函数
    public void insert(int val){
        LeftHeap heap = new LeftHeap(val);
        merge(heap);
    }
    //对外暴露的删除函数
    public int deleteMin(){
        if(root==null) return -1; //-1这里代表错误码，堆为空不删除。
        HeapNode node = root;
        root = internalMerge(root.left,root.right);
        return node.val;
    }
    public boolean isEmpty(){
        return root==null?true:false;
    }

    public static void main(String [] args){
        LeftHeap heap = new LeftHeap(2);
        for(int i=1;i<10;i++){
            heap.insert(i);
        }
        while(!heap.isEmpty()){
            System.out.print(heap.deleteMin()+" ");
        }
    }
    //左式堆的节点的定义
    private static class HeapNode{
        public int npl;
        public HeapNode left;
        public HeapNode right;
        public int val;
        public HeapNode(int val){
            this.val = val;
            left = null;
            left =null;
            npl =0;
        }
    }
}
