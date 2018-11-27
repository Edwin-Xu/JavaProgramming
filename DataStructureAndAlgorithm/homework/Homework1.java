package DataStructureAndAlgorithm.homework;

import java.util.LinkedList;


/**
 * Exercises:
 * 1. Write a recursive method that returns the number of 1’s in the binary
 *     representation of N. Use the fact that is equal to the number of 1’s in the
 *     representation of N/2, plus 1, if N is odd.
 * 2. Write the routines wise the following declarations:
 *           public void permute( String str );
 *           private void permute( char [ ] str, int low, int high )
 *      The first routine is a driver that calls the second and prints all the
 *      permutations of the characters in String str. If str is “abc”, then the strings that are
 *      output are abc, acb, bac, bca, cab,and cba. Use recursion for the second routine.
 * 3. 已知a[n]为整型数组，试写出实现下列运算的递归算法。
 *    1）求数组a中的最大整数。
 *    2）求n个整数的平均值。
 * 4. Write a recursive method that calculates and returns the length of a linked list.
 * 5.  Check recursively if the following objects are palindromes （回文）:
 *     a.  A word
 *     b. a sentence ( ignoring blanks, lower- and uppercase differences, and punctuation marks
 *     so that “Madam, I’m Adam” is accepted as a palindrome)
 *
 * @author 许涛
 */
public class Homework1 {
    private int [] a=new int[]{1,2,30,4,5};

    public int H_1 (int n){
        if (n==0)
            return 0;
        else if (n%2==0)
            return H_1(n/2);
        else {
            return 1+H_1(n/2);
        }
    }

    public void permute( String str ){
        char c[] = str.toCharArray();
        permute(c,0,str.length()-1);
    }

    public void permute(char [ ] str, int low, int high) {
        if (low == high) {
            for (int i = 0; i <= high; i++)
                System.out.print(str[i]);
            System.out.println();
        } else {
            for (int i = low; i <= high; i++) {
                // 从固定的数后第一个依次交换
                Swap(str, low, i);
                permute(str, low + 1, high);
                // 这组递归完成之后需要交换回来
                Swap(str, low, i);
            }
        }

    }
    public void Swap(char[] list, int i, int j) {
        char t = list[i];
        list[i] = list[j];
        list[j] = t;
    }

    public int H_3_1(int []arr,int beginIndex){
        if (arr.length-beginIndex>1){
            if (arr[beginIndex]>arr[beginIndex+1]){
                int temp = arr[beginIndex];
                arr[beginIndex]= arr[beginIndex+1];
                arr[beginIndex+1] = temp;
            }
            return H_3_1(arr,beginIndex+1);
        }
        else return arr[arr.length-1];
    }

    public double H_3_2(int []arr,int beginIndex) {
        if (beginIndex==arr.length-1){
            return arr[arr.length-1];
        }
        else{
            if (beginIndex!=0){
                return (arr[beginIndex]+H_3_2(arr,beginIndex+1));
            }else{
                //最后一次则要取平均数了
                return (arr[beginIndex]+H_3_2(arr,beginIndex+1)+0.00)/arr.length;
            }
        }
    }

    public int H_4(LinkedList linkedList){//?
        if (linkedList.size()==0) return 0;
        else {
            linkedList.pop();
            return 1+H_4(linkedList);
        }
    }

    public boolean H_5_1(String word){
        if (word.length()==1){
            return true;
        }
        else if (word.length()==2){
            if (word.charAt(0)==word.charAt(1)){
                return true;
            }
            else return false;
        }
        else {
            if (word.charAt(0)==word.charAt(word.length()-1)){
                return H_5_1(word.substring(1,word.length()-1));
            }
            else return false;
        }
    }

    public boolean H_5_2(String sentence) {
//        if(sentence.length()<=1){
//            return true;
//        }
//        else if (sentence.length()==2&&Character.isLetter(sentence.charAt(0))&&Character.isLetter(sentence.charAt(1))){
//            if (sentence.substring(0,1).equalsIgnoreCase(sentence.substring(sentence.length()-1)))
//                return true;
//            else return false;
//        }
//        else {
//            if (!Character.isLetter(sentence.charAt(0))){
//                return H_5_2(sentence.substring(1));
//            }
//            else if (!Character.isLetter(sentence.charAt(sentence.length()-1))){
//                return H_5_2(sentence.substring(0,sentence.length()-1));
//            }
//            else {
//                if (sentence.substring(0,1).equalsIgnoreCase(sentence.substring(sentence.length()-1))){
//                    return H_5_1(sentence.substring(1,sentence.length()-1));
//                }
//                else {
//                    return false;
//                }
//            }
//
//        }
        String s = "";
        for (int i=0;i<sentence.length();i++){
            if(Character.isLetter(sentence.charAt(i))){
                s+=sentence.charAt(i);
            }
        }
        return H_5_1(s);
    }


        public static void main(String[] args) {
        Homework1 h1 = new Homework1();
        System.out.println(h1.H_3_1(h1.a,0));
        System.out.println(h1.H_3_2(h1.a,0));
        System.out.println(h1.H_5_1("asdf g g fdsa"));
        System.out.println(h1.H_5_2(",md,m,"));
        System.out.println(h1.H_1(11));
        h1.permute("abcd");
        LinkedList l = new LinkedList();
        l.add(1);
        l.add(1);
        l.add(1);
            System.out.println(h1.H_4(l));
    }
}
