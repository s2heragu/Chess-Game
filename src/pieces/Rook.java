package pieces;

import chess.Board;
/**
 * 
 * Class that represents a Rook.
 * @author Jonathan Wong, Shreyas Heragu
 *
 */
public class Rook extends ChessPiece{
	/**
	 * Constructor to initialize row, column and team. Sets type to 'R'.
	 * @param row		the row of the piece
	 * @param col		the column of the piece
	 * @param isWhite	the piece's team
	 */
	public Rook(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('R');
	}
	
	/**
	 * Returns a copy of the Rook
	 * @return	a deep copy of the invoking Rook
	 */
	public ChessPiece copy() {
		Rook r = new Rook(this.row(),this.col(),this.isWhite());
		r.setHasMoved(this.hasMoved());
		return r;
	}

	/**
	 * Checks if a move to the new coordinate is valid. Doesn't account for checks.
	 * @param newRow	the row of the new coordinate
	 * @param newCol	the column of the new coordinate
	 * @param board		the chess board
	 * @return 			true if valid, false if not	
	 */
	public boolean canAttack(int newRow, int newCol, Board board) {
		//checking for basic errors
		if(errorCheck(newRow,newCol)) {
			return false;
		}
		
		//Rooks can move vertically and horizontally
		
		if(vertCheck(newRow,newCol,board)) {
			return true;
		}
		if(horizCheck(newRow,newCol,board)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Determines whether input move is a valid castle.
	 * @param newRow	row of destination coordinate
	 * @param newCol	column of destination coordinate
	 * @param board		relevant chess board
	 * @return			false since piece is not a king
	 */
	public boolean isValidCastle(int newRow, int newCol, Board board) {
		return false;
	}


}
