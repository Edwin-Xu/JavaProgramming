package DataStructureAndAlgorithm.LinearList;

/**
 * 约瑟夫问题，循环链表解决
 */
public class JosephusProblem_circularList {
    private CircularList cl =null;
    private int peopleNum;
    private int number;


    public JosephusProblem_circularList(int peopleNum, int number){
        cl = new CircularList();
        this.number = number;
        this.peopleNum = peopleNum;
        for (int i = 0;i<peopleNum;i++){
            cl.add(i+1);
        }
        cl.show();

        while (cl.getSize()>1) {

            System.out.println("移除第"+ (this.number)+"个 ： "+ cl.get(this.number-1));
            cl.remove2(this.number);
            cl.show();
            this.number+=(number-1);
            if (this.number>cl.getSize()){//加上这句就行了，看来前面那个remove是有问题的
                this.number%= cl.getSize();
            }
            System.out.println();
        }
        System.out.println("The lucky dog is "+cl.get(0));
    }

    public static void main(String[] args) {
        new JosephusProblem_circularList(8,3);
    }
}

/**
 * 循环链表  circular  list
 */
class CircularList {
    //附加头结点、尾结点。方便处理。
    private Node first = new Node(0);
    private Node last = new Node(1);
    private int size = 0;

    public CircularList() {
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
    
}
