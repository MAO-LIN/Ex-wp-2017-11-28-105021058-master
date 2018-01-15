package test;

import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        try {
            int port = 7277;
            ServerSocket serverSkt = new ServerSocket(port);
                System.out.println("等待連線中...");
                Socket clientSkt = serverSkt.accept();
                System.out.printf("嘗試與 %s 建立連線%n",clientSkt.getInetAddress().toString());
                System.out.println("\n確定可連線！");
                clientSkt.close();
                String str=scn.next();
                while(str.equals("false")) {
                    clientSkt = serverSkt.accept();
                    OutputStream out = clientSkt.getOutputStream();
                    System.out.println("資料傳送中...");
                    out.write(str.getBytes());
                    out.close();
                }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}