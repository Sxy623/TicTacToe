package server;

import java.io.*;
import java.net.*;
import java.util.*;
import utility.*;

public class Server {
    private ServerSocket listener;
    private static HashMap<String, Socket> clientMap = new HashMap<>();

    public Server() {
        try {
        	// 启动服务器
            listener = new ServerSocket(Utility.PORT);
            System.out.println("Server socket have been created.");
            Socket socket = null;
            while (true) {
                socket = listener.accept();
                System.out.println("Socket accept.");
                ServerThread thread = new ServerThread(socket);
                System.out.println("Client host address: " + socket.getInetAddress().getHostAddress());
                thread.start();
            }
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
		new Server();
	}

    public static void putSocket(String ID, Socket s) {
        clientMap.put(ID, s);
    }

    public static Socket getSocket(String ID) {
        return clientMap.get(ID);
    }
}

class ServerThread extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private String message;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
                String[] messages = message.split("#");
                if (messages[0].equals("0")) {
                	// 记录客户端
                    Server.putSocket(messages[1], socket);
                }
                else {
                    // 转发消息
                    Socket destination = Server.getSocket(messages[2]);
                    if (destination != null)
                        sendMessage(message, destination);
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String messages, Socket destination) throws IOException {
        PrintWriter writer = new PrintWriter(destination.getOutputStream(), true);
        writer.println(messages);
        writer.flush();
    }
}
