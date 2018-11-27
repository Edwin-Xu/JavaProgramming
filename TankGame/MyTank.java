package TankGame;

import java.util.Vector;

class MyTank extends Tank{
    protected Vector<Bullet> bullets ;// 装子弹
    private WH wh = new WH();
    protected Bullet bullet ;
    private int mySpeed = 3;
    private int LIVES = 10;

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    public boolean getAlive() {
        return isAlive ;
    }

    private boolean isAlive = true;

    public void setLIVES(int LIVES) {
        this.LIVES = LIVES;
    }

    public int getLIVES() {

        return LIVES;
    }

    public MyTank(int x, int y){
        super(x,y);
        this.setColor(0);
        bullets =new Vector<Bullet>();
    }


    public void moveLeft(){
        if(getX()>0){
        this.setX(this.getX()-mySpeed);
        }
    }
    public void moveRight(){
        if (getX()<wh.W-40){
        this.setX(this.getX()+mySpeed);
        }
    }
    public void moveUp(){
        if (getY()>0){
        this.setY(this.getY()-mySpeed);}
    }

    public void moveDown(){
        if (getY()<wh.H-70){
        this.setY(this.getY()+mySpeed);
        }
    }

    public void shotEnemy(){
        switch (this.getDirection()){
            case 0:
                bullet= new Bullet(this.getX()+10,getY(),0);
                bullets.add(bullet);
                break;
            case 3:
                bullet = new Bullet(this.getX()+30,getY()+10,3);
                bullets.add(bullet);
                break;
            case 1:
                bullet= new Bullet(this.getX()+10,getY()+30,1);
                bullets.add(bullet);
                break;
            case 2:
                bullet= new Bullet(this.getX(),getY()+10,2);
                bullets.add(bullet);
                break;
        }

        Thread t = new Thread(bullet);//每一子弹都是一个线程，添加一个便初始化
        t.start();
    }
}