package main;

import javax.swing.*;
import view.*;

public class Main {
	
	private static JFrame mainFrame;
	private static JPanel currentPanel;
	
	public static void main(String[] args) {
		
		// Create the window
		mainFrame = new JFrame();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set title and size
		mainFrame.setTitle("Tic Tac Toe");
		mainFrame.setBounds(150, 100, 800, 600);
		
		// Show the login view
		currentPanel = new LoginView();
		mainFrame.add(currentPanel);
	}
	
	public static void changeView(JPanel panel) {
		mainFrame.remove(currentPanel);
		currentPanel = panel;
        mainFrame.add(currentPanel);
        mainFrame.setVisible(true);
	}
	
}
