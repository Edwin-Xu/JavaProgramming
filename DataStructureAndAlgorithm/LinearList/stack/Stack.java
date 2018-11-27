package DataStructureAndAlgorithm.LinearList.stack;

/**
 * 栈的实现
 */
public class Stack {
    private Object []stact;
    private int top;
    private int maxSize;

    public Stack(int maxSize){
        this.stact = new Object[maxSize];
        this.maxSize = maxSize;
        this.top=0;
    }

    public int getSize (){
        return top;
    }
    public boolean isEmpty(){
        return top==0;
    }
    public Object peek(){
        if (top==0){
            return null;
        }
        else
            return stact[top-1];
    }
    public void push(Object o) throws Exception{
        if (top==maxSize){
//            throw new Exception("stack full");
        }
        else {

            stact[top++]=o;  //这个地方写成“ top++; stack[top] = o 又错；
        }
    }
    public Object pop() throws Exception{
        if (top==0){
            throw new Exception("empty stack");
//            System.out.println("empty!!!");
//            return nu;
        }
        else{
            top--;
            return stact[top];//这个地方不要写"top--"
        }
    }
    public void show(){
        for (int i =0;i<top;i++){
            System.out.print(stact[i]+"  ");
        }
        System.out.println();
    }

    public void clear(){
        top = 0;
    }
}
