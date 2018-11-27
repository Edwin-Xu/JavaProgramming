package DataStructureAndAlgorithm.LinearList;

/**
 * 单链表 解决多项式的加法、乘法操作
 * <p>
 * ctrl+r :全部替换
 * ctrl+ alt + l : 整理代码
 * <p>
 * 去掉小数点后无用的0：
 * c = c.replaceAll("0+?$", "");//去掉多余的0
 * c = c.replaceAll("[.]$", "");//如最后一位是.则去掉
 */
public class Polynomial {
    private SinglyLinkedList2 polynomial_1;
    private SinglyLinkedList2 polynomial_2;

    public Polynomial() {
        this.polynomial_1 = new SinglyLinkedList2();
        //多项式
        polynomial_1.add(1, 2);
        polynomial_1.add(-2, -2);
//        polynomial_1.add(3, -1);
//        polynomial_1.add(-4, 0);
        this.polynomial_2 = new SinglyLinkedList2();
        polynomial_2.add(1, 2);
        polynomial_2.add(2, -5);
//        polynomial_2.add(-3, 2);
//        polynomial_2.add(4, -7);
    }

    //多项式相加
    public SinglyLinkedList2 addition(SinglyLinkedList2 polynomial_1, SinglyLinkedList2 polynomial_2) {
        SinglyLinkedList2 polynomial_add = new SinglyLinkedList2();//一个存储相加后的链表

        //加之前为了避免重复判断重复加，先将多项式合并同类项，
        polynomial_1 = integrate(polynomial_1);
        polynomial_2 = integrate(polynomial_2);

        //难点: 去第一个链表，遍历data，分别与第二个链表的遍历data比对
        //count count2花了我好多时间，思维。。。
        int[] a = new int[polynomial_2.size];
        ListNode temp1 = polynomial_1.head;
        while (temp1 != null) {
            ListNode temp2 = polynomial_2.head;
            int count = 0;//
            int count2 = 0;//
            while (temp2 != null) {
                if (temp1.exp == temp2.exp) {
                    polynomial_add.add(temp1.coef + temp2.coef, temp2.exp);
                    a[count] = -1;   //标记第二个列表，如果任意一次可加，赋值-1标记
                    count2++;//标记第一个列表，如果无一次加则始终为1
                }
                count++;
                temp2 = temp2.next;
            }
            if (count2 == 0) {//找出第一个列表未被加的
                polynomial_add.add(temp1.coef, temp1.exp);
            }
            temp1 = temp1.next;
        }

        //检测 第二个列表，找出没有被加的
        ListNode temp2 = polynomial_2.head;
        int count = 0;
        while (temp2 != null) {
            if (a[count] == 0) {
                polynomial_add.add(temp2.coef, temp2.exp);
            }
            temp2 = temp2.next;
            count++;
        }
        System.out.print("Addition:  ");
        return bubbleSort(polynomial_add);
    }

    //多项式相乘
    public SinglyLinkedList2 multiply(SinglyLinkedList2 s1, SinglyLinkedList2 s2) {
        SinglyLinkedList2 polynomial_mul = new SinglyLinkedList2();
        s1 = integrate(s1);
        s2 = integrate(s2);

        ListNode temp1 = s1.head;
        while (temp1 != null) {
            ListNode temp2 = s2.head;
            while (temp2 != null) {
                polynomial_mul.add(temp1.coef * temp2.coef, temp1.exp + temp2.exp);
                temp2 = temp2.next;
            }
            temp1 = temp1.next;
        }
        System.out.print("Multiplication:  ");
        return integrate(polynomial_mul);
    }

    //合并多项式
    public static SinglyLinkedList2 integrate(SinglyLinkedList2 s) {
        bubbleSort(s);//先排序，再合并
        ListNode temp = s.head;
        while (temp.next != null) {
            if (temp.exp == temp.next.exp) {
                temp.coef = temp.coef + temp.next.coef;
                temp.next = temp.next.next;
            }
            if (temp.next != null && temp.exp != temp.next.exp) {
                temp = temp.next;
            }
        }
        return s;
    }

