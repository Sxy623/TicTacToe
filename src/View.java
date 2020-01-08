import java.awt.*;
import javax.swing.*;

public class View extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public View() {
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		// Set title and size
		setTitle("Tic Tac Toe");
		setBounds(150, 100, 800, 600);
		setLayout(new BorderLayout());
		
		// Add chess board
		JPanel chessBoard = new JPanel();
		chessBoard.setBackground(Color.red);
		add(chessBoard, BorderLayout.CENTER);
		
		// Add chat board
		JPanel chatBoard = new JPanel();
		chatBoard.setPreferredSize(new Dimension(200, 600));
		chatBoard.setBackground(Color.blue);
		add(chatBoard, BorderLayout.EAST);
	}
}
