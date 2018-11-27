package DataStructureAndAlgorithm;

import java.util.ArrayList;
import java.util.LinkedHashSet;
/**
 *递归打印组合数
 */
public class CombinatorialNumber {

    private int [] num ;
    private ArrayList<String> strings ;

    public CombinatorialNumber(int n,int r){
        strings =new ArrayList<>();
        num = new int[n];
        for (int i =0 ;i < n; i ++){
            num[i] = i+1;
        }
        combinatorialNumber(r,n,num);
        LinkedHashSet<String> set = new LinkedHashSet<>(strings);
        ArrayList<String> al = new ArrayList<>(set);
        print(al);
    }
    private void combinatorialNumber(int r,int n,int num[]){
        if (n==r){
            String s = "";
            for (int i=0;i<r;i++){
                s = s + num[i];
            }
            strings.add(s);
        }else {
            int m=0;
            for (int i = 0; i < combineNum(n-1, n );i++){
                int array[] = subarr(num,m);
                m++;
                combinatorialNumber(r, n - 1, array);// recursion
            }
        }
    }
    public int[] subarr(int []a, int k){//得到 a 中不包含 第k个元素的子序列
        int [] res = new int[a.length-1];
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0;i<a.length;i++){
            if (i!=k){
                arrayList.add(a[i]);
            }
        }
        for (int i =0;i<a.length-1;i++){
            res[i] = arrayList.get(i);
        }
        return res;
    }

    private int combineNum(int r,int n){
        int fenzi = 1;
        int fenmu = 1;
        for (int i = n;i>=n-r+1;i--){
            fenzi*=i;
        }
        for (int i=1;i<=r;i++){
            fenmu*=i;
        }
        return fenzi/fenmu;
    }

    public void print(ArrayList<String> a){
        System.out.println("总共 "+a.size() +" 个组合：");
       for (String s: a){
           System.out.println(s);
       }
    }

    public static void main(String[] args) {
        new CombinatorialNumber(9,2);
    }
}
