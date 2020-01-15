package client;

import java.io.*;
import java.net.*;
import javax.swing.*;
import model.*;
import utility.*;

public class Client {
	
    private Socket socket;

    private BufferedReader reader;
    private PrintWriter writer;

    private String userName;
    private String opponentName;

    public Client(String userName) {
        this.userName = userName;
        try {
            socket = new Socket(Utility.IP, Utility.PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("0#" + userName);  // 将用户名告知服务器
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
                        // 对方发起挑战
                        else if (message[0].equals("2")) {
                        	int answer = JOptionPane.showConfirmDialog(User.chessboardView, "是否接受来自 " + message[1] + " 的对战请求？", "对战请求", JOptionPane.YES_NO_OPTION);
                        	setOpponent(message[1]);
                        	// 同意对方
                        	if (answer == JOptionPane.YES_OPTION) {
                        		User.player = Player.CROSS;
                        		User.chessboardView.repaint();
                        		sendMessage("0", 3);
                        	}
                        	// 拒绝对方
                        	else if (answer == JOptionPane.NO_OPTION) {
                        		sendMessage("1", 3);
                        	}
                        }
                        // 对方回应挑战
                        else if (message[0].equals("3")) {
                        	// 对方同意
                        	if (message[3].equals("0")) {
                        		User.player = Player.CIRCLE;
                        		JOptionPane.showMessageDialog(User.chessboardView, "对方接受，挑战开始！");
                        		User.chessboardView.repaint();
                        	}
                        	// 对方拒绝
                        	else if (message[3].equals("1")) {
                        		JOptionPane.showMessageDialog(User.chessboardView, "对方拒绝！");
                        	}
                        }
                        // 对方下棋
                        else if (message[0].equals("4")) {
                        	int row = Integer.parseInt(message[3]);
                        	int col = Integer.parseInt(message[4]);
                        	int subRow = Integer.parseInt(message[5]);
                        	int subCol = Integer.parseInt(message[6]);
                        	User.chessboardView.chessboard.addChess(new Position(row, col, subRow, subCol));
                        	User.chessboardView.chessboard.updateSubChessboard(row, col);
                        	User.chessboardView.gameOver = User.chessboardView.chessboard.updateGameStatus();
                        	User.chessboardView.repaint();
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

    public void setOpponent(String name) {
        this.opponentName = name;
    }
}
