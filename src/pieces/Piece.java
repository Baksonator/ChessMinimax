package pieces;

import java.util.Collection;

import board.Move;
import board.Board;

public abstract class Piece {

	protected final int position;
	protected final PieceColor pieceColor;
	protected final boolean firstMove;
	protected Type type;
	private int hash;

	public Piece(int position, PieceColor pieceColor, Type type, boolean firstMove) {
		this.position = position;
		this.pieceColor = pieceColor;
		this.firstMove = firstMove;
		this.type = type;
		hash = calculateHash();
	}

	private int calculateHash() {
		int res = type.hashCode();
		res = 31 * res + pieceColor.hashCode();
		res = 31 * res + position;
		res = 31 * res + (isFirstMove() ? 1 : 0);
		return res;
	}

	public abstract Collection<Move> possibleMoves(Board tabla);
	
	public abstract Piece movePiece(Move potez);

	public PieceColor getPieceColor() {
		return pieceColor;
	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public int getPosition() {
		return position;
	}

	public Type getType() {
		return type;
	}
	
	@Override
	public int hashCode() {
		return hash;
	}
	
	public int getPieceValues() {
		return type.getPieceValue();
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(!(obj instanceof Piece)) {
			return false;
		}
		Piece piece = (Piece) obj;
		return position == piece.getPosition() && type == piece.getType() && pieceColor == piece.getPieceColor() 
				&& firstMove == piece.isFirstMove();
	}
}
