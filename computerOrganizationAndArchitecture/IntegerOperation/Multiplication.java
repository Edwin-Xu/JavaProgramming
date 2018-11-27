package computerOrganizationAndArchitecture.IntegerOperation;

import java.util.Scanner;

/**
 * 整数乘法
 * Unsigned  and signed
 */
public class Multiplication {

    /**
     * Unsigned int multiplication
     * 乘数 X 被乘数  : multiplier  X  multiplicand
     * 1.涉及部分积，乘数每位对应一个部分积，0是0,1被乘数，部分积相加是积
     * 2.乘数的部分积要根据其所在位 依次向左移动1位，表示 X 2
     * 3.两个 n 位 的二进制数相乘 得 2n bits
     *???到底是这种快还是将其分解为加法快？？？
     */
    //普通无符号整数乘法，
    public static int unsignedMultiply(int multiplier, int multiplicand){
        int sum = 0;
        for (int i = 0 ; i<32 ;i++){
            /**
             * ((multiplier>>i)&1)==1?multiplicand<<i:0
             * 除数向右移动 i 位，& 1 得 1 or 0; 1则加上被除数向左移动 i 位后的值，否则加0；
             */
            sum = AdditionAndSubtraction.add(sum,((multiplier>>i)&1)==1?multiplicand<<i:0);
        }
        return sum;
    }

    /**
     * 布思算法：
     *  原理： 2^n + 2^(n-1)+...+2^(n-k) = 2^(n+1) - 2^(n-k)
     *  M X N 时，对N做分析，遇到 0-1 组做一次加法，遇到 1-0 做一次减法，其余的直接算术右移
     * @param multiplier
     * @param multiplicand
     * @return
     */
    public static long booth(int multiplier,int multiplicand){//32位下
        int A = 0;
        int Q_1 = 0;
        int Q = multiplier;
        int M = multiplicand;
//        long AQ = A<<32+Q;
        for (int i =0;i<32;i++){
            int QQ_1 = ((Q&1)<<1)+Q_1;

            switch (QQ_1){
                case 2: //1-0
                    A-=M;
                    Q_1 = Q&1;
                    Q = Q>>1 +(A&1)<<31;
                    A = A>>>1;
                    break;
                case 1:
                    A+=M;
                    Q_1 = Q&1;
                    Q = Q>>1 +(A&1)<<31;
                    A = A>>>1;
                    break;
                case 0:
                case 3:
                    Q_1 = Q&1;
                    Q = Q>>1 +(A&1)<<31;
                    A = A>>>1;
                    break;
            }
        }


        System.out.println(Q);
        System.out.println(A);


        return A<<32+Q;
    }








    public static void main(String[] args) {
//        Booth booth = new Booth();
//        long i = System.currentTimeMillis();
//        long j = System.nanoTime();
//        System.out.println(Multiplication.unsignedMultiply(20,30));
//        System.out.println(System.currentTimeMillis()-i);
//        System.out.println(System.nanoTime()-j);
//
//        System.out.println(Multiplication.booth(2,3));

//        System.out.println(System.currentTimeMillis());
//        System.out.println(System.nanoTime());
//
//        System.out.println((System.nanoTime()+0.000)/System.currentTimeMillis());

        //nanosecond）：纳秒
        /**
         * 1纳秒=1000 皮秒　
         *
         * 　　1纳秒 =0.001  微秒
         *
         * 　　1纳秒=0.000001 毫秒
         *
         * 　　1纳秒=0.00000 0001秒
         *
         * java的System.currentTimeMillis()和System.nanoTime()有什么区别
         *
         * java中System.nanoTime()返回的是纳秒，nanoTime而返回的可能是任意时间，甚至可能是负数……按照API的说明，nanoTime主要的用途是衡量一个时间段，比如说一段代码执行所 用的时间，获取数据库连接所用的时间，网络访问所用的时间等。另外，nanoTime提供了纳秒级别的精度，但实际上获得的值可能没有精确到纳秒。
         *
         * 但总的来说，这两个函数的用途是完全不一样的！。
         *
         *二者大约相差  1 3 倍
         *
         *
         */
    }
}
