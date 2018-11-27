package DataStructureAndAlgorithm.LinearList;

/**
 * 循环链表  circular  list
 */
public class circularList {
    //附加头结点、尾结点。方便处理。
    private Node first = new Node(0);
    private Node last = new Node(1);
    private int size = 0;

    public circularList() {
        //构成循环
        last.next = first;
        first.next = last;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void add(Object o) {
        Node node = new Node(o);
        if (isEmpty()) {
            first.next = node;
            node.next = last;
        } else {
            //Q: 为什么新建一个临时Node也行，这个Node并不代表原List的吧？？？
            Node temp = first;//引用？？
            int count = 0;
            while (temp.next != last) {
                count++;
                temp = temp.next;
                if (count == size) {
                    break;
                }
            }
            temp.next = node;
            node.next = last;
        }
        size++;
    }

    public Node getLast() {
        Node temp = first;
        while (temp != last) {
            temp = temp.next;
            if (temp == last) {
                break;
            }
        }
        return temp;
    }

    public void insert(int index, Object data) {
        Node node = new Node(data);
        if (index < 0) {
            index = size + index;
        }
        if (index >= size) {
            index = index % size;
        }
        if (isEmpty()) {
            add(node);
        } else {
            Node temp = first;
            int count = 0;
            while (count < index + 2) {
                if (count == index + 1) {
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
        size++;
    }

    public void insertBefore(int index, Object data) {
        Node node = new Node(data);
        if (index < 0) {
            index = size + index;
        }
        if (index >= size) {
            index = (index) % size;
        }
        if (isEmpty()) {
            add(node);
        } else {
            Node temp = first;
            int count = 0;
            while (count < index + 1) {
                if (count == index) {
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
        size++;
    }

    public void show() {
        Node temp = first;
        System.out.print("data : ");
        while (temp != last) {
            temp = temp.next;
            if (temp == last) {
                break;
            }
            System.out.print(temp.data + "  ");
        }
        System.out.println();
    }

    public Object get(int index) {
        int count = -1;
        if (index >= size) {
            index = index % size;
        }
        Node temp = first;
        while (temp != last) {
            if (count == index) {
                break;
            }
            count++;
            temp = temp.next;
        }
        return temp.data;
    }


    public void remove(int index) {
        if (index >= size) {
            index = index % size;
        }

        if (index == 0) {
            Node temp = first.next.next;
            first = first.next;
            first.next = temp;
            size--;
        } else if (index > 0 && index < size) {
            int count = 0;
            Node temp = first;
            while (first != null) {
                if (count == index) {
                    temp.next = temp.next.next;
                    break;
                }
                temp = temp.next;
                count++;
            }
            size--;
        } else {
            System.out.println("Wrong index!");
        }
    }

    public void remove2(int index) {
        if (index > size) {
            index = index % size;
        }
        if (index == 1) {
            Node temp = first.next.next;
            first = first.next;
            first.next = temp;
            size--;
        } else if (index > 1 && index <= size) {
            int count = 1;
            Node temp = first;
            while (true) {
                if (count == index) {
                    temp.next = temp.next.next;
                    break;
                }
                temp = temp.next;
                count++;
            }
            size--;
        } else {
            System.out.println("Wrong index!");
        }
    }

    /**
     * 移除最后一个元素
     */
    public void remove() {
        if (size == 1) {
            first.next = last;
        } else {
            Node temp = first;
            while (temp != null) {
                if (temp.next.next == last) {
                    break;
                }
                temp = temp.next;
            }
            temp.next = last;
        }
    }

    public static void main(String[] args) {
        circularList cl = new circularList();
        System.out.println(cl.size);
        cl.add(1);
        cl.show();
        System.out.println(cl.size);
        cl.add(2);
        cl.add(3);
        cl.add(4);
        cl.show();
        System.out.println(cl.size);
        cl.insert(1, "new");
        System.out.println(cl.size);
        cl.show();
        cl.insertBefore(0, "bre");
        cl.show();
        cl.remove();
        cl.show();
        cl.remove(0);
        cl.show();
        System.out.println(cl.get(0));
        System.out.println(cl.get(1));
        System.out.println(cl.get(2));

        cl.remove2(4);
        cl.show();


    }
}
