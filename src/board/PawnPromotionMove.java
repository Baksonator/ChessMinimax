package board;

import board.Board.Builder;
import pieces.Piece;
import pieces.Pawn;

public class PawnPromotionMove extends Move {

	Move decoratedMove;
	Pawn pawn;
	
	public PawnPromotionMove(Move decoratedMove) {
		super(decoratedMove.getBoard(), decoratedMove.getMovedPiece(), decoratedMove.getDestination());
		this.decoratedMove = decoratedMove;
		pawn = (Pawn) decoratedMove.getMovedPiece();
	}
	
	@Override
	public Board execute() {
		Board pawnMovedBoard = decoratedMove.execute();
		Builder builder = new Builder();
		for(Piece piece : pawnMovedBoard.getCurrentPlayer().currentPieces()) {
			if(!pawn.equals(piece)) {
				builder.setBoardPiece(piece);
			}
		}
		for(Piece piece : pawnMovedBoard.getCurrentPlayer().getEnemy().currentPieces()) {
			builder.setBoardPiece(piece);
		}
		builder.setBoardPiece(pawn.getPromotionPiece().movePiece(this));
		builder.setOnTheMove(pawnMovedBoard.getCurrentPlayer().getPieceColor());
		return builder.build();
	}
	
	@Override
	public boolean isAttack() {
		return decoratedMove.isAttack();
	}
	
	@Override
	public Piece attackedPiece() {
		return decoratedMove.attackedPiece();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((decoratedMove == null) ? 0 : decoratedMove.hashCode());
		result = prime * result + ((pawn == null) ? 0 : pawn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof PawnPromotionMove && super.equals(obj);
	}
	
}
