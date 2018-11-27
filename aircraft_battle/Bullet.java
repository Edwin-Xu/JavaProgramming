package aircraft_battle;

public class Bullet implements Runnable{
    protected int x;
    protected int y;
    private int HeroOrEnemy ;//hero 是1，敌人是0
    protected boolean isAlive = true;
    protected final int BULLET_SIZE  =  6;
    private  int BULLET_SPEED = 10;

    public void setBULLET_SPEED(int BULLET_SPEED) {
        this.BULLET_SPEED = BULLET_SPEED;
    }

    private Constant CONST = new Constant();
    public Bullet(int x,int y,int n ){
        this.x=x;
        this.y=y;
        this.HeroOrEnemy = n;
    }

    //move
    public void bulletMove (int n) {
        if (n == 1) {
            y -= BULLET_SPEED;
        }
        else {
            y += BULLET_SPEED;
        }
    }
    //bullet die when beyong the border
    public void bulletDie(int n){
        if (n==1) {
            if (y <= 0) {
                isAlive = false;
            }
        }
        else {
            if (y>CONST.H+45){
                isAlive = false;
            }
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public void run() {
        while (isAlive){
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //每隔一段时间就移动，这是一个线程。
            bulletMove(HeroOrEnemy);
            bulletDie(HeroOrEnemy);
        }
    }
}
