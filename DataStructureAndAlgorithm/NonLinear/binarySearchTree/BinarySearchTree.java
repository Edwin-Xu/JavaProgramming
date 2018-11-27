package DataStructureAndAlgorithm.NonLinear.binarySearchTree;

/**
 * 搜索二叉树 Binary Search Tree
 * 定义：
 *      具有如下性质的树（含空树）
 *      1.每个结点有一个作为搜索依据的 互异的 关键码key,
 *      2.key（整数/..）：左子树 < 根 < 右子树
 *      3.左右子树本身也是BST
 *
 */
public class BinarySearchTree<T> {
    private BSTNode<T> root;

    public BinarySearchTree(){
        root = null;
    }
    public BinarySearchTree(BSTNode<T> root){
        this.root = root;
    }
    public BinarySearchTree(int k, T value){
        this.root = new BSTNode<>(k,value);
    }


    /**
     * 搜索： 根据key 来搜索
     * 如果树高为 H ， 则比较次数不超过 H
     *  O(lgN),树深(N)。????
     *
     */
    public BSTNode search(BSTNode root , int key){

        if (root==null){
            return root;
        }
        if (key>root.getKey()){
            return search(root.getRight(),key);
        }
        else if (key<root.getKey()){
            return search(root.getLeft(),key);
        }
        else
             return root;

    }



    /**
     * insert:
     * 1.if root is null, insert
     * 2.key< insert key
     * 3.
     * @return
     */
    public BSTNode<T> insert(BSTNode<T> insert){
        if (search(root,insert.getKey())!=null)return null;
        return insert(root,insert);
    }
    private BSTNode<T> insert (BSTNode<T> root, BSTNode<T> insertNode) {

             if (root==null){
                 root = insertNode;
             }
             else if (root.getKey()>insertNode.getKey()){
                    root.setLeft(insert(root.getLeft(),insertNode));
             }
             else if (root.getKey()<insertNode.getKey()){
                 root.setRight(insert(root.getRight(),insertNode));
             }
             return root;

    }

    /**
     * 删除：
     * 1.左子树为空，右子女补充
     * 2,右子树为空，左子女补充
     * 3.左右子女皆不为空，用 右子树中序下第一个节点补充（关键码最小，但比左子树都大）
     *
     * failed
     * @param node
     */
    public void delete(BSTNode<T> node){
        //node exist?
//        search
        BSTNode current = root;
        if (current.getKey()==node.getKey()) {
            root = null;
        }
        while (true){
            if (current.getKey()==node.getKey()){
                
            }
        }
    }

    //not mine
    public void delete(BSTNode<T> root,BSTNode<T> x)
    {
        if(root==null)
            return ;
        BSTNode<T> p=null;
        while(root!=null)//定位到需要删除的节点  
        {
            if(x.getKey()<root.getKey())
            {
                p=root;//记录父节点  
                root=root.getLeft();
            }
            else if(x.getKey()>root.getKey())
            {
                p=root;//记录父节点  
                root=root.getRight();
            }
            else//找到啦  
            {
                if(root.getLeft()==null&&root.getRight()==null)//①待删除的是 叶子节点  
                {
                    if(p==null)//待删除的是根节点  
                        root=null;
                    else
                    {
                        if(p.getLeft()==root)
                            p.setLeft(null);
                        else if(p.getRight()==root)
                            p.setRight(null);
                    }
                }
                else if(root.getLeft()!=null&&root.getRight()==null)//② 待删除的节点只有左孩子  
                {
                    if(p==null)//待删除的是根节点  
                        root=root.getLeft();
                    else
                    {
                        if(p.getLeft()==root)//待删除的本身是一个左孩子  
                            p.setLeft(root.getLeft()) ;
                        else if(p.getRight()==root)
                            p.setRight(root.getLeft()) ;
                    }
                }
                else if(root.getLeft()==null&&root.getRight()!=null)//② 待删除的节点只有右孩子  
                {
                    if(p==null)//待删除的是根节点  
                        root=root.getRight();
                    else
                    {
                        if(p.getLeft()==root)//待删除的本身是一个左孩子  
                            p.setLeft(root.getRight()) ;
                        else if(p.getRight()==root)
                            p.setRight(root.getRight()) ;

                    }
                }
                else//③待删除的节点即有左孩子又有右孩子    方法：得到待删除节点右子树的最小值，      
                {//该最小值与待删除节点进行“ 值 ”交换，删除该最小值位置处的节点  
                    BSTNode<T> rMin=root.getRight(); //求待删除节点的后继节点,即待删除节点的右孩子的最小值(找到的后继节点肯定没有左孩子！！！)  
                    BSTNode<T> rMinP=null;//因为需要删除后继节点位置，所以需要记录父节点  
                    while(rMin!=null)
                    {
                        rMinP=rMin;
                        rMin=rMin.getLeft();
                    }
                    int rootVtemp=root.getKey();//值交换  
                    root.key=rMin.getKey();
                    rMin.key=rootVtemp;
                    //删除rMin位置的节点，此时此位置的值已是待删节点的值  
                    if(rMinP.getLeft()==rMin)
                        rMinP.left=rMin.getRight();
                    else if(rMinP.getRight()==rMin)
                        rMinP.right=rMin.getRight();
                }
            }
            break;//找到后删了后就跳出while循环  
        }

    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(50,0);
        bst.root.setLeft(new BSTNode<>(40,1));
        bst.root.setRight(new BSTNode<>(60,2));
        bst.root.getLeft().setLeft(new BSTNode<>(30,3));
        bst.root.getLeft().setRight(new BSTNode<>(45,4));
        bst.root.getRight().setLeft(new BSTNode<>(55,5));
        bst.root.getRight().setRight(new BSTNode<>(70,6));

        System.out.println(bst.search(bst.root,70).getValue());
    }
}


