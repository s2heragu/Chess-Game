package pieces;
import chess.Board;

public class Bishop extends ChessPiece {
	public Bishop(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('B');
	}

	public boolean checkMove(int newRow, int newCol, Board board) {
		if(errorCheck(newRow,newCol)) {
			return false;
		}
		if(diagCheck(newRow,newCol,board)) {
			return true;
		}
		return false;
	}

	public ChessPiece copy() {
		return new Bishop(this.row(),this.col(),this.isWhite());
	}

}
