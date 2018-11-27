package Abstract;

abstract class People{
    protected String name;
    protected int age;
    protected long ID;

    abstract protected void getInfo();

}

class Student extends People{
    //必须实例化抽象父类的抽象方法，继承时即默认子类会全部实例化它的所有抽象方法。
    Student(String  name,int age,long ID){
        this.name = name;
        this.ID = ID;
        this.age = age;
    }
    public void getInfo(){
        System.out.println(this.age+"\n"+this.ID);
    }


}


public class AbstractClass {
    public static void main(String[] args) {
        Student std = new Student("xutao",21,171250579);
        std.getInfo();
    }

}
