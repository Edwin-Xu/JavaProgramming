package DataStructureAndAlgorithm.NonLinear;

/**
 * 线索二叉树
 * 背景：普通二叉树有不少的null节点，较为空间浪费
 * 同时，对于二叉树，如果要知道他在某种排序下的pre or SUCC，必须要遍历一遍，造成时间上的浪费
 * 于是：线索二叉树好处：
 * 1.节约空间（更好地利用空间）
 * 2.便于直接得知每个节点的前驱后继
 * 构造线索二叉树的过程叫做：线索化
 * <p>
 * <p>
 *
 * 后序线索化二叉树：https://blog.csdn.net/UncleMing5371/article/details/54291221
 *
 */
public class ThreadedBinaryTree<T> {
    private ThreadNode<T> root;   // 根节点
    private ThreadNode<T> preNode;   // 线索化过程中需要保存前驱

    public ThreadedBinaryTree() {
        this.root = null;
        this.preNode = null;
    }

    public ThreadedBinaryTree(T[] data) {  //用数组存储好数据，递归构造线索二叉树
        this.root = null;
        this.preNode = createTreeInorder(data, 0);
    }

    /**
     * 中序构造二叉树。数组中存储的是层排序下的序列
     *
     * @param data
     * @param index 初始化必须为 0
     * @return
     */
    private ThreadNode<T> createTreeInorder(T[] data, int index) {
        if (index >= data.length)
            return null;
        else if (String.valueOf(data[index]).equals("#")) {
            return null;
        }
        ThreadNode<T> node = new ThreadNode<>(data[index]);
        node.left = createTreeInorder(data, 2 * index + 1);
        node.right = createTreeInorder(data, 2 * index + 2);
        return node;
    }

    /**
     * 中序线索化二叉树
     */
    public void threadInOrder(ThreadNode<T> node) {
        if (node == null) return;
        //处理左子树
        threadInOrder(node.left);
        //左子树为空，将左子树指向前驱节点
        if (node.left == null) {
            node.left = preNode;
            node.ltag = 1;
        }
        //前一个节点的后继节点指向当前节点
        if (preNode != null && preNode.right == null) {
            preNode.right = node;
            preNode.rtag = 1;
        }
        preNode = node;

        //处理右子树
        threadInOrder(node.right);

    }

    /**
     * 前序线索化二叉树
     */
    public void threadPreOrder(ThreadNode<T> node) {
        if (node == null) return;
        if (node.left == null) {
            node.left = preNode;
            node.ltag = 1;
        }
        if (preNode != null && preNode.right == null) {
            preNode.right = node;
            preNode.rtag = 1;
        }
        preNode = node;

        //left
        if (node.ltag==0){
            threadPreOrder(node.left);
        }
        //right
        if (node.rtag ==0){
            threadPreOrder(node.right);
        }
    }


    /**
     * 中序遍历线索二叉树，按照后继方式遍历（思路：找到最左子节点开始）
     * @param node
     */
    public void inThreadList(ThreadNode<T> node) {
        //1、找中序遍历方式开始的节点
        while(node != null && node.ltag==0) {
            node = node.left;
        }

        while(node != null) {
            System.out.print(node.data + ", ");

            //如果右指针是线索
            if(node.rtag==1) {
                node = node.right;

            } else {    //如果右指针不是线索，找到右子树开始的节点
                node = node.right;
                while(node != null && node.ltag==0) {
                    node = node.left;
                }
            }
        }
    }

    /**
     * 中序遍历线索二叉树，按照前驱方式遍历（思路：找到最右子节点开始倒序遍历）
     * @param node
     */
    public void inPreThreadList(ThreadNode<T> node) {
        //1、找最后一个节点
        while(node.right != null && node.rtag==0) {
            node = node.right;
        }

        while(node != null) {
            System.out.print(node.data + ", ");

            //如果左指针是线索
            if(node.ltag==1) {
                node = node.left;

            } else {    //如果左指针不是线索，找到左子树开始的节点
                node = node.left;
                while(node.right != null && node.rtag==0) {
                    node = node.right;
                }
            }
        }
    }

    /**
     * 前序遍历线索二叉树（按照后继线索遍历）
     * @param node
     */
    public void preThreadList(ThreadNode<T> node) {
        while(node != null) {

            while(node.ltag==0) {
                System.out.print(node.data + ", ");
                node = node.left;
            }

            System.out.print(node.data + ", ");
            node = node.right;
        }
    }


    public static void main(String[] args) {
        String[] array = {"A", "B", "C", "D", "E", "F", "G", "H"};
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        ThreadNode<String> root = tree.createTreeInorder(array, 0);


        tree.threadInOrder(root);
        System.out.println("中序按后继节点遍历线索二叉树结果：");
        tree.inThreadList(root);
        System.out.println("\n中序按后继节点遍历线索二叉树结果：");
        tree.inPreThreadList(root);

        ThreadedBinaryTree tree2 = new ThreadedBinaryTree();
        ThreadNode<String> root2 = tree2.createTreeInorder(array, 0);

        tree2.threadPreOrder(root2);
        tree2.preNode = null;
        System.out.println("\n前序按后继节点遍历线索二叉树结果：");
        tree.preThreadList(root2);
    }

}


class ThreadNode<T> {
    protected T data;
    protected ThreadNode<T> left;
    protected ThreadNode<T> right;
    protected ThreadNode<T> father;
    protected byte ltag;  // 左边标记：  0：指向子节点  1： 指向前驱/后继
    protected byte rtag;  // 右边标记：  0：指向子节点  2： 指向前驱/后继

    public ThreadNode(T data) {
        this.data = data;
    }
}