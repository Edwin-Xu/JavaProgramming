package DataStructureAndAlgorithm.homework.computerHomework2;

/**
 * 利用二叉树的后缀表示来构造一棵二叉树(习题）
 * ( a + b ) * c                        a b +  c*
 * <p>
 * ( ¬ a + b ) * c                      a ¬ b + c *
 * <p>
 * d + ( - a + b ) * c                     d a ¬ b + c * +
 * <p>
 * analysis:
 * 递归实现：
 * 从右往左
 * 最后一个肯定是 根节点
 * 看前一位： 如果是运算符，则他是右节点，剩下的部分是他的子节点，最后会剩下他的兄弟
 * 如果是值，则它是一个叶结点，
 */
public class BuildBinaryTree {
    private int index;      //表达式最后位的下标
    private String postfix;  //后缀表达式
    private TreeNode root;

    public BuildBinaryTree(String a) {
        postfix = a;
        index = a.length() - 1;
        this.root = buildTree();
    }

    private TreeNode buildTree() {
        if (index != -1) { // index !=-0 而非 0；
            char c = postfix.charAt(index);
            index--;
            switch (c) {
                // 加减乘除乘方，二元运算
                case '+':
                case '-':
                case '*':
                case '/':
                case '^':
                    TreeNode node = new TreeNode(c);
                    node.rightChild = buildTree();
                    node.leftChild = buildTree();
                    return node;
                case '~':
                    TreeNode node1 = new TreeNode(c);
                    node1.leftChild = buildTree();
                    return node1;
                default:
                    return new TreeNode(c);
            }
        }
        return null;
    }

    public void displayInfix(){
        displayInfix(root);
    }
    private static void displayInfix(TreeNode treeNode) {
        //中序
        if (treeNode != null) {
            displayInfix(treeNode.leftChild);
            System.out.print(treeNode.data + " ");
            displayInfix(treeNode.rightChild);
        }
    }


    public static void main(String[] args) {
        //da~b+c*+  d+(a+b)*c
//        BuildBinaryTree bbt = new BuildBinaryTree("ab+c*");
        BuildBinaryTree bbt = new BuildBinaryTree("da~b+c*+");
        bbt.displayInfix();
    }
//    private String infixToPostfix(String e) throws Exception {
//        /** 1) 遇运算分量输出
//         * 2) 遇运算符：比较当前运算符与栈顶运算符的优先级.  若当前运算符的优先级<=栈顶运算符的优先级,
//         *  则不断取出运算符栈顶输出,  否则进栈.  因此一开始栈中要放一个优先级最低的运算符,  假设为“#”，
//         *  例子： A+B+C；  A*B-C
//         *  <p>
//         * (A+B)*(C-D)#------  AB+CD-*#
//         * 3）遇‘（’ : 每个运算符有双重优先级.
//         * 4）遇‘）’ : 依次输出栈顶直至遇匹配的‘（’为止。
//         * ‘（’不输出但要退栈。
//         * 5）遇‘#’ :依次输出栈顶，直至遇‘#’为止。
//         * ‘#’不输出但要退栈。
//         */
//
//        String postfix = "";
//        Stack stack = new Stack(e.length());//start a stack;
//        stack.push('#');//先在栈之中放一个运算优先级最低的运算符，假设为#
//        while (!stack.isEmpty()) {
//
//        }
//
//        return postfix;
//    }
//
//    //当前运算符优先级<=栈顶运算符的优先级   c<=top ture,
//    public boolean priority(char c, char top) {
//        switch (c) {
//            case '+':
//            case '-':
//                return !(top == '#');
//            case '*':
//            case '/':
//                return (top == '^' || top == '*' || top == '/');
//            case '^':
//                return top == '^';
//            default:
//                return false;
//        }
//    }
//
//    class Stack {
//        private char[] stact;
//        private int top;
//        private int maxSize;
//
//        public Stack(int maxSize) {
//            this.stact = new char[maxSize];
//            this.maxSize = maxSize;
//            this.top = 0;
//        }
//
//        public int getSize() {
//            return top;
//        }
//
//        public boolean isEmpty() {
//            return top == 0;
//        }
//
//        public Character peek() {
//            if (top == 0) {
//                return null;
//            } else
//                return stact[top - 1];
//        }
//
//        public void push(char o) throws Exception {
//            top++;
//            stact[top] = o;
//        }
//
//        public char pop() throws Exception {
//            if (top == 0) {
//                throw new Exception("empty stack");
//            } else {
//                top--;
//                return stact[top];
//            }
//        }
//
//    }


    class TreeNode {
        protected Character data;
        protected TreeNode leftChild;
        protected TreeNode rightChild;

        //空树，二叉链表
        public TreeNode() {
            data = null;
            leftChild = null;
            rightChild = null;
        }

        //含数据，二叉链表
        public TreeNode(char data) {
            this.data = data;
            leftChild = null;
            rightChild = null;
        }
    }

}
