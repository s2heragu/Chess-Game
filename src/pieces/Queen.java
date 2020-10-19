package pieces;

import chess.Board;
/**
 * 
 * Class that represents a Queen.
 * @author Jonathan Wong, Shreyas Heragu
 *
 */
public class Queen extends ChessPiece {
	/**
	 * Constructor to initialize row, column and team. Sets type to 'Q'.
	 * @param row		the row of the piece
	 * @param col		the column of the piece
	 * @param isWhite	the piece's team
	 */
	public Queen(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('Q');
	}
	
	/**
	 * Returns a copy of the Queen
	 * @return	a deep copy of the invoking Queen
	 */
	public ChessPiece copy() {
		Queen q = new Queen(this.row(),this.col(),this.isWhite());
		q.setHasMoved(this.hasMoved());
		return q;
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

		//Queen can move vertically, horizontally, or diagonally
		
		if(vertCheck(newRow,newCol,board)) {
			return true;
		}
		if(horizCheck(newRow,newCol,board)) {
			return true;
		}
		if(diagCheck(newRow,newCol,board)) {
			return true;
		}
		return false;
	}


}
