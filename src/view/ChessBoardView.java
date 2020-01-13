package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessBoardView extends JPanel {

	private static final long serialVersionUID = 1L;
	
    private final int space = 30;               // 间距
    private final int gridSize = 50;            // 网格边长
    private final int itemSize = 40;            // 圈和叉的边长
    private final int subChessBoardSize = 160;  // 3*3小棋盘边长
    private final int stroke = 5;               // 画笔粗细
    private final int offset = 5;               // 圈和叉在网格中的偏移量
    private final int row = 3;                  // 大棋盘的行数
    private final int column = 3;               // 大棋盘的列数
    

	public ChessBoardView() {
		
		setBackground(Color.green);
		
		// 添加鼠标监听事件
		addMouseEvent();
	}

    private void addMouseEvent() {
    	
    	// 添加鼠标点击事件
        MouseAdapter onPressed = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                System.out.println("Mouse clicked at (" + e.getX() + ", " + e.getY() + ").");
                
            }
        };
        addMouseListener(onPressed);
    }
    
    private void drawCircle(int x, int y, Graphics2D g2) {
    	// 设置颜色和粗细
    	g2.setColor(Color.red);
    	g2.setStroke(new BasicStroke(stroke));
    	// 抗锯齿
    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
    	// 绘制圆形
    	g2.drawOval(x + offset, y + offset, itemSize, itemSize);
	}
    
    private void drawCross(int x, int y, Graphics2D g2) {
    	// 设置颜色和粗细
    	g2.setColor(Color.yellow);
    	g2.setStroke(new BasicStroke(stroke));
    	// 抗锯齿
    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
    	// 绘制叉
    	g2.drawLine(x + offset, y + offset, x + offset + itemSize, y + offset + itemSize);
    	g2.drawLine(x + offset + itemSize, y + offset, x + offset, y + offset + itemSize);
	}
    
    private void drawSubChessBoard(int x, int y, Graphics2D g2) {
    	// 设置颜色和粗细
    	g2.setColor(Color.black);
    	g2.setStroke(new BasicStroke(stroke));
    	// 抗锯齿
    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
    	// 绘制3*3棋盘格
    	g2.drawLine(x + gridSize, y, x + gridSize, y + 3 * gridSize);
    	g2.drawLine(x + 2 * gridSize + stroke, y, x + 2 * gridSize + stroke, y + 3 * gridSize);
    	g2.drawLine(x, y + gridSize, x + 3 * gridSize, y + gridSize);
    	g2.drawLine(x, y + 2 * gridSize + stroke, x + 3 * gridSize, y + 2 * gridSize + stroke);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        
        int x = initalXOfGrid(1, 1, 1, 1);
        int y = initalYOfGrid(1, 1, 1, 1);
        drawCircle(x, y, g2);
        
        int x2 = initalXOfGrid(1, 2, 1, 1);
        int y2 = initalYOfGrid(1, 2, 1, 1);
        drawCross(x2, y2, g2);
        
        for (int i = 1; i <= row; i++) {
        	for (int j = 1; j <= column; j++) {
        		drawSubChessBoard(i * space + (i - 1) * subChessBoardSize, j * space + (j - 1) * subChessBoardSize, g2);
        	}
        }
    }
    
    private int initalXOfGrid(int row, int col, int subRow, int subCol) {
    	return col * space + (col - 1) * subChessBoardSize + (subCol - 1) * (gridSize + stroke);
    }
    
    private int initalYOfGrid(int row, int col, int subRow, int subCol) {
    	return row * space + (row - 1) * subChessBoardSize + (subRow - 1) * (gridSize + stroke);
    }
    
}