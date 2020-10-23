package pieces;

import chess.Board;
/**
 * 
 * Class that represents a Pawn.
 * @author Jonathan Wong
 * @author Shreyas Heragu
 *
 */
public class Pawn extends ChessPiece{
	/**
	 * Variable to determine if this piece can be attacked through an en passant.
	 */
	public boolean canEnPassant;
	
	/**
	 * Constructor to initialize row, column and team. Initializes type to 'p'.
	 * @param row The row of the piece.
	 * @param col The column of the piece.
	 * @param isWhite The piece's team: true if white, false if black.
	 */
	public Pawn(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('p');
	}
	
	/**
	 * Pawn implementation of abstract method for a ChessPiece to return a copy of itself.
	 * @return A copied instance of the invoking Pawn.
	 */
	public ChessPiece copy() {
		Pawn p = new Pawn(this.row(),this.col(),this.isWhite());
		p.setHasMoved(this.hasMoved());
		return p;
	}
	
	/**
	 * Pawn implementation of abstract method to check if a move to the new coordinate is valid. Doesn't account for checks.
	 * @param newRow The row of the new coordinate.
	 * @param newCol The column of the new coordinate.
	 * @param board	The chess board.
	 * @return True if valid, false if not.	
	 */
	public boolean canAttack(int newRow, int newCol, Board board) {
		//Bad new row or column
		if(errorCheck(newRow,newCol)) {
			return false;
		}
		//SET UP ENPASSANT
		//Move up 2 white pawn
		if(newRow == this.row() + 2 && newCol == this.col() && this.isWhite()) {
			//Free to move to
			if(board.board[newRow][newCol] == null) {
				//Must be first turn
				if(this.row() == 2) {
					return true;
				}
			}
		}
		//SET UP ENPASSANT
		//Move down 2 black pawn
		if(newRow == this.row() - 2 && newCol == this.col() && !this.isWhite()) {
			//Free to move to
			if(board.board[newRow][newCol] == null) {
				//Must be first turn
				if(this.row() == 7) {
					return true;
				}
			}
		}
		
		//Move up 1, only for white pawn
		if(newRow == this.row() + 1 && newCol == this.col() && this.isWhite()) {
			//Free to move to
			if(board.board[newRow][newCol] == null) {
				return true;
			}
		}
		//Move down 1, only for black pawn
		if(newRow == this.row() - 1 && newCol == this.col() && !this.isWhite()) {
			//Free to move to
			if(board.board[newRow][newCol] == null) {
				return true;
			}
		}
		//White pawn up and right
		if(newRow == this.row() + 1 && newCol == this.col() + 1 && this.isWhite()) {
			//Must have piece to attack that is black
			if(board.board[newRow][newCol] != null) {
				if(!board.board[newRow][newCol].isWhite()) {
					return true;
				}
			}
			//Check en passant because spot is empty
			else {
				if(board.board[this.row()][this.col() + 1] != null) {
					ChessPiece other = board.board[this.row()][this.col() + 1];
					//Must be a black pawn that can be en passanted
					if(other.type() == 'p') {
						Pawn otherPawn = (Pawn) other;
						if(!other.isWhite() && otherPawn.canEnPassant) {
							return true;
						}
					}
				}
			}
		}
		//White pawn up and left
		if(newRow == this.row() + 1 && newCol == this.col() - 1 && this.isWhite()) {
			//Must have piece to attack that is black
			if(board.board[newRow][newCol] != null) {
				if(!board.board[newRow][newCol].isWhite()) {
					return true;
				}
			}
			//Check en passant because spot is empty
			else {
				if(board.board[this.row()][this.col() - 1] != null) {
					ChessPiece other = board.board[this.row()][this.col() - 1];
					//Must be a black pawn that can be en passanted
					if(other.type() == 'p') {
						Pawn otherPawn = (Pawn) other;
						if(!other.isWhite() && otherPawn.canEnPassant) {
							return true;
						}
					}
				}
			}
		}
		
		//Black pawn down and left
		if(newRow == this.row() - 1 && newCol == this.col() - 1 && !this.isWhite()) {
			//Must have piece to attack that is white
			if(board.board[newRow][newCol] != null) {
				if(board.board[newRow][newCol].isWhite()) {
					return true;
				}
			}
			//Check en passant because spot is empty
			else {
				if(board.board[this.row()][this.col() - 1] != null) {
					ChessPiece other = board.board[this.row()][this.col() - 1];
					//Must be a white pawn that can be en passanted
					if(other.type() == 'p') {
						Pawn otherPawn = (Pawn) other;
						if(other.isWhite() && otherPawn.canEnPassant) {
							return true;
						}
					}
				}
			}
		}
		
		//Black pawn down and right
		if(newRow == this.row() - 1 && newCol == this.col() + 1 && !this.isWhite()) {
			//Must have piece to attack that is white
			if(board.board[newRow][newCol] != null) {
				if(board.board[newRow][newCol].isWhite()) {
					return true;
				}
			}
			//Check en passant because spot is empty
			else {
				if(board.board[this.row()][this.col() + 1] != null) {
					ChessPiece other = board.board[this.row()][this.col() + 1];
					//Must be a white pawn that can be en passanted
					if(other.type() == 'p') {
						Pawn otherPawn = (Pawn) other;
						if(other.isWhite() && otherPawn.canEnPassant) {
							return true;
						}
					}
				}
			}
		}

		
		
		return false;
	}

}
