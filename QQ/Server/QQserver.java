package QQ.Server;

import QQ.Client.QQLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class QQserver extends JFrame implements ActionListener {
    private ServerSocket ss =null;
    private Socket socket = null;

    private JButton jb_start ,jb_close;
    private MyPanel mp =null;
    private JLabel jl ;

    public QQserver(){
        jb_start = new JButton("启动");
        jb_start.addActionListener(this);
        jb_start.setSize(80,40);
        jb_close = new JButton("关闭");
        jb_close.addActionListener(this);
        jb_close.setSize(80,40);

        this.setLayout(null);
        jb_start.setLocation(100,80);
        jb_close.setLocation(200,80);
        this.add(jb_start);
        this.add(jb_close);

        jl = new JLabel("当前状态：关闭");

        mp  = new MyPanel();
        mp.setLocation(100,150);
        mp.setSize(150,100);
        this.add(mp);

        this.setSize(400,300);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("QQ服务器");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.BLUE);

        try {
            ss= new ServerSocket(9999);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        QQserver qQserver = new QQserver();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jb_start){
            try {
                System.out.println("I am server, I am waiting a client to link!");
                socket = ss.accept();
                System.out.println("已连接");
                mp.s = "开启";
                mp.repaint();
//                mp.updateUI();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if (e.getSource()==jb_close){
            try {
                ss.close();
                mp.s = "关闭";
                mp.repaint();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


}

class MyPanel extends JPanel {
    protected String s = "关闭";
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setFont(new Font("仿宋",Font.BOLD,18));
        g.drawString("当前状态："+s,15,40);
    }
}
