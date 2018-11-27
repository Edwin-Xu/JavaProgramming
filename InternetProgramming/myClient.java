package InternetProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客服端Client，可以连接服务器
 */
public class myClient {
    public myClient() {
        try {
            // 两个参数： ip地址，端口号
            Socket socket = new Socket("127.01.01.2",9999);
            //若连接成功，则客服端和服务之间的管道打通

            //传送数据：借助于io流，PrintWriter
            //true表示即使刷新
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
            pw.println("你好，服务器，我是客服端");

            BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String get = bf.readLine();
            System.out.println("我收到服务器的回信了： "+get);


            //close
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        myClient mc = new myClient();
    }
}
