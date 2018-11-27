package inherit;//继承

class person {
    protected String name ;
    protected int age;
    protected float height;
    protected float weight;

    protected int getAge(){
        return age;
    }
    public String getName(){
        return name;
    }
    public float HW(){
        return height+weight;
    }
}

class AA {

}
public class student extends person{

    public student(String name ,int age,float height,float weight){
        super.name=name;
        super.age=age;
        super.height=height;
        super.weight=weight;
    }

    public void ptint(){
        System.out.println(name);
    }
    public String getName(){
        return name+"```";
    }

    public static void main(String []args){
        student stu1 = new student("xutao",21,172,135);
        System.out.println(stu1.getName()+":"+stu1.age+","+stu1.height+","+stu1.weight);
        System.out.println(stu1.HW());
        stu1.ptint();
    }
}

