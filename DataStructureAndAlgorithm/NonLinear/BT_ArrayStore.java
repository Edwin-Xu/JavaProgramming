package DataStructureAndAlgorithm.NonLinear;

/**
 * Created by XuTao on 2018/11/18 11:54
 * 完全二叉树:
 * 用数组按层次序排列
 *
 */

/**
 * Let i, 0<=i<=n-1,be the number assigned to an element of a complete binary tree. The following are true.
 * 1) if i=0, then this element is the root of the binary tree.
 *     if i>0,then the parent of this element has been assigned the number  |_(i-1)/2_|
 * 2) if 2*i+1>=n,then this element has no left child. Otherwise,its left child has been assigned the number 2*i+1.
 * 3) if 2*i+2>=n, then this element has no right child, Otherwise its right child has been assigned the number 2*i+2.
 * 4)结点i所在的层次是：|_log2(i-1)_| + 1
 */
public class BT_ArrayStore {
    protected Node root;
    private int [] data;
    public BT_ArrayStore(int []a){
        this.data =a;
        this.root = create(a,0);
    }

    public int getHeight(){
        return (int)Math.floor(Math.log(data.length)/Math.log(2))+1;  //这里涉及到Math，易错
    }
    public int getFather(int i){
        if (i==0) return 0;
        return (int)(Math.floor((i-1)/2));
    }

    public int getLeft(int i){
        return 2*i+1;
    }
    public int getRight(int i){
        return 2*i+2;
    }

    public int getBrother(int i){
        if (i%2==0)
            return i-1;
        else return i+1;
    }
    public int  getLevel(int i){
        return (int)(Math.floor(Math.log(i-1)/Math.log(2)))+1;
    }


    private Node create(int[] arr, int index) {
        if (index >= arr.length)       // 可以不需要，但是所有的值必须要写满，任一个#都要写，不然会越界
            return null;
        else if (String.valueOf(arr[index]).equals("#")||String.valueOf(arr[index]).equals("*")) {
            return null;
        } else {
            Node node = new Node(arr[index]);
            node.left = create(arr, 2 * index + 1);
            node.right  = create(arr, 2 * index + 2);
            return node;
        }
    }

    class Node{
        private Node left;
        private Node right;
        private int data;
        public Node(int data){
            this.data = data;
        }
    }

    public Node getRoot(){
        return root;
    }

    public static void main(String[] args) {
        int []a = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        BT_ArrayStore bt = new BT_ArrayStore(a);
        System.out.println(bt.getHeight());
        System.out.println(bt.getFather(14));
        System.out.println(bt.getLeft(4));
        System.out.println(bt.getRight(5));
        System.out.println(bt.getBrother(5));
        System.out.println(bt.getLevel(5));

    }
}
