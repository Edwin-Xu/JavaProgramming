package GUI.note;

import javax.swing.*;
import java.awt.*;

/**
 * 记事本
 *
 * JMenuBar 菜单条  树干
 * JMenu 菜单组件   树枝
 * JMenuItem 菜单项组件 树叶
 *
 * JToolBar
 */
public class NotePad extends JFrame{
    private JMenuBar jmb = null;
    private JMenu menu1,menu2,menu3,menu4,menu5;
    private JMenuItem item1,item2,item3,item4,item5,item6,item7;
    private JMenu sub_menu1;//二级菜单
    private JMenuItem sub_menu1_item1,sub_menu1_item2;

    private JTextArea jta;

    //工具条
    private JToolBar jtb;
    private JButton jb1,jb2,jb3,jb4,jb5,jb6;

    public NotePad(){
        jmb = new JMenuBar();

        menu1 = new JMenu("文件(F)");
        menu2 = new JMenu("编辑(E)");
        menu3 = new JMenu("格式(O)");
        menu4 = new JMenu("查看(V)");
        menu5 = new JMenu("帮助(H)");

        jmb.add(menu1);
        jmb.add(menu2);
        jmb.add(menu3);
        jmb.add(menu4);
        jmb.add(menu5);

        item1 = new JMenuItem("新建(N)");
        item2 = new JMenuItem("打开(O)");
        item3 = new JMenuItem("保存（S）");
        item4 = new JMenuItem("另存为(A)");
        item5 = new JMenuItem("页面设置(U)");
        item6 = new JMenuItem("打印(P)");
        item7 = new JMenuItem("退出(X)");

        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);
        menu1.add(item4);
        menu1.addSeparator();//分割线
        menu1.add(item5);
        menu1.add(item6);
        menu1.add(item7);

        sub_menu1 = new JMenu("TXT");
        item1.add(sub_menu1);



        jta = new JTextArea();


        this.setJMenuBar(jmb);//必须设置这个，不然不能显示
//        this.add(jmb,BorderLayout.NORTH); 这个居然不用添加Bar，
        this.add(jta,BorderLayout.CENTER);


        this.setTitle("记事本");
        this.setSize(500,300);
        this.setLocation(0,0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);

    }

    public static void main(String[] args) {
        NotePad notePad = new NotePad();
    }



}
