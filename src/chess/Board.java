package chess;
import pieces.*;

public class Board{
	
	public ChessPiece[][] board;
	
	public boolean isWhiteTurn;
	
	/**
	 * 
	 */
	public Board() {
		//Start game with new board
		this.board = new ChessPiece[9][9];
		this.isWhiteTurn = true;
		System.out.println(board[0][0]); // null, this is correct
	}
	
	/**
	 * Checks if King on opposing team got checked upon most recent move
	 * @return result Boolean to determine whether King is checked
	 */
	public boolean kingChecked() {
		boolean result = true;
		//Dummy values
		King k = new King(-1, -1, true);
		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				if(board[i][j].type() == 'K') {
					
					//White makes move that checks black King
					if(isWhiteTurn && !board[i][j].isWhite()) {
						k = (King) board[i][j];
					}
					//Black makes move that checks white king
					//Change turn after this check, this can be changed
					else if (!isWhiteTurn && board[i][j].isWhite()) {
						k = (King) board[i][j];
					}
					
				}
			}
		}
		
		//Check diagonals
		
		
		
		
		return result;
	}
	
	//makes a copy of the Board
	public Board copy() {
		Board copy = new Board();
		for(int i = 0; i < 9;i++) {
			for(int j = 0; j<9; j++) {
				copy.board[i][j] = this.board[i][j];
			}
		}
		return copy;
	}
	
	public String toString() {
		
		//Rows go from down up
		//Cols left to right
		
		String result = "";
		for(int row = 8; row >= 1; row--) {
			for(int col = 1; col <= 8; col++) {
				//Even row, shade every even column
				if(row % 2 == 0) {
					//No piece on this spot
					if(board[row][col] == null) {
						if(col % 2 == 0) {
							result+="##";
						}
						else {
							result+="  ";
						}
					}
					//There is a chess piece, use its toString method
					else {
						
					}
				}
				//Odd row, shade every odd column
				else {
					if(board[row][col] == null) {
						if(col % 2 == 1) {
							result+="##";
						}
						else {
							result+="  ";
						}
					}
					else {
						
					}
				}
				result+=" ";
			}
			result+= Integer.toString(row);
			result+= "\n";
		}
		result+=" a  b  c  d  e  f  g  h";
		
		return result;
	}
}
