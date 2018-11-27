package Genericity;

public class genericity_class {
    public static void main(String[] args) {
        genericity<String > g = new genericity<String>("string!");
        System.out.println(g.getT());
    }
}

// class A <a string that can express any leixing>
class genericity<T>{
    private T t;
    genericity(T t){
        this.t = t;
    }
    public T getT() {
        return t;
    }
}