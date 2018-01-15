package test;

import java.io.InputStream;
import java.net.Socket;

public class Client {
    public static void main(String args[]) {
        byte buff[]=new byte[1024];
        try {
            System.out.println("Ready Send....");
            String ip = "127.0.0.1";
            int port = 7277;
            String sar="";
            Socket skt = new Socket(ip, port);
            System.out.println("connect success....");
            InputStream in=skt.getInputStream();
            int n=in.read(buff);
            System.out.print("伺服器:");
            System.out.print(new String(buff,0,n));
            sar=new String(buff,0,n);


        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}