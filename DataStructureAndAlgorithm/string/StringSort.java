package DataStructureAndAlgorithm.string;

/**
 *   //发现这个字符串比较用得比较多，写一个通用函数：
 *     //功能：将字符串按ASCII 大小顺序 从小到大排序
 */
public class StringSort {


    public String stringSort(String s) {
        if (s.equals("") || s.length() == 1) return s; //空串、 单字母串
        else { // 多长度串
            //冒泡排序
            char[] c = s.toCharArray();
            for (int i = 0; i < s.length() - 1; i++) {
                for (int j = i + 1; j < s.length(); j++) {
                    if (c[i] > c[j]) {
                        char temp = c[i];
                        c[i] = c[j];
                        c[j] = temp;
                    }
                }
            }
            return String.valueOf(c);
        }
    }

    public static void main(String[] args) {
        StringSort am = new StringSort();
        System.out.println(am.stringSort("65433221"));
    }

}