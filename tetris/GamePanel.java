package tetris;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * 宽度400，10块，每块40.
 * 高度800,20块
 *
 * problems:
 *
 *
 */
public class GamePanel extends JPanel implements Runnable {
    private Vector<Block> blocks;
    private char[] types = new char[]{'O','S','I','Z','L','J','T'};
    protected int Score = 0;
    private int [] height =null;//用于存储积累高度；
    private int [][] xy = new int[10][20];//0可用1不可用

    public GamePanel(){
        blocks = new Vector<>();
        height= new int[10];
        Block b = new Block('O',height);
        blocks.add(b);
        Thread t = new Thread(b);
        t.start();

        this.setFocusable(true); //注意： 要设置屏幕焦点，不加这句话的是不能监听普通类的。
        this.addKeyListener(blocks.get(blocks.size()-1));

        this.setBackground(Color.BLACK);
        this.setSize(400,800);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        draw(g);

        if (!blocks.get(blocks.size()-1).isAlive){ //前一个死掉了，才添加下一个
            this.removeKeyListener(blocks.get(blocks.size()-1)); //移除监听

            Block b = new Block(types[(int)(Math.random()*7)],height);
            blocks.add(b);
            this.addKeyListener(blocks.get(blocks.size()-1));
            //启动线程
            Thread t = new Thread(blocks.get(blocks.size()-1));
            t.start();

        }
    }

    private void draw(Graphics g){
        for (Block b:blocks) {
            if (b.type == 'S') {
                g.setColor(Color.RED);
                switch (b.direction) {
                    case 1:
                    case 3:
                        g.fill3DRect(b.getX() - 40, b.getY() - 40, 40, 40, false);
                        g.fill3DRect(b.getX() - 40, b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY() + 40, 40, 40, false);
                        break;
                    case 0:
                    case 2:
                        g.fill3DRect(b.getX(), b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX() + 40, b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY() + 40, 40, 40, false);
                        g.fill3DRect(b.getX() - 40, b.getY() + 40, 40, 40, false);
                        break;

                }
            }
            else if (b.type == 'O') {
                g.setColor(Color.YELLOW);
                g.fill3DRect(b.getX(), b.getY(), 40, 40, false);
                g.fill3DRect(b.getX() + 40, b.getY(), 40, 40, false);
                g.fill3DRect(b.getX(), b.getY() + 40, 40, 40, false);
                g.fill3DRect(b.getX() + 40, b.getY() + 40, 40, 40, false);
            }
            else if (b.type == 'I') {
                g.setColor(Color.GREEN);
                switch (b.direction) {
                    case 0:
                    case 2:
                        g.fill3DRect(b.getX(), b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY() + 40, 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY() + 80, 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY() + 120, 40, 40, false);
                        break;
                    case 1:
                    case 3:
                        g.fill3DRect(b.getX() - 40, b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX() + 40, b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX() + 80, b.getY(), 40, 40, false);
                        break;
                }
            }
            else if (b.type == 'Z') {
                g.setColor(Color.BLUE);
                switch (b.direction) {
                    case 0:
                    case 2:
                        g.fill3DRect(b.getX(), b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX()-40, b.getY() , 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY() + 40, 40, 40, false);
                        g.fill3DRect(b.getX()+40, b.getY() + 40, 40, 40, false);
                        break;
                    case 1:
                    case 3:
                        g.fill3DRect(b.getX() , b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY()-40, 40, 40, false);
                        g.fill3DRect(b.getX() -40, b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX() - 40, b.getY()+40, 40, 40, false);
                        break;
                }
            }
            else if (b.type == 'L') {
                g.setColor(Color.CYAN);
                switch (b.direction) {
                    case 0:
                        g.fill3DRect(b.getX(), b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY()+40 , 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY() + 80, 40, 40, false);
                        g.fill3DRect(b.getX()+40, b.getY() +80, 40, 40, false);
                        break;
                    case 1:
                        g.fill3DRect(b.getX(), b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX()-40, b.getY() , 40, 40, false);
                        g.fill3DRect(b.getX()+40, b.getY() , 40, 40, false);
                        g.fill3DRect(b.getX()-40, b.getY() + 40, 40, 40, false);
                        break;
                    case 2:
                        g.fill3DRect(b.getX(), b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX()-40, b.getY() , 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY()+40 , 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY() + 80, 40, 40, false);
                        break;
                    case 3:
                        g.fill3DRect(b.getX() , b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY()+40, 40, 40, false);
                        g.fill3DRect(b.getX() -40, b.getY()+40, 40, 40, false);
                        g.fill3DRect(b.getX() - 80, b.getY()+40, 40, 40, false);
                        break;
                }
            }
            else if (b.type == 'J') {
                g.setColor(Color.MAGENTA);
                switch (b.direction) {
                    case 0:
                        g.fill3DRect(b.getX(), b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY()+40 , 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY() + 80, 40, 40, false);
                        g.fill3DRect(b.getX()-40, b.getY() +80, 40, 40, false);
                        break;
                    case 1:
                        g.fill3DRect(b.getX()-40, b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX()-40, b.getY()+40 , 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY()+40 , 40, 40, false);
                        g.fill3DRect(b.getX()+40, b.getY() + 40, 40, 40, false);
                        break;
                    case 2:
                        g.fill3DRect(b.getX(), b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX()-40, b.getY() , 40, 40, false);
                        g.fill3DRect(b.getX()-40, b.getY()+40 , 40, 40, false);
                        g.fill3DRect(b.getX()-40, b.getY() + 80, 40, 40, false);
                        break;
                    case 3:
                        g.fill3DRect(b.getX() , b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX()-40, b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX() -80, b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX() , b.getY()+40, 40, 40, false);
                        break;
                }
            }
            else if (b.type == 'T') {
                g.setColor(Color.GRAY);
                switch (b.direction) {
                    case 0:
                        g.fill3DRect(b.getX(), b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX()-40, b.getY() , 40, 40, false);
                        g.fill3DRect(b.getX()+40, b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY() +40, 40, 40, false);
                        break;
                    case 1:
                        g.fill3DRect(b.getX(), b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX()-40, b.getY() , 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY()-40 , 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY() + 40, 40, 40, false);
                        break;
                    case 2:
                        g.fill3DRect(b.getX(), b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX()-40, b.getY() , 40, 40, false);
                        g.fill3DRect(b.getX()+40, b.getY() , 40, 40, false);
                        g.fill3DRect(b.getX(), b.getY()-40, 40, 40, false);
                        break;
                    case 3:
                        g.fill3DRect(b.getX() , b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX()-40, b.getY()-40, 40, 40, false);
                        g.fill3DRect(b.getX() -40, b.getY(), 40, 40, false);
                        g.fill3DRect(b.getX() -40, b.getY()+40, 40, 40, false);
                        break;
                }
            }

        }
    }

    private void setHeight(Block b){
        char type = b.type;
        int direction = b.direction;
        int index = b.x/40;
        if (type=='O'){
            height[index] +=40;
            height[index+1] +=40;
        }
        else if (type=='S'){
            switch (direction){
                case 0:
                case 2:
                    height[index-1]+=40;
                    height[index]+=80;
                    height[index+1]+=40;
                case 1:
                case 3:
                    height[index]+=80;
                    height[index-1]+=120;
            }
        }
    }

    private void setXY(){

    }
    @Override
    public void run() {

        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }
}

