package computerOrganizationAndArchitecture;

/**
 * 二进制
 * >>
 * <<
 * >>>
 * &
 * ^
 * |
 *~
 */
public class Binary {

    //计算整数n中1的个数
    public void CountOne(int n){
        int count =0;
        int i =32;
        while (i>0){
            if (((n>>i)&1)==1)
                count++;
            i--;
        }
        System.out.println(count);
    }

    //打印整数的Decimal，binary， hexadecimal，octal
    public static  void  displayBDOH(int n){
        System.out.println("Decimal(10): "+ n);
        System.out.println("Binary(2): "+ Integer.toBinaryString(n));
        System.out.println("Octal(8): "+ Integer.toOctalString(n));
        System.out.println("Hexadecimal(16): "+ Integer.toHexString(n));
        System.out.println();
        /**
         * Decimal(10): -1
         * Binary(2): 11111111111111111111111111111111
         * Octal(8): 37777777777
         * Hexadecimal(16): ffffffff
         * 可见，负数的octal， hexadecimal 是以补码为基础的。
         */
    }

    //打印原码 反码 补码；
    public static void displayAllCodes(int n){
        System.out.println("Original code : "+ Integer.toBinaryString(n));
        System.out.println("Inverse code : "+ Integer.toBinaryString(~n));
        System.out.println("Complement code : "+Integer.toBinaryString(~n+1) );
        System.out.println();
    }

    //进制转换：

    /**
     * @param n  数字
     * @param radix  进制
     * @param toRadix  要转化去的进制
     * Binary.conversion("-1111111111111111111111111111111",2,10);//居然二进制允许 负数,那么补码怎么办。。。
     */
    public static void conversion(String n, int radix, int toRadix){
       switch (radix){
           case 2:
               if (toRadix==8){
                   System.out.println(n+" From "+radix+" radix to "+toRadix+" radix is :"+Integer.toOctalString(Integer.valueOf(n,2)));
               }
               else if (toRadix==10){
                   System.out.println(n+" From "+radix+" radix to "+toRadix+" radix is :"+Integer.valueOf(n,2));
               }
               else if (toRadix==16){
                   System.out.println(n+" From "+radix+" radix to "+toRadix+" radix is :"+Integer.toHexString(Integer.valueOf(n,2)));
               }
               break;
           case 8:
               if (toRadix==2){
                   System.out.println(n+" From "+radix+" radix to "+toRadix+" radix is :"+Integer.toBinaryString(Integer.valueOf(n,8)));
               }
               else if (toRadix==10){
                   System.out.println(n+" From "+radix+" radix to "+toRadix+" is :"+Integer.valueOf(n,8));
               }
               else if (toRadix==16){
                   System.out.println(n+" From "+radix+" radix to "+toRadix+" radix is :"+Integer.toHexString(Integer.valueOf(n,8)));
               }
               break;
           case 10:
               if (toRadix==8){
                   System.out.println(n+" From "+radix+" radix to "+toRadix+" radix is :"+Integer.toOctalString(Integer.valueOf(n)));
               }
               else if (toRadix==2){
                   System.out.println(n+" From "+radix+" radix to "+toRadix+" radix is :"+Integer.toBinaryString(Integer.valueOf(n)));
               }
               else if (toRadix==16){
                   System.out.println(n+" From "+radix+" radix to "+toRadix+" radix is :"+Integer.toHexString(Integer.valueOf(n)));
               }
               break;
           case 16:
               if (toRadix==8){
                   System.out.println(n+" From "+radix+" radix to "+toRadix+" radix is :"+Integer.toOctalString(Integer.valueOf(n,16)));
               }
               else if (toRadix==10){
                   System.out.println(n+" From "+radix+" radix to "+toRadix+" radix is :"+Integer.valueOf(n,16));
               }
               else if (toRadix==2){
                   System.out.println(n+" From "+radix+" radix to "+toRadix+" radix is :"+Integer.toBinaryString(Integer.valueOf(n,16)));
               }
               break;
       }
    }

    public static void main(String[] args) {
        Binary binary = new Binary();
//        binary.CountOne(17);
//        Binary.displayBDOH(-1);
        Binary.displayAllCodes(-1);
        Binary.conversion("10000",2,10);//居然二进制允许 负数
    }

}
