package DataStructureAndAlgorithm.NonLinear.huffmanTree;


import java.util.LinkedList;

/**
 * 霍夫曼树 huffman
 * -----最优二叉树
 * <p>
 * 扩充二叉树  外节点
 * 内节点
 * 每个叶结点都有他的权值 w1,w2,w3,,,wn
 * 当然也有他的路径l1,l2,l3,,,ln
 * 权值X路径 的和就是 WPL————Weighted Path Length 带权路径长度
 * <p>
 *
 * 算法思路：
 *  每次选择最小的两个值，相加，生成父节点，删除两子节点并添加父节点，重复这个流程，直到只剩一个节点，即root.
 * 注意：
 * 所得的树可以有多种形态，因为左右子树是随机地左右排列的（也可以令左子树小于右子树的数值），
 * 而且同一大小的节点是可以使多个的，图像并不一定是规则的。
 */
public class HuffmanTree {
    private int[] leafLength;
    private Node root;
    LinkedList<Node> nodes;

    public HuffmanTree(int[] arr) {
        leafLength = arr;
        nodes = new LinkedList<>();
        for (int i = 0; i < leafLength.length; i++) {
            nodes.add(new Node(leafLength[i]));
        }
        root = buildHuffmanTree();
    }

    private Node buildHuffmanTree() {
        while (nodes.size() != 1) {
            Node a = getMin();
            Node b = getMin();
            Node node = new Node(a.data + b.data);//构造新节点，作为父节点
            node.left = a;
            node.right = b;
            nodes.add(node);//添加父节点
        }
        return nodes.get(0);
    }


    /**
     * 得到最小节点
     *
     * @return
     */
    private Node getMin() {
        Node temp = nodes.get(0);
        int a = 0;
        for (int j = 1; j < nodes.size(); j++) {
            if (temp.data > nodes.get(j).data) {
                temp = nodes.get(j);
                a = j;
            }
        }
        temp = nodes.get(a);
        nodes.remove(a);//删除最小节点
        return temp;
    }

    // display
    public void displayInOrder() {
        displayInOrder(root);
    }

    private static void displayInOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + "  ");
            displayInOrder(node.left);
            displayInOrder(node.right);
        }
    }

    public static void main(String[] args) {
        HuffmanTree ht = new HuffmanTree(new int[]{7, 5, 2, 4, 3});
        ht.displayInOrder();
    }

    //节点类
    class Node {
        int data;
        Node left;
        Node right;

        private Node(int data) {
            this.data = data;
            left = null;
            right = null;

        }
    }

}
