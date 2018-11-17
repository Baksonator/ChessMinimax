package board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import pieces.Piece;

public abstract class Tile {

	protected final int position;
	private static final Map<Integer, EmptyTile> EMPTY_TILES = createEmpty();

	protected Tile(int position) {
		this.position = position;
	}

	public abstract boolean occupied();

	public abstract Piece getPiece();
	
	public int getPosition() {
		return position;
	}

	private static Map<Integer, EmptyTile> createEmpty() {
		Map<Integer, EmptyTile> emptyMap = new HashMap<>();
		for (int i = 0; i < 64; i++) {
			emptyMap.put(i, new EmptyTile(i));
		}
		return Collections.unmodifiableMap(emptyMap);
	}

	public static Tile createTile(int position, Piece piece) {
		if (piece != null) {
			return new OccupiedTile(position, piece);
		} else {
			return EMPTY_TILES.get(position);
		}
	}
}
