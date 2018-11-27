package computerOrganizationAndArchitecture.IntegerOperation;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 补码下的布思算法模拟实现
 * failed
 */
public class Booth{
    private int [] Q ;   //multiplier 寄存器;
    private int [] M ; //multiplicand 寄存器;
    private int Q_1 =0;
    private int [] A;
    private String binary1, binary2;   // 二进制的String形式
    private Scanner scanner =null;
    private int length ;

    public Booth(){
        scanner = new Scanner(System.in) ;
        //以补码形式输入，负数最高位为1，整数则为0
        binary1 = scanner.nextLine();
        binary2 = scanner.nextLine();
        scanner.close();

        int difference = binary1.length() - binary2.length();
        this.length =difference>0?binary1.length():binary2.length() ; //取最长的那个二进制，短二进制向他扩展
        Q = new int[length];
        M = new int[length];
        A = new int[length];// 初始化为 0

        if (difference>0) {
            int m =difference;
            for (int i = 0; i < length; i++) {

                //Q存较长的数
                Q[i] = binary1.charAt(i) == '1' ? 1 : 0;
                if (m>0){
                    M[i] = binary2.charAt(0)=='1'?1:0;
                    m--;
                }
                else{
                    M[i] = binary2.charAt(i-difference)=='1'?1:0;
                }

            }
        }
        else {
            int m =-difference;
            for (int i = 0; i < length; i++) {

                //Q存较长的数
                Q[i] = binary2.charAt(i) == '1' ? 1 : 0;
                if (m>0){
                    M[i] = binary1.charAt(0)=='1'?1:0;
                    m--;
                }
                else{
                    M[i] = binary1.charAt(i+difference)=='1'?1:0;
                }

            }
        }
//        System.out.println(Arrays.toString(M));
//        System.out.println(Arrays.toString(Q));
        booth();
        System.out.println(toString(A)+toString(Q));

    }

    private void booth(){
        for (int i =length;i>0;i--){
            if (Q[Q.length-1]==1&&Q_1==0){       //QQ_1 :10
                //A = A -M
                //这里太不好处理了，算了 妈的 不就是那个加法吗
                A = substract(A,M);
                moveRight();
            }
            else if (Q[Q.length-1]==0&&Q_1==1){
                A = add(A,M);
                moveRight();
            }
            else {
                moveRight();
            }
        }
    }

    private void moveRight(){
        Q_1 = Q[Q.length-1];
        for (int i =Q.length-1;i>0;i--){
            Q[i] = Q[i-1];
        }
        Q[0]=A[A.length-1];

        for (int i =A.length-1;i>0;i--){
            A[i] = A[i-1];
        }
        A[0]= A[1]==1?1:0;
    }
    //a+b
    private int [] add(int a[],int b[]){
        int [] sum = XOR(a,b);
        int [] curry = shiftAfterAnd(a,b);
        while (Arrays.toString(curry).contains("1")){
            int [] temp = sum;
            sum = XOR(sum,curry);
            curry = shiftAfterAnd(curry,temp);
        }
        return sum;

//        int sum = n1^n2;
//        int curry = (n1&n2)<<1;
//        while (curry!=0){
//            int temp = sum;
//            sum = sum^curry;
//            curry = (curry&temp)<<1;
//        }
//        return sum;
    }

    private int [] substract(int a[],int b[]){
        return add(a,reverseAndPlusOne(b));
    }

    //数组实现取反加一
    private int[] reverseAndPlusOne(int []a){
        for (int i =0; i<a.length;i++){
            a[i] = a[i]==1?0:1;
        }
        a[a.length-1] = a[a.length-1] +1;
        for (int i =a.length-1;i>0;i--){ //会溢出？？？  ：会，这里这是负数变正数，1000——》0111 +1 --1000 ，length 加一后不会溢出了
            if (a[i]==2){
                a[i]=1;
                a[i-1] = a[i-1]+1;
            }
        }

        return a;
    }
    private int[] shiftAfterAnd(int []a,int []b){
        for (int i =0; i<a.length;i++){
            a[i] = (a[i]==1&&b[i]==1)?1:0;
        }
        for (int i =0; i<a.length-1;i++){
            a[i] = a[i+1];
        }
        a[a.length-1] = 0;
        return a;
    }
    private int [] XOR(int []a,int []b){
        for (int i =0; i<a.length;i++){
            a[i] = a[i]==b[i]?0:1;
        }
        return a;
    }

    public String toString(int []a){
        String s ="";
        for (int  i=0;i<a.length;i++){
            s+=a[i];
        }
        return s;
    }

    public static void main(String[] args) {
//        new Booth();
//        System.out.println(Integer.valueOf("11",2));
        BOOTH b = new BOOTH();
        int n1 = 3;
        int n2 = 33;
        int result = b.multiply(n1, n2);
        System.out.println(result);
    }

}

class BOOTH{
    /**
     ** Java Program to Implement Booth Algorithm
     **/


