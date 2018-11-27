package DataStructureAndAlgorithm.string.kmp;

/**
 * Created by XuTao on 2018/11/8 21:50
 * 概念：KMP是三位大牛：D.E.Knuth、J.H.Morris和V.R.Pratt同时发现的。其中第一位就是《计算机程序设计艺术》的作者！
 * KMP算法要解决的问题就是在字符串（也叫主串）中的模式（pattern）定位问题。说简单点就是我们平时常说的关键字搜索。
 * 模式串就是关键字（接下来称它为P），如果它在一个主串（接下来称为T）中出现，就返回它的具体位置，否则返回-1（常用手段）。
 */
public class KMP {
    private String mainStr,pattern;
    private int len_m,len_p;
    private char[] m,p;
    public KMP(String mainStr,String pattern){
        this.mainStr =mainStr;
        len_m = mainStr.length();
        this.pattern = pattern;
        len_p = pattern.length();
        m = mainStr.toCharArray();
        p = pattern.toCharArray();
    }

    /**
     * 普通算法：
     */
    public int regular(){
        for (int i =0;i<len_m - len_p;i++){
            for (int j = 0;j<len_p;j++){
                if (m[i+j]!=p[j]) break;
                else if (m[i+j]==p[j]&&j==len_p-1){
                    return i;
                }
            }
        }
        return -1;
    }

    public int kmp(){


        return -1;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP("12344567890","67");
        System.out.println(kmp.regular());
    }
}
