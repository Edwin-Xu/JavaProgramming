package GUI;


import GUI.layout.JPanel_;

import java.awt.*;
import javax.swing.*;

public class Label_ extends JFrame{
    private JPanel jp1,jp2,jp3;
    private JButton jb1,jb2;
    private JPasswordField jpf;
    private JLabel jl1,jl2;
    private JTextField jtf;

    Label_(){
        this.setBackground(new Color(1,1,1));//Why???

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        jb1 = new JButton("Log in");
        jb2 = new JButton("Cancel");

        jl1 = new JLabel("User         ");
        jl2 = new JLabel("Password");

        jpf = new JPasswordField(10);

        jtf = new JTextField(10);

        this.setLayout(new GridLayout(3,1));

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        jp1.add(jl1);
        jp1.add(jtf);

        jp2.add(jl2);
        jp2.add(jpf);

        jp3.add(jb1);
        jp3.add(jb2);

        this.setResizable(false);

        this.setTitle("登录系统");
        this.setSize(300,200);
        this.setLocation(550,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.setVisible(true);
    }




    public static void main(String[] args) {
        Label_ label_ = new Label_();
    }
}
