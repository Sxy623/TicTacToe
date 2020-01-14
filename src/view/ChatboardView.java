package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatboardView extends JPanel {

    private static final long serialVersionUID = 1L;
    
    public ChatboardView() {
		
    	setLayout(new BorderLayout());
    	setBorder(BorderFactory.createEtchedBorder());
        
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);  // 自动换行
        textArea.setEditable(false);  // 不可编辑
        add(new JScrollPane(textArea), BorderLayout.CENTER);  // 滚动条
        
        JTextField textField = new JTextField();
        KeyAdapter keyPressed = new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		super.keyPressed(e);
        		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        			String s = textField.getText();
        			if (s.isEmpty()) return;  // 不允许发送空消息
        			textArea.append(s + "\n");
        			textField.setText("");
        		}
        	}
		};
        textField.addKeyListener(keyPressed);
        add(textField, BorderLayout.SOUTH);
	}

}
