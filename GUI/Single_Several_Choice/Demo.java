/**
 * 单选框与复选框
 * 注意：
 *      单选框JRadioButton必须新建一个ButtonGroup类，然后加入其中，否则无法达到单选目的
 *      复选框JCheckBox
 */


package GUI.Single_Several_Choice;

import javax.swing.*;
import java.awt.*;

public class Demo extends JFrame{
    private JPanel jp1,jp2,jp3;
    private JLabel jl1,jl2;
    private JButton jb1,jb2;
    private JTextField jtf;
    private JCheckBox jcb1,jcb2,jcb3;
    private ButtonGroup bg;
    private JRadioButton jrb1,jrb2;

    Demo(){
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        jl1 = new JLabel("Favourite Sports");
        jl2 = new JLabel("Gender");

        jb1 = new JButton("Sign in");
        jb2 = new JButton("Cancel");

        jtf = new JTextField(10);


        jcb1 = new JCheckBox("Basketball");
        jcb2 = new JCheckBox("Football");
        jcb3 = new JCheckBox("Volleyball");

        jrb1 = new JRadioButton("Male");
        jrb2 = new JRadioButton("Female");

        bg = new ButtonGroup();

        bg.add(jrb1);
        bg.add(jrb2);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        jp1.add(jl1);
        jp1.add(jcb1);
        jp1.add(jcb2);
        jp1.add(jcb3);

        jp2.add(jl2);
        jp2.add(jrb1);
        jp2.add(jrb2);

        jp3.add(jb1);
        jp3.add(jb2);

        this.setLayout(new GridLayout(3,1));

        this.setResizable(false);


        this.setTitle("Sign System");
        this.setSize(400,200);
        this.setLocation(550,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.setVisible(true);



    }

    public static void main(String[] args) {
        Demo demo = new Demo();
    }

}
