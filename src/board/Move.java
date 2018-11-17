package board;

import board.Board.Builder;
import pieces.Piece;

public abstract class Move {
	protected Board board;
	protected Piece movedPiece;
	protected int destination;
	protected boolean firstMove;
	public static final Move INVALID = new InvalidMove();
	
	public Move(Board board, Piece movedPiece, int destination) {
		this.board = board;
		this.movedPiece = movedPiece;
		this.destination = destination;
		this.firstMove = movedPiece.isFirstMove();
	}
	
	public Move(Board board, int destination) {
		this.board = board;
		this.destination = destination;
		movedPiece = null;
		firstMove = false;
	}

	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

	public boolean isAttack() {
		return false;
	}
	
	public boolean isCastling() {
		return false;
	}
	
	public Piece attackedPiece() {
		return null;
	}
	
	public Board getBoard() {
		return board;
	}

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
		builder.setBoardPiece(movedPiece.movePiece(this));
		builder.setOnTheMove(board.getCurrentPlayer().getEnemy().getPieceColor());
		return builder.build();
	}

	public Piece getMovedPiece() {
		return movedPiece;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + destination;
		result = prime * result + movedPiece.hashCode();
		result = prime * result + movedPiece.getPosition();
		return result;
	}
	
	public int getTrenutnaPozicija() {
		return getMovedPiece().getPosition();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		if (destination != other.destination)
			return false;
		if (movedPiece == null) {
			if (other.movedPiece != null)
				return false;
		} else if (!movedPiece.equals(other.movedPiece))
			return false;
		if(getTrenutnaPozicija() != other.getTrenutnaPozicija()) {
			return false;
		}
		return true;
	}

	public static class MoveMaking {
		
		public static Move createMove(Board board, int currentPosition, int destination) {
			for(Move move : board.allPossibleMoves()) {
//				System.out.println(potez);
				if(move.getTrenutnaPozicija() == currentPosition &&
						move.getDestination() == destination) {
					return move;
				}
			}
			return INVALID;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return movedPiece + " "  + destination;
	}
}
