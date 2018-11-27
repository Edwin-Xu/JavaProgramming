package sort;

import java.util.Random;

public class Compare_Bubble_VS_Select {
    public int [] bubbleSort(int []arr){

        for (int i=0; i<arr.length-1; i++){
            int temp  = 0;

            for (int j = i+1; j < arr.length;j++){
                if (arr[i] > arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public int [] SelectSort(int []arr){
        int length = arr .length;
        int temp = 0;
        for ( int i=0; i < length - 1; i++ ){
        int min = arr[i];
        int minIndex = i;


        for (int j =i+1; j<length;j++){
            if (min > arr[j]){
                min = arr[j] ;
                minIndex = j;
            }
        }

        temp = arr[i];
        arr[i] = arr [minIndex];
        arr[minIndex] = temp;
        }
        return arr;
    }
    public void showArray(int []arr){
        for (int i: arr){
            System.out.print(i+" ");
        }
    }
    public static void main(String[] args) {
        int []arr = new int[10000];
        for (int i =0;i<10000;i++){
            arr[i] = (int)(Math.random()*10000);
        }
        Compare_Bubble_VS_Select bs = new Compare_Bubble_VS_Select();

        System.out.println(System.currentTimeMillis());
        bs.SelectSort(arr);
        System.out.println(System.currentTimeMillis());
        bs.bubbleSort(arr);
        System.out.println(System.currentTimeMillis());
    }
}
