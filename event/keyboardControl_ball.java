package event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyboardControl_ball extends JFrame {
    private myPanel mp;

    public keyboardControl_ball(){
        mp = new myPanel(10,10,100,100);
        this.add(mp);

        //监听：
        this.addKeyListener(mp);

//        this.setBackground(Color.WHITE);

        this.setTitle("BALL");
        this.setSize(400,300);
        this.setLocation(550,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        keyboardControl_ball k = new keyboardControl_ball();
    }
}



class myPanel extends JPanel implements KeyListener{
    private int x;
    private int y;
    private int width;
    private int height;

    public myPanel(int x,int y,int width,int height){
        this.height = height;
        this.x = x;
        this.y = y;
        this.width = width;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.RED);
        g.fillOval(x,y,width,height);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            y++;
        }
        else if(e.getKeyCode()==KeyEvent.VK_UP){
            y--;
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            x--;
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            x++;
        }
        //重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}