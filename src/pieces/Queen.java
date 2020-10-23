package pieces;

import chess.Board;
/**
 * 
 * Class that represents a Queen.
 * @author Jonathan Wong
 * @author Shreyas Heragu
 *
 */
public class Queen extends ChessPiece {
	/**
	 * Constructor to initialize row, column and team. Sets type to 'Q'.
	 * @param row The row of the piece.
	 * @param col The column of the piece.
	 * @param isWhite The piece's team: true if white, false if black.
	 */
	public Queen(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('Q');
	}
	
	/**
	 * Queen implementation of abstract method for a ChessPiece to return a copy of itself.
	 * @return A copied instance of the invoking Queen.
	 */
	public ChessPiece copy() {
		Queen q = new Queen(this.row(),this.col(),this.isWhite());
		q.setHasMoved(this.hasMoved());
		return q;
	}

	
	/**
	 * Queen implementation of abstract method to check if a move to the new coordinate is valid. Doesn't account for checks.
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
