package pieces;

import chess.Board;

public class King extends ChessPiece {
	public King(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('K');
	}
	
	public ChessPiece copy() {
		return new King(this.row(),this.col(),this.isWhite());
	}

	public boolean checkMove(int newRow, int newCol, Board board) {
		if(canAttack(newRow,newCol,board)) {
			return !testDupMove(newRow,newCol,board);
		}
		return false;
		/*if(errorCheck(newRow,newCol)) {
			return false;
		}
		int rowDiff = newRow-this.row();
		int colDiff = newCol - this.col();
		boolean colCheck = Math.abs(colDiff) >= 0 && Math.abs(colDiff) <= 1;
		boolean rowCheck = Math.abs(rowDiff) >= 0 && Math.abs(rowDiff) <= 1;
		if(rowCheck && colCheck) {
			if(board.get(newRow, newCol)==null || board.get(newRow, newCol).isWhite()!=this.isWhite()) {
				return !testDupMove(newRow,newCol,board);
			}
		}
		return false;*/
	}

	public boolean canAttack(int newRow, int newCol, Board board) {
		if(errorCheck(newRow,newCol)) {
			return false;
		}
		int rowDiff = newRow-this.row();
		int colDiff = newCol - this.col();
		boolean colCheck = Math.abs(colDiff) >= 0 && Math.abs(colDiff) <= 1;
		boolean rowCheck = Math.abs(rowDiff) >= 0 && Math.abs(rowDiff) <= 1;
		if(rowCheck && colCheck) {
			if(board.get(newRow, newCol)==null || board.get(newRow, newCol).isWhite()!=this.isWhite()) {
				return true;
			}
		}
		return false;
	}

}
