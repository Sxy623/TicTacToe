package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

import client.User;

public class ChatboardView extends JPanel {

    private static final long serialVersionUID = 1L;
    
    JTextPane textPane = new JTextPane();
    JTextField textField = new JTextField();
    
    public ChatboardView() {
		
    	setLayout(new BorderLayout());
    	// 边框
    	setBorder(BorderFactory.createEtchedBorder());
        
        textPane.setEditable(false);  // 不可编辑
        // 滚动条
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  // 禁止水平滚动条
        add(scrollPane, BorderLayout.CENTER);  
        
        Style normal = textPane.getStyledDocument().addStyle(null, null);
        Style redStyle = textPane.addStyle("red", normal);
        Style blueStyle = textPane.addStyle("blue", normal);
        StyleConstants.setForeground(redStyle, Color.RED);
        StyleConstants.setForeground(blueStyle, Color.BLUE);
        
        KeyAdapter keyPressed = new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		super.keyPressed(e);
        		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        			String s = textField.getText();
        			if (s.isEmpty()) return;  // 不允许发送空消息
        			appendSting(User.userName + ": ", User.player.colorString());
        			appendSting(s + "\n");
        			User.client.sendMessage(s, 1);
        			textField.setText("");
        		}
        	}
		};
        textField.addKeyListener(keyPressed);
        add(textField, BorderLayout.SOUTH);
	}
    
    // 添加普通样式的文字
    public void appendSting(String str) {
    	try {
            textPane.getDocument().insertString(textPane.getDocument().getLength(), str, textPane.getStyle("normal"));
            textPane.setCaretPosition(textPane.getDocument().getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    
    // 添加特殊样式的文字
    public void appendSting(String str, String color) {
    	try {
            textPane.getDocument().insertString(textPane.getDocument().getLength(), str, textPane.getStyle(color));
            textPane.setCaretPosition(textPane.getDocument().getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

}
