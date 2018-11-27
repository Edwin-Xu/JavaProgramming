package TankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Vector;


class MyPanel extends JPanel implements KeyListener,Runnable{
    int i=0;
    private final int ENEMY_NUM = 15;
    private final int MAX_BULLET_NUM_ONE_TIME = 10;

    private WH wh = new WH();
    private MyTank mytank ;
    private Vector<Enemy> enemies = new Vector<Enemy>();

    private Thread[] enemyThread = new Thread[ENEMY_NUM];//数组行吗？？？？？？？？？？？？？？

    public MyPanel(){
        mytank = new MyTank(150,100);

        for (int i= 0 ; i<ENEMY_NUM;i++){
            Enemy e = new Enemy(200,200);
            enemies.add(e);
            enemyThread[i] = new Thread(enemies.get(i));
            enemyThread[i].start();
        }
    }

    @Override
    public void paint(Graphics g){//重写的这个paint方法应该是自动调用的
        super.paint(g);
        g.fillRect(0,0,wh.W,wh.H);
//        if(mytank.getAlive()) {
            this.drawTank(mytank.getX(), mytank.getY(), g, mytank.getDirection(), mytank.getColor());
//            if (mytank.getLIVES()>0){
//                mytank.setAlive(true);
//            }
//        }
        //敌人坦克
        for (int i=0 ; i<enemies.size();i++){
            if (enemies.get(i).isTankAlive){
                drawTank(enemies.get(i).getX(),enemies.get(i).getY(),g,enemies.get(i).getDirection(),enemies.get(i).getColor());
            }
//            else {
//                Toolkit toolkit = this.getToolkit();
//                Image image = toolkit.getImage("D:\\图片\\编程图片\\坦克大战\\1.jpeg");
//                g.drawImage(image,enemies.get(i).getX(),enemies.get(i).getY(),50,50,this);
//
//
//            }
        }
        //敌人坦克子弹
        for (int i=0;i<enemies.size();i++) {
            for (int j =0;j<enemies.get(i).bullets.size();j++) {
                Bullet bu = enemies.get(i).bullets.get(j);

//                EnemyShotMyTank(mytank,bu); 用了会出现卡顿现象，为什么啊？

                if (bu != null && bu.isBulletAlive&&enemies.get(i).isTankAlive) {
                    g.setColor(Color.RED);
                    g.fillOval(enemies.get(i).bullets.get(j).getX(), enemies.get(i).bullets.get(j).getY() + 5, 4, 4);
                }
                //死亡，将其移除。
                if (!bu.isBulletAlive){
                    enemies.get(i).bullets.remove(bu);
                }

            }
        }

        //我的坦克的子弹
        for (int i=0;i<mytank.bullets.size();i++) {
            Bullet b = mytank.bullets.get(i);
            if (b != null && b.isBulletAlive) {
                g.setColor(Color.RED);
                g.fillOval(mytank.bullets.get(i).getX(), mytank.bullets.get(i).getY() + 5, 4, 4);
            }

//            如果子弹打到敌人，则敌人和子弹都应该消失
            Iterator<Enemy> iterator = enemies.iterator();
            while (iterator.hasNext()) {
                Enemy enemy = iterator.next();
                MyTankShotEnemy(enemy,b);
            }
            if (!b.isBulletAlive) {//如果子弹已经死了，则将其从容器中移除。
                mytank.bullets.remove(b);
            }
        }

    }
    public void MyTankShotEnemy(Enemy enemy, Bullet b){
        if (enemy.getDirection() == 0 || enemy.getDirection() == 1) {
            if (b.getX() >= enemy.getX() && b.getX() <= enemy.getX() + 20 && b.getY() >= enemy.getY() && b.getY() <= enemy.getY() + 30) {
                b.setBulletAlive(false);
                enemy.setTankAlive(false);
            }
        } else {
            if (b.getX() >= enemy.getX() && b.getX() <= enemy.getX() + 30 && b.getY() >= enemy.getY() && b.getY() <= enemy.getY() + 20) {
                b.setBulletAlive(false);
                enemy.setTankAlive(false);
            }
        }

    }
    public void EnemyShotMyTank(MyTank mytank, Bullet b){
        if (mytank.getDirection() == 0 || mytank.getDirection() == 1) {
            if (b.getX() >= mytank.getX() && b.getX() <= mytank.getX() + 20 && b.getY() >= mytank.getY() && b.getY() <= mytank.getY() + 30) {
                mytank.setAlive(false);
                mytank.setLIVES(mytank.getLIVES()-1);
            }
        } else {
            if (b.getX() >= mytank.getX() && b.getX() <= mytank.getX() + 30 && b.getY() >= mytank.getY() && b.getY() <= mytank.getY() + 20) {
                mytank.setAlive(false);
                mytank.setLIVES(mytank.getLIVES()-1);
            }
        }
        if (mytank.getLIVES()>0&&!mytank.getAlive()){
            try {
                Thread.sleep(300);
//                mytank.setAlive(true);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void drawTank(int x,int y,Graphics g,int direction,int type){
        switch (type){
            case 0:
                g.setColor(Color.CYAN);
                break;
            case 1:
                g.setColor(Color.yellow);
        }

        switch (direction){
            case 0://向上
                g.fill3DRect(x,y,5,30,false);
                g.fill3DRect(x+15,y,5,30,false);
                g.fill3DRect(x+5,y+5,10,20,false);
                g.fillOval(x+5,y+10,10,10);
                g.drawLine(x+10,y+10,x+10,y);
                break;
            case 1://down
                g.fill3DRect(x,y,5,30,false);
                g.fill3DRect(x+15,y,5,30,false);
                g.fill3DRect(x+5,y+5,10,20,false);
                g.fillOval(x+5,y+10,10,10);
                g.drawLine(x+10,y+30,x+10,y+10);
                break;
            case 2://left
                g.fill3DRect(x-5,y+5,30,5,false);
                g.fill3DRect(x-5,y+20,30,5,false);
                g.fill3DRect(x,y+10,20,10,false);
                g.fillOval(x+5,y+10,10,10);// don't change
                g.drawLine(x-5,y+15,x+15,y+15);
                break;
            case 3://right
                g.fill3DRect(x-5,y+5,30,5,false);
                g.fill3DRect(x-5,y+20,30,5,false);
                g.fill3DRect(x,y+10,20,10,false);
                g.fillOval(x+5,y+10,10,10);// don't change
                g.drawLine(x+10,y+15,x+25,y+15);
                break;

        }


    }


    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                this.mytank.moveUp();
                this.mytank.setDirection(0);
                break;
            case KeyEvent.VK_S:
                this.mytank.moveDown();
                this.mytank.setDirection(1);
                break;
            case KeyEvent.VK_A:
                mytank.moveLeft();
                mytank.setDirection(2);
                break;
            case KeyEvent.VK_D:
                mytank.moveRight();
                mytank.setDirection(3);
                break;
        }

        // press "j" to shot
        if (e.getKeyCode()==KeyEvent.VK_J &&mytank.bullets.size()<MAX_BULLET_NUM_ONE_TIME) {
            mytank.shotEnemy();
        }
//        this.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(i);
            i++;
            this.repaint();
        }
        /**
         * 时时刻刻都在刷新，故上面那个paint不要也行
         */
    }
}
