package DataStructureAndAlgorithm.NonLinear.indexedBinarySearchTree;

public class IndexedBST<T> {
    private IBSTNode<T> root;

    public IndexedBST(){
        root = null;
    }

    public IndexedBST(T data){
        root = new IBSTNode<>(data);
    }

    /**
     * 递归函数实现在带索引的二叉搜索树（IndexBST)中查找第k个小的元素
     * 分析：
     *      k==leftsie:  get it ,左边有k个小的，得到了
     *      k >　leftsize: 在本节点的右边，pre = right
     *      ｋ　＜ leftsize : 在本节点的左边，pre = left
     */
    public IBSTNode<T> findKth(IBSTNode<T> root , int k ){
        if (root==null||k<1) return null;
        if (k==root.getLeftSize()) {
            return root;
        }
        else if (k>root.getLeftSize()){
            return findKth(root.right,k);
        }
        else return findKth(root.left,k);
    }
}




