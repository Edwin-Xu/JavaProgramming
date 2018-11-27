package GluttonySnake;

public class SnakeParts implements Runnable{
    private WH wh = new WH();
    private int x;
    private int y;
    private int speed = wh.MIN_SPEED;
    private int size ;

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getSpeed() { return  speed; }

    //运动方向：1左2右，3上4下
    private int moveDirection =3;//初始化时向左

    public SnakeParts(int x,int y,int size,int direction){
        //取中心点坐标应该要简单点
        this.x = x;
        this.y = y;
        this.size = size;
        this.moveDirection = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMoveDirection() {
        return moveDirection;
    }

    public void setMoveDirection(int moveDirection) {
        this.moveDirection = moveDirection;
    }

    public int getSize() {
        return size;
    }

    public void moveUp(){
        y-=speed;
    }
    public void moveDown(){
        y+=speed;
    }
    public void moveLeft(){
        x-=speed;
    }
    public void moveRight(){
        x+=speed;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (moveDirection) {
                case 1:
                    moveUp();
                    break;
                case 2:
                    moveDown();
                    break;
                case 3:
                    moveLeft();
                    break;
                case 4:
                    moveRight();
                    break;
            }
        }
    }
}