import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.spi.DecimalFormatSymbolsProvider;
import java.util.Arrays;

public class Temp    {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int size = 0;
        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] != nums[size]){
                nums[++size] = nums[i];
            }
        }
        return size + 1;
    }



    public static void main(String[] args) {
        Temp temp = new Temp();
        int [] a = new int[]{1,1,2,3,3,4,5,5,5,5};
        System.out.println(temp.removeDuplicates(a));

    }

}

