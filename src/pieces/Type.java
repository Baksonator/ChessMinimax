package pieces;

public enum Type {
	PAWN("P", 100) {
		@Override
		public boolean isRook() {
			// TODO Auto-generated method stub
			return false;
		}
	},
	KNIGHT("S", 300) {
		@Override
		public boolean isRook() {
			// TODO Auto-generated method stub
			return false;
		}
	},
	BISHOP("L", 300) {
		@Override
		public boolean isRook() {
			// TODO Auto-generated method stub
			return false;
		}
	},
	ROOK("T", 500) {
		@Override
		public boolean isRook() {
			// TODO Auto-generated method stub
			return true;
		}
	},
	QUEEN("D", 900) {
		@Override
		public boolean isRook() {
			// TODO Auto-generated method stub
			return false;
		}
	},
	KING("K", 10000) {
		@Override
		public boolean isRook() {
			// TODO Auto-generated method stub
			return false;
		}
	};
	
	private String pieceName;
	private int pieceValue;

	private Type(String pieceName, int pieceValue) {
		this.pieceName = pieceName;
		this.pieceValue = pieceValue;
	}
	
	@Override
	public String toString() {
		return pieceName;
	}
	
	public abstract boolean isRook();
	
	public int getPieceValue() {
		return pieceValue;
	}
}
