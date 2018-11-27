/**
 * 贪吃蛇游戏尝试开发
 * @author xutao
 * @time 2018.8.17---?
 */
package GluttonySnake;
/**
 * @author xutao
 * @name 贪吃蛇
 * @time 2018 .9
 *
 * 问题：
 * 1.startPanel问题，加入就会影响键盘监听
 * 2.数据共享、修改问题（speed修改不是对所有类）
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GluttonySnakeGame extends JFrame implements ActionListener {

    private MyPanel myPanel;
    private StartPanel startPanel;
    private WH wh = new WH();
    private JMenuBar jmb ;
    private JMenu jm_game,jm_settings,jm_help,jm_recoder, jm_info , jm_set_color,jm_framesize,jm_hardness;
    private  JMenuItem jmi_start,jmi_pause, jmi_continue, jmi_exit,
                        jmi_backgroundcolor,jmi_snakecolor,jmi_headcolor,jmi_foodcolor,jmi_size_l,jmi_size_m,jmi_size_s,jmi_easy,jmi_middle,jmi_hard,
                        jmi_best,jmi_worst,jmi_all,
                        jmi_help,
                        jmi_programmer,jmi_gameinfo;
    private JColorChooser jcc ;
    private JLabel jl;

    public  GluttonySnakeGame(){
        //加了不能出发键盘监听：
//        startPanel = new StartPanel();
//        this.add(startPanel);
////        this.remove(startPanel);
//         t =new Thread(startPanel);
//        t.start();

        jmb = new JMenuBar();
        this.setJMenuBar(jmb);
        jmb.setBorderPainted(true);
        jmb.setBackground(new Color(200,233,200));

        jm_game = new JMenu("游戏");
        jm_settings = new JMenu("设置");
        jm_help = new JMenu("帮助");
        jm_recoder = new JMenu("记录");
        jm_info = new JMenu("关于");
        jm_set_color = new JMenu("颜色");

        jmb.add(jm_game);
        jmb.add(jm_settings);
        jmb.add(jm_help);
        jmb.add(jm_recoder);
        jmb.add(jm_info);

        jmi_start= new JMenuItem("开始");
        jmi_pause =new JMenuItem("暂停");
        jmi_continue = new JMenuItem("继续");
        jmi_exit= new JMenuItem("退出");
        jm_game.add(jmi_start);
        jm_game.add(jmi_pause);
        jm_game.add(jmi_continue);
        jm_game.add(jmi_exit);

        jmi_backgroundcolor =new JMenuItem("背景颜色");
        jmi_snakecolor = new JMenuItem("蛇身颜色");
        jmi_headcolor = new JMenuItem("蛇头颜色");
        jmi_foodcolor = new JMenuItem("食物颜色");
        jm_set_color .add(jmi_backgroundcolor);
        jm_set_color .add(jmi_headcolor);
        jm_set_color .add(jmi_snakecolor);
        jm_set_color .add(jmi_foodcolor);

        jm_framesize = new JMenu("大小");
        jmi_size_l = new JMenuItem("大");
        jmi_size_m = new JMenuItem("中");
        jmi_size_s = new JMenuItem("小");
        jm_framesize.add(jmi_size_l);
        jm_framesize.add(jmi_size_m);
        jm_framesize.add(jmi_size_s);
        jm_settings.add(jm_set_color);
        jm_settings.add(jm_framesize);

        jm_hardness = new JMenu("难度");
        jmi_easy = new JMenuItem("简单");
        jmi_middle = new JMenuItem("一般");
        jmi_hard = new JMenuItem("困难");
        jm_hardness.add(jmi_easy);
        jm_hardness.add(jmi_middle);
        jm_hardness.add(jmi_hard);
        jm_settings.add(jm_hardness);

        jmi_help = new JMenuItem("游戏帮助");
        jm_help .add(jmi_help);

        jmi_best = new JMenuItem("最高分");
        jmi_worst = new JMenuItem("最低分");
        jmi_all = new JMenuItem("详细");
        jm_recoder.add(jmi_best);
        jm_recoder.add(jmi_worst);
        jm_recoder.add(jmi_all);

        jmi_programmer = new JMenuItem("开发者信息");
        jmi_gameinfo = new JMenuItem("游戏相关");
        jm_info .add(jmi_programmer);
        jm_info .add(jmi_gameinfo);

        //注册监听：
        jmi_start.addActionListener(this);
        jmi_pause.addActionListener(this);
        jmi_continue.addActionListener(this);
        jmi_exit.addActionListener(this);

        jmi_backgroundcolor.addActionListener(this);
        jmi_foodcolor.addActionListener(this);
        jmi_snakecolor.addActionListener(this);
        jmi_headcolor.addActionListener(this);

        jmi_easy.addActionListener(this);
        jmi_middle.addActionListener(this);
        jmi_hard.addActionListener(this);

        jmi_programmer.addActionListener(this);
        jmi_gameinfo.addActionListener(this);

        jmi_help.addActionListener(this);

        jl= new JLabel("            贪吃蛇");
//        jl.setLocation(wh.W/2-100,wh.H/2-100); 无效
        jl.setFont(new Font("仿宋",Font.BOLD,36));
        jl.setForeground(Color.BLUE);
        this.add(jl,BorderLayout.CENTER);

        this.setSize(wh.W,wh.H);
        this.setBackground(Color.green);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300,300);
        this.setTitle("贪吃蛇");
        this.setVisible(true);
        this.setResizable(false);

    }

    public static void main(String[] args) {
        GluttonySnakeGame gsg = new GluttonySnakeGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jmi_start){
//            this.remove(startPanel);
//            t.stop();
            this.remove(jl);

            myPanel = new MyPanel();
            this.add(myPanel);

            this.addKeyListener(myPanel);//!!!!这句话是必须的，如果没有的话，不会产生任何效果，主要是让JFrame来监听MyPanel,本来是他自己监听也可以的
            Thread t1 = new Thread(myPanel);
            t1.start();
        }
        else if (e.getSource() == jmi_exit){
            System.exit(0);
        }else if (e.getSource() == jmi_pause){
            for (SnakeParts sp : myPanel.snakePartsVector){
                sp.setSpeed(0);
            }
        }else if (e.getSource() == jmi_continue){
            for (SnakeParts sp : myPanel.snakePartsVector){
                sp.setSpeed(wh.MIN_SPEED);
            }
        }else if (e.getSource() == jmi_backgroundcolor){
            /**
             * 这个颜色选择器是一个难点,如果不把panel传进去，那么你得到的永远是那个调色板默认的颜色。
             * 把panel传进去，让调色板在触发的时候自己调用我的panel，吧它的颜色给设定好。妙妙妙！！！！1
             */
             new ColorChooser(myPanel,0);
        }else if (e.getSource()==jmi_headcolor){
            new ColorChooser(myPanel,1);
        }else if (e.getSource()==jmi_snakecolor){
            new ColorChooser(myPanel,2);
        }else if (e.getSource()==jmi_foodcolor){
            new ColorChooser(myPanel,3);
        }else if (e.getSource()==jmi_size_l){
            wh.H = 900;
            wh.W = 1200;
        }else if (e.getSource()==jmi_size_m){
            wh.H = 600;
            wh.W = 800;
        }else if (e.getSource()==jmi_size_s){
            wh.H = 300;
            wh.W = 400;
        }

        else if(e.getSource()==jmi_easy){
            myPanel.head.setSpeed(wh.MIN_SPEED);
        }else if(e.getSource()==jmi_middle){
            myPanel.head.setSpeed(wh.MIDDLE_SPEED);
        }else if(e.getSource()==jmi_hard){
            myPanel.head.setSpeed(wh.MAX_SPEED);
        }else if(e.getSource()==jmi_programmer){
            JOptionPane.showMessageDialog(null,"开发人：许涛\n年龄：21\n学校：南京大学\n专业：软件工程\n入学年份：2017\n出生日期：1997-08-03\n籍贯：贵州\n高中毕业学校：遵义四中\n婚姻状况：单身\n月薪：740\n梦想：创业+有钱","开发者信息",JOptionPane.NO_OPTION);
        }else if(e.getSource()==jmi_gameinfo){
            JOptionPane.showMessageDialog(null,"游戏名称：贪吃蛇\n开发日期：2018-9\n开发人：许涛\n版本：1.0","游戏信息",JOptionPane.NO_OPTION);
        }else if(e.getSource()==jmi_help){
            JOptionPane.showMessageDialog(null,"移动：\n   上：UP / w\n   下：DOWM / s\n   左：LEFT / a\n   右：RIGHT / r\n得分：\n   吃一个得一分\n","帮助",JOptionPane.NO_OPTION);
        }
    }
}
