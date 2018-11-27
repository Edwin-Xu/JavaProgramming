package containerClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Clerk{
    private String name;
    private int ID;
    private int salary;

    Clerk(String name, int ID, int salary){
        this.name = name;
        this.ID = ID;
        this.salary = salary;
    }

    public int getID() {
        return ID;
    }

    public int getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}

class clerkManage {
    private ArrayList<Clerk> clerk_list = null;

    clerkManage() {
        clerk_list = new ArrayList<Clerk>();
    }

    public void add(Clerk clerk) {
        clerk_list.add(clerk);
    }

    public void delete(Clerk clerk) {
        clerk_list.remove(clerk_list.indexOf(clerk));
    }

    public void showInfo(int ID) throws IOException {
        System.out.println("Enter the ID :");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int targrtID = Integer.valueOf(br.readLine());
        for (Clerk clerk : clerk_list) {
            if (clerk.getID() == targrtID) {
                System.out.println("Name : " + clerk.getName() + "\nID : " + clerk.getID() + "\nSalsry : " + clerk.getSalary());
                break;
            }
        }
    }

    public void showAll() {
//        clerk_list.
        for (Clerk clerk : clerk_list) {
            System.out.println("Name : " + clerk.getName() + "\nID : " + clerk.getID() + "\nSalsry : " + clerk.getSalary() + "\n");
        }
    }

    public void changeSalary(Clerk clerk) {
        System.out.println("Enter the new salary:");
        Scanner scanner = new Scanner(System.in);
        clerk.setSalary(scanner.nextInt());
        scanner.close();
        System.out.println(clerk.getSalary());
    }

    public void showAverageMaxMin() {
        int totalSalary = 0;
        int max = clerk_list.get(0).getSalary();
        int min = max;
        for (Clerk clerk : clerk_list) {
            totalSalary+=clerk.getSalary();
            if(clerk.getSalary()>max){
                max = clerk.getSalary();
            }
            if(clerk.getSalary()<min){
                min = clerk.getSalary();
            }
        }
        System.out.println("Average: "+totalSalary+"\nMax : "+max+"\nMin"+min);
    }

//    public void sortOnSalary(){
//        ArrayList<Clerk> c = new ArrayList<Clerk>();
//        ArrayList<Clerk> fuben = clerk_list;
//        while (fuben.size()!=0){
//            int max = fuben.get(0)
//        }
//    }
}
public class ArrayList_clerk {
    public static void main(String[] args) {
        clerkManage cm = new clerkManage();
        cm.add(new Clerk("xutao",171250579,1000000));
        cm.add(new Clerk("laoer",171250578,10));
        Clerk c3 = new Clerk("wang",171240546,11);
        cm.add(c3);
        cm.showAll();
        cm.changeSalary(c3);
    }
}
