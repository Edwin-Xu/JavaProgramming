package TankGame;

import java.util.Vector;

class Enemy extends Tank  implements Runnable{
    private WH wh = new WH();
    protected Vector<Bullet> bullets = new Vector<Bullet>();
    private Bullet bullet;

    protected boolean isTankAlive = true ;
    private int enemySpeed =10;

    public Enemy(int x, int y) {
        super(x, y);
        this.setColor(1);

    }



    public void setTankAlive(boolean tankAlive) {
        isTankAlive = tankAlive;
    }

    public void moveLeft(){
        if(getX()>30){
            for (int i = 0 ; i<3;i++) {
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
                this.setX(this.getX() - enemySpeed);
            }
        }
    }
    public void moveRight(){
        if (getX()<wh.W-80){
            for (int i = 0 ; i<3;i++) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.setX(this.getX() + enemySpeed);
            }
        }
    }
    public void moveUp(){
        if (getY()>30) {
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.setY(this.getY() - enemySpeed);
            }
        }
    }

    public void moveDown(){
        if (getY()<wh.H-80){
            for (int i = 0 ; i<3;i++) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.setY(this.getY() + enemySpeed);
            }
        }
    }

    public void shot(){
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
        Thread t = new Thread(bullet);
        t.start();
    }

    @Override
    public void run() {
        while (true){
            try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            switch ((int)(Math.random()*4)){
                case 0:
                    this.setDirection(0);
                    moveUp();
                    break;
                case 1:
                    this.setDirection(1);
                    moveDown();
                    break;
                case 2:
                    this.setDirection(2);
                    moveLeft();
                    break;
                case 3:
                    this.setDirection(3);
                    moveRight();
                    break;
            }
            if (Math.random()<0.3){
                shot();
            }
            if (!isTankAlive){
                break;
            }
        }
    }
}
