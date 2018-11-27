package polymorphic;

class animal{
    protected String name;
    protected int age;
    protected category sort;

    public void printName(){
        System.out.println(name);
    }
    public void printInfo(){
        System.out.println("我是"+name+",年龄"+age+",类别"+sort);
    }
}

class cat extends animal{
    private String sub_name;
}

public class dog extends animal{
    public dog(String name,int age,category cg){
        this.age=age;
        this.sort=cg;
        this.name=name;
    }

    public void printInfo(){
        System.out.println("我是"+name+",年龄"+age);
    }

    public static void main(String[] args) {
        animal dog1 = new dog("小花",2,category.dogCategory);
        dog1.printInfo();

        dog dog2 = new dog("小花花",3,category.dogCategory);
        dog2.printInfo();

    }
}