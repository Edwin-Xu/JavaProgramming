package DataStructureAndAlgorithm.LinearList.Queue;

//导入
import DataStructureAndAlgorithm.LinearList.Node;

/**
 * Queue 队列  ：       链表实现
 *   head 删除点 《------------《rear 插入点
 */
public class Queue_Node{
    private Node head;
    private int length = 0;
    private int maxSize = Integer.MAX_VALUE; //默认“没有大小限制”；
    private Node currentNode ;

    public Queue_Node(){
        this.head = new  Node() ;
        this.currentNode = head;

    }

    public Queue_Node(int maxSize){
        this.head = new  Node() ;//头结点
        this.maxSize = maxSize;
        this.currentNode = head;//记录当前节点；
    }

    public boolean isEnpty (){
        return length == 0;
    }
    public boolean isFull (){
        return length == maxSize;
    }

    public void enQueue(Object o){
        if (isFull()){
            try {
                throw new Exception ("The Queue is full!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            currentNode.next = new Node(o);
            currentNode = currentNode.next;
            length++;
        }
    }

    public Object deQueue() {
        //empty queue can't delete element;
        if (isEnpty()) {
            try {
                throw new Exception("The Queue is empty !");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else {
           Node res = head.next;
           head.next = head.next.next;
           length--;
           return res;
        }
    }

    public void display(){
        System.out.println(length+" Queue Elements as follows ： ");
        Node temp = head.next;
        int count = 0;
        while (temp!=null){
            System.out.println("NO."+count +": "+ temp.data);
            count++;
            temp = temp.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Queue_Node qn = new Queue_Node();
        qn.enQueue(1);
        qn.enQueue(2);
        qn.enQueue(3);
        qn.display();

        qn.enQueue(4);
        qn.enQueue(5);
        qn.display();

        qn.deQueue();
        qn.deQueue();
        qn.deQueue();
        qn.deQueue();
        qn.display();
    }
}


