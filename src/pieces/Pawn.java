package pieces;

public class Pawn extends ChessPiece{
	public Pawn(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('p');
	}
	
	public ChessPiece copy() {
		return new Pawn(this.row(),this.col(),this.isWhite());
	}

}
