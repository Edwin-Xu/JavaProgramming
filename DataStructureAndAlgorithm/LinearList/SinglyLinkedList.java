package DataStructureAndAlgorithm.LinearList;

/**
 * 单链表 ： singly linked list
 * 也叫线性链表
 * 一个存储节点 Node 分两部分：
 *      数据域 链域
 *      data link
 *
 */
public class SinglyLinkedList {
    private Node head = null;
    private int size = 0;

    public boolean isEmpty(){
        return size==0;
    }

    public int getSize(){
        return size;
    }

    public void add (Object o){
        Node node = new Node(o);
        if (isEmpty()){
            head = node;
        }
        else {
            //Q: 为什么新建一个临时Node也行，这个Node并不代表原List的吧？？？
            Node temp = head;//引用？？
            while (temp.next != null){
                temp = temp.next;
            }
            temp.next = node;
        }
        size++;
    }

    //插入index的后面
    public void insert(int index, Object data){
        Node node = new Node(data);
        int count = 0;
        if (index>=0 && index<size){
            if (isEmpty()){
                add(node);
            }
            else {
                Node temp = head;
                while (temp != null){
                    if (count==index){
                        //如果有1,2,3号位置，要插入2号位置，先取出3号，插入4到2后，4的next变成取出的3号，而2号的next变为插入的4号
                        Node temp2 = temp.next;
                        node.next = temp2;
                        temp.next = node;
                        break;
                    }
                    temp = temp.next;
                    count++;

                }
            }
        }
        else {
            System.out.println("Wrong index!");
        }
    }


    /**
     * 自己写的，失败
     */
    public void reverse() {
//        int size = sll.size;
//        for (int i = 0, j = size; i <= j; i++, j--){
//
//        }
        for (int i =0;i<size;i++){
            int n = -1;
            for (int j=0;j<size-1;j++){
                n=j;
                if (j==0){
                    Node temp = head;
                    head = head.next;
                    head.next = temp;
                }

                else {
                    Node temp = head;
                    int count =0;
                    while (temp!=null&&count<j-1){
                        temp = temp.next;
                        count++;
                    }
                    Node temp2 = temp.next;
                    temp.next = temp.next.next;
                    head.next.next = temp2;
                    if (j== size-1){
                        head.next.next.next=null;
                    }
                }
            }
            if (i==0){
                Node temp = head;
                int count =0;
                while (temp!=null&&count<size){
                    temp = temp.next;
                    count++;
                }
                temp.next=null;
            }
            size--;
        }
//            return sll;
    }

    /**
     * 这个看不太懂，为什么一定要是head = reverse(head)才管用？？？？？
     * @param head
     * @return
     */
    public static Node reverse(Node head) {
        if (head == null)
            return head;
        Node pre = head;// 上一结点
        Node cur = head.next;// 当前结点
        Node tmp;// 临时结点，用于保存当前结点的指针域（即下一结点）
        while (cur != null) {// 当前结点为null，说明位于尾结点
            tmp = cur.next;
            cur.next=pre;// 反转指针域的指向
            // 指针往下移动
            pre = cur;
            cur = tmp;
        }
        // 最后将原链表的头节点的指针域置为null，还回新链表的头结点，即原链表的尾结点
        head.next=null;
        return pre;
    }


    /**
     * 你无法改变当前的实例，比如你欲将TEMP 变为null， temp = null 是不可行的，只能更改它的 成员变量?????? head = head.next;
     * @param index
     */
    public void remove(int index) {
        if (index == 0 ){
            Node temp = head.next.next;
            head = head.next;
            head.next = temp;
        }
        if (index > 0 && index < size) {
            int count = 0;
            Node temp = head;
            while (head!=null){
                if (count==index-1){
                   temp.next = temp.next.next;
                   break;
                }
                temp = temp.next;
                count++;
            }
            size--;
        }
        else {
            System.out.println("Wrong index!");
        }
    }

    //移除最后一个
    public void remove(){
        if (size==1){
            head = null;
            size=0;
        }
        else if (size>0){
            Node temp = head;
            while (temp != null) {
                if (temp.next.next==null) {
                    break;
                }
                temp = temp.next;
            }
            temp.next=null;
            size--;
        }
    }

    public void show(){
        Node temp = head;
        System.out.println("DATA: ");
        while (temp!=null){
            System.out.print(temp.data+"  ");
            temp = temp.next;
        }
        System.out.println();
    }

    public Node get(int index){
        Node temp = head;
        int count = 0;
        while (temp!=null){
            if (count==index){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public void set(int index,Object o){
        Node temp = head;
        int count = 0;
        while (temp!=null){
            if (count==index){
                temp.data = o;
                break;
            }
            temp = temp.next;
        }
    }


    public static void main(String[] args) {
        SinglyLinkedList sld = new SinglyLinkedList();
        sld.add(1);
        sld.add(2);
        sld.add(3);
        sld.add(4);
        sld.add(5);
        sld.show();
//        sld.insert(1,12);
//        sld.insert(0,0);
//        sld.show();
//        sld.remove();
//        sld.show();
//        sld.remove();
//        sld.show();
//        sld.reverse();
        sld.head=reverse(sld.head);
        sld.show();
    }
}

//结点类
