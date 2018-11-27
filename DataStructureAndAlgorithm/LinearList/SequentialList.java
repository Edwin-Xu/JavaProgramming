package DataStructureAndAlgorithm.LinearList;

/**
 * ADT
 * Linear线性表
 * Sequential list 顺序表，依赖于物理内存实现
 * <p>
 * 顺序表性能分析：
 * 搜索：O(n)
 * 插入：O(N)
 * 删除：O(N)
 */
public class SequentialList {

    private int[] data = null;
    private int size = 0;
    private int last = -1;

    public SequentialList() {
        data = new int[size];
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int a) {
        int arr[] = new int[size + 1];
        for (int i = 0; i < size; i++) {
            arr[i] = data[i];
        }
        arr[size] = a;
        data = arr;
        size++;
    }

    public void add(int[] a) {
        int arr[] = new int[size + a.length];
        for (int i = 0; i < size + a.length; i++) {
            if (i >= size) {
                arr[i] = a[i - a.length];
            } else
                arr[i] = data[i];
        }
        data = arr;
        size += a.length;
    }

    public int getLast() {
        return data.length == 0 ? null : data[data.length - 1];
    }

    public int get(int index) {
        if (index >= 0 && index < size) {
            return data[index];
        } else return -1; //如果错误 怎么办。
    }

    public void remote(int a) {
        /**
         * 默认移除第一个。如果有多个
         */
        int arr[] = new int[size - 1];
        int middle = -1;
        for (int i = 0; i < size; i++) {
            if (data[i] != a) {
                arr[i] = data[i];
            } else {
                middle = i;
                break;
            }
        }
        for (int i = middle; i < size - 1; i++) {
            arr[i] = data[i + 1];
        }
        data = arr;
        size--;
    }

    public void remoteAll(int a) {
        int count = 0;
        for (int i : data) {
            if (i == a)
                count++;
        }
        int arr[] = new int[size - count];
        int a_count = 0;
        for (int i = 0; i < size - count; i++) {
            if (data[i] != a) {
                arr[i] = data[i + a_count];
            } else {
                a_count++;
                arr[i] = data[i + a_count];
            }
        }
        data = arr;
        size -= count;
    }

    public void printAll() {
        System.out.print("all of it:");
        for (int a : data) {
            System.out.print(a + "  ");
        }
        System.out.println();
    }

    public void sort() {
        int[] arr = new int[size];
        //选择排序法：
        for (int i = 0; i < size; i++) {
            int min = data[i];
            for (int j = i + 1; j < size; j++) {
                if (data[j] < min) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;

                }
            }
        }
    }

    public void sortFromMaxToMax() {
        int[] arr = new int[size];
        //选择排序法：
        for (int i = 0; i < size; i++) {
            int min = data[i];
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (data[j] < min) {
                    int temp = data[minIndex];
                    data[minIndex] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        SequentialList sl = new SequentialList();
        System.out.println(sl.size);
        sl.add(1);
        sl.add(2);
        sl.add(3);
        sl.add(2);
        System.out.println(sl.size);
        sl.add(1);
        System.out.println(sl.size);
        sl.remote(2);
        System.out.println(sl.size);
        sl.printAll();
        sl.remoteAll(1);
        sl.printAll();
        System.out.println("\n" + sl.size);
        sl.add(4);
        sl.add(-1);
        sl.printAll();
        sl.sort();
        sl.printAll();
    }

}
