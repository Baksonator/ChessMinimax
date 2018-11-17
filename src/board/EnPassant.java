package board;


import board.Board.Builder;
import pieces.Piece;

public class EnPassant extends TakingPawnMove {

	public EnPassant(Board board, Piece movedPiece, int destination, Piece attacked) {
		super(board, movedPiece, destination, attacked);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof EnPassant && super.equals(obj);
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
			if(!piece.equals(attackedPiece())) {
				builder.setBoardPiece(piece);
			}
		}
		builder.setBoardPiece(movedPiece.movePiece(this));
		builder.setOnTheMove(board.getCurrentPlayer().getEnemy().getPieceColor());
		return builder.build();
	}
}