    //冒泡排序, 对链表进行排序
    public static SinglyLinkedList2 bubbleSort(SinglyLinkedList2 s) {
        ListNode head = s.head;
        if (head == null || head.next == null)  //链表为空或者仅有单个结点
            return s;

        ListNode cur = null, tail = null;

        cur = head;

        while (cur.next != tail) {
            while (cur.next != tail) {
                if (cur.exp > cur.next.exp) {
                    int tmp = cur.exp;
                    cur.exp = cur.next.exp;
                    cur.next.exp = tmp;

                    float tmp2 = cur.coef;
                    cur.coef = cur.next.coef;
                    cur.next.coef = tmp2;
                }
                cur = cur.next;
            }
            tail = cur;  //下一次遍历的尾结点是当前结点(仔细琢磨一下里面的道道)
            cur = head;     //遍历起始结点重置为头结点    
        }
        return s;
    }

    public static void main(String[] args) {
        Polynomial slp = new Polynomial();
        slp.polynomial_1.show();
        slp.polynomial_2.show();
        slp.addition(slp.polynomial_1, slp.polynomial_2).show();
//        slp.multiply(slp.polynomial_1,slp.polynomial_2).show();
    }
}


class SinglyLinkedList2 {
    protected ListNode head;
    protected int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void add(float c, int e) {
        ListNode node = new ListNode(c, e);
        if (size == 0) {
            head = node;
        } else {
            //Q: 为什么新建一个临时Node也行，这个Node并不代表原List的吧？？？
            ListNode temp = head;//引用？？
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
        size++;
    }

    //插入index的后面
    public void insert(int index, float c, int e) {
        ListNode node = new ListNode(c, e);
        int count = 0;
        if (index >= 0 && index < size) {
            if (isEmpty()) {
                add(c, e);
            } else {
                ListNode temp = head;
                while (temp != null) {
                    if (count == index) {
                        //如果有1,2,3号位置，要插入2号位置，先取出3号，插入4到2后，4的next变成取出的3号，而2号的next变为插入的4号
                        ListNode temp2 = temp.next;
                        node.next = temp2;
                        temp.next = node;
                        break;
                    }
                    temp = temp.next;
                    count++;

                }
            }
        } else {
            System.out.println("Wrong index!");
        }
    }

    public void remove(int index) {
        if (index == 0) {
            ListNode temp = head.next.next;
            head = head.next;
            head.next = temp;
        }
        if (index > 0 && index < size) {
            int count = 0;
            ListNode temp = head;
            while (head != null) {
                if (count == index - 1) {
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

    //移除最后一个
    public void remove() {
        if (size == 1) {
            head = null;
            size = 0;
        } else if (size > 0) {
            ListNode temp = head;
            while (temp != null) {
                if (temp.next.next == null) {
                    break;
                }
                temp = temp.next;
            }
            temp.next = null;
            size--;
        }
    }

    public void show() {
        ListNode temp = head;
        String s = "";
        s += (subZeroAndDot(Float.toString(head.coef)) + exp(temp.exp));
        while (temp != null && temp.coef != 0) {
            temp = temp.next;
            if (temp == null) {
                break;
            }
            s += ((temp.coef > 0 ? "+" : "") + subZeroAndDot(Float.toString(temp.coef)) + exp(temp.exp));
        }
        System.out.println(s);
    }

    public String exp(int e) {
        if (e == 0) {
            return "";
        } else if (e == 1) {
            return "X";
        } else if (e > 0) {
            return "X^" + e;
        } else {
            return "X^(" + e + ")";
        }
    }

    public static String subZeroAndDot(String s) {
        if (s.matches("1\\.0+")) {
            //系数为1
            return "";
        } else if (s.matches("-1\\.0+")) {
            //系数为-1
            return "-";
        }
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    public ListNode get(int index) {
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            if (count == index) {
                return temp;
            }
            if (count == size - 1) {//注意：当要去最后一个时，不加这段是不行的，count达到了但是TEMP是null
                return temp;
            }
            temp = temp.next;
            count++;
        }
        return null;
    }

    public void set(int index, float c, int e) {
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            if (count == index) {
                temp.exp = e;
                temp.coef = c;
                break;
            }
            temp = temp.next;
        }

    }

}

//结点类
class ListNode {
    //data
    protected float coef;
    protected int exp;
    protected ListNode next;

    public ListNode(float coef, int exp) {
        this.coef = coef;
        this.exp = exp;
    }
}