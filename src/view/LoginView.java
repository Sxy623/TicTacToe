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
    	
    	// Set the layout
    	setLayout(null);

    	//  Add components
    	addComponents();
    }

    private void addComponents() {

        // Image Icon
//        ImageIcon iconImage = new ImageIcon(Utility.iconPath);
//        iconImage.setImage(iconImage.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
//        iconLabel = new JLabel(iconImage);
//        iconLabel.setBounds(325, 100, 150, 150);
//        add(iconLabel);

        // User Label
        userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
        userLabel.setBounds(200, 300, 170, 50);
        add(userLabel);

        // Password Label
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
        passwordLabel.setBounds(200, 350, 170, 50);
        add(passwordLabel);

        // User Text Field
        userTextField = new JTextField();
        userTextField.setBounds(380, 310, 220, 30);
        add(userTextField);

        // Password Text Field
        passwordField = new JPasswordField();
        passwordField.setBounds(380, 360, 220, 30);
        add(passwordField);

        // Login Button
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
                Main.changeView(new GameView());
                break;
        }
    }
}
