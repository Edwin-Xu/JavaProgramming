
package GluttonySnake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Vector;

public class MyPanel extends JPanel implements Runnable,KeyListener{
    private WH wh = new WH();
    protected Color backgroundColor = Color.black;
    protected Color headColor = Color.BLUE;
    protected Color bodyColor = Color.blue;
    protected Color foodColor = Color.red;
    protected SnakeHead head =new SnakeHead(wh.W/2,wh.H/2,wh.HEAD_SIZE,0);
    private int score = 0;

    //蛇身
    protected Vector<SnakeParts> snakePartsVector ;

    private Food[] foods = new Food[wh.FOOD_NUM];
    private Vector<Food> food = new Vector<>();

    private Thread []thread = new Thread[wh.MAX_PARTS_NUM];


    public MyPanel(){

        super();
        this.setSize(wh.W,wh.H);
//        System.out.println("dd");
        snakePartsVector = new Vector<SnakeParts>();

        //把蛇头也加到里面去
        snakePartsVector.add(head);

        //初始化food：
        for (int i=0;i<wh.FOOD_NUM;i++){
//            foods[i] = new Food((int)(Math.random()*wh.W-20),(int )(Math.random()*wh.H-20),wh.FOOD_SIZE,0);
            Food f = new Food((int)(Math.random()*wh.W-20),(int )(Math.random()*wh.H-20),wh.FOOD_SIZE,0);
            food.add(f);

        }

    }

    @Override
    public void paint(Graphics g){
        super.paint(g);//不加这句话的后果很严重啊！！！！！！！！！！！！！！！
        //画出蛇头
        setSnakeLive();

        this.setBackground(backgroundColor);

        g.setColor(Color.BLUE);
        g.setFont(new Font("仿宋",Font.BOLD,18));
        g.drawString("分数："+score,10,30);

//        画蛇身
        g.setColor(bodyColor);
        for (int i=1;i<snakePartsVector.size();i++) {
            g.fillOval(snakePartsVector.get(i).getX(), snakePartsVector.get(i).getY(), snakePartsVector.get(i).getSize(), snakePartsVector.get(i).getSize());
        }
//        画出蛇头
        g.setColor(headColor);
        if (head.isAlive()) {
            g.fillOval(head.getX(), head.getY(), head.getSize(), head.getSize());
        }else {

        }
//        画出食物
        g.setColor(foodColor);
        for (int i = 0;i<food.size();i++) {
            if (food.get(i).isExist()) {
                g.fillOval(food.get(i).getX(), food.get(i).getY(), food.get(i).getSize(), food.get(i).getSize());
            }
        }
//添加food
        if (food.size()<wh.FOOD_NUM){
            for (int i = 0 ;i<(int)(wh.FOOD_NUM/10*Math.random());i++){
                Food f = new Food((int)(Math.random()*wh.W-20),(int )(Math.random()*wh.H-20),wh.FOOD_SIZE,0);
                food.add(f);
            }
        }
    }


