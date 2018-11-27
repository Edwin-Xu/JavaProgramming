package DataStructureAndAlgorithm.LinearList.Queue;

/**
 * 队列的数组(循环数组)实现：
 *循环队列
 */
public class Queue_arr {
    private final int maxSixe ;
    private Object [] arr ;
    private int length = 0;//current size
    private int front = 0;
    private int rear  = 0;

    //  delete <-----------------insert
    //        front          rear

    public Queue_arr(int maxSixe){
        arr = new Object[maxSixe];
        this.maxSixe =maxSixe;
    }

    public boolean isEnpty (){
        return length == 0;
    }
    public boolean isFull (){
        return length == maxSixe;
    }
    public int getSize(){
        return length;
    }
    public Object getFront(){
        return arr[front];
    }
    public Object getRear(){
        return arr[rear];
    }

    public void enQueue(Object o){
        //if it is empty
        if(length==0){
            arr[rear] = o;
            length++;
        }
        else if (isFull()){
            try {
                throw new Exception ("The Queue is full!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            rear++;
            //如果到了队尾而数组未满，则返回至队首
            if (rear==maxSixe){
                rear = 0;
            }
            arr[rear] = o;
            length++;
        }
    }

    public Object deQueue() {
        //empty queue can't delete element;
        if (isEnpty()) {
            try {
                throw new Exception("The Queue is empty !");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else {
            front++;
            length--;
            if (front==maxSixe){
                front = 0 ;
                return arr[maxSixe -1];
            }
            return arr[front - 1];
        }
    }

    public void display(){
        System.out.println("Queue Elements: "+length);

        if (front<=rear) {
            for (int i = front, count = 0; i <= rear; i++, count++) {
                System.out.println(count + ":  " + arr[i]);
            }
        }
        else {
            for (int i = front, count = 0; ; i++, count++) {
                if (i==maxSixe){
                    i=0;
                }
                if (i==rear+1) break;
                System.out.println(count + ":  " + arr[i]);
            }
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Queue_arr queue_arr = new Queue_arr(20);
        queue_arr.enQueue(1);
        queue_arr.enQueue(2);
        queue_arr.enQueue(3);
        queue_arr.display();
        queue_arr.enQueue(4);
        queue_arr.enQueue(5);
        queue_arr.display();

        queue_arr.deQueue();
        queue_arr.deQueue();
        queue_arr.deQueue();

        queue_arr.display();

        queue_arr.enQueue(6);
        queue_arr.enQueue(7);
        queue_arr.display();
    }

}
