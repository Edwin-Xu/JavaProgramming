package DataStructureAndAlgorithm.NonLinear.binarySearchTree;

public class BSTNode<T> {
    private T value;
    protected int key;
    protected BSTNode<T> left;
    protected BSTNode<T> right;

    public T getValue() {
        return value;
    }
    public int getKey() {
        return key;
    }
    public BSTNode<T> getLeft() {
        return left;
    }
    public BSTNode<T> getRight() {
        return right;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }
    public void setRight(BSTNode<T> right) {
        this.right = right;
    }
    public String toString(){
        return "[ "+key+" , "+value+" ]";
    }



    public BSTNode(int key,T value){
        this.key = key;
        this.value = value;
    }
}
