package pieces;

public class Knight extends ChessPiece {
	public Knight(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('N');
	}
}
