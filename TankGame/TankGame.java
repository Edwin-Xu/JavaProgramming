

package TankGame;
import javax.swing.*;

public class TankGame extends JFrame {

    private MyPanel mypanel;
    private WH wh=new WH();
    public TankGame(){
        mypanel = new MyPanel();

        this.add(mypanel);

        Thread t = new Thread(mypanel);
        t.start();

        //Listener:
        this.addKeyListener(mypanel);

        this.setTitle("Tank Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(wh.W,wh.H);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }
}