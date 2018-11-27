package math;

/**
 * Created by XuTao on 2018/11/18 12:53
 *
 * java 没办法求除10以外为底的对数
 * 利用换底公式实现任意底的对数求值
 */
public class Logarithm {
    public int log(double value, int base){
        return  (int)(Math.log(value)/Math.log(base));
    }

    public static void main(String[] args) {
        Logarithm log = new Logarithm();
        System.out.println(log.log(1024,2));
        System.out.println(Math.log(2.7183));
        System.out.println(Math.log10(100));

    }
}
