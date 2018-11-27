package aircraft_battle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

public class MyPanel extends JPanel implements Runnable,KeyListener {
    private Constant CONST = new Constant();
    protected Hero hero ;
    protected Vector<Enemy> enemies ;
    protected int Score = 0;
    protected boolean isContinue = true;

//    BufferedImage bi = null;
protected Color heroColor = new Color(255,0,0);//red
protected Color enemyColor = new Color(0,255,0);//
protected Color bgColor = new Color(0,0,0);

    public MyPanel(){
        hero = new Hero(CONST.W/2-20,CONST.H-100);
        enemies = new Vector<>();

        Enemy enemy = new Enemy(100,0);
        enemies.add(enemy);
        Thread t = new Thread(enemy);
        t.start();

        this.setSize(CONST.W,CONST.H);

//        try {
//            bi = ImageIO.read(new File("D:\\JavaFiles\\iintellij_idea\\OOP\\src\\aircraft_battle.BG.jpg"));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        this.setBackground(bgColor);
        Score = CONST.ememyDestroyNum*2 - CONST.enemyMissNum;
//      1.画我的飞机
        drawHero(g);
//      2.画我的子弹
        drawBullet(g);
//      3.画敌人飞机
        drawEnemies(g);
//      4.画敌人子弹
        drawEnemyBullet(g);
//      5.画当前分数
        g.setFont(new Font("仿宋",Font.BOLD,20));
        g.setColor(Color.BLUE);
        g.drawString("分数 "+Score,190,20);
        g.drawString("击落 "+CONST.ememyDestroyNum,10,20);
        g.drawString("逃脱 "+CONST.enemyMissNum,100,20);

    }

    private void drawHero (Graphics g){
        g.setColor(heroColor);
        int [] xPoints = {hero.getX()+20,hero.getX()+15,hero.getX()+25};
        int []yPoints = {hero.getY(),hero.getY()+20,hero.getY()+20};
        g.fillPolygon(xPoints,yPoints,3);

        int [] xPoints2 = {hero.getX()+20,hero.getX(),hero.getX()+40};
        int []yPoints2= {hero.getY()+15,hero.getY()+30,hero.getY()+30};
        g.fillPolygon(xPoints2,yPoints2,3);

        g.fill3DRect(hero.getX()+15,hero.getY()+30,10,5,true);

        int [] xPoints3 = {hero.getX()+20,hero.getX()+10,hero.getX()+30};
        int []yPoints3 = {hero.getY()+30,hero.getY()+40,hero.getY()+40};
        g.fillPolygon(xPoints3,yPoints3,3);
    }
    private void drawBullet(Graphics g){
        g.setColor(heroColor);
        for (int i=0;i<hero.bullets.size();i++){
            g.fillOval(hero.bullets.get(i).x-hero.bullets.get(i).BULLET_SIZE/2,hero.bullets.get(i).y,hero.bullets.get(i).BULLET_SIZE,hero.bullets.get(i).BULLET_SIZE);
        }
    }
    private void drawEnemies(Graphics g){
        g.setColor(enemyColor);
        for (int i=0;i<enemies.size();i++){
            Enemy enemy = enemies.get(i);
//            int xRandom = (int)(Math.random()*CONST.W);

            int [] xPoints = {enemy.getX()+20,enemy.getX()+15,enemy.getX()+25};
            int []yPoints = {enemy.getY(),enemy.getY()-20,enemy.getY()-20};
            g.fillPolygon(xPoints,yPoints,3);

            int [] xPoints2 = {enemy.getX()+20,enemy.getX(),enemy.getX()+40};
            int []yPoints2= {enemy.getY()-15,enemy.getY()-30,enemy.getY()-30};
            g.fillPolygon(xPoints2,yPoints2,3);

            g.fill3DRect(enemy.getX()+15,enemy.getY()-35,10,5,true);

            int [] xPoints3 = {enemy.getX()+20,enemy.getX()+10,enemy.getX()+30};
            int []yPoints3 = {enemy.getY()-30,enemy.getY()-40,enemy.getY()-40};
            g.fillPolygon(xPoints3,yPoints3,3);
        }
    }
    private void drawEnemyBullet(Graphics g){
        g.setColor(enemyColor);
        for (int i =0;i<enemies.size();i++){
            Enemy enemy = enemies.get(i);
            for (int j=0;j<enemy.bullets.size();j++){
                g.fillOval(enemy.bullets.get(j).x+20,enemy.bullets.get(j).y,enemy.bullets.get(j).BULLET_SIZE,enemy.bullets.get(j).BULLET_SIZE);
            }
        }
    }
    @Override
    public void run() {
        while (hero.isAlive ){
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //移除死亡子弹：
            removeDeadBullet();

            //移除死亡飞机
            removeDeadEnemy();
            //添加飞机
            addEnemy();
            //敌人死亡
            enemyDie();
            //重绘
            repaint();
        }
    }

    public void addEnemy(){
        if (Math.random()>0.95&&isContinue){
            Enemy enemy = new Enemy((int)(Math.random()*(CONST.W-40)),0);
            enemies.add(enemy);
            Thread t = new Thread(enemy);
            t.start();
        }
    }

    public void removeDeadBullet(){
        int i =-1;
        for (int j = 0;j<hero.bullets.size();j++){
            if (!hero.bullets.get(j).isAlive){
                i=j;
            }
        }
        if (i!=-1){
            hero.bullets.remove(i);
        }
    }

    public void removeDeadEnemy(){
        int i =-1;
        for (int j = 0;j<enemies.size();j++){
            if (!enemies.get(j).isAlive){
                i=j;
            }
        }
        if (i!=-1){
            enemies.remove(i);
        }
    }

    public void enemyDie(){
        for (Enemy enemy:enemies){
            if (enemy.getY()>CONST.H-50){
                enemy.isAlive = false;
                CONST.enemyMissNum ++;
                System.out.println("逃跑了 " +CONST.enemyMissNum+" 个敌人");
            }
            for (int i =0 ;i<hero.bullets.size();i++){
                if (hero.bullets.get(i).x>enemy.getX()&&hero.bullets.get(i).x<enemy.getX()+40&&hero.bullets.get(i).y>enemy.getY()-10&&hero.bullets.get(i).y<enemy.getY()){
                    enemy.isAlive = false;
                    hero.bullets.get(i).isAlive = false;
                    CONST.ememyDestroyNum++;
                    System.out.println("消灭了 "+ CONST.ememyDestroyNum+" 个敌人");
                }
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
//        if (e.getKeyCode()==KeyEvent.VK_J){
//            hero.shot();
//        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                hero.moveUp();
                break;
            case KeyEvent.VK_S:
                hero.moveDown();
                break;
            case KeyEvent.VK_A:
                hero.moveLeft();
                break;
            case KeyEvent.VK_D:
                hero.moveRight();
                break;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_J){
            hero.shot();
        }
    }
}
