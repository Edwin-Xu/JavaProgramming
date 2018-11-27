package DataStructureAndAlgorithm.NonLinear.indexedBinarySearchTree;

/**
 * 带索引的二叉树节点：
 *  leftsize left data right
 *  带索引的二叉搜索树的节点中的leftsize指定了:左子树中的节点个数+1
 *
 * @param <T>
 */
public class IBSTNode<T> {
    private int leftSize;

    public int getLeftSize() {
        return leftSize;
    }

    private T data;
    protected IBSTNode<T> left;
    protected IBSTNode<T> right;

    public IBSTNode(){
        left = null;
        right = null;
    }
    public IBSTNode(T data){
        this.data = data;

    }
}
