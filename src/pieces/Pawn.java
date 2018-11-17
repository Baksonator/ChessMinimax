package pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import board.EnPassant;
import board.PawnDouble;
import board.PawnMove;
import board.PawnPromotionMove;
import board.TakingPawnMove;
import board.Move;
import board.Board;
import board.BoardUtil;

public class Pawn extends Piece {

	private static final int[] POSSIBLE_MOVES = { 7, 8, 9, 16 };

	public Pawn(int position, PieceColor pieceColor) {
		super(position, pieceColor, Type.PAWN, true);
	}

	public Pawn(int position, PieceColor pieceColor, boolean firstPosition) {
		super(position, pieceColor, Type.ROOK, firstPosition);
	}
	
	@Override
	public Collection<Move> possibleMoves(Board board) {
		int destination;
		List<Move> possibleMoves = new ArrayList<>();
		for (int currentCandidate : POSSIBLE_MOVES) {
			destination = this.position + currentCandidate * getPieceColor().direction();
			if(!BoardUtil.validPosition(destination)) {
				continue;
			}
			if(currentCandidate == 8 && !board.getTile(destination).occupied()) {
				if(pieceColor.tileForPawnPromotion(destination)) {
					possibleMoves.add(new PawnPromotionMove(new PawnMove(board, this, destination)));
				} else {
					possibleMoves.add(new PawnMove(board, this, destination));
				}
			} else {
				if(currentCandidate == 16 && isFirstMove() && (((BoardUtil.SECOND_ROW[position]) && 
						pieceColor.equals(PieceColor.WHITE)) || (BoardUtil.SEVENTH_ROW[position] && pieceColor.equals(PieceColor.BLACK)))) {
					int firstTile = position + 8 * getPieceColor().direction();
					if(!board.getTile(firstTile).occupied() && !board.getTile(destination).occupied()) {
						possibleMoves.add(new PawnDouble(board, this, destination));
					}
				} else {
					if(currentCandidate == 7 && !((BoardUtil.FIRST_COLUMN[position] && pieceColor.equals(PieceColor.WHITE))
							|| (BoardUtil.EIGHTH_COLUMN[position] && pieceColor.equals(PieceColor.BLACK)))) {
						if(board.getTile(destination).occupied()) {
							Piece attacked = board.getTile(destination).getPiece();
							if(attacked.getPieceColor() != pieceColor) {
								if(pieceColor.tileForPawnPromotion(destination)) {
									possibleMoves.add(new PawnPromotionMove(new TakingPawnMove(board, this, destination, attacked)));
								} else {
									possibleMoves.add(new TakingPawnMove(board, this, destination, attacked));
								}
							}
						} else if(board.getEnPassant() != null) {
							if(board.getEnPassant().getPosition() == position + (pieceColor.oppositeDirection())) {
								Piece piece = board.getEnPassant();
								if(pieceColor != piece.getPieceColor()) {
									possibleMoves.add(new EnPassant(board, this, destination, piece));
								}
							}
						}
					} else {
						if(currentCandidate == 9 && !((BoardUtil.FIRST_COLUMN[position] && pieceColor.equals(PieceColor.BLACK))
								|| (BoardUtil.EIGHTH_COLUMN[position] && pieceColor.equals(PieceColor.WHITE)))) {
							if(board.getTile(destination).occupied()) {
								Piece attacked = board.getTile(destination).getPiece();
								if(attacked.getPieceColor() != pieceColor) {
									if(pieceColor.tileForPawnPromotion(destination)) {
										possibleMoves.add(new PawnPromotionMove(new TakingPawnMove(board, this, destination, attacked)));
									} else {
										possibleMoves.add(new TakingPawnMove(board, this, destination, attacked));
									}
								}
							} else if(board.getEnPassant() != null) {
								if(board.getEnPassant().getPosition() == position - (pieceColor.oppositeDirection())) {
									Piece piece = board.getEnPassant();
									if(pieceColor != piece.getPieceColor()) {
										possibleMoves.add(new EnPassant(board, this, destination, piece));
									}
								}
							}
						}
					}
				}
			}
		}

		return Collections.unmodifiableList(possibleMoves);
	}

	@Override
	public String toString() {
		return Type.PAWN.toString();
	}

	@Override
	public Pawn movePiece(Move move) {
		return new Pawn(move.getDestination(), move.getMovedPiece().getPieceColor());
	}
	
	public Piece getPromotionPiece() {
		return new Queen(position, pieceColor, false);
	}
}
