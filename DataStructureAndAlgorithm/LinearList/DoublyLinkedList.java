package DataStructureAndAlgorithm.LinearList;

/**
 * 双向表：doubly linked list
 */
public class DoublyLinkedList<T> {

    //内部类
    private class Node<T> {
        public Node<T> pre;
        public Node<T> next;
        public T data;

        public Node() {
            this.data = null;
            this.next = null;
            this.pre = null;
        }

        public Node(T data, Node pre, Node next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }
    }

    //链表的属性 大小 头节点 尾节点
    private int size;
    private Node<T> header;
    private Node<T> tail;

    //初始化链表
    public DoublyLinkedList() {
        this.size = 0;
        header = new Node<T>(null, null, null);
        tail = new Node<T>(null, header, null);
        header.next = tail;
        if (header == null) {
            System.out.println("header is null");
        }
        if (tail == null) {
            System.out.println("tail isi null");
        }
    }

    //添加元素
    public void add(T item) {
        Node<T> newNode = new Node(item, null, null);
        tail.pre.next = newNode;
        newNode.pre = tail.pre;
        newNode.next = tail;
        tail.pre = newNode;
        size++;
    }

    //判空
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    //获取某个节点
    public T get(int idx) {
        if (idx >= size || idx < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node current = header;
        for (int i = 0; i <= idx; i++) {
            current = current.next;
        }
        return (T) current.data;
    }

    //输出链表
    public void print() {
        Node<T> current = header.next;
        while (current.next != null) {
            System.out.println(current.data.toString());
            current = current.next;
        }
    }

    //    测试
    public static void main(String[] args) {
        DoublyLinkedList<String> strs = new DoublyLinkedList<>();
        strs.add("first");
        strs.add("second");
        strs.add("three");
        System.out.println("size :" + strs.size);
        strs.print();
        System.out.println(strs.get(0));
    }

}
