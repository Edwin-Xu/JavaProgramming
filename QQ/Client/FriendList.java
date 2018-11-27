package QQ.Client;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;

/**
 * JList 三种构造器：
 * 1.无参数
 * 2.数组参数
 * 3.向量参数
 */
public class FriendList extends JFrame implements ListSelectionListener{
    private JButton jb1, jb2;
    private JTabbedPane jtp1;
    private JPanel jp1,jp2,jp3;
    private ArrayList<String> names;
    private BufferedReader br =null;
    private JScrollPane jsp;
    private JList jList;
    private int Num =0;
    private chat[] chats;
    private Chatting[] chattings;
    private Vector<JLabel> Chat = new Vector<>();

    public FriendList(){
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jtp1 = new JTabbedPane();
        jtp1.add(jp1,"好友");
        jtp1.add(jp2,"群聊");
        jtp1.add(jp3,"空间");

        this.add(jtp1);

        names = new ArrayList<>();
        String ss ="";
        try {
            String name = "";
            br = new BufferedReader(new InputStreamReader(new FileInputStream("src\\QQ\\Client\\friends.txt")));
            while ((name = br.readLine())!=null){
                names.add(name);
                Num++;
                ss = ss +name+"                                                                     ,";
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }
        for (int i=0;i<Num;i++){
            JLabel jl = new JLabel(names.get(i));
            Chat.add(jl);
        }
//        jList = new JList(names.toArray());
        jList = new JList(Chat);
        jList.setSize(280,560);

        jList.setVisibleRowCount(28);
        jList.setFixedCellWidth(240);
        jsp = new JScrollPane(jList);
        jp1.add(jsp);
        jList.addListSelectionListener(this);//注册监听；

        chats = new chat[Num];
        chattings = new Chatting[Num];




        this.setSize(300,600);
        this.setTitle("XT QQ");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
//        this.setResizable(false);

    }


    public static void main(String[] args) {
        new FriendList();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        chats[e.getFirstIndex()]  = new chat(names.get(e.getFirstIndex()));
//        chattings[e.getFirstIndex()]  = new Chatting(names.get(e.getFirstIndex()));

    }
}
