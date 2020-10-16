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

	protected boolean errorCheck(int newRow, int newCol) {
		if(newRow > 7 || newCol > 7 || newRow < 0 || newCol < 0) {
			return true;
		}
		if(newRow == this.row() && newCol == this.col()) {
			return true;
		}
		return false;
	}
	
	protected boolean diagCheck(int newRow, int newCol, Board board) {
		if(Math.abs(newRow-this.row()) == Math.abs(newCol - this.col())) {
			int rowInc = 1;
			int colInc = 1;
			if(newRow<this.row) {
				rowInc = -1;
			}
			if(newCol<this.col) {
				colInc = -1;
			}
			int Row = this.row+rowInc;
			int Col = this.col+colInc;
			while(Row!=newRow && Col!=newCol) {
				if(board.board[Row][Col] != null) {
					return false;
				}
				Row += rowInc;
				Col += colInc;
			}
			if(board.board[Row][Col]==null || board.board[Row][Col].isWhite != this.isWhite) {
				Board editedBoard = board.copy();
				editedBoard.board[this.row][this.col] = null;
				editedBoard.board[newRow][newCol] = this.copy();
				editedBoard.board[newRow][newCol].row = newRow;
				editedBoard.board[newRow][newCol].col = newCol;
				if(editedBoard.kingChecked(this.isWhite)) {
					return false;
				}
				this.row = newRow;
				this.col = newCol;
				board = editedBoard;
				return true;
			}
		}
		return false;
	}
	
	protected boolean vertCheck(int newRow, int newCol, Board board) {
		if(newCol != this.col) {
			return false;
		}
		int inc = 1;
		if(newRow < this.row) {
			inc = -1;
		}
		int Row = this.row + inc;
		while(newRow != Row) {
			if(board.board[Row][newCol]!=null) {
				return false;
			}
			Row += inc;
		}
		if(board.board[newRow][newCol]==null || board.board[newRow][newCol].isWhite != this.isWhite) {
			Board editedBoard = board.copy();
			editedBoard.board[this.row][this.col] = null;
			editedBoard.board[newRow][newCol] = this.copy();
			editedBoard.board[newRow][newCol].row = newRow;
			editedBoard.board[newRow][newCol].col = newCol;
			if(editedBoard.kingChecked(this.isWhite)) {
				return false;
			}
			this.row = newRow;
			this.col = newCol;
			board = editedBoard;
			return true;
		}
		return false;
	}
	
	protected boolean horizCheck(int newRow, int newCol, Board board) {
		if(newRow != this.row) {
			return false;
		}
		int inc = 1;
		if(newCol < this.col) {
			inc = -1;
		}
		int Col = this.col + inc;
		while(newCol != Col) {
			if(board.board[newRow][Col]!=null) {
				return false;
			}
			Col += inc;
		}
		if(board.board[newRow][newCol]==null || board.board[newRow][newCol].isWhite != this.isWhite) {
			Board editedBoard = board.copy();
			editedBoard.board[this.row][this.col] = null;
			editedBoard.board[newRow][newCol] = this.copy();
			editedBoard.board[newRow][newCol].row = newRow;
			editedBoard.board[newRow][newCol].col = newCol;
			if(editedBoard.kingChecked(this.isWhite)) {
				return false;
			}
			this.row = newRow;
			this.col = newCol;
			board = editedBoard;
			return true;
		}
		return false;
	}
	
	public abstract boolean checkMove(int newRow, int newCol, Board board);
	
	public abstract ChessPiece copy();
	
	public String toString() {
		if(this.isWhite) {
			return "w"+this.type;
		}
		return "b"+this.type;
	}
}
