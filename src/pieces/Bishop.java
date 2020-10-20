package pieces;
import chess.Board;
/**
 * 
 * Class that represents a Bishop.
 * @author Jonathan Wong, Shreyas Heragu
 *
 */
public class Bishop extends ChessPiece {
	
	/**
	 * Constructor to initialize row, column and team. Initializes type to 'B'.
	 * @param row		the row of the piece
	 * @param col		the column of the piece
	 * @param isWhite	the piece's team
	 */
	public Bishop(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('B');
	}
	
	/**
	 * Returns a copy of the Bishop
	 * @return	a copy of the invoking Bishop
	 */
	public ChessPiece copy() {
		Bishop b = new Bishop(this.row(),this.col(),this.isWhite());
		b.setHasMoved(this.hasMoved());
		return b;
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
		//checking if requested move is a diagonal move
		if(diagCheck(newRow,newCol,board)) {
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
