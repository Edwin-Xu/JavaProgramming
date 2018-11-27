package containerClass;

import java.util.HashMap;
import java.util.Iterator;

//若有相同的键，会覆盖掉前面的。
public class HashMap_ {
    public static void main(String[] args) {
        HashMap hm  = new HashMap();
        hm.put(1,111);
        hm.put(2,222);
        hm.put(3,333);

//        System.out.println(hm.get(2));

        //遍历——迭代
        Iterator iterator = hm.keySet().iterator();/////////////////////////
        while (iterator.hasNext()){
            int key =(int) iterator.next();
            System.out.println(hm.get(key));
        }
        hm.put(4,333);
        if (hm.containsKey(4)){
            System.out.println("yes");
        }
        if (hm.containsValue(333)){
            System.out.println("YYYES");
        }



    }
}
