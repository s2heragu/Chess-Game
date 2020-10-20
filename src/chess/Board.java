package chess;
import pieces.*;

/**
 * 
 * Class to represent chess board
 * @author Jonathan Wong, Shreyas Heragu
 *
 */
public class Board{
	

	/**
	 * Integer to represent the state of the game
	 * 1 = white wins
	 * 0 = draw
	 * -1 = black wins
	 */
	public int gameState;
	
	/**
	 * 2D array of ChessPieces to store chess game state
	 */
	public ChessPiece[][] board;
	
	/**
	 * boolean to represent white or black team's turn,
	 * true if white turn,
	 * false if black turn
	 */
	public boolean isWhiteTurn;
	
	/**
	 * Class Constructor to initialize board with chess pieces and starts as white team's turn
	 */
	public Board() {
		//Start game with new board
		this.board = new ChessPiece[9][9];
		this.isWhiteTurn = true;
		gameState = 0;
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
	 * Given a valid input, perform the move option
	 * @param move the user input that has been validated
	 */
	public void performMove(String move) {
		//Split by spaces
		String[] info = move.split(" ");
		String from = info[0];
		String to = info[1];
		int fromRow = Character.getNumericValue(from.charAt(1));
		int fromCol = charToCol(from.charAt(0));
		int toRow = Character.getNumericValue(to.charAt(1));
		int toCol = charToCol(to.charAt(0));
		ChessPiece piece = board[fromRow][fromCol];
		this.movePieceNoCheck(toRow, toCol, piece);
		piece.move();
		//Promotion
		if(piece.type() == 'p') {
			//White pawn promotion
			if(piece.isWhite()) {
				if(toRow == 8) {
					char newType;
					ChessPiece newPiece = null;
					if(info.length == 3) {
						newType = info[2].charAt(0);
					}
					//Default to queen piece
					else {
						newType = 'Q';
					}
					if(newType == 'B') {
						newPiece = new Bishop(toRow, toCol, piece.isWhite());
						board[toRow][toCol] = newPiece;
					}
					else if(newType == 'N') {
						newPiece = new Knight(toRow, toCol, piece.isWhite());
						board[toRow][toCol] = newPiece;
					}
					else if(newType == 'R') {
						newPiece = new Rook(toRow, toCol, piece.isWhite());
						board[toRow][toCol] = newPiece;
					}
					else if(newType == 'Q') {
						newPiece = new Queen(toRow, toCol, piece.isWhite());
						board[toRow][toCol] = newPiece;
					}
					newPiece.move();
					
				}
			}
			//Black pawn promotion
			else {
				if(toRow == 1) {
					char newType;
					ChessPiece newPiece = null;
					if(info.length == 3) {
						newType = info[2].charAt(0);
					}
					//Default to queen piece
					else {
						newType = 'Q';
					}
					if(newType == 'B') {
						newPiece = new Bishop(toRow, toCol, piece.isWhite());
						board[toRow][toCol] = newPiece;
					}
					else if(newType == 'N') {
						newPiece = new Knight(toRow, toCol, piece.isWhite());
						board[toRow][toCol] = newPiece;
					}
					else if(newType == 'R') {
						newPiece = new Rook(toRow, toCol, piece.isWhite());
						board[toRow][toCol] = newPiece;
					}
					else if(newType == 'Q') {
						newPiece = new Queen(toRow, toCol, piece.isWhite());
						board[toRow][toCol] = newPiece;
					}
					newPiece.move();
					
				}
			}
		}
	}
	
	/**
	 * Method to determine if a move input is legal or not
	 * @param move the string representing where to move a piece "FileRank FileRank otherInfo"
	 * @return returns true if move is legal, false otherwise
	 */
	public boolean parseMove(String move) {
		//Split by spaces
		String[] info = move.split(" ");
		String from = info[0];
		String to = info[1];
		int fromRow = Character.getNumericValue(from.charAt(1));
		int fromCol = charToCol(from.charAt(0));
		int toRow = Character.getNumericValue(to.charAt(1));
		int toCol = charToCol(to.charAt(0));
		ChessPiece piece = board[fromRow][fromCol];
		if(piece == null) {
			return false;
		}
		//Trying to move wrong team piece
		if(this.isWhiteTurn != piece.isWhite()) {
			return false;
		}

		//Legality
		if(piece.checkMove(toRow, toCol, this)) {
			if(info.length == 3) {
				if(info[2].equals("draw?")) {
					
				}
				else {
					//TODO Promotion
				}
			}
			return true;
		}
		return false;
	}
	/**
	 * Method to map a column character to the numerical representation of the column
	 * @param colChar Column in the chess board
	 * @return integer mapping of the column
	 */
	private int charToCol(char colChar) {
		if(colChar == 'a') {
			return 1;
		}
		else if(colChar == 'b') {
			return 2;
		}
		else if(colChar == 'c') {
			return 3;
		}
		else if(colChar == 'd') {
			return 4;
		}
		else if(colChar == 'e') {
			return 5;
		}
		else if(colChar == 'f') {
			return 6;
		}
		else if(colChar == 'g') {
			return 7;
		}
		else if(colChar == 'h') {
			return 8;
		}
		return 0;
	}
	
	/**
	 * Clears the enpassant status of the corresponding team's pawns
	 * Occurs when opposing team fails to act on enpassant opportunity
	 * @param isWhite team color we want to clear enpassant status from
	 */
	public void clearEnpassant(boolean isWhite) {
		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				if(this.board[i][j] != null) {
					ChessPiece curr = this.board[i][j];
					if(curr.type() == 'p') {
						if(curr.isWhite() == isWhite) {
							Pawn pawn = (Pawn) curr;
							pawn.canEnPassant = false;
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * Moves a piece to destination without checking for legality
	 * @param row	destination row
	 * @param col	destination column
	 * @param piece	Chess Piece to be moved
	 */
	public void movePieceNoCheck(int row, int col, ChessPiece piece) {
		int currRow = piece.row();
		int currCol = piece.col();
		boolean movingToNull = false;
		//checking if the move is a king castle move and moving rook accordingly
		if(piece.isValidCastle(row, col, this)) {
			int newRookCol = 6;
			int oldRookCol = 8;
			if(col == 3) {
				oldRookCol = 1;
				newRookCol = 4;
			}
			ChessPiece rook = this.get(row,oldRookCol);
			board[row][newRookCol]=rook;
			board[row][oldRookCol] = null;
			rook.setCol(newRookCol);
			rook.move();
		}
		if(board[row][col] == null) {
			movingToNull = true;
		}
		board[row][col] = piece;
		piece.setRow(row);
		piece.setCol(col);
		board[currRow][currCol] = null;
		piece.move();
		//Set up enpassant
		if(piece.type() == 'p') {
			Pawn pawn = (Pawn)piece;
			//Moved 2 spaces, can be attacked by enpassant for one turn
			if(Math.abs(row - currRow) == 2) {
				pawn.canEnPassant = true;
			}
			//Check if enpassant is performed, eat the piece
			else if(pawn.isWhite()) {
				if(movingToNull) {
					if(currCol != pawn.col()) {
						board[pawn.row() - 1][pawn.col()] = null;
					}
				}
			}
			else if(!pawn.isWhite()) {
				if(movingToNull) {
					if(currCol != pawn.col()) {
						board[pawn.row() + 1][pawn.col()] = null;
					}
				}
			}
		}
	}
	
	/**
	 * Checks if King on opposing team got checked upon most recent move
	 * @param kingTeam checks if this king's team is checked
	 * @return  Boolean to determine whether King is checked
	 */
	public boolean kingChecked(boolean kingTeam) {
		King k = new King(-1, -1, true);
		//Find king on kingTeam
		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				if(board[i][j] != null) {
					if(board[i][j].type() == 'K') {
						if(board[i][j].isWhite() == kingTeam) {
							k = (King) board[i][j];
						}
					}
				}
			}
		}
		
		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				if(board[i][j] != null) {
					//Enemy team, check if enemy can attack location of the king
					if(board[i][j].isWhite() != kingTeam) {
						//Can this piece attack the king location, if so in check
						if(board[i][j].canAttack(k.row(), k.col(), this)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	/**
	 * Determines whether game is in check mate for a team
	 * @param kingTeam true if king to check for checkmate on is white, black if false
	 * @return boolean that is true if check mated, false otherwise
	 */
	public boolean isCheckMate(boolean kingTeam) {
		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				if(board[i][j] != null) {
					//Same team, check legal moves
					if(board[i][j].isWhite() == kingTeam) {
						if(board[i][j].existsLegalMove(this)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	/**
	 * Determines whether game is in check mate, TODO rmeove this soon
	 * @return boolean that is true if check mated, false otherwise
	 */
	public boolean badCheckMate(boolean kingTeam) {
		boolean result = true;
		//Dummy values
		King k = new King(-1, -1, true);
		//Find the king
		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				if(board[i][j].type() == 'K') {
					if(board[i][j].isWhite() == kingTeam) {
						k = (King) board[i][j];
					}
				}
			}
		}
		Board tempBoard = copy();
		
		int kingRow = k.row();
		int kingCol = k.col();
		
		int row = kingRow;
		int col = kingCol;
		
		if(!kingChecked(kingTeam)) {
			return false;
		}
		
		row = kingRow;
		col = kingCol;
		row++;
		col--;
		//Valid position
		if(!(row < 1 || col >= 8)) {
			if(!(row < 1 || col >= 8)) {
				//Other spot has to be free to move to
				if(board[row][col] == null) {
					//This spot has to be attacked by other team
					//Not attacked, free to move to not in checkmate
					if(!isSpotAttacked(row, col, !kingTeam)) {
						return false;
					}
					//Can we defend it and no longer be in check
					else {
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
				//Not free to move to
				else {
					//Occupied by teammate or enemy
					if(board[row][col].isWhite() == kingTeam) {
						//ignore teammate
					}
					else {
						//Enemy, can we kill enemy and not be in check
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
			}
		}
		tempBoard = copy();

		row = kingRow;
		col = kingCol;
		row++;
		//Valid position
		if(!(row < 1 || col >= 8)) {
			if(!(row < 1 || col >= 8)) {
				//Other spot has to be free to move to
				if(board[row][col] == null) {
					//This spot has to be attacked by other team
					//Not attacked, free to move to not in checkmate
					if(!isSpotAttacked(row, col, !kingTeam)) {
						return false;
					}
					//Can we defend it and no longer be in check
					else {
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
				//Not free to move to
				else {
					//Occupied by teammate or enemy
					if(board[row][col].isWhite() == kingTeam) {
						//ignore teammate
					}
					else {
						//Enemy, can we kill enemy and not be in check
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
			}
		}
		
		
		tempBoard = copy();
		row = kingRow;
		col = kingCol;
		row++;
		col++;
		//Valid position
		if(!(row < 1 || col >= 8)) {
			if(!(row < 1 || col >= 8)) {
				//Other spot has to be free to move to
				if(board[row][col] == null) {
					//This spot has to be attacked by other team
					//Not attacked, free to move to not in checkmate
					if(!isSpotAttacked(row, col, !kingTeam)) {
						return false;
					}
					//Can we defend it and no longer be in check
					else {
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
				//Not free to move to
				else {
					//Occupied by teammate or enemy
					if(board[row][col].isWhite() == kingTeam) {
						//ignore teammate
					}
					else {
						//Enemy, can we kill enemy and not be in check
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
			}
		}
		tempBoard = copy();
		row = kingRow;
		col = kingCol;
		col++;
		//Valid position
		if(!(row < 1 || col >= 8)) {
			if(!(row < 1 || col >= 8)) {
				//Other spot has to be free to move to
				if(board[row][col] == null) {
					//This spot has to be attacked by other team
					//Not attacked, free to move to not in checkmate
					if(!isSpotAttacked(row, col, !kingTeam)) {
						return false;
					}
					//Can we defend it and no longer be in check
					else {
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
				//Not free to move to
				else {
					//Occupied by teammate or enemy
					if(board[row][col].isWhite() == kingTeam) {
						//ignore teammate
					}
					else {
						//Enemy, can we kill enemy and not be in check
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
			}
		}
		tempBoard = copy();
		row = kingRow;
		col = kingCol;
		row--;
		col++;
		//Valid position
		if(!(row < 1 || col >= 8)) {
			if(!(row < 1 || col >= 8)) {
				//Other spot has to be free to move to
				if(board[row][col] == null) {
					//This spot has to be attacked by other team
					//Not attacked, free to move to not in checkmate
					if(!isSpotAttacked(row, col, !kingTeam)) {
						return false;
					}
					//Can we defend it and no longer be in check
					else {
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
				//Not free to move to
				else {
					//Occupied by teammate or enemy
					if(board[row][col].isWhite() == kingTeam) {
						//ignore teammate
					}
					else {
						//Enemy, can we kill enemy and not be in check
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
			}
		}
		tempBoard = copy();
		row = kingRow;
		col = kingCol;
		col--;
		//Valid position
		if(!(row < 1 || col >= 8)) {
			if(!(row < 1 || col >= 8)) {
				//Other spot has to be free to move to
				if(board[row][col] == null) {
					//This spot has to be attacked by other team
					//Not attacked, free to move to not in checkmate
					if(!isSpotAttacked(row, col, !kingTeam)) {
						return false;
					}
					//Can we defend it and no longer be in check
					else {
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
				//Not free to move to
				else {
					//Occupied by teammate or enemy
					if(board[row][col].isWhite() == kingTeam) {
						//ignore teammate
					}
					else {
						//Enemy, can we kill enemy and not be in check
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
			}
		}
		tempBoard = copy();
		row = kingRow;
		col = kingCol;
		row--;
		col--;
		//Valid position
		if(!(row < 1 || col >= 8)) {
			if(!(row < 1 || col >= 8)) {
				//Other spot has to be free to move to
				if(board[row][col] == null) {
					//This spot has to be attacked by other team
					//Not attacked, free to move to not in checkmate
					if(!isSpotAttacked(row, col, !kingTeam)) {
						return false;
					}
					//Can we defend it and no longer be in check
					else {
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
				//Not free to move to
				else {
					//Occupied by teammate or enemy
					if(board[row][col].isWhite() == kingTeam) {
						//ignore teammate
					}
					else {
						//Enemy, can we kill enemy and not be in check
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
			}
		}
		tempBoard = copy();
		row = kingRow;
		col = kingCol;
		col--;
		//Valid position
		if(!(row < 1 || col >= 8)) {
			if(!(row < 1 || col >= 8)) {
				//Other spot has to be free to move to
				if(board[row][col] == null) {
					//This spot has to be attacked by other team
					//Not attacked, free to move to not in checkmate
					if(!isSpotAttacked(row, col, !kingTeam)) {
						return false;
					}
					//Can we defend it and no longer be in check
					else {
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
				//Not free to move to
				else {
					//Occupied by teammate or enemy
					if(board[row][col].isWhite() == kingTeam) {
						//ignore teammate
					}
					else {
						//Enemy, can we kill enemy and not be in check
						if(isSpotAttacked(row, col, kingTeam)) {
							ChessPiece defender = tempBoard.spotAttacker(row, col, kingTeam);
							tempBoard.movePieceNoCheck(row, col, defender);
							//Not checked after moving
							if(!tempBoard.kingChecked(kingTeam)) {
								return false;
							}
						}
					}
				}
			}
		}

		return result;
	}
	
	
	/**
	 * 
	 * @param attackRow	the row being attacked
	 * @param attackCol the column being attacked
	 * @param isWhiteTeam the team doing the attacking, true if white and false for black team
	 * @return		true if spot is attacked by the team and false otherwise
	 */
	public boolean isSpotAttacked(int attackRow,int attackCol, boolean isWhiteTeam) {
		if(spotAttacker(attackRow, attackCol, isWhiteTeam) != null) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param row the row that is being attacked
	 * @param col the column being attacked
	 * @param isWhiteTeam the team doing the attacking, true if white and false for black team
	 * @return 		the attacking piece, null if not present
	 */
	public ChessPiece spotAttacker(int attackRow, int attackCol, boolean isWhiteTeam) {
		int row = attackRow;
		int col = attackCol;
		int distance = 0;
		//Diagonals
		
		
		//Down and left
		row--;
		col--;
		distance++;
		while(row > 0 && col > 0) {
			if(board[row][col] != null) {
				ChessPiece other = board[row][col];
				if(other.isWhite() == isWhiteTeam) {
					//Only white pawn can move and up right to attack
					if(other.type() == 'p' && distance == 1 && other.isWhite()) {
						return other;
					}
					else if(other.type() == 'B' || other.type() == 'Q') {
						return other;
					}
				}
				//Wrong team, done checking
				else {
					break;
				}
			}
			row--;
			col--;
			distance++;
		}
		//Down and right
		row = attackRow;
		col = attackCol;
		distance = 0;
		row--;
		col++;
		distance++;
		while(row > 0 && col <= 8) {
			if(board[row][col] != null) {
				ChessPiece other = board[row][col];
				if(other.isWhite() == isWhiteTeam) {
					//Only white pawn can move and up right to attack
					if(other.type() == 'p' && distance == 1 && other.isWhite()) {
						return other;
					}
					else if(other.type() == 'B' || other.type() == 'Q') {
						return other;
					}
				}
				//Wrong team, done checking
				else {
					break;
				}
			}
			row--;
			col++;
			distance++;
		}
		
		
		//Up and Left
		row = attackRow;
		col = attackCol;
		distance = 0;
		row++;
		col--;
		distance++;
		while(row <= 8 && col > 0) {
			if(board[row][col] != null) {
				ChessPiece other = board[row][col];
				if(other.isWhite() == isWhiteTeam) {
					//Only black pawn can move diagonally down to attack;
					if(other.type() == 'p' && distance == 1 && (!other.isWhite())) {
						return other;
					}
					else if(other.type() == 'B' || other.type() == 'Q') {
						return other;
					}
				}
				//Wrong team, done checking
				else {
					break;
				}
			}
			row++;
			col--;
			distance++;
		}
		//Up and right
		row = attackRow;
		col = attackCol;
		distance = 0;
		row++;
		col++;
		distance++;
		while(row <= 8 && col <= 8) {
			if(board[row][col] != null) {
				ChessPiece other = board[row][col];
				if(other.isWhite() == isWhiteTeam) {
					//Only black pawn can move diagonally down to attack;
					if(other.type() == 'p' && distance == 1 && (!other.isWhite())) {
						return other;
					}
					else if(other.type() == 'B' || other.type() == 'Q') {
						return other;
					}
				}
				//Wrong team, done checking
				else {
					break;
				}
			}
			row++;
			col++;
			distance++;
		}
		
		distance = 0;
		//Horizontal, vertical
		
		//Left
		row = attackRow;
		col = attackCol;
		col--;
		while(col > 0) {
			if(board[row][col] != null) {
				ChessPiece other = board[row][col];
				if(other.isWhite() == isWhiteTeam) {
					//Only rook and queen can attack
					if(other.type() == 'R' || other.type() == 'Q') {
						return other;
					}
				}
				//Wrong team, done checking
				else {
					break;
				}
			}
			col--;
		}
		
		//right
		row = attackRow;
		col = attackCol;
		col++;
		while(col <= 8) {
			if(board[row][col] != null) {
				ChessPiece other = board[row][col];
				if(other.isWhite() == isWhiteTeam) {
					//Only rook and queen can attack
					if(other.type() == 'R' || other.type() == 'Q') {
						return other;
					}
				}
				//Wrong team, done checking
				else {
					break;
				}
			}
			col++;
		}
		
		//down
		row = attackRow;
		col = attackCol;
		row--;
		while(row > 0) {
			if(board[row][col] != null) {
				ChessPiece other = board[row][col];
				if(other.isWhite() == isWhiteTeam) {
					//Only rook and queen can attack
					if(other.type() == 'R' || other.type() == 'Q') {
						return other;
					}
				}
				//Wrong team, done checking
				else {
					break;
				}
			}
			row--;
		}
		
		//up
		row = attackRow;
		col = attackCol;
		row++;
		while(row <= 8) {
			if(board[row][col] != null) {
				ChessPiece other = board[row][col];
				if(other.isWhite() == isWhiteTeam) {
					//Only rook and queen can attack
					if(other.type() == 'R' || other.type() == 'Q') {
						return other;
					}
				}
				//Wrong team, done checking
				else {
					break;
				}
			}
			row++;
		}
		
		
		//Knights
		row = attackRow;
		col = attackCol;
		int knightRow = row + 1;
		int knightCol = col - 2;
		//Check knights
		//Check valid position
		if(!(knightRow < 1 || knightRow >= 8)) {
			if(!(knightCol < 1 || knightCol >= 8)) {
				if(board[knightRow][knightCol] != null) {
					ChessPiece other = board[row][col];
					//Knight of attacking team
					if(other.type() == 'N' && (other.isWhite() == isWhiteTeam)) {
						return other;
					}
				}
			}
		}
		
		//Knights
		row = attackRow;
		col = attackCol;
		knightRow = row + 2;
		knightCol = col - 1;
		//Check knights
		//Check valid position
		if(!(knightRow < 1 || knightRow >= 8)) {
			if(!(knightCol < 1 || knightCol >= 8)) {
				if(board[knightRow][knightCol] != null) {
					ChessPiece other = board[row][col];
					//Knight of attacking team
					if(other.type() == 'N' && (other.isWhite() == isWhiteTeam)) {
						return other;
					}
				}
			}
		}
		row = attackRow;
		col = attackCol;
		knightRow = row + 2;
		knightCol = col + 1;
		//Check knights
		//Check valid position
		if(!(knightRow < 1 || knightRow >= 8)) {
			if(!(knightCol < 1 || knightCol >= 8)) {
				if(board[knightRow][knightCol] != null) {
					ChessPiece other = board[row][col];
					//Knight of attacking team
					if(other.type() == 'N' && (other.isWhite() == isWhiteTeam)) {
						return other;
					}
				}
			}
		}
		row = attackRow;
		col = attackCol;
		knightRow = row + 1;
		knightCol = col + 2;
		//Check knights
		//Check valid position
		if(!(knightRow < 1 || knightRow >= 8)) {
			if(!(knightCol < 1 || knightCol >= 8)) {
				if(board[knightRow][knightCol] != null) {
					ChessPiece other = board[row][col];
					//Knight of attacking team
					if(other.type() == 'N' && (other.isWhite() == isWhiteTeam)) {
						return other;
					}
				}
			}
		}
		
		row = attackRow;
		col = attackCol;
		knightRow = row - 1;
		knightCol = col + 2;
		//Check knights
		//Check valid position
		if(!(knightRow < 1 || knightRow >= 8)) {
			if(!(knightCol < 1 || knightCol >= 8)) {
				if(board[knightRow][knightCol] != null) {
					ChessPiece other = board[row][col];
					//Knight of attacking team
					if(other.type() == 'N' && (other.isWhite() == isWhiteTeam)) {
						return other;
					}
				}
			}
		}
		row = attackRow;
		col = attackCol;
		knightRow = row - 2;
		knightCol = col + 1;
		//Check knights
		//Check valid position
		if(!(knightRow < 1 || knightRow >= 8)) {
			if(!(knightCol < 1 || knightCol >= 8)) {
				if(board[knightRow][knightCol] != null) {
					ChessPiece other = board[row][col];
					//Knight of attacking team
					if(other.type() == 'N' && (other.isWhite() == isWhiteTeam)) {
						return other;
					}
				}
			}
		}
		row = attackRow;
		col = attackCol;
		knightRow = row - 2;
		knightCol = col - 1;
		//Check knights
		//Check valid position
		if(!(knightRow < 1 || knightRow >= 8)) {
			if(!(knightCol < 1 || knightCol >= 8)) {
				if(board[knightRow][knightCol] != null) {
					ChessPiece other = board[row][col];
					//Knight of attacking team
					if(other.type() == 'N' && (other.isWhite() == isWhiteTeam)) {
						return other;
					}
				}
			}
		}
		row = attackRow;
		col = attackCol;
		knightRow = row - 1;
		knightCol = col - 2;
		//Check knights
		//Check valid position
		if(!(knightRow < 1 || knightRow >= 8)) {
			if(!(knightCol < 1 || knightCol >= 8)) {
				if(board[knightRow][knightCol] != null) {
					ChessPiece other = board[row][col];
					//Knight of attacking team
					if(other.type() == 'N' && (other.isWhite() == isWhiteTeam)) {
						return other;
					}
				}
			}
		}
		
		
		//No piece attacking
		return null;
	}
	
	/**
	 * Checks if King on opposing team got checked upon most recent move, TODO remove this if possible
	 * @param kingTeam checks if this king's team is checked
	 * @return  Boolean to determine whether King is checked
	 */
	public boolean badKingChecked(boolean kingTeam) {
		boolean result = false;
		//Dummy values
		King k = new King(-1, -1, true);
		//Find the king
		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				if(board[i][j].type() == 'K') {
					if(board[i][j].isWhite() == kingTeam) {
						k = (King) board[i][j];
					}
				}
			}
		}
		
		int kingRow = k.row();
		int kingCol = k.col();
		//Being attacked by opposing team
		if(isSpotAttacked(kingRow, kingCol, !k.isWhite())) {
			return true;
		}
		
		
		return result;
	}
	
	/**
	 * Copy's game state into another instance of the Board Class
	 * @return Copied instance of the Board class
	 */
	public Board copy() {
		Board copy = new Board();
		copy.board = new ChessPiece[9][9];
		for(int i = 0; i < 9;i++) {
			for(int j = 0; j<9; j++) {
				if(this.get(i, j) != null) {
					copy.board[i][j] = this.get(i, j).copy();
				}
			}
		}
		return copy;
	}
	/**
	 * Returns the string representation of the board in ASCII art
	 * @return string representation of chess board
	 */
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
						result += board[row][col].toString();
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
						result += board[row][col].toString();
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
	 * @return ChessPiece at location, null if none
	 */
	public ChessPiece get(int row, int col) {
		return board[row][col];
	}
}
