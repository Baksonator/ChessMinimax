package pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import board.PieceMove;
import board.TakingPieceMove;
import board.Tile;
import board.Move;
import board.Board;
import board.BoardUtil;

public class Knight extends Piece {

	private final static int[] POSSIBLE_MOVES = { -17, -15, -10, -6, 6, 10, 15, 17 };

	public Knight(int position, PieceColor pieceColor) {
		super(position, pieceColor, Type.KNIGHT, true);
	}
	
	public Knight(int position, PieceColor pieceColor, boolean firstPosition) {
		super(position, pieceColor, Type.ROOK, firstPosition);
	}

	@Override
	public Collection<Move> possibleMoves(Board board) {
		int destination;
		List<Move> possibleMoves = new ArrayList<>();
		for (int currentCandidate : POSSIBLE_MOVES) {
			destination = this.position + currentCandidate;
			if(firstColumn(this.position, currentCandidate) || secondColumn(this.position, currentCandidate) ||
					seventhColumn(this.position, currentCandidate) || eighthColumn(this.position, currentCandidate)) {
				continue;
			}
			if (BoardUtil.validPosition(destination)) {
				Tile candidateTile = board.getTile(destination);
				if (!candidateTile.occupied()) {
					possibleMoves.add(new PieceMove(board, this, destination));
				} else {
					Piece occupyingPiece = candidateTile.getPiece();
					PieceColor occupyingTile = occupyingPiece.getPieceColor();
					if (this.pieceColor != occupyingTile) {
						possibleMoves.add(new TakingPieceMove(board, this, destination, occupyingPiece));
					}
				}
			}
		}

		return Collections.unmodifiableList(possibleMoves);
	}

	private static boolean firstColumn(int currentPos, int currentCandidate) {
		return BoardUtil.FIRST_COLUMN[currentPos] && ((currentCandidate == 6) || (currentCandidate == -10) || 
				(currentCandidate == 15) || (currentCandidate == -17));
	}
	
	private static boolean secondColumn(int currentPos, int currentCandidate) {
		return BoardUtil.SECOND_COLUMN[currentPos] && ((currentCandidate == 6) || (currentCandidate == -10));
	}
	
	private static boolean seventhColumn(int currentPos, int currentCandidate) {
		return BoardUtil.SEVENTH_COLUMN[currentPos] && ((currentCandidate == -6) || (currentCandidate == 10));
	}
	
	private static boolean eighthColumn(int currentPos, int currentCandidate) {
		return BoardUtil.EIGHTH_COLUMN[currentPos] && ((currentCandidate == -6) || (currentCandidate == 10) || 
				(currentCandidate == -15) || (currentCandidate == 17));
	}
	
	@Override
	public String toString() {
		return Type.KNIGHT.toString();
	}

	@Override
	public Knight movePiece(Move move) {
		return new Knight(move.getDestination(), move.getMovedPiece().getPieceColor());
	}
}
