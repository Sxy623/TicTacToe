package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.*;

public class ChessboardView extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private final int rows = 3;                 // 大棋盘的行数
    private final int columns = 3;              // 大棋盘的列数
    private final int subChessBoardSize = 160;  // 小棋盘边长
    private final int space = 30;               // 小棋盘间距
    private final int gridSize = 50;            // 网格边长
    private final int itemSize = 40;            // 圈圈和叉叉的边长
    private final int stroke = 5;               // 画笔粗细
    private final int offset = 2;               // 圈和叉在网格中的偏移量
    
    private Chessboard chessboard = new Chessboard();

    public ChessboardView() {
        
        setBackground(Color.white);
        
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
                Position position = calPosition(e.getX(), e.getY());
                if (position != null) {
                	chessboard.addChess(position);
                }
                repaint();
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
        // 绘制圈圈
        g2.drawOval(x + offset, y + offset, itemSize, itemSize);
    }
    
    private void drawCross(int x, int y, Graphics2D g2) {
        // 设置颜色和粗细
        g2.setColor(Color.blue);
        g2.setStroke(new BasicStroke(stroke));
        // 抗锯齿
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
        // 绘制叉叉
        g2.drawLine(x + offset, y + offset, x + offset + itemSize, y + offset + itemSize);
        g2.drawLine(x + offset + itemSize, y + offset, x + offset, y + offset + itemSize);
    }
    
    private void drawCircle(Position position, Graphics2D g2) {
        int x = initalXOfGrid(position);
        int y = initalYOfGrid(position);
        drawCircle(x, y, g2);
    }
    
    private void drawCross(Position position, Graphics2D g2) {
        int x = initalXOfGrid(position);
        int y = initalYOfGrid(position);
        drawCross(x, y, g2);
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
    
    private void drawChess(Chessboard chessboard, Graphics2D g2) {
    	for (Chess chess : chessboard.list) {
        	// 绘制圈圈
        	if (chess.type == ChessType.CIRCLE) {
        		drawCircle(chess.position, g2);
        	}
        	// 绘制叉叉
        	else {
        		drawCross(chess.position, g2);
			}
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                drawSubChessBoard(i * space + (i - 1) * subChessBoardSize, j * space + (j - 1) * subChessBoardSize, g2);
            }
        }
        
//        int x = initalXOfGrid(1, 1, 1, 1);
//        int y = initalYOfGrid(1, 1, 1, 1);
//        drawCircle(x, y, g2);
//        
//        int x2 = initalXOfGrid(1, 2, 1, 1);
//        int y2 = initalYOfGrid(1, 2, 1, 1);
//        drawCross(x2, y2, g2);
        
        drawChess(chessboard, g2);
    }
    
    private int initalXOfGrid(int row, int col, int subRow, int subCol) {
        return col * space + (col - 1) * subChessBoardSize + (subCol - 1) * (gridSize + stroke);
    }
    
    private int initalYOfGrid(int row, int col, int subRow, int subCol) {
        return row * space + (row - 1) * subChessBoardSize + (subRow - 1) * (gridSize + stroke);
    }
    
    private int initalXOfGrid(Position pos) {
        int col = pos.col;
        int subCol = pos.subCol;
        return col * space + (col - 1) * subChessBoardSize + (subCol - 1) * (gridSize + stroke);
    }
    
    private int initalYOfGrid(Position pos) {
        int row = pos.row;
        int subRow = pos.subRow;
        return row * space + (row - 1) * subChessBoardSize + (subRow - 1) * (gridSize + stroke);
    }
    
    private Position calPosition(int x, int y) {
        int row = 0, col = 0, subRow = 0, subCol = 0;
        for (int i = 1; i <= rows; i++)
            for (int j = 1; j <= columns; j++)
                for (int ii = 1; ii <= rows; ii++)
                    for (int jj = 1; jj <= columns; jj++)
                        if (x >= initalXOfGrid(i, j, ii, jj) &&  x <= initalXOfGrid(i, j, ii, jj) + gridSize &&
                            y >= initalYOfGrid(i, j, ii, jj) &&  y <= initalYOfGrid(i, j, ii, jj) + gridSize) {
                            row = i;
                            col = j;
                            subRow = ii;
                            subCol = jj;
                            return new Position(row, col, subRow, subCol);
                        }
        return null;
    }
    
}