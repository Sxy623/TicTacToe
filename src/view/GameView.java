package view;
import java.awt.*;
import javax.swing.*;

public class GameView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public GameView() {
		
		// Set the layout
		setLayout(new BorderLayout());
		
		// Add chessboard
		JPanel chessboardPanel = new ChessboardView();
		add(chessboardPanel, BorderLayout.CENTER);
		
		// Add chatboard
		JPanel chatboardPanel = new ChatboardView();
		chatboardPanel.setPreferredSize(new Dimension(200, 600));
		add(chatboardPanel, BorderLayout.EAST);
	}
}
