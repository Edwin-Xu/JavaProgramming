package DataStructureAndAlgorithm.homework.computerHomework2;

/**
 * 功能：构造树
 * 说明：
 * 1.主函数输入模式有两种，BT参数 true 图形界面，false  控制台输入
 * 2.构造树是按层次遍历结果输入的 如：ABCDE*F**GH
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BT extends JFrame implements ActionListener {
    private BufferedReader br=null;
    private MyPanel myPanel;
    private JTextField jtf;
    private JButton jb1, jb2;
    private JLabel jl;

    public BT(boolean isGUIMode) {
        if (isGUIMode) {
            this.setLayout(null); //自定义布局
            jtf = new JTextField("");
            jtf.setFont(new Font("宋体", Font.BOLD, 16));//Arial
            jtf.setColumns(40);
            jb1 = new JButton("确定");
            jb2 = new JButton("重置");
            jb1.setFont(new Font("宋体",Font.PLAIN,16));
            jb2.setFont(new Font("宋体",Font.PLAIN,16));//设置按钮的字体
            jl = new JLabel("输入");
            jl.setFont(new Font("华文行楷",Font.PLAIN,20));//设置标签的字体样式

            jl.setBounds(35, 20, 50, 50); //如果设置了绝对布局，那么要通过setBounds()来设置绝对位置与绝对大小
            jtf.setBounds(80, 30, 350, 30);
            jb1.setBounds(120, 100, 100, 25);
            jb2.setBounds(270, 100, 100, 25);
            this.add(jl);
            this.add(jtf);
            this.add(jb1);
            this.add(jb2);

            myPanel = new MyPanel();
            Thread t = new Thread(myPanel);
            t.start();//启动线程
            myPanel.setBounds(0, 150, 3000, 200);//如果没挡住了的话是不会调用paint方法的
            this.add(myPanel);
            this.setTitle("BuildTree");
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(500, 380);
            this.setLocation(500, 200);

            jb1.addActionListener(this);//注册监听
            jb2.addActionListener(this);

        } else {
            consoleInput();
        }
    }

    private void consoleInput() {
        br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input:");
        try {
            String input = br.readLine();
            while (!input.equals("q")) {
                char[] nodes = input.toCharArray();
                TreeNode treeNode = create(nodes, 0);
                System.out.println("前序：" + displayPreOrder(treeNode));
                System.out.println("中序：" + displayInOrder(treeNode));
                System.out.println("后序：" + displayPostOrder(treeNode));
                System.out.print("Input:");
                input = br.readLine();
            }
        } catch (IOException e) {
            try {
                br.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    private TreeNode create(char[] arr, int index) {
        if (index >= arr.length)       // 可以不需要，但是所有的值必须要写满，任一个#都要写，不然会越界
            return null;
        else if (String.valueOf(arr[index]).equals("#")||String.valueOf(arr[index]).equals("*")) {
            return null;
        } else {
            TreeNode node = new TreeNode(arr[index]);
            node.leftChild = create(arr, 2 * index + 1);
            node.rightChild = create(arr, 2 * index + 2);
            return node;
        }
    }

    private static String displayInOrder(TreeNode treeNode) {
        //中序
        if (treeNode != null) {
            return displayInOrder(treeNode.leftChild) + (treeNode.data == '*' ? "" : treeNode.data) +
                    displayInOrder(treeNode.rightChild);
        }
        return "";
    }

    private static String displayPreOrder(TreeNode treeNode) {
        //前序
        if (treeNode != null) {
            return (treeNode.data == '*' ? "" : treeNode.data) + displayPreOrder(treeNode.leftChild) + displayPreOrder(treeNode.rightChild);
        }
        return "";
    }

    private static String displayPostOrder(TreeNode treeNode) {
        //中序
        if (treeNode != null) {
            return displayPostOrder(treeNode.leftChild) + displayPostOrder(treeNode.rightChild) + (treeNode.data == '*' ? "" : treeNode.data);
        }
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            String s = jtf.getText();
            if (s.matches("[A-Za-z]([A-Za-z]|\\*|#)*")) {  //注意：是w就可以了，不要用/w,就代表字母下划线
                myPanel.display(s.toCharArray());
            } else {
                JOptionPane.showMessageDialog(null, "输入错误！\n请重试！","ERROR",JOptionPane.ERROR_MESSAGE);
                jtf.setText("");
            }
        }
        else if (e.getSource() == jb2) {
            jtf.setText("");
        }
    }

    public static void main(String[] args) {
        new BT(true);
    }

    class MyPanel extends JPanel implements Runnable {
        String[] strings;

        MyPanel() {
            strings = new String[]{"", "", ""};
//            this.setSize(100, 1000);//看来绝对布局里的setBounds()方法设置的大小具有更高优先级，可以覆盖这条设置
            this.setBackground(new Color(125, 134, 234));
        }

        public void display(char[] chars) {
            TreeNode treeNode = create(chars, 0);
            strings[0] = displayPreOrder(treeNode);
            strings[1] = displayInOrder(treeNode);
            strings[2] = displayPostOrder(treeNode);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);//
            g.setFont(new Font("宋体", Font.BOLD, 24));
            g.drawString("前序: " + strings[0], 10, 50);
            g.drawString("中序: " + strings[1], 10, 100);
            g.drawString("后序: " + strings[2], 10, 150);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(500);
                    repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class TreeNode {
        Character data;
        TreeNode leftChild;
        TreeNode rightChild;
        //含数据，二叉链表
        TreeNode(char data) {
            this.data = data;
            leftChild = null;
            rightChild = null;
        }
    }
}
