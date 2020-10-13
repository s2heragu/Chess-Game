package pieces;

public class Pawn extends ChessPiece{
	public Pawn(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('p');
	}

}
