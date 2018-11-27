package sort;

public class Bubble {

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
    public void showArray(int []arr){
        for (int i: arr){
            System.out.print(i+" ");
        }
    }

    public static void main(String[] args) {
        int []arr = {-1,2,3,1,4,5,3,56,-234,6553,0};
        Bubble b = new Bubble();
        b.showArray(b.bubbleSort(arr));
    }



}
