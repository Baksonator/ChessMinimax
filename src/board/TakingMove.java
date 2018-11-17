package board;

import pieces.Piece;

public class TakingMove extends Move {

	private Piece attacked;
	
	public TakingMove(Board board, Piece movedPiece, int destination, Piece attacked) {
		super(board, movedPiece, destination);
		this.attacked = attacked;
	}
	
	@Override
	public boolean isAttack() {
		return true;
	}
	
	@Override
	public Piece attackedPiece() {
		return attacked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((attacked == null) ? 0 : attacked.hashCode());
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
		TakingMove other = (TakingMove) obj;
		if (attacked == null) {
			if (other.attacked != null)
				return false;
		} else if (!attacked.equals(other.attacked))
			return false;
		return true;
	}

	
	
}
