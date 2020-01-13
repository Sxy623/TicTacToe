import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
        userLabel = new JLabel("User");
        userLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
        userLabel.setBounds(300, 300, 70, 50);
        add(userLabel);

        // Password Label
        passwordLabel = new JLabel("Key");
        passwordLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
        passwordLabel.setBounds(300, 350, 70, 50);
        add(passwordLabel);

        // User Text Field
        userTextField = new JTextField();
        userTextField.setBounds(380, 310, 130, 30);
        add(userTextField);

        // Password Text Field
        passwordField = new JPasswordField();
        passwordField.setBounds(380, 360, 130, 30);
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
                Main.changeView(new GameView());
                break;
        }
    }
}
