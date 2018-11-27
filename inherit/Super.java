package inherit;
//
//class A {
//    protected int i;
//    A(int i){
//        this.i=i;
//        System.out.println("xutao : "+i);
//    }
//}
//
//class B extends A{
//    private int j;
//    B(int i,int j){
//        super(i);
////        this.i=i;
//        this.j=j;
//
//        System.out.println("j:"+j);
//    }
//}
//
//public class Super {
//
//    public static void main(String[] args) {
//        B b = new B(2,21);
//
//    }
//
//}

class A{
    A(int a){

    }
}

class B extends A{

    B(int a ){
        super(a);
        System.out.println("I can have my initialization");
    }
}

public class Super{
    public static void main(String[] args) {
        B b = new B(2);
    }
}