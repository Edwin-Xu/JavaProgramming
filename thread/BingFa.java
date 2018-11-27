package thread;

public class BingFa {
    public static void main(String[] args) {
        ticketSell ts = new ticketSell();

        Thread t = new Thread(ts);
        t.start();
        Thread t1 = new Thread(ts);
        t1.start();
        Thread t2 = new Thread(ts);
        t2.start();
        //会出现并发错误，因为多个线程共享一个数据，在共享是由于代码的读取必然过程会导致错误
    }
}


class ticketSell implements Runnable{
    private  int ticketNum = 100;

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            synchronized (this){
                if (ticketNum > 0) {
                    System.out.println(Thread.currentThread() + "is selling the" + ticketNum + "th ticket!");
                    ticketNum--;
                }
            }
        }
    }
}