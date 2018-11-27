package GUI.layout;

import javax.swing.*;
import java.awt.*;

public class GridLayout_ extends JFrame {
    private String[] button =new String[]{"清空","<-","%","/","7","8","9","X","4","5","6","-","1","2","3","+","0","+/-",".","="};
    private final int size = 20;
    private JButton [] jb ;

    GridLayout_(){
        this.jb = new JButton[size];

        for (int i=0; i<size ;i++){
            jb[i] = new JButton(button[i]);
        }

        this.setLayout(new GridLayout(5,4,50,30));

        for (int i =0 ; i<size; i++){
            this.add(jb[i]);
        }


        this.setTitle("GridLayout");
        this.setSize(600,400);
        this.setLocation(550,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }


    public static void main(String[] args) {
        GridLayout_ gridLayout_ = new GridLayout_();
    }
}
