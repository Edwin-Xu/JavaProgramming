package DataStructureAndAlgorithm.NonLinear;
/**
 * 计数：
 * 一个n个节点的树，前序排列按自然数递增排列：1,2，，，n , 那么由中序排列的可能树就可以求得 这棵树有多少不同的构成
 * （ Bn = 2nCn / (n+1)
 * = 1/(n+1) * (2n)!/(n!)^2
 * ）
 */

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * Father tree
 * <p>
 * 思考：
 * 1.怎样将一个存在的树快速添加到一个树中
 * <p>
 * <p>
 * <p>
 * 另类思索：
 * 如果每个节点都含有一个 byte的“层” 数据会不会要好一点
 */
public class BinaryTree<T> {
    protected TreeNode<T> root;
    private int NodeNum = 0; //记录节点数
    private int Height = 0;  //记录高度、深度；

    private int count = 0;

    private BufferedReader br = null;

    private TreeNode currentNode = root; //保存当前树节点

    //构造器1
    public BinaryTree() {
        try {
            root = null;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("DataStructureAndAlgorithm/NonLinear/ReadFromFile.txt"))));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //构造器2
    public BinaryTree(TreeNode<T> root) {
        this.root = root;
        this.root.father = null;
    }

    //构造器3
    public BinaryTree(T rootdata) {
        this.root = new TreeNode(rootdata);
        NodeNum++;
        this.root.father = null;
    }

    public void buildRoot(T data) throws Exception {
        if (root == null) {
            root = new TreeNode(data);
            NodeNum++;
            this.root.father = null;
        } else {
            throw new Exception("Have had A Root!");
        }
    }

    public int getNodeNum() {
        return NodeNum;
    }

    public void addLeftChild(T data, TreeNode<T> father) {//这个方法应该添加到TreeNode
        if (father != null) {
            father.leftChild = new TreeNode(data);
            NodeNum++;
            father.leftChild.father = father;
        }
    }

    public void addRightChild(int data, TreeNode father) {
        if (father != null) {
            father.rightChild = new TreeNode(data);
            NodeNum++;
            father.rightChild.father = father;
        }
    }

    //递归遍历
    public void displayInfix(TreeNode<T> treeNode) {
        //中序
        if (treeNode != null) {
            displayInfix(treeNode.leftChild);
            System.out.println(treeNode.data);
            displayInfix(treeNode.rightChild);
        }
//        System.out.println();
    }

    public void displayPostfix(TreeNode<T> treeNode) {
        //后序
        if (treeNode != null) {
            displayInfix(treeNode.leftChild);
            displayInfix(treeNode.rightChild);
            System.out.println(treeNode.data);
        }
        System.out.println();//为什么只在所有输出末尾换行，不是一个每个叶结点之后就换行
    }

    public void displayPrefix(TreeNode<T> treeNode) {
        //前序
        if (treeNode != null) {
            System.out.println(treeNode.data);
            displayInfix(treeNode.leftChild);
            displayInfix(treeNode.rightChild);
        }
        System.out.println();
    }

    //非递归遍历： 利用栈；
    //自己还不会写，重点。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
    public void displayInfixNonRecursion(TreeNode<T> root) throws Exception {
        TreeStack stack = new TreeStack<>(50);
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.leftChild;
            }
            // 所有的左子节点压栈后，开始出栈
            // 拿到栈顶的节点
            root = (TreeNode<T>) stack.pop();
            System.out.println(root.data);
            root = root.rightChild;
        }
    }

    public void displayPrefixNonRecursion(TreeNode<T> root) throws Exception {
        if (root == null) {
            return;
        }
        TreeStack<TreeNode> stack = new TreeStack<>(30);
        stack.push(root);
        // 拿出元素
        while (!stack.isEmpty()) {
            TreeNode node = (TreeNode) stack.pop();
            System.out.println("非递归前序遍历节点：" + node.data);
            if (node.rightChild != null) {
                stack.push(node.rightChild);
            }
            if (node.leftChild != null) {
                stack.push(node.leftChild);
            }
            // 继续拿出元素进行遍历，这时拿的应该是左边的
        }
    }

    public void displayPostfixNonRecursion(TreeNode<T> node) throws Exception {
        if (node == null) {
            return;
        }
        TreeStack<TreeNode> stack = new TreeStack<>(40);
        TreeNode lastVisitNode = null;
        while (node != null) {
            stack.push(node);
            node = node.leftChild;
        }
        // 先从最后一个左子节点开始判断
        while (!stack.isEmpty()) {
            // 判断当前节点是否有右子节点，并且该右子节点没有被访问过
            node = (TreeNode) stack.pop();
            if (node.rightChild != null && node.rightChild != lastVisitNode) {
                // 说明有右子节点，该根节点还需要再被用到，所以压回栈
                stack.push(node);
                node = node.rightChild;
                while (node != null) {
                    stack.push(node);
                    node = node.leftChild;
                }
            } else {
                // 说明当前的节点没右子节点（左子节点也没有）
                System.out.println("非递归后序遍历节点：" + node.data);
                // 访问过后，代表该节点被访问过
                lastVisitNode = node;
            }
        }
    }

    // 层次遍历, 思路？？？？？？？？？
    public void levelOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.addLast(node);
        while (list.size() > 0) {
            // 取出队头元素
            node = list.removeFirst();
            System.out.println("层次遍历：" + node.data);
            if (node.leftChild != null) {
                list.addLast(node.leftChild);
            }
            if (node.rightChild != null) {
                list.addLast(node.rightChild);
            }
        }
    }

    public void insert(TreeNode<T> treeNode, TreeNode<T> newNode, boolean isNewNodeLeft, boolean isNewNodeChildLeft) {
        if (treeNode != null) {
            if (isNewNodeLeft) {
                TreeNode<T> temp = treeNode.leftChild;
                if (isNewNodeChildLeft) {
                    treeNode.leftChild = newNode;
                    newNode.leftChild = temp;
                } else {
                    treeNode.rightChild = newNode;
                    newNode.rightChild = temp;
                }
                newNode.father = treeNode;
                temp.father = newNode;
            } else {
                TreeNode<T> temp = treeNode.rightChild;
                if (isNewNodeChildLeft) {
                    treeNode.leftChild = newNode;
                    newNode.leftChild = temp;
                } else {
                    treeNode.rightChild = newNode;
                    newNode.rightChild = temp;
                }
                newNode.father = treeNode;
                temp.father = newNode;
            }
        }
    }

    //清空树，root如果不为空则不清空（无法清空???  可以对一个对象再赋值null,因为他是本类的私有变量）
    public void clear() {
        root = null;
        NodeNum = 0;
    }

    /**
     * 前序排序递归构造 二叉树，# 表示空 -------------
     * 用数组+下标实现，不需要根节点TreeNode<T> root
     * 注意注意注意：                                数组中是按层排列的， 【层排列】， 不是前中后排序
     * @param arr
     * @param index  使用时初始化为 0
     * @return
     */
    public TreeNode<T> creatPreOrder(T[] arr, int index) {
        if (index >= arr.length)                    // 可以不需要，但是所有的值必须要写满，任一个#都要写，不然会越界
            return null;
        else if (String.valueOf(arr[index]).equals("#")) {
            return null;
        } else {
            TreeNode<T> node = new TreeNode<>(arr[index]);

            /**
             * 关键点
             */
            node.leftChild = creatPreOrder(arr, 2 * index + 1);
            node.rightChild = creatPreOrder(arr, 2 * index + 2);
            return node;
        }
    }

    public TreeNode<T> creatPostOrder(T[] arr, int index) {
        /**
         * public class BuildBinaryTree {
         *     private int index ;
         *     private String postfix;
         *     public BuildBinaryTree(String a){
         *         postfix = a;
         *         index = a.length()-1;
         *     }
         *
         *     public TreeNode buildTree(){
         *         if (index!=-1){ // index !=-0 而非 0；
         *             char c = postfix.charAt(index);
         *             index--;
         *             switch (c){
         *                 case '+':
         *                 case '-':
         *                 case '*':
         *                 case '/':
         *                 case '^':
         *                     TreeNode node = new TreeNode(c);
         *                     node.rightChild = buildTree();
         *                     node.leftChild = buildTree();
         *                     return node;
         *                 case '~':
         *                     TreeNode node1 = new TreeNode(c);
         *                     node1.leftChild = buildTree();
         *                     return node1;
         *                 default:
         *                     return new TreeNode(c);
         *
         *             }
         *         }
         *         return null;
         *     }
         */
        return null;
    }

    /**
     * 从文件读，前序创建二叉树，文件中一行一个节点数据，
     * 终于成功了
     * Q： 为什么中序、后序读出来是有问题的？？
     * @param root
     * @param br
     * @return
     * @throws IOException
     */
    public TreeNode<String> creatInfix(TreeNode<String> root, BufferedReader br) throws IOException {
        String s = "";
        if (!(s = br.readLine()).equals("#")) {
            root = new TreeNode<>(s);
            root.leftChild = creatInfix(root.leftChild, br);
            root.rightChild = creatInfix(root.rightChild, br);
            return root;
        } else return null;
    }


    //获取亲兄弟
    public TreeNode getBrother(TreeNode treeNode) {
        if (treeNode == null) {
            throw new NullPointerException("Argument Null!");
        }
        //为了区别，root的兄弟就是自己
        else
            return treeNode != root ? (treeNode.father.leftChild == treeNode ? treeNode.father.rightChild : treeNode) : root;
    }

    //获取一个同辈的“堂兄弟们”（同层兄弟）,含亲兄弟,含自己；即一层的所有兄弟
    //注意：只会返回同一层存在的兄弟，
    public ArrayList<TreeNode> getCousins(TreeNode treeNode) throws NullPointerException {
        if (treeNode != null) {
            ArrayList<TreeNode> cousins = new ArrayList<>();

            if (treeNode == root.leftChild) {
                cousins.add(treeNode);
//?????????                 System.out.println(treeNode.father);
                cousins.add(root.rightChild);
                return cousins;
            } else if (treeNode == root.rightChild) {
                cousins.add(treeNode);
                cousins.add(treeNode.father.leftChild);
                return cousins;
            }

            /**算法思路：递归
             * 要找一个节点的cousins， 先找其父亲的cousins，他们的子女即是，由此递归，直到到达根节点
             *
             */
            for (TreeNode tn : getCousins(treeNode.father)) {
                //要是参差不齐是麻烦了，有的没有子孙：
                //tn可能是null
                if (tn != null) {
                    if (tn.leftChild != null)
                        cousins.add(tn.leftChild);
                    if (tn.rightChild != null)
                        cousins.add(tn.rightChild);
                }
            }
            return cousins;
        } else throw new NullPointerException("Argument Null!");

    }

    //延伸1：获取一个同辈的“堂兄弟们”（同层兄弟）,含亲兄弟,不含自己；
    public ArrayList<TreeNode> getCousinsExceptMe(TreeNode treeNode) {
        ArrayList<TreeNode> arrayList = getCousins(treeNode);
        int i = 0;
        for (TreeNode tn : arrayList) {
            if (tn == treeNode) break;
            i++;
        }
        arrayList.remove(i);
        return arrayList;
    }

    //延伸2：获取同一层存在的兄弟，root为第一层；
    public ArrayList<TreeNode> getAllLevelChildren(int level) {
        int count = 1;
        TreeNode temp = root;
        while (true) {
            if (temp.leftChild != null) {
                //......
                break;
            }
        }
        return null;

    }

    //算法思考：获取任一个最深子孙
    public TreeNode getDeepestChild() {

        return null;
    }


    /**
     * 利用前序、中序来确定唯一的一颗树  (recursion)
     */
    public TreeNode<T> createFromInPre(T[] infix, T[] prefix) {


        return null;
    }

    public static void main(String[] args) {
        BinaryTree<Character> tree = new BinaryTree<>();
//        tree.root = new TreeNode<>();
//        tree.addLeftChild(1, tree.root);//
//        tree.addRightChild(2, tree.root);//
////
//        tree.addLeftChild(3, tree.root.leftChild);
//        tree.addRightChild(4, tree.root.leftChild);
//
//        tree.addLeftChild(5,tree.root.rightChild);
//        tree.addRightChild(6,tree.root.rightChild);
//
//        tree.displayInfix(tree.root);
//        System.out.println();
//        try {
//            tree.displayInfixNonRecursion(tree.root);
//            tree.levelOrder(tree.root);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        System.out.println("Node Num:"+tree.getNodeNum());
//        tree.clear();
//        tree.displayInfix(tree.root);

//        TreeNode treeNode = tree.root.leftChild.leftChild;
//        System.out.println(tree.getBrother(treeNode).data);
//
//        for (TreeNode tn : tree.getCousinsExceptMe(treeNode)) {
//            System.out.println(tn.data);
//        }

//        try {
//            tree.buildTreeFromFile("src/DataStructureAndAlgorithm/NonLinear/tree/tree.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        tree.displayInfix(tree.root);
//        Character[] c = new Character[]{'a','b','c','d','#','#','#','#'};

//        BinaryTree<Character> t =  new BinaryTree<>();
////        t.root = t.creatBinaryTree(c);
////        t.displayInfix(t.root);
//        ArrayList<Character> arrayList = new ArrayList<>();
//        arrayList.add('a');
//        arrayList.add('b');
//        arrayList.add('#');
//        arrayList.add('#');
//        arrayList.add('#');
//        try {
//            t.root = t.buildTreeInfix(arrayList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        t.displayInfix(t.root);
        Character a[] = new Character[]{'0', '1', '#', '#', '#'};
        Character c[] = new Character[]{'0', '1', '#', '#', '2', '#', '#'};
        Character b[] = new Character[]{'0', '1', '3', '#', '#', '4', '#', '#', '2', '#', '#'};

        Character level[] = new Character[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '#'};

//        try {
//            TreeNode <String> node = tree.creatInfix(tree.root,tree.br);
////            tree.displayInfix(node);
////            tree.displayPrefix(node);
//            tree.displayPostfix(node);
//
//            System.out.println(node.leftChild.leftChild.rightChild.data);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

        tree.displayInfix(tree.creatPreOrder(level, 0));

    }


    class TreeNode<T> {
        protected T data;
        protected TreeNode<T> father;//有这个就是三叉链表，无就是二叉链表
        protected TreeNode<T> leftChild;
        protected TreeNode<T> rightChild;
        //这里其实还是可以添加更多的子孙（数组），这里就不添加了，因为几乎用不到，这个是二叉树了，如果需要更多的子孙，要定义一个数组
//    TreeNode [] treeNodes ;

        //空树，二叉链表
        public TreeNode() {
            data = null;
            father = null;
            leftChild = null;
            rightChild = null;
        }

        //含数据，三叉链表
        public TreeNode(T data, TreeNode father) {
            this.data = data;
            this.father = father;
            leftChild = null;
            rightChild = null;
        }

        //含数据，二叉链表
        public TreeNode(T data) {
            this.data = data;
            this.father = null;
            leftChild = null;
            rightChild = null;
        }


    }


    class TreeStack<T> {
        private Object[] stact;
        private int top;
        private int maxSize;

        public TreeStack(int maxSize) {
            this.stact = new Object[maxSize];
            this.maxSize = maxSize;
            this.top = 0;
        }

        public int getSize() {
            return top;
        }

        public boolean isEmpty() {
            return top == 0;
        }

        public Object peek() {
            if (top == 0) {
                return null;
            } else
                return stact[top - 1];
        }

        public void push(Object o) throws Exception {
            if (top == maxSize) {
//            throw new Exception("TreeStack full");
            } else {

                stact[top++] = o;  //这个地方写成“ top++; TreeStack[top] = o 又错；
            }
        }

        public Object pop() throws Exception {
            if (top == 0) {
                throw new Exception("empty TreeStack");
//            System.out.println("empty!!!");
//            return nu;
            } else {
                top--;
                return stact[top];//这个地方不要写"top--"
            }
        }

        public void show() {
            for (int i = 0; i < top; i++) {
                System.out.print(stact[i] + "  ");
            }
            System.out.println();
        }

        public void clear() {
            top = 0;
        }
    }
}