    /** Class Booth **/
    /** Function to multiply **/
    public int multiply(int n1, int n2)
    {
        int[] m = binary(n1);
        int[] m1 = binary(-n1);
        int[] r = binary(n2);
        int[] A = new int[9];
        int[] S = new int[9];
        int[] P = new int[9];
        for (int i = 0; i < 4; i++)
        {
            A[i] = m[i];
            S[i] = m1[i];
            P[i + 4] = r[i];
        }
        display(A, 'A');
        display(S, 'S');
        display(P, 'P');
        System.out.println();

        for (int i = 0; i < 4; i++)
        {
            if (P[7] == 0 && P[8] == 0);
                // do nothing
            else if (P[7] == 1 && P[8] == 0)
                add(P, S);
            else if (P[7] == 0 && P[8] == 1)
                add(P, A);
            else if (P[7] == 1 && P[8] == 1);
            // do nothing

            rightShift(P);
            display(P, 'P');
        }
        return getDecimal(P);
    }
    /** Function to get Decimal equivalent of P **/
    public int getDecimal(int[] B)
    {
        int p = 0;
        int t = 1;
        for (int i = 7; i >= 0; i--, t *= 2)
            p += (B[i] * t);
        if (p > 64)
            p = -(256 - p);
        return p;
    }
    /** Function to right shift array **/
    public void rightShift(int[] A)
    {
        for (int i = 8; i >= 1; i--)
            A[i] = A[i - 1];
    }
    /** Function to add two binary arrays **/
    public void add(int[] A, int[] B)
    {
        int carry = 0;
        for (int i = 8; i >= 0; i--)
        {
            int temp = A[i] + B[i] + carry;
            A[i] = temp % 2;
            carry = temp / 2;
        }
    }
    /** Function to get binary of a number **/
    public int[] binary(int n)
    {
        int[] bin = new int[4];
        int ctr = 3;
        int num = n;
        /** for negative numbers 2 complment **/
        if (n < 0)
            num = 16 + n;
        while (num != 0)
        {
            bin[ctr--] = num % 2;
            num /= 2;
        }
        return bin;
    }
    /** Function to print array **/
    public void display(int[] P, char ch)
    {
        System.out.print("\n"+ ch +" : ");
        for (int i = 0; i < P.length; i++)
        {
            if (i == 4)
                System.out.print(" ");
            if (i == 8)
                System.out.print(" ");
            System.out.print(P[i]);
        }
    }
    /** Main function **/
//            public static void main (String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Booth Algorithm Test\n");
        /** Make an object of Booth class **/
        BOOTH b = new BOOTH();

        /** Accept two integers **/
        System.out.println("Enter two integer numbers\n");
        int n1 = scan.nextInt();
        int n2 = scan.nextInt();
        int result = b.multiply(n1, n2);
        System.out.println("\n\nResult : "+ n1 +" * "+ n2 +" = "+ result);
    }
}

/**
 * Others ,
 *
 *  ** Java Program to Implement Booth Algorithm
 *
 *
         *import java.util.Scanner;
         *
         *  Class Booth
         *

public class Booth
 *{
         *public static Scanner s=new Scanner(System.in);
        *    // /** Function to multiply *
        *public int multiply(int n1,int n2)
        *{
        *int[]m=binary(n1);
        *int[]m1=binary(-n1);
        *int[]r=binary(n2);
        *int[]A=new int[9];
        *int[]S=new int[9];
        *int[]P=new int[9];
        *for(int i=0;i< 4;i++)
        *{
        *A[i]=m[i];
        *S[i]=m1[i];
        *P[i+4]=r[i];
        *}
        *display(A,'A');
        *display(S,'S');
        *display(P,'P');
        *System.out.println();
        *
        *for(int i=0;i< 4;i++)
        *{
        *if(P[7]==0&&P[8]==0);
        *                 // do nothing
        *else if(P[7]==1&&P[8]==0)
        *add(P,S);
        *else if(P[7]==0&&P[8]==1)
        *add(P,A);
        *else if(P[7]==1&&P[8]==1);
        *                 // do nothing
        *
        *rightShift(P);
        *display(P,'P');
        *}
        *return getDecimal(P);
        *}
        *    // /** Function to get Decimal equivalent of P **
        *public int getDecimal(int[]B)
        *{
        *int p=0;
        *int t=1;
        *for(int i=7;i>=0;i--,t*=2)
        *p+=(B[i]*t);
        *if(p>64)
        *p=-(256-p);
        *return p;
        *}
        *     ///** Function to right shift array *
        *public void rightShift(int[]A)
        *{
        *for(int i=8;i>=1;i--)
        *A[i]=A[i-1];
        *}
        *    // /** Function to add two binary arrays *
        *public void add(int[]A,int[]B)
        *{
        *int carry=0;
        *for(int i=8;i>=0;i--)
        *{
        *int temp=A[i]+B[i]+carry;
        *A[i]=temp%2;
        *carry=temp/2;
        *}
        *}
        *   //  /** Function to get binary of a number
        *public int[]binary(int n)
        *{
        *int[]bin=new int[4];
        *int ctr=3;
        *int num=n;
        *      //   /** for negative numbers 2 complment **
        *if(n< 0)
        *num=16+n;
        *while(num!=0)
        *{
        *bin[ctr--]=num%2;
        *num/=2;
        *}
        *return bin;
        *}
        *    // /** Function to print array **
        *public void display(int[]P,char ch)
        *{
        *System.out.print("\n"+ch+" : ");
        *for(int i=0;i<P.length;i++)
        *{
        *if(i==4)
        *System.out.print(" ");
        *if(i==8)
        *System.out.print(" ");
        *System.out.print(P[i]);
        *}
        *}
        *     ///** Main function **
        *public static void main(String[]args)
        *{
        *Scanner scan=new Scanner(System.in);
        *System.out.println("Booth Algorithm Test\n");
        *       //  /** Make an object of Booth class **
        *Booth b=new Booth();
        *
        *        // /** Accept two integers **
        *System.out.println("Enter two integer numbers\n");
        *int n1=scan.nextInt();
        *int n2=scan.nextInt();
        *int result=b.multiply(n1,n2);
        *System.out.println("\n\nResult : "+n1+" * "+n2+" = "+result);
        *}
        *}
 */