package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import client.*;
import main.*;

public class LoginView extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
    public JLabel iconLabel;
    public JLabel userLabel;
    public JLabel passwordLabel;
    public JTextField userTextField;
    public JPasswordField passwordField;
    public JButton loginButton;

    public LoginView() {
    	
    	// 布局
    	setLayout(null);

    	// 添加组件
    	addComponents();
    }

    private void addComponents() {

        // 游戏图标
//        ImageIcon iconImage = new ImageIcon(Utility.iconPath);
//        iconImage.setImage(iconImage.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
//        iconLabel = new JLabel(iconImage);
//        iconLabel.setBounds(325, 100, 150, 150);
//        add(iconLabel);

        // 用户名标签
        userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
        userLabel.setHorizontalAlignment(JLabel.CENTER);
        userLabel.setBounds(200, 300, 170, 50);
        add(userLabel);

        // 密码标签
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel.setBounds(200, 350, 170, 50);
        add(passwordLabel);

        // 用户名文本框
        userTextField = new JTextField();
        userTextField.setBounds(380, 310, 220, 30);
        add(userTextField);

        // 密码文本框
        passwordField = new JPasswordField();
        passwordField.setBounds(380, 360, 220, 30);
        add(passwordField);

        // 登陆按钮
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        loginButton.setBounds(350, 420, 100, 40);
        loginButton.setActionCommand("loginButton");
        loginButton.addActionListener(this);
        add(loginButton);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "loginButton":
            	System.out.println("Login button pressed.");
            	User.userName = userTextField.getText();
            	User.createClient();
            	User.frame.setTitle("Tic Tac Toe - " + User.userName);
                Main.changeView(new GameView());
                break;
        }
    }
}
