package aircraft_battle;

public class Aircaft {
    private int x;
    private int y;
    protected boolean isAlive =true;

    public Aircaft(int x,int y){
        this.x = x;
        this.y = y;
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



}
