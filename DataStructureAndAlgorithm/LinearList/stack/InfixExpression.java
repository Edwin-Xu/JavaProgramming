package DataStructureAndAlgorithm.LinearList.stack;

/**
 * 由于中缀要考虑优先级，这个算法有局限性
 *
 */
public class InfixExpression {
    private Stack stack;
//    private String expression;
    private char[] chars;
    private int i;

    public InfixExpression(String expression){
//        this.expression =expression;
        stack = new Stack(expression.length());
        chars = expression.toCharArray();
        try {
            this.expressionCompute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void expressionCompute() throws Exception{
        for (i = 0;i<chars.length;i++){
            char c = chars[i];
            if (c=='('||Character.isDigit(c)||c=='.'){  //( & 数字
                stack.push(c);
            }
            else if (c=='+'){
               i=conclute('+');

            }else if (c=='-'){
               i=conclute('-');

            }else if (c=='*'){
               i=conclute('*');

            }else if (c=='/'){
               i=conclute('/');

            }
            else if (c==')'){
                Object o = stack.pop();
                stack.pop();
                stack.push(o);
            }
        }
        System.out.println(stack.peek());
    }

    private  int conclute(char c) throws Exception{
        String s1="",s2 = "";
        int n1 =0 ,n2 = 0;
        double m1=0,m2=0;
        while (Character.isDigit((char)stack.peek())||(char)stack.peek()=='.'){
            s1 = (char)stack.pop()+s1;
        }
        while ((Character.isDigit(chars[i])||chars[i]=='.')&&i<chars.length){
            s2+=chars[i];
            i++;
        }
        if (!s1.contains(".")&&!s2.contains(".")){
            n1 = Integer.valueOf(s1);
            n2 = Integer.valueOf(s2);
            switch (c){
                case '+': stack.push(n1+n2);
                break;
                case '-':stack.push(n1-n2);
                break;
                case '*':stack.push(n1*n2);
                break;
                case '/':stack.push(n1/(n2+0.00));
                break;
            }
        }
        else {
            m1 = Double.valueOf(s1);
            m2 = Double.valueOf(s2);
            switch (c){
                case '+': stack.push(m1+m2);
                    break;
                case '-':stack.push(m1-m2);
                    break;
                case '*':stack.push(m1*m2);
                    break;
                case '/':stack.push(m1/(m2+0.00));
                    break;
            }
        }
        return i;
    }


    public static void main(String[] args) {
        try {
            new InfixExpression("((2+3))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




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

}
