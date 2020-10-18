package pieces;

import chess.Board;

public class Rook extends ChessPiece{
	public Rook(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('R');
	}

	public boolean checkMove(int newRow, int newCol, Board board) {
		if(canAttack(newRow,newCol,board)) {
			return !testDupMove(newRow,newCol,board);
		}
		return false;
		/*if(errorCheck(newRow,newCol)) {
			return false;
		}
		if(vertCheck(newRow,newCol,board)) {
			return true;
		}
		if(horizCheck(newRow,newCol,board)) {
			return true;
		}
		return false;*/
	}
	
	public ChessPiece copy() {
		return new Rook(this.row(),this.col(),this.isWhite());
	}

	public boolean canAttack(int newRow, int newCol, Board board) {
		if(errorCheck(newRow,newCol)) {
			return false;
		}
		if(vertCheck(newRow,newCol,board)) {
			return true;
		}
		if(horizCheck(newRow,newCol,board)) {
			return true;
		}
		return false;
	}


}
