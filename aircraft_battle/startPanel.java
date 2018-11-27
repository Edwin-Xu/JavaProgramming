package aircraft_battle;

import javax.swing.*;
import java.awt.*;

public class startPanel extends JPanel implements Runnable{
    private Constant C = new Constant();
    private int count =0;

    public startPanel(){
        this.setSize(C.W,C.H);
        this.setBackground(Color.GRAY);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setFont(new Font("仿宋", Font.BOLD, 40));
        if (count % 3 == 0) {
            g.drawString("Chapter 1", C.W / 2 - 100, C.H / 2-200);
        }
        if (count==Integer.MIN_VALUE-100){
            count=0;
        }
    }
    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(300);
            }catch (Exception e){
                e.printStackTrace();
            }
            count++;
            repaint();
        }

    }
}
