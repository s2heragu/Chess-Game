package pieces;

import chess.Board;

public class King extends ChessPiece {
	
	/**
	 * Constructor to initialize row, column and team. Initializes type to 'K'.
	 * @param row		the row of the piece
	 * @param col		the column of the piece
	 * @param isWhite	the piece's team
	 */
	public King(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('K');
	}
	
	/**
	 * Returns a copy of the King
	 * @return	a copy of the invoking King
	 */
	public ChessPiece copy() {
		King k = new King(this.row(),this.col(),this.isWhite());
		k.setHasMoved(this.hasMoved());
		return k;
	}
	
	/**
	 * Determines whether input move is a valid castle.
	 * @param newRow	row of destination coordinate
	 * @param newCol	column of destination coordinate
	 * @param board		relevant chess board
	 * @return	true if valid, false if not
	 */
	public boolean isValidCastle(int newRow, int newCol, Board board){
		//castle can't be done if king has moved
		if(this.hasMoved()) {
			return false;
		}
		
		//castle can only happen on the same row as the King
		if(newRow != this.row()) {
			return false;
		}
		
		//arbitrary chessPiece referring to what we think is the rook
		ChessPiece rook;
		
		//dealing with side with further "rook"
		if(newCol==3) {
			//assigning chessPiece value at expected "rook" position to rook
			rook = board.get(newRow, 1);
			
			//checking for all empty spaces in between king and "rook"
			for(int i = this.col();i>1;i--) {
				if(board.get(newRow, i)!=null) {
					return false;
				}
			}
			
			//indicates either that the "rook" space is empty, the actual rook is there but it has moved, or that the chessPiece there isn't a rook
			if(rook==null||rook.hasMoved()) {
				return false;
			}
			return true;	
		}
		
		//dealing with side with closer "rook"
		else if(newCol==7) {
			//assigning chessPiece value at expected "rook" position to rook
			rook = board.get(newRow, 8);
			
			//checking for all empty spaces in between king and "rook"
			for(int i = this.col();i<8;i++) {
				if(board.get(newRow, i)!=null) {
					return false;
				}
			}
			
			//indicates either that the "rook" space is empty, the actual rook is there but it has moved, or that the chessPiece there isn't a rook
			if(rook==null||rook.hasMoved()) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if a Castle move results in the king being checked
	 * @param newRow	the row of the new coordinate
	 * @param newCol	the column of the new coordinate
	 * @param board		the chess board
	 * @return 			true if king is checked, false if not	
	 */
	protected boolean testDupCastle(int newCol, Board board) {
		//board copy
		Board editedBoard = board.copy();
		
		//moving king and adjusting column field
		editedBoard.board[this.row()][this.col()] = null;
		editedBoard.board[this.row()][newCol] = this.copy();
		editedBoard.get(this.row(),newCol).setCol(newCol);
		
		//variable to help determine where to place rook
		int inc = 0;
		
		//variable denoting rook's original column
		int rookCol = 0;
		
		//dealing with further rook
		if(newCol == 3) {inc = 1; rookCol = 1;}
		
		//dealing with closer rook
		else {inc = -1; rookCol = 8;}
		
		//moving rook and adjusting rook column field
		editedBoard.board[this.row()][newCol+inc] = editedBoard.get(this.row(), rookCol);
		editedBoard.board[this.row()][rookCol] = null;
		editedBoard.get(this.row(), newCol+inc).setCol(newCol+inc);
		
		//return whether king is checked as a result or not
		return editedBoard.kingChecked(this.isWhite());
	}
	
	/**
	 * Checks if input coordinate is a valid chess move. Accounts for checks by calling testDupMove.
	 * Overrides ChessPiece method since King potentially has a special castle move
	 * @param newRow	the row of the new coordinate
	 * @param newCol	the column of the new coordinate
	 * @param board		the chess board
	 * @return 			true if valid, false if not	
	 */
	public boolean checkMove(int newRow, int newCol, Board board) {
		//we can attack the destination spot
		if(canAttack(newRow,newCol,board)) {
			//resulting move is a castle move
			if(isValidCastle(newRow,newCol,board)) {
				//checking if castle move results in check and returning its negation
				return !testDupCastle(newCol,board);
			}
			//checking if non-castle move results in check and returning its negation
			return !testDupMove(newRow,newCol,board);
		}
		return false;
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
		
		//checking if move is a valid castle move
		if(isValidCastle(newRow,newCol,board)) {
			return true;
		}
		
		//rowDiff = change in row, colDiff = change in columns
		int rowDiff = newRow-this.row();
		int colDiff = newCol - this.col();
		
		//change in row and column can only be 0 or 1 in absolute value for each
		boolean colCheck = Math.abs(colDiff) >= 0 && Math.abs(colDiff) <= 1;
		boolean rowCheck = Math.abs(rowDiff) >= 0 && Math.abs(rowDiff) <= 1;
		
		if(rowCheck && colCheck) {
			//checking if destination coordinate is empty or has an opponent team's piece
			if(board.get(newRow, newCol)==null || board.get(newRow, newCol).isWhite()!=this.isWhite()) {
				return true;
			}
		}
		return false;
	}

}
