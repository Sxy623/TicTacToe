package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import client.*;

public class GameView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public GameView() {
		
		// 布局
		setLayout(new BorderLayout());
		
		// 菜单栏
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("开始");
		JMenuItem item = new JMenuItem("发起对战");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String opponentName = JOptionPane.showInputDialog("想挑战的用户", "发起对战");
				User.client.setOpponent(opponentName);
				User.client.sendMessage("", 2);
			}
		});
		menu.add(item);
		menuBar.add(menu);
		add(menuBar, BorderLayout.NORTH);
		
		// 棋盘
		ChessboardView chessboardPanel = new ChessboardView();
		add(chessboardPanel, BorderLayout.CENTER);
		User.chessboardView = chessboardPanel;
		
		// 聊天室
		ChatboardView chatboardPanel = new ChatboardView();
		chatboardPanel.setPreferredSize(new Dimension(200, 600));
		add(chatboardPanel, BorderLayout.EAST);
		User.chatboardView = chatboardPanel;
	}
}
