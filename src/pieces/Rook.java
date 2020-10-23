package pieces;

import chess.Board;
/**
 * 
 * Class that represents a Rook.
 * @author Jonathan Wong
 * @author Shreyas Heragu
 *
 */
public class Rook extends ChessPiece{
	/**
	 * Constructor to initialize row, column and team. Sets type to 'R'.
	 * @param row The row of the piece.
	 * @param col The column of the piece.
	 * @param isWhite The piece's team: true if white, false if black.
	 */
	public Rook(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('R');
	}
	
	/**
	 * Rook implementation of abstract method for a ChessPiece to return a copy of itself.
	 * @return A copied instance of the invoking Rook.
	 */
	public ChessPiece copy() {
		Rook r = new Rook(this.row(),this.col(),this.isWhite());
		r.setHasMoved(this.hasMoved());
		return r;
	}

	/**
	 * Rook implementation of abstract method to check if a move to the new coordinate is valid. Doesn't account for checks.
	 * @param newRow The row of the new coordinate.
	 * @param newCol The column of the new coordinate.
	 * @param board	The chess board.
	 * @return True if valid, false if not.	
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


}
