package DataStructureAndAlgorithm.LinearList;

/**
 * 约瑟夫问题 数组解决方法
 *
 * 分析：
 * 有两种方法：一是每次都把一个数移除，二是不移除，仅取一个标记,这里取第二种
 *
 */
public class JosephusProblem_array {
    private int arr[];
    private int total;
    private int num;//记录要移除的元素

    public JosephusProblem_array(int total, int num) {
        this.total = total;
        this.num = num;
        arr = new int[total];
        for (int i = 0; i < total; i++) {
            arr[i] = i + 1;
        }
        if (total>num) {
            josephus();
        }
    }

    public void josephus(){
        int left = total;//剩余次数
        int cur = num-1;//当前下标
        arr[cur]=0;

        while (left > 2) {
            int count = 0;//记录跳过了几个
            for (int i = cur+1; i < total+1; i++) {
                if (i == total) { //从头开始遍历
                    i = 0;
                }
                if (arr[i] != 0) {
                    count++;
//                    cur++;
                }
                cur++;//不管什么情况都要加一
                if (cur == total) {
                    cur = 0;
                }
                if (count == num) {
                    break;
                }
            }
            arr[cur] = 0;
            left--;
        }
        for (int i : arr) {
            if (i!=0) {
                System.out.println("The lucky dog is "+i);
//                break;
            }
        }
    }
    public static void main(String[] args) {
        new JosephusProblem_array(8,3);
    }
}
