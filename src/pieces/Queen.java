package pieces;

import chess.Board;

public class Queen extends ChessPiece {
	public Queen(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('Q');
	}
	
	public ChessPiece copy() {
		return new Queen(this.row(),this.col(),this.isWhite());
	}

	@Override
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
		if(diagCheck(newRow,newCol,board)) {
			return true;
		}
		return false;
	}


}
