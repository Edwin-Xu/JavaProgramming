package Graphics;

import javax.swing.*;
import java.awt.*;

public class Demo extends JFrame {

    public static void main(String[] args) {
        JPanel jp = new JPanel();
        Graphics g = jp.getGraphics();
//        jp.print(g);

        g.drawOval(10,10,10,10);


        JFrame jf = new JFrame();
        jf.add(jp);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

    }
}
//class myPanel extends JPanel{
//
//    @Override
//    public void print(Graphics g){
//        super.paint(g);
//        g.draw3DRect();w
//    }
//
//}
