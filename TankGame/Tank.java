package TankGame;

class Tank {
//    private int speed = 3;

    private int x;
    private int y;
    private int color;
    private int direction = 0;


    public Tank(int x,int y){
        this.x = x;
        this.y = y;

    }

    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public int getDirection() {
        return direction;
    }
    ;//0up  1down   2 left  3 right
    public void setX(int x) {
        this.x = x;
    }
//    public int getSpeed() {
//        return speed;
//    }
    public void setY(int y) {
        this.y = y;
    }


    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
}
