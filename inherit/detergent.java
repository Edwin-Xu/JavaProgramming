package inherit;


class Cleanser {
    private String  s = "Cleanser,";
    protected void append(String a){
        s+=a;
    }
    public void dilute(){
        append("dilute,");
    }
    public void apply(){
        append("apply,");
    }
    public void scrub(){
        append("scrub,");
    }

    public String toString (){
        return s;
    }

    public static void main(String[] args) {
        Cleanser x = new Cleanser();
        x.dilute();
        x.apply();
        x.scrub();
        System.out.println(x);
    }
}

public class detergent extends Cleanser{
    public void scrub(){
        append("detergent.scrub,");
        super.scrub();
    }

    public void foam(){
        append("foam,");
    }

    public static void main(String[] args) {
        detergent x = new detergent();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();

        System.out.println(x);

        System.out.println("Testing base class: ");
        Cleanser.main(args);

//        detergent.main(args);
        }


}



