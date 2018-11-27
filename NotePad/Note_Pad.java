package NotePad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.RandomAccessFile;

public class Note_Pad extends JFrame implements ActionListener {
    private JTextArea jta;
    private JMenuBar jmb;
    private JMenu jm_file,jm_edit,jm_format,jm_cheak,jm_help,jm_creator;
    private JMenuItem jmi_1,jmi_2,jmi_3,creator_infor,help;
    private String filePath = null;
    private FileWriter fw = null;
    private RandomAccessFile raf =null;
    private BufferedReader br =null;

    public Note_Pad(){
        jta = new JTextArea();
        jmb= new JMenuBar();
        jm_file = new JMenu("文件(F)");
        jm_file.setMnemonic('F');
        jm_edit = new JMenu("编辑（E）");
        jm_edit.setMnemonic('E');
        jm_format = new JMenu("格式（L）");
        jm_format.setMnemonic('L');
        jm_cheak = new JMenu("查看（V）");
        jm_cheak.setMnemonic('V');
        jm_help = new JMenu("帮助（H）");
        jm_help.setMnemonic('H');
        jm_creator = new JMenu("开发者信息（I）");
        jm_creator.setMnemonic('I');
        creator_infor = new JMenuItem("开发人信息");
        help = new JMenuItem("帮助");


        jmi_1 = new JMenuItem("打开（O）");
//        jmi_1 = new JMenuItem("打开（O）",new ImageIcon(""));
        //设置快捷键：
        jmi_1.setMnemonic('O');
        jmi_2 = new JMenuItem("保存（S）");
        jmi_2.setMnemonic('S');
        jmi_3 = new JMenuItem("另存为（A）");
        jmi_3.setMnemonic('S');

        this.add(jta);
//        this.add(jmb);不能这样添加
        this.setJMenuBar(jmb);//设置菜单栏
        jmb.add(jm_file);
        jm_file.add(jmi_1);
        jm_file.addSeparator();
        jm_file.add(jmi_2);
        jm_file.add(jmi_3);

        jmb.add(jm_edit);
        jmb.add(jm_format);
        jmb.add(jm_cheak);
        jmb.add(jm_help);
        jmb.add(jm_creator);

        jm_creator.add(creator_infor);
        jm_help.add(help);

        //注册监听
        jmi_1.addActionListener(this);//添加
        jmi_1.setActionCommand("open");//设置命令
        jmi_2.addActionListener(this);//添加
        jmi_2.setActionCommand("save");//设置命令
        jmi_3.addActionListener(this);//添加
        jmi_3.setActionCommand("save as");//设置命令

        creator_infor.addActionListener(this);
        creator_infor.setActionCommand("creator");
        help.addActionListener(this);
        help.setActionCommand("help");


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,400);
        this.setTitle("XT记事本");
        this.setVisible(true);
    }







    public static void main(String[] args) {
        Note_Pad np = new Note_Pad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //open:
        if (e.getActionCommand().equals("open")) {
            //文件选择窗口
            JFileChooser jfc = new JFileChooser();
//            设置标题
            jfc.setDialogTitle("请选择文件");
//            显示，null表示默认
            jfc.showOpenDialog(null);

            //获得绝对路径
            filePath = jfc.getSelectedFile().getAbsolutePath();
            BufferedReader br=null;
            FileWriter fw = null;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
                String temp ="";
                String content ="";
                while ((temp = br.readLine())!=null){
                    content += (temp+"\r\n");
                }
                jta.setText(content);


            } catch (Exception e1) {
                e1.printStackTrace();
            }finally{
                //注意：是finally，不是final。
                try{
                    br.close();
                }catch (Exception e2){
                    e2.printStackTrace();
                }
            }
        }
        else if (e.getActionCommand().equals("save")&&filePath!=null){
            String textField = jta.getText();
            try {
                //注意，randomAccessFile 参数必须要 路径和模式
                 raf = new RandomAccessFile(filePath,"rw");
                 raf.seek(0);
                 raf.write(textField.getBytes());
                 raf.setLength(textField.getBytes().length);
            }catch (Exception e4){
                e4.printStackTrace();
            }finally {
                try {
                    raf.close();
                }catch (Exception e5){
                    e5.printStackTrace();
                }
            }
        }
        else if (e.getActionCommand().equals("save as")){
            JFileChooser jfc = new JFileChooser();
//            设置标题
            jfc.setDialogTitle("请选择文件");
//            显示，null表示默认
            jfc.showOpenDialog(null);

            String filepath = jfc.getSelectedFile().getAbsolutePath();
            String filename = filepath.substring(filepath.lastIndexOf("\\")+1);
        //这个地方没有考虑文件是否已经存在。
            String textField = jta.getText();
            try {
                //注意，randomAccessFile 参数必须要 路径和模式
                raf = new RandomAccessFile(filepath,"rw");
                raf.seek(0);
                raf.write(textField.getBytes());
                raf.setLength(textField.getBytes().length);
            }catch (Exception e4){
                e4.printStackTrace();
            }finally {
                try {
                    raf.close();
                }catch (Exception e5){
                    e5.printStackTrace();
                }
            }
        }else if (e.getActionCommand().equals("creator")){
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:\\JavaFiles\\iintellij_idea\\OOP\\src\\NotePad\\creator.txt"))));
                String temp = "";
                String text = "";
                while ((temp = br.readLine())!=null){
                    text += temp+"\r\n";
                }
                jta.setText(text);
            }catch (Exception e6){
                e6.printStackTrace();
            }finally {
                try{
                    br.close();
                }catch (Exception e7){
                    e7.printStackTrace();
                }
            }

        }else if (e.getActionCommand().equals("help")) {
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:\\JavaFiles\\iintellij_idea\\OOP\\src\\NotePad\\help.txt"))));
                String temp = "";
                String text = "";
                while ((temp = br.readLine()) != null) {
                    text += temp + "\r\n";
                }
                jta.setText(text);
            } catch (Exception e6) {
                e6.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
            }


        }
        }
}
