package board;

import board.Board.Builder;
import pieces.Piece;
import pieces.Pawn;

public class PawnDouble extends Move {

	public PawnDouble(Board board, Piece movedPiece, int destination) {
		super(board, movedPiece, destination);
	}

	@Override
	public Board execute() {
		Builder builder = new Builder();
		for(Piece piece : board.getCurrentPlayer().currentPieces()) {
			if(!movedPiece.equals(piece)) {
				builder.setBoardPiece(piece);
			}
		}
		for(Piece piece : board.getCurrentPlayer().getEnemy().currentPieces()) {
			builder.setBoardPiece(piece);
		}
		Pawn movedPawn = (Pawn) movedPiece.movePiece(this);
		builder.setBoardPiece(movedPawn);
		builder.setEnPassant(movedPawn);
		builder.setOnTheMove(board.getCurrentPlayer().getEnemy().getPieceColor());
		return builder.build();
	}
	
	@Override
	public String toString() {
		return BoardUtil.getPosition(destination);
	}
}
