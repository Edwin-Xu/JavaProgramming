package combination;
//一个Java文件里只有一个主类，该类初始化用public className,而其他可以存在多个类，用类名即可初始化
class A {
    protected String name;
    protected int age;

    A(String name, int age){
        this.name=name;
        this.age = age;
    }

    protected String introdection(){
        return "I am "+name+" and I'm "+age;
    }
}

class B {
    private A a ;
    B(A a){
        this.a = a;
    }
    protected String introduction(){
        return "I belong to B class. "+a.introdection();
    }
}

class C {
    private B b ;
    private A a;
    C (A a , B b){
        this.b = b;
        this.a = a;
    }
    protected String introduction(){
        return "I belongs to C. "+a.introdection();
    }

}


public class combination {
    protected void printIntroduction(String s){
        System.out.println(s);
    }
    public static void main(String []args){
        combination zuhe = new combination();

        A a = new A("xutao",21);
        zuhe.printIntroduction(a.introdection());

        B b = new B(a);
        zuhe.printIntroduction(b.introduction());

        C c = new C(a,b);
        zuhe.printIntroduction(c.introduction());
    }



}
