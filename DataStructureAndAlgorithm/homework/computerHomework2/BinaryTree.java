package DataStructureAndAlgorithm.homework.computerHomework2;

import computerOrganizationAndArchitecture.Binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 题目： 建立一颗二叉树，并输出前序中序后序遍历结果
 * <p>
 * 分析：
 * 两种输入模式：
 * 1.既定的数据(以层次序排列)
 * 2.控制台输入
 * 注意：都一个 # 表示空子树
 */
public class BinaryTree<T> {
    private TreeNode<T> root;
    private BufferedReader br = null;

    public BinaryTree() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * 构造树，index初始化0
     *
     * @param data
     * @param index
     * @return
     */
    public TreeNode<T> create(T[] data, int index) {
        if (index >= data.length) return null;
        else if (String.valueOf(data[index]).equals("#")) {
            return null;
        } else {
            TreeNode<T> node = new TreeNode<>(data[index]);
            node.leftChild = create(data, 2 * index + 1);
            node.rightChild = create(data, 2 * index + 2);
            return node;
        }
    }

    /**
     * 控制台输入数据，
     *
     * @return
     */


    private TreeNode<String> create() {
        try {
            String temp = br.readLine();
            if (!temp.equals("#")) {
                TreeNode<String> node = new TreeNode<>(temp);//暂时以String
                node.leftChild = create();
                node.rightChild = create();
                return node;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void displayInfix(TreeNode<T> treeNode) {
        //中序
        if (treeNode != null) {
            displayInfix(treeNode.leftChild);
            System.out.println(treeNode.data);
            displayInfix(treeNode.rightChild);
        }
    }

    public void displayPostfix(TreeNode<T> treeNode) {
        //后序
        if (treeNode != null) {
            displayPostfix(treeNode.leftChild);
            displayPostfix(treeNode.rightChild);
            System.out.println(treeNode.data);
        }
    }

    //前序
    public void displayPrefix(TreeNode<T> treeNode) {
        //前序
        if (treeNode != null) {
            System.out.println(treeNode.data);
            displayPrefix(treeNode.leftChild);
            displayPrefix(treeNode.rightChild);
        }
    }


    public static void main(String[] args) {
        BinaryTree<Character> bt = new BinaryTree<>();
        Character[] data = new Character[]{'0', '1', '2', '3', '4', '5', '6', '7', '#', '#', '8', '#', '#', '9', '#'};
        TreeNode<Character> tn = bt.create(data, 0);
        bt.displayInfix(tn);
//        bt.displayPrefix(tn);
//        bt.displayPostfix(tn);

        System.out.println("中序输入：");
        BinaryTree<String> bt2 = new BinaryTree<>();
        bt2.displayInfix(bt2.create());

    }

}

class TreeNode<T> {
    protected T data;
    protected TreeNode<T> leftChild;
    protected TreeNode<T> rightChild;

    //空树，二叉链表
    public TreeNode() {
        data = null;
        leftChild = null;
        rightChild = null;
    }

    //含数据，二叉链表
    TreeNode(T data) {
        this.data = data;
        leftChild = null;
        rightChild = null;
    }
}


