package view;

import java.awt.*;
import javax.swing.*;
import client.*;

public class GameView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public GameView() {
		
		// Set the layout
		setLayout(new BorderLayout());
		
		// Add chessboard
		ChessboardView chessboardPanel = new ChessboardView();
		add(chessboardPanel, BorderLayout.CENTER);
		User.chessboardView = chessboardPanel;
		
		// Add chatboard
		ChatboardView chatboardPanel = new ChatboardView();
		chatboardPanel.setPreferredSize(new Dimension(200, 600));
		add(chatboardPanel, BorderLayout.EAST);
		User.chatboardView = chatboardPanel;
	}
}