    @Override
    public void run() {
        // 线程初始化，必须步骤
        for (int i=1;i<snakePartsVector.size();i++){
            thread[i] = new Thread(snakePartsVector.get(i));
            thread[i].start();
        }

        Thread t = new Thread(head);
        t.start();

        while (head.isAlive()){
//            try{
//                Thread.sleep(1);//不睡眠的话可能要好一点，刷新频率高
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }

            //判断food是否被吃掉
            for (int i = 0 ; i<food.size();i++){
                if (food.get(i).isExist()) {
                    foodEat(food.get(i));
                }
            }

            setPartsDirection();
            repaint();

//游戏结束弹出提示框：
            if (!head.isAlive()){
                JOptionPane.showConfirmDialog(null,"游戏结束\n分数："+score,"游戏提示",JOptionPane.CLOSED_OPTION);
                break;
            }

            /**
             * 对Vector、ArrayList在迭代的时候如果同时对其进行修改就会抛出java.util.ConcurrentModificationException异常
             */
//            failed
//            使用迭代器移除元素food
//            Iterator<Food> iterator = food.iterator();
//            while (iterator.hasNext()){
//                Food f = iterator.next();
//                if (!f.isExist()){
//                    iterator.remove();
//                    food.remove(f);
//                }
//            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    public void setPartsDirection(){
        //思想：让后一个保持和前一个同样的方向
        for (int i = 1;i<snakePartsVector.size();i++){
            if (snakePartsVector.get(i).getMoveDirection()!=snakePartsVector.get(i-1).getMoveDirection()){
                switch (snakePartsVector.get(i-1).getMoveDirection()){
                    case 1:
                        if (Math.abs(snakePartsVector.get(i).getX()-snakePartsVector.get(i-1).getX())<=3){
                            snakePartsVector.get(i).setMoveDirection(1);
                        }
                        break;
                    case 2:
                        if (Math.abs(snakePartsVector.get(i).getX()-snakePartsVector.get(i-1).getX())<=3){
                            snakePartsVector.get(i).setMoveDirection(2);
                        }
                        break;
                    case 3:
                        if (Math.abs(snakePartsVector.get(i).getY()-snakePartsVector.get(i-1).getY())<=3){
                            snakePartsVector.get(i).setMoveDirection(3);
                        }
                        break;
                    case 4:
                        if (Math.abs(snakePartsVector.get(i).getY()-snakePartsVector.get(i-1).getY())<=3){
                            snakePartsVector.get(i).setMoveDirection(4);
                        }
                        break;
                }

            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //点一下改变方向比较好；
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                if (head.getMoveDirection()!=2)
                    head.setMoveDirection(1);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                if (head.getMoveDirection()!=1)
                    head.setMoveDirection(2);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                if (head.getMoveDirection()!=4)
                     head.setMoveDirection(3);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                if (head.getMoveDirection()!=3)
                     head.setMoveDirection(4);
                break;

        }
        this.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void setSnakeLive(){
        if (head.getX()<0||head.getX()>wh.W-7.5*head.getSize()||head.getY()<0||head.getY()>wh.H-7.5*head.getSize()){
            head.setAlive(false);
        }
    }

    public void foodEat(Food food){
        int xFood = food.getX()+wh.FOOD_SIZE/2;
        int yFood = food.getY()+wh.FOOD_SIZE/2;
        int xHead = head.getX()+head.getSize()/2;
        int yHead = head.getY()+head.getSize()/2;

        //
        if ((xFood-xHead)*(xFood-xHead)+(yFood-yHead)*(yFood-yHead)<=(wh.FOOD_SIZE+head.getSize())*(wh.FOOD_SIZE+head.getSize())/4){
            food.setExist(false);
            this.food.remove(food);//移除,这个位置最好，不要另开一个函数来检查、
            score++;
            //当吃了一个food时船舰part并加入容器
            SnakeParts sp = new SnakeParts(0,0,0,0);
            switch (snakePartsVector.get(snakePartsVector.size()-1).getMoveDirection()){
                case 1:
                     sp = new SnakeParts(snakePartsVector.get(snakePartsVector.size()-1).getX(),snakePartsVector.get(snakePartsVector.size()-1).getY()+head.getSize()/2,wh.HEAD_SIZE,1);
                    Thread t= new Thread(sp);
                    t.start();
                     break;
                case 2:
                    sp = new SnakeParts(snakePartsVector.get(snakePartsVector.size()-1).getX(),snakePartsVector.get(snakePartsVector.size()-1).getY()-head.getSize()/2,wh.HEAD_SIZE,2);
                    Thread t1= new Thread(sp);
                    t1.start();
                    break;
                case 3:
                    sp = new SnakeParts(snakePartsVector.get(snakePartsVector.size()-1).getX()+head.getSize()/2,snakePartsVector.get(snakePartsVector.size()-1).getY(),wh.HEAD_SIZE,3);
                    Thread t3= new Thread(sp);
                    t3.start();
                    break;
                case 4:
                    sp = new SnakeParts(snakePartsVector.get(snakePartsVector.size()-1).getX()-head.getSize()/2,snakePartsVector.get(snakePartsVector.size()-1).getY(),wh.HEAD_SIZE,4);
                    Thread t4= new Thread(sp);
                    t4.start();
                    break;
            }

            snakePartsVector.add(sp);
        }
    }
}
