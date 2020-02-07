package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import config.ServerInfo;
import jdbc.common.Database;
import vo.Alarm;
 
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(ServerInfo.ALARM_SERVER_PORT);
            HashMap<String, Object> hm = new HashMap<String, Object>();
            while(true) {
                System.out.println("server waiting...");
                Socket sock = server.accept();
                ChatThread chatThread = new ChatThread(sock, hm);
                chatThread.start();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 
class ChatThread extends Thread{
    private Socket sock;
    private String id;
    private BufferedReader br;
    private HashMap<String, Object> hm;
    private boolean initFlag = false;
    public ChatThread(Socket sock, HashMap<String,Object> hm) {
        this.sock = sock;
        this.hm = hm;
        try {
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            id = br.readLine();
            //broadcast(id + "님이 접속하셨습니다.");
            System.out.println("접속한 사용자의 아이디 : "+id);
            synchronized (hm) {
                hm.put(this.id, pw);
            }
            initFlag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run() {
        try {
            String line = null;
            while((line = br.readLine()) != null) {
                if(line.equals("/quit")) {
                    break;
                }
                if(line.indexOf("/to") == 0) {
                    sendmsg(line);
                }else {
                    broadcast(id+" : "+line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            synchronized (hm) {
                hm.remove(id);
            }
            broadcast(id+"님이 접속을 종료했습니다.");
            try {
                if(sock != null) {
                    sock.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    public void sendmsg(String msg) throws SQLException {
    	Database database = new Database();
        int start = msg.indexOf(" ") + 1;
        int end = msg.indexOf(" ",start);
        if(end != -1) {
            String to = msg.substring(start, end);
            long memberId = Long.parseLong(to);
            String msg2 = msg.substring(end +1);
            Object obj = hm.get(to);
            if(obj != null) {
                PrintWriter pw = (PrintWriter)obj;
                checkAlarm(database, pw, to, memberId);
            }
        }
    }
    
    public void checkAlarm(Database database, PrintWriter pw, String to, long memberId) throws SQLException {
    	//pw.println(id + "님이 다음의 귓속말을 보내셨습니다. : " + msg2);
    	while(true) {
    		pw.println("[" + to + "] ::alarmList::" + database.getAllAlarm(memberId));
            pw.flush();
            try {
    			Thread.sleep(1000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
   
    public void broadcast(String msg) {
        synchronized (hm) {
            Collection<Object> collection = hm.values();
            Iterator<?> iter = collection.iterator();
            while(iter.hasNext()) {
                PrintWriter pw = (PrintWriter)iter.next();
                pw.println(msg);
                pw.flush();
            }
        }
    }
}