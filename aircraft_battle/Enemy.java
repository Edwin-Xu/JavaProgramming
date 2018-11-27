package aircraft_battle;

import java.util.Vector;

public class Enemy extends Aircaft implements Runnable{//由于敌人产生就会自己移动，算一个线程
    private  int ENEMY_SPEED = 3;
    private Constant CONST = new Constant();
    protected boolean isContinue = true;
    public void setENEMY_SPEED(int ENEMY_SPEED) {
        this.ENEMY_SPEED = ENEMY_SPEED;
    }

    protected Vector<Bullet >bullets ;
    protected Bullet bu ;
    //Enemy是倒过来的
    public Enemy (int x,int y){
        super(x,y);
        bullets = new Vector<>();
        bu = new Bullet(-20,-20,0);
        bullets.add(bu);
    }

    public void move(){
        setY(getY()+ENEMY_SPEED);
    }

    private void shot() {
        if (isContinue) {
            bu = new Bullet(getX(), getY(), 0);
            bullets.add(bu);
            //start it
            Thread t = new Thread(bu);
            t.start();
        }
    }
    @Override
    public void run() {
        while (isAlive){
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //自我移动
            move();
            //shot
            if (Math.random()>0.95) {
                shot();
            }
            //移除死亡子弹
            removeDeadBullet();
            //移动到下边界，死亡；
            if (getY()>=CONST.H){
                isAlive = false;
            }

        }
    }
    public void removeDeadBullet(){
        int i =-1;
        for (int j = 0;j<bullets.size();j++){
            if (!bullets.get(j).isAlive){
                i=j;
            }
        }
        if (i!=-1){
            bullets.remove(i);
        }
    }
}
