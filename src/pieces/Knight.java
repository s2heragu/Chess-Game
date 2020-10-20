package pieces;

import chess.Board;
/**
 * 
 * Class that represents a Knight.
 * @author Jonathan Wong, Shreyas Heragu
 *
 */
public class Knight extends ChessPiece {
	/**
	 * Constructor to initialize row, column and team. Initializes type to 'N'.
	 * @param row		the row of the piece
	 * @param col		the column of the piece
	 * @param isWhite	the piece's team
	 */
	public Knight(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('N');
	}
	
	/**
	 * Returns a copy of the Knight
	 * @return	a copy of the invoking Knight
	 */
	public ChessPiece copy() {
		Knight k = new Knight(this.row(),this.col(),this.isWhite());
		k.setHasMoved(this.hasMoved());
		return k;
	}

	/**
	 * Checks if a move to the new coordinate is valid. Doesn't account for checks.
	 * @param newRow	the row of the new coordinate
	 * @param newCol	the column of the new coordinate
	 * @param board		the chess board
	 * @return 			true if valid, false if not	
	 */
	public boolean canAttack(int newRow, int newCol, Board board) {
		//checking for errors
		if(errorCheck(newRow,newCol)) {
			return false;
		}
		
		//rowDiff is change in rows, colDiff is change in columns
		int rowDiff = newRow-this.row();
		int colDiff = newCol - this.col();
		
		if(colDiff!=0 && rowDiff!=0 && Math.abs(colDiff)+Math.abs(rowDiff)==3) {
			//checking if destination coordinate is empty or has an opponent team's piece
			if(board.get(newRow, newCol)==null || board.get(newRow, newCol).isWhite()!=this.isWhite()) {
				return true;
			}
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
