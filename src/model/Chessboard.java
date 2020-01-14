package model;

import java.util.ArrayList;
import java.util.List;

public class Chessboard {
	
	public List<Chess> list = new ArrayList<Chess>();
	
	private Chess[][][][] chesses = new Chess[4][4][4][4];
	private boolean isCircle = true;
	
	public Chessboard() {
		addChess(new Position(1, 1, 1, 1));
		addChess(new Position(2, 2, 2, 3));
	}
	
	// 向棋盘中添加棋子
	public void addChess(Position position) {
		Chess chess;
		if (position == null) return;
		int row = position.row;
		int col = position.col;
		int subRow = position.subRow;
		int subCol = position.subCol;
		// 检测格中是否已经有棋子
		if (chesses[row][col][subRow][subCol] != null) return;
    	// 添加圈圈到棋盘
    	if (isCircle) {
    		chess = new Chess(position, ChessType.CIRCLE);
    		list.add(chess);
    		chesses[row][col][subRow][subCol] = chess;
    		isCircle = !isCircle;
    	}
    	// 添加叉叉到棋盘
    	else {
    		chess = new Chess(position, ChessType.CROSS);
    		list.add(chess);
    		chesses[row][col][subRow][subCol] = chess;
    		isCircle = !isCircle;
    	}
	}

}
