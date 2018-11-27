package Genericity;

import java.lang.reflect.Method;

public class Reflection {
    public static void main(String[] args) {
        reflect r = new reflect(1,2);
        System.out.println(r.getClass());
        System.out.println(r.getClass().getName());

        Method []m = r.getClass().getDeclaredMethods();
        for (Method mm :m){
            System.out.println(mm);
            System.out.println(mm.getName());
        }

        System.out.println(r.getClass().getPackage().getName());
    }


}

class reflect{
    private int id;
    private int age;
    reflect(int age,int id){
        this.age =age;
        this.id=id;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public int getid(){
        return id;
    }

}