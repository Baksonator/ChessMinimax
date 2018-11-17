package board;

import pieces.Piece;

public class PieceMove extends Move {

	public PieceMove(Board board, Piece movedPiece, int destination) {
		super(board, movedPiece, destination);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof PieceMove && super.equals(obj);
	}
	
	@Override
	public String toString() {
		return movedPiece.getType().toString() + BoardUtil.getPosition(destination);
	}
}
