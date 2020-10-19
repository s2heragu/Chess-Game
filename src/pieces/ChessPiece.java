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
	 * Keeps track of whether the piece has moved
	 */
	private boolean hasMoved;
	
	/**
	 * Constructor to initialize row, column and team
	 * @param row		the row of the piece
	 * @param col		the column of the piece
	 * @param isWhite	the piece's team
	 */
	public ChessPiece(int row, int col, boolean isWhite) {
		//type is determined only in subclasses
		this.isWhite = isWhite;
		this.row = row;
		this.col = col;
		this.hasMoved = false;
	}
	
	/**
	 * Gets row
	 * @return this.row		the piece's row
	 */
	public int row() {
		return this.row;
	}
	
	/**
	 * Gets column
	 * @return this.col		the piece's column
	 */
	public int col() {
		return this.col;
	}
	
	/**
	 * Gets team
	 * @return this.isWhite		the piece's team
	 */
	public boolean isWhite() {
		return this.isWhite;
	}
	
	/**
	 * Gets type
	 * @return this.type	the piece's type
	 */
	public char type() {
		return this.type;
	}
	
	/**
	 * Gets hasMoved
	 * @return this.hasMoved	whether the piece has moved or not
	 */
	public boolean hasMoved() {
		return this.hasMoved;
	}
	
	/**
	 * Sets row
	 * @param row	the new row
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Sets column
	 * @param col	the new column
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	/**
	 * Sets hasMoved to bool
	 * @param bool	the desired value of this.hasMoed
	 */
	protected void setHasMoved(boolean bool) {
		this.hasMoved = bool;
	}
	
	/**
	 * Sets hasMoved to true
	 */
	public void move() {
		this.hasMoved = true;
	}
	
	/**
	 * Sets type
	 * @param type	the desired type of the piece
	 */
	protected void setType(char type) {
		this.type = type;
	}
	
	/**
	 * Checks if there is an error with coordinate itself with respect to the board
	 * @param newRow	the row of the new coordinate
	 * @param newCol	the column of the new coordinate
	 */
	protected boolean errorCheck(int newRow, int newCol) {
		//new coordinate not within bounds of board
		if(newRow > 8 || newCol > 8 || newRow < 1 || newCol < 1) {
			return true;
		}
		//new coordinate is the same as the current coordinate
		if(newRow == this.row() && newCol == this.col()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if input coordinate is a valid diagonal move (doesn't account for check)
	 * @param newRow	the row of the new coordinate
	 * @param newCol	the column of the new coordinate
	 * @param board		the relevant chess board
	 * @return 			true if valid, false if not	
	 */
	protected boolean diagCheck(int newRow, int newCol, Board board) {
		//a diagonal move must have a slope with the absolute value of 1
		if(Math.abs(newRow-this.row()) == Math.abs(newCol - this.col())) {
			
			//row and column incrementers
			int rowInc = 1;
			int colInc = 1;
			
			//adjusting row and column incrementers for newRow < this.row and newCol < this.col
			if(newRow<this.row) {
				rowInc = -1;
			}
			if(newCol<this.col) {
				colInc = -1;
			}
			
			//checking that there are only empty spaces along the diagonal path
			int Row = this.row+rowInc;
			int Col = this.col+colInc;
			while(Row!=newRow && Col!=newCol) {
				//return false if there is an obstruction
				if(board.board[Row][Col] != null) {
					return false;
				}
				Row += rowInc;
				Col += colInc;
			}
			
			//if the destination coordinate is empty, or it has an opposing team's piece, we can move it there
			if(board.board[newRow][newCol]==null || board.board[newRow][newCol].isWhite != this.isWhite) {
				return true;
			}
		}
		//we've encountered an error
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
		//checking if columns are the same
		if(newCol != this.col) {
			return false;
		}
		
		//adjusting row incrementer for newRow < this.row or newRow > this.row 
		int inc = 1;
		if(newRow < this.row) {
			inc = -1;
		}
		
		//checking that there are only empty spaces along the vertical path
		int Row = this.row + inc;
		while(newRow != Row) {
			if(board.board[Row][newCol]!=null) {
				return false;
			}
			Row += inc;
		}
		
		//if the destination coordinate is empty, or it has an opposing team's piece, the move is valid
		if(board.board[newRow][newCol]==null || board.board[newRow][newCol].isWhite != this.isWhite) {
			return true;
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
		//checking that rows are the same
		if(newRow != this.row) {
			return false;
		}
		
		//adjusting column incrementer for newCol < this.col or newCol > this.col
		int inc = 1;
		if(newCol < this.col) {
			inc = -1;
		}
		
		//making sure there are only empty spaces along horizontal path
		int Col = this.col + inc;
		while(newCol != Col) {
			//return false since there is an obstruction
			if(board.board[newRow][Col]!=null) {
				return false;
			}
			Col += inc;
		}
		
		//if the destination coordinate is empty, or it has an opposing teams piece, the move is valid
		if(board.board[newRow][newCol]==null || board.board[newRow][newCol].isWhite != this.isWhite) {
			return true;
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
		//copy of the input board
		Board editedBoard = board.copy();
		
		//moving the desired piece to the new location on the board
		editedBoard.board[this.row()][this.col()] = null;
		editedBoard.board[newRow][newCol] = this.copy();
		
		//notifying program that the piece has been moved at least once, and setting new row and column value
		editedBoard.get(newRow, newCol).move();
		editedBoard.get(newRow, newCol).setRow(newRow);
		editedBoard.get(newRow, newCol).setCol(newCol);
		
		//seeing if the move results in the king from the piece's team being checked
		return editedBoard.kingChecked(this.isWhite);
	}
	
	/**
	 * Checks if a legal move exists, by parsing through the entire board
	 * @param board		the chess board
	 * @return 			true if a legal move exists, false otherwise
	 */
	public boolean existsLegalMove(Board board) {
		//invoking checkMove for all spaces on the chessBoard to see if there exists a legal move
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
	 * Checks if input coordinate is a valid chess move. Accounts for checks by calling testDupMove.
	 * @param newRow	the row of the new coordinate
	 * @param newCol	the column of the new coordinate
	 * @param board		the chess board
	 * @return 			true if valid, false if not	
	 */
	public boolean checkMove(int newRow, int newCol, Board board) {
		//calling the can attack method to see if the piece can move to the desired spot
		if(canAttack(newRow,newCol,board)) {
			//returning the negation of whether the relevant king checked as a result of executing the move
			//this determines validity
			return !testDupMove(newRow,newCol,board);
		}
		return false;
	}
	
	/**
	 * Checks if a move to the new coordinate is valid. Doesn't account for checks.
	 * @param newRow	the row of the new coordinate
	 * @param newCol	the column of the new coordinate
	 * @param board		the chess board
	 * @return 			true if valid, false if not	
	 */
	public abstract boolean canAttack(int newRow, int newCol, Board board);
	
	/**
	 * Returns a copy of the chess piece.
	 * @return	a copy of the invoking chessPiece
	 */
	public abstract ChessPiece copy();
	
	/**
	 * Returns the string representation of the piece
	 * @return	a string which has the team and piece type
	 */
	public String toString() {
		if(this.isWhite) {
			return "w"+this.type;
		}
		return "b"+this.type;
	}
}
