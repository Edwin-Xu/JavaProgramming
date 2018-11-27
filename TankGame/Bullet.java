package TankGame;

class Bullet implements Runnable{
    private WH wh = new WH();
    private int x,y,direction;
    private int bulletSpeed = 10;
    protected boolean isBulletAlive ;

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


    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public Bullet(int x, int y, int direction){
        this.x = x;
        this.y = y;
        this.direction =direction;
        isBulletAlive = true;
    }

    public void setBulletAlive(boolean bulletAlive) {
        isBulletAlive = bulletAlive;
    }

    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(50);
            }catch (Exception e){
                e.printStackTrace();
            }
            switch (direction){
                case 0:
                    y-=bulletSpeed;
                    break;
                case 1:
                    y+=bulletSpeed;
                    break;
                case 2:
                    x-=bulletSpeed;
                    break;
                case 3:
                    x+=bulletSpeed;
                    break;
            }

            if (x<0||x>wh.W||y<0||y>wh.H){
                isBulletAlive = false;
                break;
            }

        }
    }
}
