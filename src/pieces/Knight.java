package pieces;

import chess.Board;

public class Knight extends ChessPiece {
	public Knight(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('N');
	}
	
	public ChessPiece copy() {
		return new Knight(this.row(),this.col(),this.isWhite());
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
		if(colDiff!=0 && rowDiff!=0 && Math.abs(colDiff)+Math.abs(rowDiff)==3) {
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
		if(colDiff!=0 && rowDiff!=0 && Math.abs(colDiff)+Math.abs(rowDiff)==3) {
			if(board.get(newRow, newCol)==null || board.get(newRow, newCol).isWhite()!=this.isWhite()) {
				return true;
			}
		}
		return false;
	}

}
