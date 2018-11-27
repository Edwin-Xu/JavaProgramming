package Graphics;

import javax.swing.*;
import java.awt.*;

public class drawACircle extends JFrame{
    private MyPanel myPanel;

    drawACircle(){
        myPanel = new MyPanel();


        this.add(myPanel);
        this.setTitle("DrawCircle");
//        this.setFont(new Font("宋体",Font.BOLD,16));
        this.setSize(300,200);
        this.setLocation(550,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        drawACircle d = new drawACircle();
    }
}


class MyPanel extends JPanel{
    @Override
    //??????? 这个方法为什么会自动调用？？？？？？？
    public void paint (Graphics g){
//        super.paint(g);
        g.drawOval(10,10,100,100);
        System.out.println("我被调用了！");

        g.draw3DRect(50,50,30,30,false);
    }
}