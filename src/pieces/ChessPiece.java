package pieces;

import chess.Board;

public abstract class ChessPiece {
	private int row;
	private int col;
	private boolean isWhite;
	private char type;
	
	public ChessPiece(int row, int col, boolean isWhite) {
		this.isWhite = isWhite;
		this.row = row;
		this.col = col;
	}
	
	public int row() {
		return this.row;
	}
	
	public int col() {
		return this.col;
	}
	
	public boolean isWhite() {
		return this.isWhite;
	}
	
	public char type() {
		return this.type;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	protected void setType(char type) {
		this.type = type;
	}
	
	protected boolean errorCheck(int newRow, int newCol) {
		if(newRow > 7 || newCol > 7 || newRow < 0 || newCol < 0) {
			return true;
		}
		if(newRow == this.row() && newCol == this.col()) {
			return true;
		}
		return false;
	}
	
	public boolean diagCheck(int newRow, int newCol, Board board) {
		if(Math.abs(newRow-this.row()) == Math.abs(newRow - newCol)) {
			int rowInc = 1;
			int colInc = 1;
			if(newRow<this.row()) {
				rowInc = -1;
			}
			if(newCol<this.col()) {
				colInc = -1;
			}
			newRow += rowInc;
			newCol += colInc;
			while(newRow!=this.row() && newCol!=this.col()) {
				if(board.board[newRow][newCol] != null) {
					return false;
				}
				newRow += rowInc;
				newCol += colInc;
			}
			return true;
		}
		return false;
	}
	
	public abstract boolean checkMove(int newRow, int newCol, Board board);
	
	public String toString() {
		if(this.isWhite) {
			return "w"+this.type;
		}
		return "b"+this.type;
	}
}
