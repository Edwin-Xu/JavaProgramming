package DataStructureAndAlgorithm.LinearList.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 前缀表达式：波兰表达式
 * 后缀表达式: 逆波兰表达式
 * <p>
 * 程序功能：
 * 中缀表达式  -->后缀表达式--->对后缀表达式求值, 合为一趟来做。
 * <p>
 * <p>
 * 分析：
 * 第一步：infix --> postfix:
 * infix Expression             postfix Expression
 * A+B*C#--------  ABC*+#
 * A+B*C-D#--------  ABC*+-#
 * 1) 遇运算分量输出
 * 2) 遇运算符：比较当前运算符与栈顶运算符的优先级.  若当前运算符的
 * 优先级<=栈顶运算符的优先级,  则不断取出运算符栈顶输
 * 出,  否则进栈.  因此一开始栈中要放一个优先级最低的运
 * 算符,  假设为“#”，  例子： A+B+C；  A*B-C
 * <p>
 * (A+B)*(C-D)#------  AB+CD-*#
 * 3）遇‘（’ : 每个运算符有双重优先级.
 * 4）遇‘）’ : 依次输出栈顶直至遇匹配的‘（’为止。
 * ‘（’不输出但要退栈。
 * 5）遇‘#’ :依次输出栈顶，直至遇‘#’为止。
 * ‘#’不输出但要退栈。
 */
public class ExpressionEvaluation {
    private stack stack;
    private boolean isSpecial = false;
    private BufferedReader br = null;
    private String postfix = "";

    public ExpressionEvaluation() {
        String expression = "";
        br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("输入表达式 :");
            try {
                expression = br.readLine() + "#";
                if (expression.equals("q#")) {
                    br.close();
                    break;
                }

                stack = new stack(expression.length());
                try {
                    stack.push('#');
                    postfix = infixToPostfix(expression);

                    System.out.println("值: " + calculate(postfix));
                    System.out.println();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private String infixToPostfix(String e) throws Exception {
        String res = "";

        //-(...)
        if (e.matches("-\\(.+\\)#")) {
            e = e.substring(1);
            isSpecial = true;
        }

        //-(...)...
        if (e.matches("-\\(.+\\).+#")) {
            int i = 0;
            int m = 0, n = 0;
            for (int j = 0; j < e.length(); j++) {
                if (e.charAt(j) == '(') {
                    m++;
                } else if (e.charAt(j) == ')') {
                    n++;
                    i = j;
                }
                if (m == n) {
                    break;
                }
            }
            String s = e.substring(2, i);
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '+') {
                    s = s.substring(0, j) + "-" + s.substring(j + 1);
                }
                if (s.charAt(j) == '-') {
                    s = s.substring(0, j) + "+" + s.substring(j + 1);
                }
                if (s.charAt(j) == '(') {
                    int a = 0, b = 0;
                    while (true) {
                        if (s.charAt(j) == '(') {
                            a++;
                        }
                        if (s.charAt(j) == ')') {
                            b++;
                        }
                        if (a == b) {
                            break;
                        }
                        j++;
                    }
                }

            }
            e = "(" + s + ")" + e.substring(i);
        }

        for (int i = 0; i < e.length(); i++) {
            //将一个多位的数取出,如果是负数。。。
            String temp = "";
            // -100......解决
            if (i == 0 && e.charAt(i) == '-') {
                temp += '-';
                i++;
            }
            while (Character.isDigit(e.charAt(i))) {
                temp += e.charAt(i);
                i++;
            }
            res += temp + " ";

            //(-100...)
            if (e.charAt(i) == '-' && e.charAt(i - 1) == '(' && i >= 1) {
                i++;
                String temp1 = "-";
                while (Character.isDigit(e.charAt(i))) {
                    temp1 += e.charAt(i);
                    i++;
                }
                res += temp1 + " ";
                i--;
                continue;
            }


            char c = e.charAt(i);
            if (c == '+' || (c == '-') || c == '*' || c == '/' || c == '^') {
                if (priority(c, (char) stack.peek())) {
                    res += (stack.pop() + " ");
                    i--;//重新检查当前位。
                } else {
                    stack.push(c);
                }
            } else if (c == '#') {
                while ((char) stack.peek() != '#') {
                    res += (stack.pop() + " ");
                }
            } else if (c == '(') {
                stack.push(c);
                stack.push('#');//  !!！！！ 要重新添加，以供（ 后面的比对
            } else if (c == ')') {
                while ((char) stack.peek() != '(') {
                    char cc = (char) stack.pop();
                    if (cc != '#') // 将多余的# 去除
                        res += (cc + " ");
                }
                stack.pop();
            }
        }
        return res;
    }

    public String calculate(String postfix) throws Exception {
        /**
         * 分析：
         *       1) 遇分量进栈;
         *       2) 遇运算符:  取栈顶两个分量进行运算,栈中退了两个分量,并将结果进栈.
         * 注意：
         *      多位数值处理
         */
        stack.clear();
        String[] arr = postfix.split(" +");////
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")) {
                double n1 = Double.valueOf(stack.pop().toString());
                double n2 = Double.valueOf(stack.pop().toString());
                switch (s) {
                    case "+":
                        stack.push(n1 + n2);
                        break;
                    case "-":
                        stack.push(n2 - n1);
                        break;//注意是先出栈的为被减数
                    case "*":
                        stack.push(n1 * n2);
                        break;
                    case "/":
                        stack.push(n2 / n1);
                        break; //注意是先出栈的为被除数
                    case "^":
                        stack.push(Math.pow(n2, n1));
                        break;//注意是先出栈的为指数
                }
            } else {
                if (!s.equals("")) {//排除空串
                    if (s.charAt(0) == '-') {
                        stack.push(-Double.valueOf(s.substring(1)));

                    } else
                        stack.push(Double.valueOf(s));
                }
            }
        }
        //因为统一是按double处理的，如果结果是整数，就应该把它化为整数形式。
        String result = stack.pop().toString();
        result = result.replaceAll("0+$", "");
        result = result.replaceAll("\\.$", "");//不要将小数固有的小数点去掉了
        if (isSpecial) {
            if (result.charAt(0) == '-')
                return result.substring(1);
            else
                return "-" + result;
        }
        return result;
    }

    //当前运算符优先级<=栈顶运算符的优先级   c<=top ture,
    public boolean priority(char c, char top) {
        switch (c) {
            case '+':
            case '-':
                return !(top == '#');
            case '*':
            case '/':
                return (top == '^' || top == '*' || top == '/');
            case '^':
                return top == '^';
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        new ExpressionEvaluation();
    }
}


/**
 * 栈的实现
 */
class stack {
    private Object[] stact;
    private int top;
    private int maxSize;

    public stack(int maxSize) {
        this.stact = new Object[maxSize];
        this.maxSize = maxSize;
        this.top = 0;
    }

    public int getSize() {
        return top;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public Object peek() {
        if (top == 0) {
            return null;
        } else
            return stact[top - 1];
    }

    public void push(Object o) throws Exception {
        if (top == maxSize) {
//            throw new Exception("stack full");
        } else {

            stact[top++] = o;  //这个地方写成“ top++; stack[top] = o 又错；
        }
    }

    public Object pop() throws Exception {
        if (top == 0) {
            throw new Exception("empty stack");
//            System.out.println("empty!!!");
//            return nu;
        } else {
            top--;
            return stact[top];//这个地方不要写"top--"
        }
    }

    public void show() {
        for (int i = 0; i < top; i++) {
            System.out.print(stact[i] + "  ");
        }
        System.out.println();
    }

    public void clear() {
        top = 0;
    }
}
