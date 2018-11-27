package sort;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class GetArray{
    private ArrayList<Integer> arr = new ArrayList<>();

    public int [] getIntArray(){
        System.out.println("Input integer array, with non-int string to end it");
        try{
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextInt()){
                arr.add(scanner.nextInt());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        int [] arrInt = new int[arr.size()];
        for(int i=0;i<arr.size();i++){
            arrInt[i] = arr.get(i);
        }
        return arrInt;
    }



}



public class SelectSort {
    private GetArray getArray=new GetArray();

    public int[] resultArr() throws Exception{
        int []arr ;
        if((arr=getArray.getIntArray())!=null){

        }
        else {
            arr = new int[1];
        }
        int length = arr!=null?arr.length:0;


        //select sorting:
        /**
         * 每次选出最小或者是最大的，然后从一边开始交换。
         */
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
    public void showArr(int []arr){
        for (int i :arr){
            System.out.print(i+" ");
        }
    }

    public static void main(String[] args) {
        try{
            SelectSort ss = new SelectSort();
            ss.showArr(ss.resultArr());

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
