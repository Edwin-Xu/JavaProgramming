package aircraft_battle;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class Hero extends Aircaft implements KeyListener ,Runnable{//边走边发子弹？？？？？
    private  int HERO_SPEED = 4;

    public void setHERO_SPEED(int HERO_SPEED) {
        this.HERO_SPEED = HERO_SPEED;
    }

    protected Bullet bullet ;

    // 线程安全，装炮弹
    protected Vector<Bullet> bullets ;
    Constant constant = new Constant();

    public Hero(int x,int y){
        super(x,y);
        //以飞机所在正方形的左上角坐标代表飞机坐标
        bullet = new Bullet(x+20,y,1);

        bullets = new Vector<>();


    }


    public void shot (){
        //射击一次即创建一颗子弹
        Bullet b = new Bullet(getX()+20,getY(),1);
        bullets.add(b);

        //射击里面其实只需要开始这个线程即可
        Thread t = new Thread(b);
        t.start();

    }

    public void moveUp(){
        setY(getY()-HERO_SPEED);
    }
    public void moveDown(){
            if (getY()<constant.H-100){
                 setY(getY()+HERO_SPEED);
        }
    }
    public void moveLeft(){
        setX(getX()-HERO_SPEED);
    }
    public void moveRight(){
        setX(getX()+HERO_SPEED);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_J){
            shot();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {

    }
}
