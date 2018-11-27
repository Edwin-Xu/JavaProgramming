package sort;

/**
 * 据书上的测试图来看，在数据量小于20的时候，插入排序具有最好的性能。
 * 当大于20时，快速排序具有最好的性能，归并(merge sort)和堆排序(heap sort)也望尘莫及，尽管复杂度都为nlog2(n)。
 */

import java.util.Arrays;

public class AllSort {
//    private int [] a = new int[]{2,3,1,4,2,4,542,34,2,3223,23,-1,3,23,23};
//    [-14,-13,-12,-11,-10,-9,-8,-7,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,18,19,20,21,22,23,24,25]
//    [-14,25,-13,24,23,22,21,20,19,-12,18,16,15,-11,-10,-9,14,13,-8,-7,-5,12,11,10,-4,-3,9,-2,8,7,6,-1,5,4,3,2,0,1]

    public int[] insertSort(int[] arr) {
        /**
         * 插入排序：
         *      算法思想：从第二个元素开始到最后一个元素，取出元素，依次与它前面的元素比较（从后往前），如果大于等于，插入，反之则将该位置的元素向后移动，继续与前一个比较直至插入
         *      时间复杂度：O(n^2)
         *      空间复杂度：O(1)
         *      稳定性：稳定
         */
        for (int i = 1; i < arr.length; i++) {     //从第二个元素开始，n-1次插入
            int temp = arr[i];       //每趟将arr[i]插入到前面的排序子序列中
            int j;//j初始化为当前元素的前一个
            for (j = i -1; j >= 0 && temp < arr[j]; j--) {
                arr[j+1] = arr[j ];//***###  //在到达第一个元素之前且当前元素（i）小于依次比较的元素，将前面较大的元素向后移动。
            }
            arr[j + 1] = temp;      //temp值到达插入位置
        }
        return arr;
    }

    public int[] bubbleSort(int[] arr) {
        /**
         * 冒泡排序：
         *      算法思路：每个元素后排序方向的每一个元素比较，大/小则交换，从而每次将最大往目标方向移动
         *      时间复杂度： O(n^2)
         *      空间复杂度： O(1)
         *      稳定性： 稳定
         */
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = 0;
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public int[] selectSort(int[] arr) {
        /**
         *选择排序法：
         *      算法思路：设定最大/小值，初始化为要排序的第一个元素，然后向一边与每个元素比较，找出最大/最小值，放到一边
         *      时间复杂度：O(n^2)
         *      空间复杂度：O(1)
         *      稳定性：-不稳定-
         */
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = min;
            arr[minIndex] = temp;

        }
        return arr;
    }

    public int[] quickSort(int a[], int left, int right) {
        /**
         * 快速排序  十分重要
         * 快速排序是冒泡排序的改进版，也是最好的一种内排序，在很多面试题中都会出现，也是作为程序员必须掌握的一种排序方法。
         * 当分区选取的基准元素为待排序元素中的"中值"，为最好的情况，时间复杂度为O(nlog2n)。
         * ???不太理解
         */
        int L= left;
        int R= right;
        if (L< R) {   //待排序的元素至少有两个的情况
            int temp = a[L];  //待排序的第一个元素作为基准元素
            while (L!= R) {   //从左右两边交替扫描，直到L= R
                while (R> L&& a[R] >= temp) R--;        //从右往左扫描，找到第一个比基准元素小的元素  （注意：包含等于）
                a[L] = a[R];  //找到这种元素a[R]后与a[L]交换
                while (L< R&& a[L] <= temp) L++;         //从左往右扫描，找到第一个比基准元素大的元素 （注意：包含等于）
                a[R] = a[L];  //找到这种元素a[L]后，与a[R]交换
            }
            a[R] = temp;    //基准元素归位
            quickSort(a, left, L- 1);  //对基准元素左边的元素进行递归排序
            quickSort(a, R+ 1, right);  //对基准元素右边的进行递归排序
        }
        return a;
    }


