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

public class Bishop extends Piece {

	private static final int[] POSSIBLE_MOVES = { -9, -7, 7, 9 };

	public Bishop(int position, PieceColor pieceColor) {
		super(position, pieceColor, Type.BISHOP, true);
	}
	
	public Bishop(int position, PieceColor pieceColor, boolean firstPosition) {
		super(position, pieceColor, Type.ROOK, firstPosition);
	}

	@Override
	public Collection<Move> possibleMoves(Board board) {
		int destination;
		List<Move> possibleMoves = new ArrayList<>();
		for (int currentCandidate : POSSIBLE_MOVES) {
			destination = this.position;
			while(BoardUtil.validPosition(destination)) {
				if(firstColumn(destination, currentCandidate) || eighthColumn(destination, currentCandidate)) {
					break;
				}
				destination += currentCandidate;
				if(BoardUtil.validPosition(destination)) {
					Tile kandidatPolje = board.getTile(destination);
					if (!kandidatPolje.occupied()) {
						possibleMoves.add(new PieceMove(board, this, destination));
					} else {
						Piece occupyingPiece = kandidatPolje.getPiece();
						PieceColor occupyingColor = occupyingPiece.getPieceColor();
						if (this.pieceColor != occupyingColor) {
							possibleMoves.add(new TakingPieceMove(board, this, destination, occupyingPiece));
						}
						break;
					}
				}
			}
		}

		return Collections.unmodifiableList(possibleMoves);
	}

	private static boolean firstColumn(int currentPos, int currentCandidate) {
		return BoardUtil.FIRST_COLUMN[currentPos] && (currentCandidate == -9 || currentCandidate == 7);
	}
	
	private static boolean eighthColumn(int currentPos, int currentCandidate) {
		return BoardUtil.EIGHTH_COLUMN[currentPos] && (currentCandidate == 9 || currentCandidate == -7);
	}
	
	@Override
	public String toString() {
		return Type.BISHOP.toString();
	}

	@Override
	public Bishop movePiece(Move move) {
		return new Bishop(move.getDestination(), move.getMovedPiece().getPieceColor());
	}
}
