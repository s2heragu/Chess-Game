package pieces;

public class Pawn extends ChessPiece{
	public Pawn(int row, char col, boolean isWhite) {
		super(row,col,isWhite);
		setType('p');
	}

}
