package GUI.layout;

// Jpanel 的默认布局是 流式布局


import java.awt.*;
import javax.swing.*;
public class JPanel_ extends JFrame{
    private JPanel jp1,jp2;
    private JButton jb1,jb2,jb3,jb4,jb5;

    JPanel_(){
        jp1 = new JPanel();
        jp1.setSize(600,200);
        jp2 = new JPanel();

        jb1 = new JButton("A");
        jb2 = new JButton("B");
        jb3 = new JButton("老子最大");
        jb4 = new JButton("C");
        jb5 = new JButton("D");

        this.add(jp1,BorderLayout.NORTH);
        this.add(jp2,BorderLayout.SOUTH);
        this.add(jb3,BorderLayout.CENTER);

        jp1.add(jb1);
        jp1.add(jb2);

        jp2.add(jb4);
        jp2.add(jb5);

        this.setTitle("Complicated Layout ---JPanel");
        this.setSize(600,400);
        this.setLocation(550,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);


    }

    public static void main(String[] args) {
        JPanel_ jPanel_ = new JPanel_();
    }
}
