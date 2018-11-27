package GUI.layout;

//由于JFrame的默认布局是边界布局，所以必须用：this.setLayout(null) 不用任何布局
//窗体大小禁止改变大小：this.setResizable(false)

import javax.swing.*;
import java.awt.*;

public class FlowLayout_ extends JFrame {
    private JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8;

    FlowLayout_(){
        this.jb1 = new JButton("A");
//        jb1.setSize(20,30);
        this.jb2 = new JButton("B");
        this.jb3 = new JButton("C");
        this.jb4 = new JButton("D");
        this.jb5 = new JButton("E");
        this.jb6 = new JButton("F");
        this.jb7 = new JButton("G");
        this.jb8 = new JButton("H");

        //设置布局模式：this.setLayout(new FlowLayout())
        this.setLayout(new FlowLayout());//默认是居中
//        this.setLayout(null);  不使用任何布局

//        this.setLayout(new FlowLayout(FlowLayout.LEADING));


//        this.setLayout(new BorderLayout());//JFrame 默认是边界布局

        this.setResizable(false);

        this.add(jb1);
        this.add(jb2);
        this.add(jb3);
        this.add(jb4);
        this.add(jb5);
        this.add(jb6);
        this.add(jb7);
        this.add(jb8);

        this.setTitle("FlowLayout");
        this.setSize(300,200);
        this.setLocation(550,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        FlowLayout_ flowLayout_ =new FlowLayout_();
    }

}
