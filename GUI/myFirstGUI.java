package GUI;

import javax.swing.*;
import java.awt.*;

public class myFirstGUI {
    //放进类更好
    public static void main(String[] args) {
        //this is not swing, without "J" like "JFrame". it's awt gui, but swing is the son class.
//        Frame frame = new Frame();
//        frame.setTitle("xutao");
//        frame.setVisible(true);
//        frame.setSize(200,200);
//        Label label = new Label();


        //Swing :
        JFrame jFrame = new JFrame();

        //Button:
        JButton jb = new JButton ("haha");
        jb.setText("push me");
//        jb.setBackground(new Color(2,200,3));
        jb.setVisible(true);

        jFrame.add(jb);
        jFrame.setTitle("Xutao' Swing");
        jFrame.setSize(400,300);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocation(550,300);

//        jFrame.setBackground(new Color(45,200,100));//why ?
//        jFrame.setEnabled(false); 该界面卡死，不要用






    }

}
