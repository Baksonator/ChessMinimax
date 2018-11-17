package board;

import pieces.PieceColor;
import pieces.Piece;

public class OccupiedTile extends Tile {

	private final Piece piece;

	protected OccupiedTile(int position, Piece piece) {
		super(position);
		this.piece = piece;
	}

	@Override
	public boolean occupied() {
		return true;
	}

	@Override
	public Piece getPiece() {
		return piece;
	}

	@Override
	public String toString() {
		if(piece.getPieceColor().equals(PieceColor.WHITE)) {
			return piece.toString();
		} else {
			return piece.toString().toLowerCase();
		}
	}
}
