package GluttonySnake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ColorChooser extends JFrame implements ActionListener{
    private Container container;  //容器
    private JPanel colorPanel; //用于反映颜色变化的面板
    private MyPanel myPanel;
    private  int n ;
    //n=0: background
    //n=1: head
    //n=2: body
    //n=3: food

    public Color getColor() {
        return colorPanel.getBackground();
    }

    public ColorChooser(MyPanel myPanel,int n) {  //构造函数
        this.myPanel =myPanel;
        this.n=n;

        this.setTitle("调色板");//调用JFrame的构造函数
        container = getContentPane();  //得到容器
        colorPanel=new JPanel();  //初始化面板
        JButton selectColorButton = new JButton( "选取颜色" );  //初始化颜色选择按钮
//        selectColorButton.addActionListener(  //为颜色选择按钮增加事件处理
//                new ActionListener() {
//                    public void actionPerformed( ActionEvent event )
//                    {
//                        JColorChooser chooser=new JColorChooser();	//实例化颜色选择器
//                        Color color=chooser.showDialog(ColorChooser.this,"选取颜色",Color.lightGray );  //得到选择的颜色
//                        if (color==null)  //如果未选取
//                            color=Color.gray;  //则设置颜色为灰色
//                        colorPanel.setBackground(color);  //改变面板的背景色
//                    }
//                });


        selectColorButton.addActionListener(this);

        container.add(selectColorButton,BorderLayout.NORTH);  //增加组件
        container.add(colorPanel,BorderLayout.CENTER);  //增加组件

        setSize( 400, 200 );  //设置窗口尺寸
        setVisible(true);  //设置窗口可见
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );  //关闭窗口时隐藏程序
    }

    public static void main(String args[]) {
        ColorChooser cc  = new ColorChooser(new MyPanel(),0);
        while(true)
            System.out.println(cc.getColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JColorChooser chooser=new JColorChooser();	//实例化颜色选择器
        Color color=chooser.showDialog(ColorChooser.this,"选取颜色",Color.lightGray );  //得到选择的颜色
        if (color==null)  //如果未选取
            color=Color.black;  //则设置颜色为灰色
        colorPanel.setBackground(color);  //改变面板的背景色
        if (n==0)
            myPanel.backgroundColor = color;
        if (n==1)
            myPanel.headColor = color;
        if (n==2)
            myPanel.bodyColor = color;
        if (n==3)
            myPanel.foodColor = color;

    }
}
