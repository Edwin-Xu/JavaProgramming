package DataStructureAndAlgorithm.LinearList.generalizedLiat;

public class GeneralizedList {
}


/**
 * GLNode  广义表节点，
 * 特点：
 * 1.所有表都含有一个附加头结点，空表亦然， 便于操作
 * @param <T>
 */
class GLNode<T>{
    private T data;
    private int type; //标志域： 0：存放指向该表表头元素节点的引用
                //               1:存放值
                //               2：指向的子表
    private GLNode<T> next;

    public GLNode(){
        type = 1;
        next = null;
    }

    public GLNode(T data){
        this.data = data;
    }
    public void setNext(GLNode<T> node){
        this.next = node;
    }

}