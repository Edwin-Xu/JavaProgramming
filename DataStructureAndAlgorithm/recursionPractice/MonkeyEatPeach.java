package DataStructureAndAlgorithm.recursionPractice;

/**
 * 小猴子第一天摘下若干桃子,当即吃掉一半,又多吃一个.第二天早上又将剩下的桃子吃
 * 一半,又多吃一个.以后每天早上吃前一天剩下的一半另一个.到第10天早上猴子想再吃时发
 * 现,只剩下一个桃子了.问第一天猴子共摘多少个桃子？
 */
public class MonkeyEatPeach {
    public MonkeyEatPeach(){
        System.out.println(countPeach(1));
    }

    private int countPeach(int day){
        if (day==10){
            return 1;
        }
        else {
            return (1+countPeach(day+1))*2;
        }
    }

    public static void main(String[] args) {
        new MonkeyEatPeach();
    }
}
