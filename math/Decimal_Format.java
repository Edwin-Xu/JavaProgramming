package math;

import java.text.DecimalFormat;

public class Decimal_Format {

    public void df(){
        DecimalFormat df = new DecimalFormat("000.###");
        String s = df.format( 1.11111);
        System.out.println(s);
        //output:   001.111

        df.applyPattern("000.000");  //重新应用新的模式
        System.out.println(df.format(1.11111));
        //output: 001.111

        df.applyPattern("000E000");
        System.out.println(df.format(1111111));
        //output: 111E004  (1111111 约等于111 X 10^4)
        df.applyPattern("0E0");
        System.out.println(df.format(1000000000));
        //output: 1E9

        df.applyPattern("###,###,###.###");
        System.out.println(df.format(11111111.11));
        //output:11,111,111.11

        df.applyPattern("0.0%");
        System.out.println(df.format(0.1));
        //output: 10.0%

        df.applyPattern("\u00A50.00");  //可添加其他常规字符
        System.out.println(df.format(0.1));
        //output: sd.10

    }
}
