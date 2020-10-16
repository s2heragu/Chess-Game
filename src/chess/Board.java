package chess;
import pieces.*;

public class Board{
	
	public ChessPiece[][] board;
	
	public boolean isWhiteTurn;
	
	/**
	 * Initializes board with chess pieces and starts as white team's turn
	 */
	public Board() {
		//Start game with new board
		this.board = new ChessPiece[9][9];
		this.isWhiteTurn = true;
		board[1][1] = new Rook(1, 1, true);
		board[1][2] = new Knight(1, 2, true);
		board[1][3] = new Bishop(1, 3, true);
		board[1][4] = new Queen(1, 4, true);
		board[1][5] = new King(1, 5, true);
		board[1][6] = new Bishop(1, 6, true);
		board[1][7] = new Knight(1, 7, true);
		board[1][8] = new Rook(1, 8, true);
		
		board[2][1] = new Pawn(2, 1, true);
		board[2][2] = new Pawn(2, 2, true);
		board[2][3] = new Pawn(2, 3, true);
		board[2][4] = new Pawn(2, 4, true);
		board[2][5] = new Pawn(2, 5, true);
		board[2][6] = new Pawn(2, 6, true);
		board[2][7] = new Pawn(2, 7, true);
		board[2][8] = new Pawn(2, 8, true);
		
		board[7][1] = new Pawn(7, 1, false);
		board[7][2] = new Pawn(7, 2, false);
		board[7][3] = new Pawn(7, 3, false);
		board[7][4] = new Pawn(7, 4, false);
		board[7][5] = new Pawn(7, 5, false);
		board[7][6] = new Pawn(7, 6, false);
		board[7][7] = new Pawn(7, 7, false);
		board[7][8] = new Pawn(7, 8, false);
		
		
		board[8][1] = new Rook(8, 1, false);
		board[8][2] = new Knight(8, 2, false);
		board[8][3] = new Bishop(8, 3, false);
		board[8][4] = new Queen(8, 4, false);
		board[8][5] = new King(8, 5, false);
		board[8][6] = new Bishop(8, 6, false);
		board[8][7] = new Knight(8, 7, false);
		board[8][8] = new Rook(8, 8, false);
		
	}
	
	/**
	 * Checks if King on opposing team got checked upon most recent move
	 * @return result Boolean to determine whether King is checked
	 */
	public boolean kingChecked() {
		boolean result = false;
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
		int row = k.row();
		int col = k.col();
		int distance = 0;
		row--;
		col--;
		distance++;
		boolean team = k.isWhite();
		//Down and left
		while(row > 0 && col > 0) {
			if(board[row][col] != null) {
				ChessPiece other = board[row][col];
				if(other.isWhite() != team) {
					//Diagonals attacked by bishop, pawn, Queen
					if(distance == 1 && other.type() == 'p' && other.isWhite()) {
						return true;
					}
					else if(other.type() == 'B' || other.type() == 'Q') {
						return true;
					}
				}
				//Same team, not attacked diagonally
				else {
					break;
				}
			}
			row--;
			col--;
			distance++;
		}
		//Down and right
		row = k.row();
		col = k.col();
		distance = 0;
		row--;
		col++;
		distance++;
		//Down and left
		while(row > 0 && col <= 8) {
			if(board[row][col] != null) {
				ChessPiece other = board[row][col];
				if(other.isWhite() != team) {
					//Diagonals attacked by bishop, pawn, Queen
					if(distance == 1 && other.type() == 'p' && other.isWhite()) {
						return true;
					}
					else if(other.type() == 'B' || other.type() == 'Q') {
						return true;
					}
				}
				//Same team, not attacked diagonally
				else {
					break;
				}
			}
			row--;
			col++;
			distance++;
		}
		//Up and left
		row = k.row();
		col = k.col();
		distance = 0;
		row--;
		col++;
		distance++;
		//Down and left
		while(row > 0 && col <= 8) {
			if(board[row][col] != null) {
				ChessPiece other = board[row][col];
				if(other.isWhite() != team) {
					//Diagonals attacked by bishop, pawn, Queen
					if(distance == 1 && other.type() == 'p' && other.isWhite()) {
						return true;
					}
					else if(other.type() == 'B' || other.type() == 'Q') {
						return true;
					}
				}
				//Same team, not attacked diagonally
				else {
					break;
				}
			}
			row--;
			col--;
			distance++;
		}
		//Up and right
		
		
		
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
	/**
	 * Gets the chess piece at location
	 * @param row the row of the piece
	 * @param col the column of the piece
	 * @return returns a chess piece at the location, null if none is there
	 */
	public ChessPiece get(int row, int col) {
		return board[row][col];
	}
}
