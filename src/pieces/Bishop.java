package pieces;
import chess.Board;
/**
 * 
 * Class that represents a Bishop.
 * @author Jonathan Wong
 * @author Shreyas Heragu
 *
 */
public class Bishop extends ChessPiece {
	
	/**
	 * Constructor to initialize row, column and team. Initializes type to 'B'.
	 * @param row The row of the piece.
	 * @param col The column of the piece.
	 * @param isWhite The piece's team: true if white, false if black.
	 */
	public Bishop(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('B');
	}
	
	/**
	 * Returns a copy of the Bishop.
	 * @return A copy of the invoking Bishop.
	 */
	public ChessPiece copy() {
		Bishop b = new Bishop(this.row(),this.col(),this.isWhite());
		b.setHasMoved(this.hasMoved());
		return b;
	}

	/**
	 * Checks if a move to the new coordinate is valid. Doesn't account for checks.
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
		//checking if requested move is a diagonal move
		if(diagCheck(newRow,newCol,board)) {
			return true;
		}
		return false;
	}

}
