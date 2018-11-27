package DataStructureAndAlgorithm;
import java.util.Scanner;
/**
 * @author xutao
 * 汉诺塔问题递归算法
 * 要求：大盘在下，一次一盘；
 * 分析：
 * 设有n个盘，塔有1，2，3；
 * 要将n个盘由1移动到3，则考虑先将第n个盘上的n-1给盘移动到中转盘2，在将第n盘
 * 移到3，最后将那n-1个盘移动到3；由此重复递归；
 */
public class TowerOfHanoi {
    static int m =0;//标记移动次数
    //实现移动的函数
    public static void move(int disks,char from,char to)
    {
        System.out.println("第" + (++m) +" 次移动 :  "+ disks+" 号盘  " + from +" ---->  " + to);
    }
    //递归实现汉诺塔的函数
    public static void hanoi(int n,char A,char B,char C)
    {
        if(n == 1)//圆盘只有一个时，只需将其从A塔移到C塔
            TowerOfHanoi.move(1, A, C);//将编b号为1的圆盘从A移到C
        else {
            hanoi(n - 1, A, C, B);//递归，把A塔上编号1~n-1的圆盘移到B上，以C为辅助塔
            TowerOfHanoi.move(n, A, C);//把A塔上编号为n的圆盘移到C上
            hanoi(n - 1, B, A, C);//递归，把B塔上编号1~n-1的圆盘移到C上，以A为辅助塔
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char A = 'A';
        char B = 'B';
        char C = 'C';
        System.out.print("圆盘个数：");
        int disks = input.nextInt();
        TowerOfHanoi.hanoi(disks, A, B, C);
        System.out.println("-->移动了" + m + "次");
        input.close();
    }
}