package board;

import pieces.Piece;

public class PawnMove extends Move {

	public PawnMove(Board board, Piece movedPiece, int destination) {
		super(board, movedPiece, destination);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof PawnMove && super.equals(obj);
	}
	
	@Override
	public String toString() {
		return BoardUtil.getPosition(destination);
	}
}
