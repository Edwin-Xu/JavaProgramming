package DataStructureAndAlgorithm.recursionPractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 使用递归的方法将一个字符串反转
 *
 *
 */
public class reverseString {
    private String s ;
    public reverseString(){
        s = input(2);
        System.out.println(reverse(s.length()));
    }

    private String reverse(int len){
        if (len==0){
            return "";
        }
        else {
            return s.charAt(len-1)+reverse(len-1);
        }
    }

    public static void main(String[] args) {
        new reverseString();
    }


    public String input(int n){
        //普片使用的输入方法：
        int INT =0;
        String STRING = "";
        double DOU = 0;
        try {
            System.out.println("Input: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s =br.readLine();
            if (n==1){//整数
                INT = Integer.valueOf(s);
            }
            else if (n==2){
                //string
                STRING = s;
            }
            else if (n==3){
                //double
                DOU = Double.valueOf(s);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return STRING;
    }


}
