package board;

import pieces.Piece;

public class EmptyTile extends Tile {

	protected EmptyTile(int position) {
		super(position);
	}

	@Override
	public boolean occupied() {
		return false;
	}

	@Override
	public Piece getPiece() {
		return null;
	}

	@Override
	public String toString() {
		return "-";
	}
}
