package event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class colorChange extends JFrame implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        arrayList.add(jp3.getBackground());
        if(!arrayList.get(arrayList.size()-1).equals(arrayList.get(arrayList.size()-2))){
            countOfColor++;
        }

        count ++;
        jl.setText("You have clicked for "+count + " time(s).");
        jl2.setText("Color has changed for "+countOfColor + " time(s).");
        switch (e.getActionCommand()){
            case "red":
                jp3.setBackground(Color.red);
                break;
            case "yellow":
                jp3.setBackground(Color.yellow);
        }
    }
    private ArrayList arrayList = new ArrayList();
    private JLabel jl,jl2;
    private int count=0;
    private int countOfColor = 0;
    private JPanel jp1,jp2,jp3;
    private JButton jb1,jb2;

    public colorChange (){
        arrayList.add(Color.blue);

        this.jb1 = new JButton("change to red");
        this.jb2 = new JButton("change to yellow");

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        jp1.add(jb1);
        jp2.add(jb2);

        jp3.setBackground(Color.blue);

        this.add(jp1,BorderLayout.NORTH);//set the location in border layout;
        this.add(jp2,BorderLayout.SOUTH);
        this.add(jp3,BorderLayout.CENTER);

        jl = new JLabel("You have clicked for "+count + " time(s).");
        jl.setFont(new Font("宋体",Font.BOLD,20));

        jl2 = new JLabel("Color has changed for "+countOfColor + " time(s).");
        jl2.setFont(new Font("宋体",Font.BOLD,20));

        jp3.add(jl,BorderLayout.NORTH);
        jp3.add(jl2,BorderLayout.SOUTH);

        jb1.addActionListener(this);// ？？？？ the actionListener is the class self;
        jb1.setActionCommand("red");  //设置行为命令

        jb2.addActionListener(this);
        jb2.setActionCommand("yellow");



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,300);
        this.setLocation(550,350);
        this.setVisible(true);

    }




    public static void main(String[] args) {
        colorChange cc = new colorChange();

    }
}

