package board;

import pieces.Piece;
import pieces.Rook;

public class CastleQueenSide extends Castling {

	public CastleQueenSide(Board board, Piece movedPiece, int destination, Rook rook,
			int rookPosition, int rookDestination) {
		super(board, movedPiece, destination, rook, rookPosition, rookDestination);
	}

	@Override
	public String toString() {
		return "0-0-0";
	}
	
	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof CastleQueenSide && super.equals(obj);
	}
}
