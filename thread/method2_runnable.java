package thread;

public class method2_runnable implements Runnable {
    int i =0;
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("hi  "+i);
            i++;
            if (i==10){
                break;
            }
        }
    }


    public static void main(String[] args) {
        method2_runnable m2 = new method2_runnable();
        Thread t = new Thread(m2);
        t.start();
    }
}
