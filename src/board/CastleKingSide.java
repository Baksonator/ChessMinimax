package board;

import pieces.Piece;
import pieces.Rook;

public class CastleKingSide extends Castling {

	public CastleKingSide(Board board, Piece movedPiece, int destination, Rook rook,
			int rookPosition, int rookDestination) {
		super(board, movedPiece, destination, rook, rookPosition, rookDestination);
	}

	@Override
	public String toString() {
		return "0-0";
	}
	
	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof CastleKingSide && super.equals(obj);
	}
}
