package GluttonySnake;

public class SnakeHead extends SnakeParts{
    private boolean isAlive = true;

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isAlive() {
        return isAlive;

    }

    SnakeHead(int x , int y, int size, int direction){
        super(x,y,size,direction);

    }

}
