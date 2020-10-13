package pieces;

public abstract class ChessPiece {
	private int row;
	private int col;
	private boolean isWhite;
	private char type;
	
	public ChessPiece(int row, int col, boolean isWhite) {
		this.isWhite = isWhite;
		this.row = row;
		this.col = col;
	}
	
	public int row() {
		return this.row;
	}
	
	public int col() {
		return this.col;
	}
	
	public boolean isWhite() {
		return this.isWhite;
	}
	
	public char type() {
		return this.type;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	protected void setType(char type) {
		this.type = type;
	}
	
	public String toString() {
		if(this.isWhite) {
			return "w"+this.type;
		}
		return "b"+this.type;
	}
}
