package main;

import javax.swing.*;
import view.*;

public class Main {
	
	private static JFrame mainFrame;
	private static JPanel currentPanel;
	
	public static void main(String[] args) {
		
		// 创建窗口
		mainFrame = new JFrame();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 设置标题和尺寸
		mainFrame.setTitle("Tic Tac Toe");
		mainFrame.setBounds(150, 100, 800, 630);
		
		// 显示登陆界面
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
