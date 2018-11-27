package InternetProgramming.QQChatting;
/**
 * 及其需要注意的地方：
 * 用PrintWriter发送时，要注意第二个参数，必须将刷新流设置为true，不然打死你你也收不到
 * 即：
 *       PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
 *      true: autoFlush,自动刷新流


    d对于追加，jta(JTextArea)有追加功能：append();

    尚未解决的问题：键盘监听JTextField实现回车发送？？？？？？？？？？？？？？？？

 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class MyServer extends JFrame implements ActionListener,KeyListener {
    private JPanel jp = null;
    private JButton jb = null;
    private JTextField jtf = null;
    private JTextArea  jta = null;
    private JScrollPane jsp = null;
    private BufferedReader bf = null;
    private PrintWriter pw = null;
    private ServerSocket ss =null;
    private String jtaText ="";
    private Date date = new Date();

    public MyServer (){
        //界面
        jp = new JPanel();
        jb= new JButton("发送");
        jta = new JTextArea();
        jtf = new JTextField(20);
        jsp = new JScrollPane(jta);

        jp.add(jtf);
        jp.add(jb);

        this.add(jsp,BorderLayout.CENTER);
        this.add(jp,BorderLayout.SOUTH);

        this.setTitle("服务器");
        this.setSize(400,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //注册监听
        jb.addActionListener(this);

        jtf.addKeyListener(this);

        //通道

        try {
            ss = new ServerSocket(9999);// ********* ServerSocket的参数是端口号
            System.out.println("监听中......");
            Socket s = ss.accept();//等待客服端连接，返回一个Socket；
            System.out.println("连接成功");

            bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw = new PrintWriter(s.getOutputStream(),true);

            while (true) {
                String inf = bf.readLine();
                jtaText+=(date.toString()+"\n服务器: "+inf+"\n");
                jta.setText(jtaText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bf.close();
                pw.close();
                ss.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==jb){
            String info = jtf.getText();
            pw.println(info);
            jtf.setText("");
            jtaText+=(date.toString()+"\n我: "+info+"\n");
            jta.setText(jtaText);
        }
    }

    public static void main(String[] args) {
        MyServer ms = new MyServer();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource()==this) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                System.out.println("yes");

                String info = jtf.getText();
                pw.println(info);
                jtf.setText("");
                jtaText += (date.toString() + "\n我: " + info + "\n");
                jta.setText(jtaText);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
