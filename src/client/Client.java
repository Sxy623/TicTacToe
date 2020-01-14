package client;

import java.io.*;
import java.net.*;
import utility.*;

public class Client {
	
    private Socket socket;

    private BufferedReader reader;
    private PrintWriter writer;

    private String userName;
    private String opponentName;

    public Client(String userID) {
        this.userName = userID;
        try {
            socket = new Socket(Utility.IP, Utility.PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("0#" + userID);  // 告知服务器本线程的用户ID
            writer.flush();
            receiveMessage();   // 创建接受消息线程
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // 客户端接受信息
    public void receiveMessage() {
        Runnable runnable = new Runnable() {
            String messages;

            @Override
            public void run() {
                while (true) {
                    try {
                        if (!((messages = reader.readLine()) != null)) break;
                        System.out.println("Receive message: " + messages);
                        String[] message = messages.split("#");
                        // 聊天室消息
                        if (message[0].equals("1")) {
                        	User.chatboardView.appendSting(message[1] + ": ", User.player.opponentColorString());
                        	User.chatboardView.appendSting(message[3] + "\n");
                        }
                        // 发起挑战
                        else if (message[0].equals("2")) {
                        	
                        }
                        // 接受挑战
                        else if (message[0].equals("3")) {
                        	
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        // 创建线程接受消息
        new Thread(runnable).start();
    }

    public void sendMessage(String content, int type) {
        String message = type + "#" + userName + "#" + opponentName + "#" + content;
        writer.println(message);  // 发送信息给另一个客户端
        writer.flush();
    }

    public void setOppentID(String oppentID) {
        this.opponentName = oppentID;
    }
}
