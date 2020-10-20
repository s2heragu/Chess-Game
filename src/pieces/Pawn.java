package pieces;

import chess.Board;
/**
 * 
 * Class that represents a Pawn.
 * @author Jonathan Wong, Shreyas Heragu
 *
 */
public class Pawn extends ChessPiece{
	/**
	 * Variable to determine if this piece can be attacked through an en passant
	 */
	public boolean canEnPassant;
	
	/**
	 * Pawn constructor
	 * @param row Sets the field of the pawn's row
	 * @param col Sets the field of the pawn's column
	 * @param isWhite true if the pawn is on the white team, false for the black team
	 */
	public Pawn(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('p');
	}
	/**
	 * Method to get a deep copy of a Pawn object
	 * @return Returns a new pawn copied instance
	 */
	public ChessPiece copy() {
		Pawn p = new Pawn(this.row(),this.col(),this.isWhite());
		p.setHasMoved(this.hasMoved());
		return p;
	}
	
	/**
	 * Method to determine if a piece can move to a spot without checking for king checked.
	 * @param newRow destination row of the piece
	 * @param newCol destination column of the piece
	 * @param board Chess board class instance with current game state
	 * @return boolean to determine whether a piece can move here without checking for king being checked, 
	 * true if can attack, false otherwise
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
