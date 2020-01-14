package model;

public class Chessboard {
	
	public Chess[][][][] chesses = new Chess[4][4][4][4];
	public Chess[][] largeChesses = new Chess[4][4];
	public Player winner;
	public int currentRow = 2;                  // 当前小棋盘的行号
	public int currentCol = 2;                  // 当前小棋盘的列号
	public boolean limit = true;                // 是否限制区域
	public boolean isCircle = true;
	
    private final int rows = 3;                 // 大棋盘的行数
    private final int columns = 3;              // 大棋盘的列数
	
	public Chessboard() {}
	
	// 向棋盘中添加棋子
	public void addChess(Position position) {
		Chess chess;
		if (position == null) return;
		int row = position.row;
		int col = position.col;
		int subRow = position.subRow;
		int subCol = position.subCol;
		// 检查是否落在规定小棋盘中
		if (limit) {
			if (currentRow != row) return;
			if (currentCol != col) return;
		}
		// 检测小棋盘是否已经决出胜负
		if (largeChesses[row][col] != null) return;
		// 检测格中是否已经有棋子
		if (chesses[row][col][subRow][subCol] != null) return;
    	// 添加圈圈到棋盘
    	if (isCircle) {
    		chess = new Chess(position, Player.CIRCLE);
    		chesses[row][col][subRow][subCol] = chess;
    		isCircle = !isCircle;
    	}
    	// 添加叉叉到棋盘
    	else {
    		chess = new Chess(position, Player.CROSS);
    		chesses[row][col][subRow][subCol] = chess;
    		isCircle = !isCircle;
    	}
    	// 更新下一步的小棋盘
    	currentRow = subRow;
    	currentCol = subCol;
	}
	
	// 向棋盘中添加巨型棋子
	// 清空对应位置的所有小棋子
	public void addLargeChess(int row, int col, Player player) {
		Chess chess = new Chess(new Position(row, col), player);
		largeChesses[row][col] = chess;
		for (int ii = 1; ii <= rows; ii++)
            for (int jj = 1; jj <= columns; jj++) {
            	chesses[row][col][ii][jj] = null;
			}
	}
	
	// 判断小棋盘是否决出胜负
	// 若已决出胜负，用巨型棋子取代整个小棋盘
	public void updateSubChessboard(int row, int col) {
		// 检查行
		for (int ii = 1; ii <= rows; ii++) {
			if (checkSame(chesses[row][col][ii][1], chesses[row][col][ii][2], chesses[row][col][ii][3])) {
				addLargeChess(row, col, chesses[row][col][ii][1].player);
				return;
			}
		}
		// 检查列
		for (int jj = 1; jj <= columns; jj++) {
			if (checkSame(chesses[row][col][1][jj], chesses[row][col][2][jj], chesses[row][col][3][jj])) {
				addLargeChess(row, col, chesses[row][col][1][jj].player);
				return;
			}
		}
		// 检查对角线
		if (checkSame(chesses[row][col][1][1], chesses[row][col][2][2], chesses[row][col][3][3])) {
			addLargeChess(row, col, chesses[row][col][2][2].player);
			return;
		}
		if (checkSame(chesses[row][col][1][3], chesses[row][col][2][2], chesses[row][col][3][1])) {
			addLargeChess(row, col, chesses[row][col][2][2].player);
			return;
		}
	}
	
	// 判断获胜条件是否满足，更新游戏状态
	public boolean updateGameStatus() {
		// 更新限制条件
		if (largeChesses[currentRow][currentCol] == null) {
			limit = true;
		} 
		else {
			limit = false;
		}
		// 检查行
		for (int i = 1; i <= rows; i++) {
			if (checkSame(largeChesses[i][1], largeChesses[i][2], largeChesses[i][3])) {
				winner = largeChesses[i][1].player;
				return true;
			}
		}
		// 检查列
		for (int j = 1; j <= columns; j++) {
			if (checkSame(largeChesses[1][j], largeChesses[2][j], largeChesses[3][j])) {
				winner = largeChesses[1][j].player;
				return true;
			}
		}
		// 检查对角线
		if (checkSame(largeChesses[1][1], largeChesses[2][2], largeChesses[3][3])) {
			winner = largeChesses[2][2].player;
			return true;
		}
		if (checkSame(largeChesses[1][3], largeChesses[2][2], largeChesses[3][1])) {
			winner = largeChesses[2][2].player;
			return true;
		}
		return false;
	}
	
	// 检查三颗棋子是否相同
	private boolean checkSame(Chess a, Chess b, Chess c) {
		if (a == null) return false;
		if (b == null) return false;
		if (c == null) return false;
		return (a.player == b.player) && (b.player == c.player);
	}

}
