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
	 * Checks if input coordinate is valid
	 * @param newRow	the row of the new coordinate
	 * @param newCol	the column of the new coordinate
	 */
	protected boolean errorCheck(int newRow, int newCol) {
		if(newRow > 8 || newCol > 8 || newRow < 1 || newCol < 1) {
			return true;
		}
		if(newRow == this.row() && newCol == this.col()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if input coordinate is a valid diagonal move
	 * @param newRow	the row of the new coordinate
	 * @param newCol	the column of the new coordinate
	 * @param board		the chess board
	 * @return 			true if valid, false if not	
	 */
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
				return true;
				//return !testDupMove(newRow,newCol,board);
			}
		}
		return false;
	}
	
	/**
	 * Checks if input coordinate is a valid vertical move
	 * @param newRow	the row of the new coordinate
	 * @param newCol	the column of the new coordinate
	 * @param board		the chess board
	 * @return 			true if valid, false if not	
	 */
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
			return true;
			//return !testDupMove(newRow,newCol,board);
		}
		return false;
	}
	
	/**
	 * Checks if input coordinate is a valid horizontal move
	 * @param newRow	the row of the new coordinate
	 * @param newCol	the column of the new coordinate
	 * @param board		the chess board
	 * @return 			true if valid, false if not	
	 */
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
			return true;
			//return !testDupMove(newRow,newCol,board);
		}
		return false;
	}
	
	/**
	 * Checks if a move results in the king being checked
	 * @param newRow	the row of the new coordinate
	 * @param newCol	the column of the new coordinate
	 * @param board		the chess board
	 * @return 			true if king is checked, false if not	
	 */
	protected boolean testDupMove(int newRow, int newCol, Board board) {
		Board editedBoard = board.copy();
		editedBoard.board[this.row()][this.col()] = null;
		editedBoard.board[newRow][newCol] = this.copy();
		editedBoard.board[newRow][newCol].setRow(newRow);
		editedBoard.board[newRow][newCol].setCol(newCol);
		return board.kingChecked(this.isWhite());
	}
	
	/**
	 * Checks if a legal move exists, by parsing through the entire board
	 * @param board		the chess board
	 * @return 			true if a legal move exists
	 */
	public boolean existsLegalMove(Board board) {
		for(int i = 1;i<9;i++) {
			for(int j = 1;j<9;j++) {
				if(this.checkMove(i, j, board)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if input coordinate is a valid chess move
	 * @param newRow	the row of the new coordinate
	 * @param newCol	the column of the new coordinate
	 * @param board		the chess board
	 * @return 			true if valid, false if not	
	 */
	public abstract boolean checkMove(int newRow, int newCol, Board board);
	
	public abstract boolean canAttack(int newRow, int newCol, Board board);
	
	/**
	 * Returns a copy of the chess piece.
	 */
	public abstract ChessPiece copy();
	
	/**
	 * Returns the string representation of the piece
	 */
	public String toString() {
		if(this.isWhite) {
			return "w"+this.type;
		}
		return "b"+this.type;
	}
}
