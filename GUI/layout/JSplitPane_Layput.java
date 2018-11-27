package GUI.layout;
/**
 *分割界面：JSplitPane
 *      JSplitPane = new JSplitPane (JSplitPane.H / 1 )
 *
 *      其他组件须要加入 jsp中
 *
 * 标志Label也可以是图片：
 *      JLabel jl = new JLabel(new ImageIcon("picture path"))
 *
 */

import javax.swing.*;

public class JSplitPane_Layput extends JFrame{
    private JList jl;
    private JSplitPane jsp;
    private String [] jListContent = {"洗马小学","杉树岭小学","林关小学","滨海民工子弟学校","流渡小学","流渡中学","建国中学","遵义四中","南京大学",};
    private JLabel jLabel;

    public JSplitPane_Layput() {
        jl = new JList(jListContent);
        jl.setName("xutao");

        jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        jsp.setOneTouchExpandable(true);

        ImageIcon ii = new ImageIcon("D:\\图片\\许涛.jpg");
        ii.setDescription("xutao");

        jLabel = new JLabel(ii);

        jsp.add(jl);
        jsp.add(jLabel);
        this.add(jsp);

        this.setTitle("My Schools");
        this.setSize(1000,800);
        this.setLocation(550,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);


    }

    public static void main(String[] args) {
        JSplitPane_Layput demo = new JSplitPane_Layput();
    }
}
