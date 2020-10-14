package pieces;

import chess.Board;
/**
 * 
 * 
 * @author Jonathan Wong, Shreyas Heragu
 * Abstract class to fulfill role of different chess pieces.
 *
 */
public abstract class ChessPiece {
	/**
	 * Keeps track of piece's row
	 */
	private int row;
	/**
	 * Keeps track of piece's column
	 */
	private int col;
	/**
	 * Keeps track of piece's team color
	 */
	private boolean isWhite;
	/**
	 * Keeps track of piece's type (King, Queen, Bishop, Knight, Rook, Pawn)
	 */
	private char type;
	/**
	 * Constructor to initialize row, column and team
	 * @param row	the row of the piece
	 * @param col	the column of the piece
	 * @param isWhite	the piece's team
	 */
	public ChessPiece(int row, int col, boolean isWhite) {
		this.isWhite = isWhite;
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Gets row
	 * @return this.row the piece's row
	 */
	public int row() {
		return this.row;
	}
	/**
	 * Gets column
	 */
	public int col() {
		return this.col;
	}
	/**
	 * Gets team
	 */
	public boolean isWhite() {
		return this.isWhite;
	}
	/**
	 * Gets piece type
	 */
	public char type() {
		return this.type;
	}
	/**
	 * Sets row
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * Sets column
	 */
	public void setCol(int col) {
		this.col = col;
	}
	/**
	 * Sets type
	 */
	protected void setType(char type) {
		this.type = type;
	}
	/**
	 * Sets row
	 */
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