    public int[] binaryInsertSort(int[] a) {
        /**
         *二分插入排序：
         *      在插入排序的基础上改进而来，直接插入排序中，我们每次都要向前依次比较，二分插入法则在要插入的元素前通过二分折半搜索找到
         *      插入地点，从而大大减小时间复杂度，达到 O(n*log2(n))
         *
         */
        for (int i = 1; i < a.length; i++) {//第i个元素为要向前插入的元素
            //搜索部分
            int left = 0; //左边边界
            int right = i - 1; //右边边界
            int temp = a[i];
            while (left <= right) {             // 利用折半查找插入位置
                int mid = (left + right) / 2;   // 取中点
                if (a[mid] > temp)             // 插入值小于中点值，在前半部分
                    right = mid - 1;          // 向左缩小区间
                else //在后半部分
                    left = mid + 1;           // 向右缩小区间
            }
            //移动部分
            // left即为找到的要插入的位置，所以下边的循环将left-(i-1)位置的元素依次向后移动
            for (int j = i - 1; j >= left; j--) {
                a[j + 1] = a[j];
            }
            a[left] = temp;    // 将temp插入到left位置
        }
        return a;
    }

    public int[] shellSort(int[] a) { //三个循环，一个是增量，另外两个是插入排序
        /**
         * 希尔排序
         * 1959年Shell发明，第一个突破O(n2)的排序算法，是简单插入排序的改进版。它与插入排序的不
         * 同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
         * 其最坏时间复杂度依然为O(n2)，一些经过优化的增量序列如Hibbard经过复杂证明可使得最坏时间复杂度为O(n3/2)。
         * <p>
         * 思想：将数组分为几个区，然后在每个区内排序，然后将区数缩小，再次迭代
         */
        for (int gap = a.length / 2; gap > 0; gap /= 2) {//增量每次都/2, 并非是最优解
            //从增量那组开始进行插入排序，直至完毕
            for (int i = gap; i < a.length; i++) {
                int temp = a[i];
                int j;
                // j - gap 就是代表与它同组隔壁的元素
                for (j = i; j - gap >= 0 && a[j - gap] > temp; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = temp;
            }
        }
        return a;
    }

    public int[] mergeSort(int [] a){
        /**
         * 归并排序： 分治法：Divide And Conquer
         *  思路：将一个序列分为N个子序列，然后时子序列有序，然后合并子序列得到有序完整序列
         *  2-路归并：N= 2，分成两个子序列
         *
         *  性能：
         *  时间复杂度： O(nlog2(n))
         */
        return a;
    }
 
    public int heapSort(int[] a) {

        return 0;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 1, 4, 2, 4, 542, 34, 2, 3223, 23, -1, 3, 23, 23};
        int[] b = new int[10000];
        int[] b2 = new int[10000];
        int[] b3 = new int[10000];
        int[] b4 = new int[10000];
        int[] b5 = new int[10000];
        int[] b6 = new int[10000];
        for (int i = 0; i < 10000; i++) {
            b[i] = (int) (Math.random() * 10000);
            b2[i] = b[i];
            b3[i] = b[i];
            b4[i] = b[i];
            b5[i] = b[i];
            b6[i] = b[i];
        }


        AllSort allSort = new AllSort();

        long t1 = System.currentTimeMillis();
        System.out.println(Arrays.toString(allSort.bubbleSort(b)));
        System.out.println("Bubble Sort :" + (System.currentTimeMillis() - t1));

        long t2 = System.currentTimeMillis();
        System.out.println(Arrays.toString(allSort.selectSort(b2)));
        System.out.println("Select Sort :" + (System.currentTimeMillis() - t2));

        long t3 = System.currentTimeMillis();
        System.out.println(Arrays.toString(allSort.insertSort(b3)));
        System.out.println("Insert Sort :" + (System.currentTimeMillis() - t3));

        long t4 = System.currentTimeMillis();
        System.out.println(Arrays.toString(allSort.binaryInsertSort(b4)));
        System.out.println("BinaryInsert Sort :" + (System.currentTimeMillis() - t4));

        long t5 = System.currentTimeMillis();
        System.out.println(Arrays.toString(allSort.shellSort(b5)));
        System.out.println("Shell Sort :" + (System.currentTimeMillis() - t5));

        long t6 = System.currentTimeMillis();
        System.out.println(Arrays.toString(allSort.quickSort(b6, 0, b6.length - 1)));
        System.out.println("Quick Sort :" + (System.currentTimeMillis() - t6));

/**
 * 十万数量级：
 * Bubble Sort :16837
 * Select Sort :3079
 * Insert Sort :856
 */

    }
}
