package view;
import java.awt.*;
import javax.swing.*;

public class GameView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public GameView() {
		
		// Set the layout
		setLayout(new BorderLayout());
		
		// Add chess board
		JPanel chessBoard = new ChessBoardView();
		add(chessBoard, BorderLayout.CENTER);
		
		// Add chat board
		JPanel chatBoard = new JPanel();
		chatBoard.setPreferredSize(new Dimension(200, 600));
		chatBoard.setBackground(Color.blue);
		add(chatBoard, BorderLayout.EAST);
	}
}
