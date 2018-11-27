package Interface;

public class Interface_1 {
    public static void main(String[] args) {
        china c = new china();
        c.show();
        c.showChina();
        c.showGuizhou();
    }
}

interface people{
    public int num = 60;
    public void show();

}

interface Chinese extends people{
    int num_China = 14;
    public void showChina();

}

interface Guizhou{
    public void showGuizhou();
}

class china implements Chinese,Guizhou{
    @Override
    public void show() {
        System.out.println("World!");
    }

    @Override
    public void showChina() {
        System.out.println("China!");
    }

    @Override
    public void showGuizhou() {
        System.out.println("Guizhou!");
    }
}