package tetris;
/**
 * 方块类
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Block implements KeyListener ,Runnable{
    //决定不要基本块，x,y表示最中间的块的左上角坐标
    protected int x = 200;
    protected int y = 0;
    protected char type;
    protected int direction = 0;
    protected int speed = 40;
    protected boolean isAlive = true;
    private int[] height = null;
    private int leftX;
    private int rightX;
    private int downY;


    public Block(char type,int [] height){
        this.type =type;
        this.height = height;
    }

    public void setBorder(){
        if (type=='S'){
            switch (direction){
                case 0:
                case 2:
                    leftX = x - 40;
                    rightX = x+80;
                    downY = y+80;
                    break;
                case 1:
                case 3:
                    leftX = x - 40;
                    rightX = x+40;
                    downY = y+80;
                    break;
            }
        }
        else if (type=='Z'){
            switch (direction){
                case 0:
                case 2:
                    leftX = x - 40;
                    rightX = x+80;
                    downY = y+80;
                    break;
                case 1:
                case 3:
                    leftX = x - 40;
                    rightX = x+40;
                    downY = y+80;
                    break;
            }
        }
        else if (type=='I'){
            switch (direction){
                case 0:
                case 2:
                    leftX = x ;
                    rightX = x+40;
                    downY = y+160;
                    break;
                case 1:
                case 3:
                    leftX = x - 40;
                    rightX = x+120;
                    downY = y+40;
                    break;
            }
        }
        else if (type=='O'){
                leftX = x ;
                rightX = x+80;
                downY = y+80;
        }
        else if (type=='L'){
            switch (direction){
                case 0:
                    leftX = x ;
                    rightX = x+80;
                    downY = y+120;
                    break;
                case 1:
                    leftX = x-40 ;
                    rightX = x+80;
                    downY = y+80;
                    break;
                case 2:
                    leftX = x-40 ;
                    rightX = x+40;
                    downY = y+120;
                    break;
                case 3:
                    leftX = x - 80;
                    rightX = x+40;
                    downY = y+80;
                    break;
            }
        }
        else if (type=='J'){
            switch (direction){
                case 0:
                    leftX = x-40 ;
                    rightX = x+40;
                    downY = y+120;
                    break;
                case 1:
                    leftX = x-40 ;
                    rightX = x+80;
                    downY = y+40;
                    break;
                case 2:
                    leftX = x-40 ;
                    rightX = x+40;
                    downY = y+120;
                    break;
                case 3:
                    leftX = x - 80;
                    rightX = x+40;
                    downY = y+80;
                    break;
            }
        }
        else if (type=='T'){
            switch (direction){
                case 0:
                    leftX = x-40 ;
                    rightX = x+80;
                    downY = y+80;
                    break;
                case 1:
                    leftX = x-40 ;
                    rightX = x+40;
                    downY = y+80;
                    break;
                case 2:
                    leftX = x-40 ;
                    rightX = x+80;
                    downY = y+40;
                    break;
                case 3:
                    leftX = x - 40;
                    rightX = x+40;
                    downY = y+80;
                    break;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void moveLeft() {
        if (leftX>0&&downY<800&&isAlive)
            this.x -= 40;
    }

    private void moveRight() {
        if (rightX<400&&downY<800&&isAlive)
            this.x += 40;
    }

    private void moveDowm() {//加速下降
        if (isAlive&&downY<760)//////这里是关键，如果到了760，那么再下降一次就越界了
             this.y += 2 * speed;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (isAlive) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                this.moveDowm();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                this.moveLeft();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.moveRight();
            } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) { //空白建  或  向上键 旋转
                direction++;
                if (direction == 4) {
                    direction = 0;
                }
            }
    }
    }

    @Override
    public void run() {
        while (isAlive){
            try {
                /**
                 * 为什么会有出边界呢，因为刷新频率不够高
                 *问题：
                 *      1.如果快速按下键，就会出边界
                 *      这种情况好像难以避免，看来只有降低他的速度来解决，当要到达的时候，取消快速下降的功能，就是上面那个控制下降的坐标标准
                 *
                 *
                 *
                 *            实际上是因为：  键盘监听是可以容忍快速点击，然后快速下降，然而这个线程自动下降的速度却是固定的
                 *            每隔一秒下降一次，然而键盘确是 y 加了许多，此时得到的 downY 是不准确的，所以一方面还是run
                 *            刷新不够。
                 *      现在基本解决下越界了
                 *
                 *      2. 如何解决左右边界变换时左右越界？？
                 *
                 */
               for (int i=0;i<10;i++) {
//                   提高刷新频率
                   setBorder();//设置好边界属性
                   if (downY>=800){
                       isAlive  =false;
                       break;
                   }
                   Thread.sleep(100);
               }
                if(downY<770) {
                    this.y += speed;
                }
                setBorder();//设置好边界属性

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (downY>=770){
                isAlive  =false;
            }
        }
    }
}

