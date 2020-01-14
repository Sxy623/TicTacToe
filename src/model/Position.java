package model;

public class Position {
	
	public int row;
	public int col;
	public int subRow;
	public int subCol;
	
	public Position() {}
	
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
		this.subRow = 0;
		this.subCol = 0;
	}
	
	public Position(int row, int col, int subRow, int subCol) {
		this.row = row;
		this.col = col;
		this.subRow = subRow;
		this.subCol = subCol;
	}
	
}
