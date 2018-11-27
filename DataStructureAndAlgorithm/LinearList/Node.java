package DataStructureAndAlgorithm.LinearList;

public class Node {
    //data
    public Object data;
    //point to next node, a class has himself！！！！
    public Node next;//方便其他包可见

    public Node(Object data){
        this.data = data;
    }
    public Node(){
    }
}