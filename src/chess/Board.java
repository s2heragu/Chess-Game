package chess;
import pieces.*;

public class Board{
	public ChessPiece[][] board;
	
	
	/*
	 */
	public Board() {
		//Start game with new board
		this.board = new ChessPiece[9][9];
		System.out.println(board[0][0]); // null, this is correct
	}
	
	//checks if the king is checked;
	public boolean kingChecked(boolean white) {
		return false;
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
