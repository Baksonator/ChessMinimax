package board;

import pieces.Piece;

public class TakingPieceMove extends TakingMove {

	public TakingPieceMove(Board board, Piece movedPiece, int destination, Piece attacked) {
		super(board, movedPiece, destination, attacked);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof TakingPieceMove && super.equals(obj);
	}
	
	@Override
	public String toString() {
		return movedPiece.getType().toString() + BoardUtil.getPosition(destination); 
	}
}
