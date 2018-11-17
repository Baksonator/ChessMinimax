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

public class King extends Piece {

	private static final int[] POSSIBLE_MOVES = { -9, -8, -7, -1, 1, 7, 8, 9 };
	
	public King(int position, PieceColor pieceColor) {
		super(position, pieceColor, Type.KING, false);
	}
	
	public King(int position, PieceColor color, boolean firstPosition) {
		super(position, color, Type.KING, firstPosition);
	}

	@Override
	public Collection<Move> possibleMoves(Board board) {
		int destination;
		List<Move> possibleMoves = new ArrayList<>();
		for(int currentCandidate : POSSIBLE_MOVES) {
			destination = position + currentCandidate;
			if(firstColumn(position, currentCandidate) || eighthColumn(position, currentCandidate)) {
				continue;
			}
			if(BoardUtil.validPosition(destination)) {
				Tile candidateTile = board.getTile(destination);
				if (!candidateTile.occupied()) {
					possibleMoves.add(new PieceMove(board, this, destination));
				} else {
					Piece occupyingPiece = candidateTile.getPiece();
					PieceColor occupyingColor = occupyingPiece.getPieceColor();
					if (this.pieceColor != occupyingColor) {
						possibleMoves.add(new TakingPieceMove(board, this, destination, occupyingPiece));
					}
				}
			}
		}
		
		return Collections.unmodifiableList(possibleMoves);
	}
	
	private static boolean firstColumn(int currentPos, int currentCandidate) {
		return BoardUtil.FIRST_COLUMN[currentPos] && ((currentCandidate == 7) || (currentCandidate == -1) || 
				(currentCandidate == -9));
	}
	
	private static boolean eighthColumn(int currentPos, int currentCandidate) {
		return BoardUtil.EIGHTH_COLUMN[currentPos] && ((currentCandidate == -7) || (currentCandidate == 1) || 
				(currentCandidate == 9));
	}
	
	@Override
	public String toString() {
		return Type.KING.toString();
	}

	@Override
	public King movePiece(Move move) {
		return new King(move.getDestination(), move.getMovedPiece().getPieceColor());
	}
}
