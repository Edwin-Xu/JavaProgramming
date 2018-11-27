package computerOrganizationAndArchitecture.IntegerOperation;

/**
 * 整数加法减法----模拟ALU。
 * 上溢：相加：同号运算，结果变号，注意，无论是否有进位都可能出现上溢
 * <p>
 * 加法分析：
 * 我们像这样来拆分这个运算过程：13+9 =22
 * <p>
 * 1.不考虑进位，分别对各位数进行相加，结果为sum：
 * 个位数3加上9为2；十位数1加上0为1； 最终结果为12；
 * <p>
 * 2.只考虑进位，结果为carry：
 * 3 + 9 有进位，进位的值为10；
 * <p>
 * 3.如果步骤2所得进位结果carry不为0，对步骤1所得sum，步骤2所得carry重复步骤1、 2、3；如果carry为0则结束，最终结果为步骤1所得sum：
 * 这里即是对sum = 12 和carry = 10重复以上三个步骤，(a) 不考虑进位，分别对各位数进行相加:sum = 22; (b) 只考虑进位: 上一步没有进位，所以carry = 0； (c) 步骤2carry = 0，结束，结果为sum = 22.
 * <p>
 * 我们发现这三板斧行得通！
 * <p>
 * 那我们现在还使用上面的三板斧把十进制运算放在二进制中看看是不是也行的通。
 * <p>
 * 13的二进制为0000 1101，9的二进制为0000 1001:
 * <p>
 * 1.不考虑进位，分别对各位数进行相加：
 * sum = 0000 1101 + 0000 1001 = 0000 0100
 * <p>
 * 2.考虑进位：
 * 有两处进位，第0位和第3位，只考虑进位的结果为：
 * carry = 0001 0010
 * <p>
 * 3.步骤2carry == 0 ?，不为0，重复步骤1 、2 、3；为0则结束，结果为sum：
 * 本例中，
 * (a)不考虑进位sum = 0001 0110;
 * (b)只考虑进位carry = 0;
 * (c)carry == 0，结束，结果为sum = 0001 0110
 * 转换成十进制刚好是22.
 * <p>
 * <p>
 * 这就是计算架加法器的原理-----------------------------------------------------------------------------
 * 1.无进位---sum ----实际上是 异或：仅当1-0组合是才为1
 * 2.仅进位---curry---实际上为 和运算： 仅当都为1时左移一位才为1；
 * 3.curry==0?  N - loop;   Y - over, sum get.
 */


/**
 * 注意：
 * a.add(2147483647, 1) =  -2147483648；  上溢出了
 * 说明： n 位的数据 ，可以表示 -2^n--2^n-1之间的数，共2^n个，
 * 以非补码（单纯二进制递增来看）有对应关系：
 *    0    1   10   11   100   101 ...... 0111...111     1000...000    1000...001  ...... 1111...111
 *    0    1   2    3      3    4  ......  2^n-1           -2^n        -2^n+1              -1
 *                                         -------turn------->
 */
public class  AdditionAndSubtraction {

    //加法：1.递归实现：
    public static int add(int n1, int n2) {
//        int sum = n1^n2;
//        int curry = (n1&n2)<<1;
//        if (curry!=0) return add(sum,curry);
//        else return sum;

        //code improve:
        if (n2 == 0) return n1;
        int sum = n1 ^ n2;
        int curry = (n1 & n2) << 1;
        return add(sum, curry);
    }

    //加法：2.迭代实现：
    public static int add_iteration(int n1, int n2){
        int sum = n1^n2;
        int curry = (n1&n2)<<1;
        while (curry!=0){
            int temp = sum;
            sum = sum^curry;
            curry = (curry&temp)<<1;
        }
        return sum;
    }



    /**
     *  //减法： 转化为加法：
     * @param n1 减数  可以是负数
     * @param n2  被减数
     * @return
     *
     * 溢出：
     * a.subtract(-2147483648,1) = 2147483647
     */
    public static int subtract(int n1,int n2){
        return add(n1,~n2+1);
    }

    public static void main(String[] args) {
        System.out.println(AdditionAndSubtraction.add(2147483647, 1));
        System.out.println(AdditionAndSubtraction.add_iteration(90, 13));
        System.out.println(AdditionAndSubtraction.subtract(-2147483648,1));
    }
}
