package pieces;

import chess.Board;
/**
 * 
 * Class that represents a Knight.
 * @author Jonathan Wong
 * @author Shreyas Heragu
 *
 */
public class Knight extends ChessPiece {
	/**
	 * Constructor to initialize row, column and team. Initializes type to 'N'.
	 * @param row The row of the piece.
	 * @param col The column of the piece.
	 * @param isWhite The piece's team: true if white, false if black.
	 */
	public Knight(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('N');
	}
	
	/**
	 * Knight implementation of abstract method for a ChessPiece to return a copy of itself.
	 * @return A copied instance of the invoking Knight.
	 */
	public ChessPiece copy() {
		Knight k = new Knight(this.row(),this.col(),this.isWhite());
		k.setHasMoved(this.hasMoved());
		return k;
	}

	/**
	 * Knight implementation of abstract method to check if a move to the new coordinate is valid. Doesn't account for checks.
	 * @param newRow The row of the new coordinate.
	 * @param newCol The column of the new coordinate.
	 * @param board	The chess board.
	 * @return True if valid, false if not.	
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

}
