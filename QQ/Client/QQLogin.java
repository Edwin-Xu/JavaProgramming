package QQ.Client;
/**
 * 选项卡窗口：JTabbedPane
 *
 * 子窗体尽量不要用JFrame
 * 用JDialog
 * JDialog可以指定Onwer
 * 也有getOnwer()方法可以获得父窗体的引用
 * 一般来说，一个良好的桌面应用
 * 应该只启动一个Frame而不应该出现多个Frame
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class QQLogin extends JFrame implements ActionListener {

    private JLabel jlNorthPicture,jlQQNunber,jlPassword,forgetNum,help;
    private JCheckBox jcb1,jcb2;
    private JTabbedPane jtp = null;
    private JTextField jtf = null;
    private JPasswordField jpf;
    private JButton login,cancel,guide,clear;
    private JPanel jpCenter,jpSouth,jp1,jp2,jp3;

    private FriendList fl = null;
    private String QQNum ;
    private String QQPassword;

    public QQLogin(){
        jlNorthPicture = new JLabel(new ImageIcon("D:\\图片\\编程图片\\QQ\\QQ2.jpg"));

        login = new JButton("登录");
        cancel = new JButton("取消");
        guide = new JButton("注册");

        jpSouth = new JPanel();
        jpSouth.add(login);
        jpSouth.add(cancel);
        jpSouth.add(guide);

        jlQQNunber = new JLabel("QQ号码",JLabel.CENTER);
        jlPassword = new JLabel("QQ密码",JLabel.CENTER);
        jtf = new JTextField(15);
        jpf = new JPasswordField(15);
        clear = new JButton("清除号码");

        forgetNum =new JLabel("忘记密码",JLabel.CENTER);
        forgetNum.setFont(new Font("宋体",Font.PLAIN,16));
        forgetNum.setForeground(Color.BLUE);

        help = new JLabel("帮助",JLabel.CENTER);
        help.setFont(new Font("行楷",Font.BOLD,12));//粗体 bold
        help.setForeground(Color.blue);

        jcb1 = new JCheckBox("隐身登录");
//        jcb1.setBackground(Color.blue);


        jcb2 = new JCheckBox("记住号码");

        jpCenter = new JPanel();
        jpCenter.setLayout(new GridLayout(3,3,10,10));

        jpCenter.add(jlQQNunber);
        jpCenter.add(jtf);
        jpCenter.add(clear);
        jpCenter.add(jlPassword);
        jpCenter.add(jpf);
        jpCenter.add(forgetNum);
        jpCenter.add(jcb1);
        jpCenter.add(jcb2);
        jpCenter.add(help);

        this.add(jlNorthPicture,BorderLayout.NORTH);
        this.add(jpCenter,BorderLayout.CENTER);
        this.add(jpSouth,BorderLayout.SOUTH);

        login.addActionListener(this);

        this.setResizable(false);

        this.setTitle("腾讯QQ");
        this.setSize(300,200);
        this.setLocation(550,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color c  = new Color(20,200,200);
        this.setBackground(c);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        QQLogin qqLogin = new QQLogin();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==login){
            QQPassword = jpf.getText();
            QQNum = jtf.getText();

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src\\QQ\\Client\\QQ.txt")));
                String s = "";
                while ((s=br.readLine())!=null){
                    if (s.equals(QQNum+" "+QQPassword)){
                        fl = new FriendList();


                        this.setVisible(false);
                        break;
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
    }
}
