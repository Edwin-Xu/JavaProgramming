package DataStructureAndAlgorithm.recursionPractice;

/**
 * 一对刚出生的雌雄兔子，假定过两个月便可繁殖雌雄各一的一对小兔子。问过n个月后
 * 共有多少对兔子？
 */
public class PregnantRubbits {
    private final int n = 30;

    public PregnantRubbits(){
        System.out.println(countRubbits(n));
    }
    private int countRubbits(int n){
        if (n==1){
            return 1;
        }
        else {
            return 2*countRubbits(n-1);
        }
    }

    public static void main(String[] args) {
        new PregnantRubbits();
    }
}
