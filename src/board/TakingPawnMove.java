package board;

import pieces.Piece;

public class TakingPawnMove extends TakingMove {

	public TakingPawnMove(Board board, Piece movedPiece, int destination, Piece attacked) {
		super(board, movedPiece, destination, attacked);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof TakingPawnMove && super.equals(obj);
	}
	
	@Override
	public String toString() {
		return BoardUtil.getPosition(movedPiece.getPosition()).substring(0, 1) + "x" + 
				BoardUtil.getPosition(destination);
	}
}
