package DataStructureAndAlgorithm.homework;

import DataStructureAndAlgorithm.LinearList.SinglyLinkedList;
import org.w3c.dom.Node;

/**
 *  exercises:
 *  1. Swap two adjacent elements by adjusting only the links(and not the data)  using:
 *      a. Singly linked lists.
 *      b. Doubly linked lists.
 *  2. Given two sorted lists L1 and L2,write a procedure to compute L1∩ L2
 *       using only the basic list operations.
 *  3. Given two sorted lists, L1 and L2, write a procedure to compute L1ᴜ L2
 *      using only the basic list operations.
 *  4. Write a nonrecursive method to reverse a singly linked
 *      List in O(N) time.
 *
 */
public class Homework3 {
    private SinglyLinkedList sll ;
    public Homework3(){
        this.sll = new SinglyLinkedList();
        for (int i =0;i<10;i++){
            sll.add(i+1);
        }
    }

    public void swap(Node n1, Node n2){

    }
}
