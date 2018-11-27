package InternetProgramming;

/**
 * 服务器，监听一个端口，看是否有客服端进入该端口
 *
 * 用netstat 指令在命令行可以查看状态
 *
 * 单工：只能一个发一个收，
 * 半双工：你发我不发，我发你不发
 * 双工：可以同时发
 *
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class myServer {
    ServerSocket ss =null;
    public myServer() {
        try {
            ss = new ServerSocket(9999);
            System.out.println("我是服务端，我正在监听端口9999");

            //ServerSocket 的accept方法返回一个Socket对象
            //如果没有监听到服务端进入，会一直监听，程序不终止
            Socket s = ss.accept();
            System.out.println("我监听到了，有客服端进入该端口");

            //若连接成功，则接收数据：借助于io流
            BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String get = bf.readLine();
            System.out.println("我服务器接收到了一条信息： "+get);

            //服务端详客服端发消息：
            PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
            pw.println("你好，客服端，我收到你的信息了");


            ss.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        myServer ms = new myServer();
    }
}
