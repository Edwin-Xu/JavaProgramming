package containerClass;

import java.util.LinkedList;
//后进先出

public class LinkedList_ {
    public static void main(String[] args) {
        LinkedList <Integer> ll = new LinkedList<Integer>();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        for (int i :ll){
            System.out.println(i);
        }
        for (int i=0; i<ll.size();i++){
            System.out.println(ll.get(i));
        }
        ll.addFirst(4);
        ll.addFirst(5);
        ll.addFirst(6);

        for (int i :ll){
            System.out.println(i);
        }
        for (int i=0; i<ll.size();i++){
            System.out.println(ll.get(i));
        }

        ll.addLast(7);
        ll.addLast(8);
        ll.addLast(9);
        for (int i=0; i<ll.size();i++){
            System.out.println(ll.get(i));
        }

        ll.push(66);
        for (int i=0; i<ll.size();i++){
            System.out.println(ll.get(i));
        }
        ll.pop();
        for (int i=0; i<ll.size();i++){
            System.out.println(ll.get(i));
        }
    }
}
