package GluttonySnake;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel implements Runnable{
    private WH wh =new WH();
    private int i=1;
    private JRadioButton jrb1,jrb2,jrb3;
    private  ButtonGroup bg ;
    private  JLabel jl;

    public StartPanel(){
        this.setBackground(Color.BLACK);

        jrb1 = new JRadioButton("大");
        jrb2 = new JRadioButton("中");
        jrb3 = new JRadioButton("小");
        bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        bg.add(jrb3);

        jl = new JLabel("请选择界面大小:");
        jl.setFont(new Font("仿宋",Font.BOLD,45));
        jl.setForeground(Color.BLUE);

        this.add(jl);
        this.add(jrb1,BorderLayout.WEST);
        this.add(jrb2,BorderLayout.CENTER);
        this.add(jrb3,BorderLayout.EAST);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setFont(new Font("仿宋",Font.BOLD,50));
        g.setColor(Color.BLUE);
        if (i%3==0)
            g.drawString("贪吃蛇",wh.W/2,wh.H/2);
        i++;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }

    public static void main(String[] args) {
        JFrame a = new JFrame();
        StartPanel s =new StartPanel();
        a.add(s);
        Thread t =new Thread(s);
        t.start();

        a.setSize(400,300);

        a.setVisible(true);
    }
}
