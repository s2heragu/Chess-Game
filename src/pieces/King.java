package pieces;

public class King extends ChessPiece {
	public King(int row, int col, boolean isWhite) {
		super(row,col,isWhite);
		setType('K');
	}
	
	public ChessPiece copy() {
		return new King(this.row(),this.col(),this.isWhite());
	}

}
