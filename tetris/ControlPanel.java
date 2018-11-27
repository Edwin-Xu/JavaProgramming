package tetris;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel implements Runnable{
    private GamePanel gp;
    public ControlPanel(GamePanel gp){
        this.gp = gp;
        this.setBackground(new Color(80,150,156));
        this.setSize(150,800);
    }

    @Override
    public void paint(Graphics g){
        Font font = new Font("仿宋",Font.BOLD,30);
        g.setFont(font);
        g.setColor(Color.BLUE);
        g.drawString("Best",430,100);
        g.drawString("Score",430,200);
        g.drawString(String.valueOf(gp.Score),435,150);
        g.drawString("Next",430,400);

    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(500);
                this.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
