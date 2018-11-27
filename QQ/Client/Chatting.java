package QQ.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class Chatting extends JDialog implements ActionListener {
    private JPanel jp = null;
    private JButton jb = null;
    private JTextField jtf = null;
    private JTextArea  jta = null;
    private JScrollPane jsp = null;

    private BufferedReader bf = null;
    private PrintWriter pw = null;
    private Socket s= null;
    private String jtaText ="";
    private Date date = new Date();

    public Chatting (String name){
        jp = new JPanel();
        jb= new JButton("发送");
        jta = new JTextArea();
        jtf = new JTextField(20);
        jsp = new JScrollPane(jta);

        jp.add(jtf);
        jp.add(jb);

        this.add(jsp,BorderLayout.CENTER);
        this.add(jp,BorderLayout.SOUTH);

        this.setTitle(name);
        this.setLocation(500,0);
        this.setSize(400,300);
        this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        this.setVisible(true);

        //通道
        //注册监听
        jb.addActionListener(this);
        try {
            s = new Socket("127.01.01.2",9999);// ********* Socket的参数是IP+端口号
            if (s.isConnected()) {
                bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
                pw = new PrintWriter(s.getOutputStream(), true);//**********************
            }
            while (s.isConnected()){
                //接收
                String string = bf.readLine();
                jta.setText(string);
                jtaText+=(date.toString()+"\n好友: "+string+"" + "\n");
                jta.setText(jtaText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (s.isConnected()) {
                    bf.close();
                    pw.close();
                    s.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jb&&s.isConnected()){
            String info = jtf.getText();
            pw.println(info);
            jtf.setText("");
            jtaText+=(date.toString()+"\n我: "+info+"\n");
            jta.setText(jtaText);
        }
    }

    public static void main(String[] args) {
        new chat("");
    }
}
