package event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseEvent_  extends JFrame {
    private myPanel_1 mp;

    public MouseEvent_(){
        mp = new myPanel_1();
        this.add(mp);

        //监听：
        this.addMouseListener(mp);
        this.addMouseMotionListener(mp);

        this.setTitle("MouseEvent");
        this.setSize(400,300);
        this.setLocation(550,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        MouseEvent_ me = new MouseEvent_();
    }
}



class myPanel_1 extends JPanel implements MouseListener,MouseMotionListener ,WindowListener {
    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println("("+e.getX()+","+e.getY()+")");
//        System.out.println(e.getLocationOnScreen().getLocation());
//        System.out.println(e.getClickCount());




    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("pressed!");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        System.out.println("released!");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println("Entered!");
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println("Exited!");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Dragged!");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Moved");
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}