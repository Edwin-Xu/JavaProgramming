package GluttonySnake;

public class Food extends SnakeParts {
    public void setExist(boolean exist) {
        isExist = exist;
    }

    public boolean isExist() {
        return isExist;

    }
//是否被吃掉了
    private boolean isExist = true;
    public Food(int x, int y, int size,int direction) {
        super(x, y, size,direction);
    }


    @Override
    public void moveUp(){

    }
    public void moveDown(){

    }
    public void moveLeft(){

    }
    public void moveRight(){

    }

}
