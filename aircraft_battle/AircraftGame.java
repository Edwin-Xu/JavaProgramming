package aircraft_battle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AircraftGame extends JFrame implements ActionListener {
    private MyPanel myPanel ;
    private Constant CONST = new Constant();
    private startPanel sp = null;
    private JMenuBar jmb =null;
    private JMenu jm_game,jm_info,jm_help,jm_settings,jm_heroColor,
            jm_enemyColor,jmi_bg,jm_record;
    private JMenuItem jmi_start,jmi_info,jmi_help,jmi_color_red,jmi_color_yellow,jmi_color_green,
            jmi_color_red_2,jmi_color_yellow_2,jmi_color_green_2,jmi_restart,jmi_bg_black,
            jmi_bg_pink,jmi_bg_green,jmi_exit,jmi_highestScore,jmi_pause,jmi_continue;
    private Thread mypanel = null;
    private JLabel jl;



    public AircraftGame(){
        myPanel = new MyPanel();

        jmb = new JMenuBar();
        this.setJMenuBar(jmb);
//        jmb.setBackground(Color.GRAY);
        //开始面板
        sp = new startPanel();
        this.add(sp);
        Thread t = new Thread(sp);
        t.start();

        //添加菜单栏：
        jm_game =new JMenu("游戏");
        jm_info = new JMenu("开发者信息");
        jm_help =new JMenu("帮助");
        jm_settings= new JMenu("设置");
        jm_record = new JMenu("记录");

        jmi_start =new JMenuItem("开始");
        jmi_restart =new JMenuItem("重新开始");
        jmi_info = new JMenuItem("信息");
        jmi_help = new JMenuItem("帮助");
        jmi_exit = new JMenuItem("退出");
        jmi_highestScore = new JMenuItem("最高分");
        jmi_pause = new JMenuItem("暂停");
        jmi_continue = new JMenuItem("继续");

        jm_record.add(jmi_highestScore);

        jm_heroColor = new JMenu("飞机颜色");
        jm_enemyColor = new JMenu("敌人颜色");

        jmi_color_red = new JMenuItem("红色");
        jmi_color_yellow= new JMenuItem("黄色");
        jmi_color_green= new JMenuItem("绿色");

        jmi_color_red_2 = new JMenuItem("红色");
        jmi_color_yellow_2= new JMenuItem("黄色");
        jmi_color_green_2= new JMenuItem("绿色");

        jmi_bg_black = new JMenuItem("黑色");
        jmi_bg_pink= new JMenuItem("粉红");
        jmi_bg_green= new JMenuItem("绿色");

        jmi_bg = new JMenu("背景色");
        jmi_bg.add(jmi_bg_black);
        jmi_bg.add(jmi_bg_green);
        jmi_bg.add(jmi_bg_pink);

        jmb.add(jm_game);
        jmb.add(jm_settings);
        jmb.add(jm_record);
        jmb.add(jm_help);
        jmb.add(jm_info);

        jm_game.add(jmi_start);
        jm_game.add(jmi_pause);
        jm_game.add(jmi_continue);
        jm_game.add(jmi_restart);
        jm_game.add(jmi_exit);

        jm_help.add(jmi_help);
        jm_info.add(jmi_info);

        jm_settings.add(jm_heroColor);
        jm_settings.add(jm_enemyColor);
        jm_settings.add(jmi_bg);

        jm_heroColor.add(jmi_color_red);
        jm_heroColor.add(jmi_color_yellow);
        jm_heroColor.add(jmi_color_green);

        jm_enemyColor.add(jmi_color_red_2);
        jm_enemyColor.add(jmi_color_yellow_2);
        jm_enemyColor.add(jmi_color_green_2);


//        注册监听
        jmi_start.addActionListener(this);
        jmi_start.setActionCommand("start");

        jmi_restart.addActionListener(this);
        jmi_restart.setActionCommand("restart");

        jmi_color_red.addActionListener(this);
        jmi_color_red.setActionCommand("hero red");

        jmi_color_green.addActionListener(this);
        jmi_color_green.setActionCommand("hero green");

        jmi_color_yellow.addActionListener(this);
        jmi_color_yellow.setActionCommand("hero yellow");

        jmi_color_red_2.addActionListener(this);
        jmi_color_red_2.setActionCommand("enemy red");

        jmi_color_green_2.addActionListener(this);
        jmi_color_green_2.setActionCommand("enemy green");

        jmi_color_yellow_2.addActionListener(this);
        jmi_color_yellow_2.setActionCommand("enemy yellow");

        jmi_bg_black.addActionListener(this);
        jmi_bg_black.setActionCommand("bg black");

        jmi_bg_pink.addActionListener(this);
        jmi_bg_pink.setActionCommand("bg pink");

        jmi_bg_green.addActionListener(this);
        jmi_bg_green.setActionCommand("bg green");

        jmi_exit.addActionListener(this);
        jmi_exit.setActionCommand("exit");

        jmi_pause.addActionListener(this);
        jmi_pause.setActionCommand("pause");

        jmi_continue.addActionListener(this);
        jmi_continue.setActionCommand("continue");


        this.addKeyListener(myPanel);
        this.addKeyListener(myPanel.hero);

        //设置背景，失败
//        ImageIcon bg = new ImageIcon("BG.jpg");
//        JLabel jl = new JLabel(bg);
//        jl.setBounds(0,0,CONST.W,CONST.H);
//        JPanel imagePanel = (JPanel)this.getContentPane();
//        imagePanel.setOpaque(false);
//        this.getLayeredPane().add(jl,new Integer(Integer.MIN_VALUE));




//        this.setResizable(false);
        this.setTitle("Aircraft Game");
        this.setVisible(true);
        this.setSize(CONST.W,CONST.H);
        this.setLocation(0,0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        AircraftGame aircraftGame = new AircraftGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("start")){
            this.remove(sp);
            this.add(myPanel);
            mypanel = new Thread(myPanel);
            mypanel.start();
        }
//        else if (e.getActionCommand().equals("restart")){  //failed
//            if(this.getContentPane()==sp){
//                this.remove(sp);
//            }
////            if(this.getContentPane()==myPanel) {
//                this.remove(myPanel);
//                mypanel=null;
//                myPanel.isThreadrunning = false;
////            }
//            this.add(myPanel);
//            myPanel.isThreadrunning = true;
//            mypanel = new Thread(myPanel);
//            mypanel.start();
//        }
        else if (e.getActionCommand().equals("hero red")){
            myPanel.heroColor = new Color(255,0,0);
        }else if (e.getActionCommand().equals("hero green")){
            myPanel.heroColor = new Color(0,136,0);
        }else if (e.getActionCommand().equals("hero yellow")){
            myPanel.heroColor = new Color(255,255,0);
        }else if (e.getActionCommand().equals("enemy red")){
            myPanel.enemyColor = new Color(255,0,0);
        }else if (e.getActionCommand().equals("enemy green")){
            myPanel.enemyColor = new Color(0,136,0);
        }else if (e.getActionCommand().equals("enemy yellow")){
            myPanel.enemyColor = new Color(255,255,0);
        }else if (e.getActionCommand().equals("bg green")){
            myPanel.bgColor = new Color(187,255,153);
        }else if (e.getActionCommand().equals("bg pink")){
            myPanel.bgColor = new Color(255,136,136);
        }else if (e.getActionCommand().equals("bg black")){
            myPanel.bgColor = new Color(0,0,0);
        }else if (e.getActionCommand().equals("exit")){
            System.exit(0);
        }else if (e.getActionCommand().equals("pause")){
            myPanel.hero.setHERO_SPEED(0);
            for (Enemy enemy: myPanel.enemies) {
                enemy.setENEMY_SPEED(0);
                enemy.isContinue =false;
                myPanel.isContinue =false;
            }
            for (Enemy enemy: myPanel.enemies) {
                for (Bullet bullet :enemy.bullets){
                    bullet.setBULLET_SPEED(0);
                }
            }

        }else if (e.getActionCommand().equals("continue")){
            myPanel.hero.setHERO_SPEED(CONST.HERO_SPEED);
            for (Enemy enemy: myPanel.enemies) {
                enemy.setENEMY_SPEED(CONST.ENEMY_SPEED);
                enemy.isContinue =true;
                myPanel.isContinue =true;
            }
            for (Enemy enemy: myPanel.enemies) {
                for (Bullet bullet :enemy.bullets){
                    bullet.setBULLET_SPEED(CONST.BULLET_SPEED);
                }
            }
        }
    }
}
