package GUI.JTextArea;


import javax.swing.*;
import java.awt.*;

public class QQchatting extends JFrame{
    private JButton jb= null;
    private JTextField jtf;
    private JTextArea jta;
    private JPanel jp;
    private JScrollPane jsp1,jsp2;
    private JComboBox jcb;
    private String [] myQQFriends = {"习近平","李克强","马云","马化腾","刘强东","普京","王健林","王宝强","王岐山","特朗普","许"};

    public QQchatting(){
        jb = new JButton("发送");
        jtf = new JTextField(10);
        jta = new JTextArea("输入您将要发送的消息：");
        jp = new JPanel();
        jsp1 = new JScrollPane(jta);
        jcb = new JComboBox(myQQFriends);
        jsp2 = new JScrollPane(jcb);

        jp.add(jsp2);
        jp.add(jtf);
        jp.add(jb);
        jta.setRows(10);
//        jsp.add(jta);

        this.add(jp,BorderLayout.SOUTH);
        this.add(jsp1,BorderLayout.NORTH);

//        this.setIconImage("); why?
        this.setTitle("腾讯QQ");
        this.setSize(400,250);
        this.setLocation(550,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);

    }

    public static void main(String[] args) {
        QQchatting qq = new QQchatting();
    }
}
