package polymorphic;

class Animal{
    protected String name;
    protected Food food;
    protected void cry(){
        System.out.println("cry!");
    };
    protected void show(){
        System.out.println("I am "+ (name.equals("")?"":name.split("\\.")[1])+", I eat "+food+"!");
    }
}

class Dog extends Animal{
    Dog(){
        name=this.getClass().toString();
        food = Food.gutou;
    }
    @Override
    public void cry(){
        System.out.println("WangWang!");
    }
}

class Cat extends Animal {
    Cat() {
        name = this.getClass().toString();
        food = Food.fish;
    }

    public void cry() {
        System.out.println("MiaoMiao!");
    }
}
class People extends Animal{
    People(){
        name=this.getClass().toString();
        food = Food.wine;
    }
    public void cry(){
        System.out.println("Shit!");
    }
}

class Master{
    protected Animal animal;
}

enum Food{
    fish,
    gutou,
    rice,
    wine
}
public class Demo {
    public static void main(String[] args) {

        Animal an = new People();
        an.cry();
        an.show();
        an = new Cat();
        an.cry();
        an.show();
        an = new Animal();
        an.show();
    }
}
