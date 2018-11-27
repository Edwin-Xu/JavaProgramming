package thread;

public class method1_ThreadExtends extends Thread{
    @Override
    public void run(){
        int i=0;

        while (i<100){
            try{
                Thread.sleep(1000);
                System.out.println("haha"+System.currentTimeMillis());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        method1_ThreadExtends mte = new method1_ThreadExtends();
        mte.start();
        }

}
