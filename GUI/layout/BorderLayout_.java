package GUI.layout;

/**
 * 边界布局：
 * 1.继承窗体
 * 2.创建组件
 * 3.实例化组件（构造函数）
 * 4.设置窗口属性（可视化）
 */

import javax.swing.*;
import java.awt.*;//注意：BorderLayout 类时属于java.awt类的，不是属于他的子类Swing

//边界布局是Jframe，Jdialog的默认布局

public class BorderLayout_ extends JFrame {
    private JButton jb1,jb2,jb3,jb4,jb5;

    BorderLayout_(){
        this.jb1 = new JButton("Center");
        this.jb2 = new JButton("North");
        this.jb3 = new JButton("South");
        this.jb4 = new JButton("East");
        this.jb5 = new JButton("West");

        this.add(jb1,BorderLayout.CENTER);//中部的会自动调整大小
        this.add(jb2,BorderLayout.NORTH);
        this.add(jb3,BorderLayout.SOUTH);
        this.add(jb4,BorderLayout.EAST);
        this.add(jb5,BorderLayout.WEST);
//        this.add(jb5,BorderLayout.AFTER_LINE_ENDS);

        this.setTitle("BorderLayout");
        this.setSize(600,400);
        this.setLocation(550,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }






    public static void main(String[] args) {
        BorderLayout_ bl = new BorderLayout_();
    }
}
