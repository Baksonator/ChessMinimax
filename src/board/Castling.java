package board;

import board.Board.Builder;
import pieces.Piece;
import pieces.Rook;

public abstract class Castling extends Move {

	protected Rook rook;
	protected int rookPosition;
	protected int rookDestination;
	
	public Castling(Board board, Piece movedPiece, int destination, Rook rook, int rookPosition,
			int rookDestination) {
		super(board, movedPiece, destination);
		this.rook = rook;
		this.rookPosition = rookPosition;
		this.rookDestination = rookDestination;
	}

	public Rook getRook() {
		return rook;
	}

	@Override
	public boolean isCastling() {
		return true; 
	}
	
	@Override
	public Board execute() {
		Builder builder = new Builder();
		for(Piece piece : board.getCurrentPlayer().currentPieces()) {
			if(!movedPiece.equals(piece) && !rook.equals(piece)) {
				builder.setBoardPiece(piece);
			}
		}
		for(Piece piece : board.getCurrentPlayer().getEnemy().currentPieces()) {
			builder.setBoardPiece(piece);
		}
		builder.setBoardPiece(movedPiece.movePiece(this));
		builder.setBoardPiece(new Rook(rookDestination, rook.getPieceColor()));
		builder.setOnTheMove(board.getCurrentPlayer().getEnemy().getPieceColor());
		return builder.build();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((rook == null) ? 0 : rook.hashCode());
		result = prime * result + rookDestination;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Castling other = (Castling) obj;
		if (rook == null) {
			if (other.rook != null)
				return false;
		} else if (!rook.equals(other.rook))
			return false;
		if (rookDestination != other.rookDestination)
			return false;
		if (rookPosition != other.rookPosition)
			return false;
		return true;
	}
	
	
}
