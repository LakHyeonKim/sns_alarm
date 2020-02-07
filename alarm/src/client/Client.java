package client;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
 
public class Client {
 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("client 2");
 
        Socket CS = null;
        try {
            CS = new Socket();
 
            System.out.print("Login? (enter num): ");
            int temp = input.nextInt();
 
            CS.connect(new InetSocketAddress(InetAddress.getLocalHost(), 4040));
            System.out.println("SUCCESS");
 
            while (true) {
                System.out.print("> ");
                String message = input.nextLine();
                byte[] as = message.getBytes("UTF-8");
                OutputStream OS = CS.getOutputStream();
                OS.write(as);
            }
 
        } catch (Exception e) {
            System.out.println(e);
        }
 
        try {
            CS.close();
        } catch (Exception e) {
            System.out.println(e);
        }
 
    }
}