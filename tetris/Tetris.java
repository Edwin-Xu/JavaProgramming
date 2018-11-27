package tetris;
/**
 * 俄罗斯方块
 * @author xutao
 * @date 2018.9.29,3--2018.9.30
 *
 * 难点：：
 *  1.边界问题：
 *          A：每个方块的不同形态都有不同的边界坐标，这些坐标应该都是在左右下三个边界之内的。
 *          给每一个形态都标记边界坐标值，让这些值始终在边界内
 *          Q：如何保证 在边界出变化时不出界？？？
 *
 *2.叠加问题：如何使方块一层一层叠加起来？？
 *          思路1：
 *              建立一个底线的数组，存储10个高度。
 *          思路2：
 *              用二维数组建立坐标系
 *
 *              先用1试一下吧。
 *
 *
 *
 *
 * 3.消除问题：
 *
 * 注意：添加一个诸如 JMenuBar 之类的时候，Frame的坐标要从 JMenuBar左上角算起，该组件也算是在JFrame中
 *
 *
 *
 *
 *
 * 其他方法总结：
 *      1.用4维数组，分别记录 类型、状态、x,y;
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tetris extends JFrame {
    private BufferedImage image = null;
    private GamePanel gp ;
    private ControlPanel cp ;

    private JMenuBar jmb;
    private JMenuItem jmi_pause;
    private JMenu jm_game;

    public Tetris(){
        /**
         * 加上这些后会让整体错位
         */
//        jmb = new JMenuBar();
//        this.setJMenuBar(jmb);
//        jmb.setBackground(Color.CYAN);
//        jm_game = new JMenu("游戏");
//        jmi_pause = new JMenuItem("暂停");
//        jm_game.add(jmi_pause);
//        jmb.add(jm_game);
//
//        int i = jmb.getHeight();
//        System.out.println(i);

        //添加图标
        try {
            image = ImageIO.read(this.getClass().getResource("./icon.jpg"));
            this.setIconImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        gp = new GamePanel();
        this.add(gp );
        cp = new ControlPanel(gp);
        this.add(cp );

        Thread t1 =new Thread(cp);
        t1.start();
        Thread t =new Thread(gp);
        t.start();

        this.setTitle("俄罗斯方块");
        this.setSize(550,840);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setBackground(Color.WHITE);
    }


    public static void main(String[] args) {
        new Tetris();
    }
}
