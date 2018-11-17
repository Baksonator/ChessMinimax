package player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import board.Move;
import board.Board;
import pieces.PieceColor;
import pieces.Piece;
import pieces.King;
import pieces.Type;

public abstract class Player {
	
	protected Board board;
	protected King king;
	protected Collection<Move> possibleMoves;
	private boolean check;
	
	public Player(Board board, Collection<Move> enemy, Collection<Move> possibleMoves) {
		this.board = board;
		this.king = getKing();
		Collection<Move> all = new ArrayList<>();
		all.addAll(possibleMoves);
		all.addAll(calculateCastling(possibleMoves, enemy));
		this.possibleMoves = all;
		this.check = !Player.checkAttack(king.getPosition(), enemy).isEmpty();
	}
	
	public King getKing() {
		for(Piece p : currentPieces()) {
			if(p.getType().equals(Type.KING)) {
				return (King) p;
			}
		}
		throw new RuntimeException("No king");
	}
	
	public boolean possibleMove(Move move) {
		return possibleMoves.contains(move);
	}
	
	public Collection<Move> getPossibleMoves() {
		return possibleMoves;
	}

	public boolean check() {
		return check;
	}
	
	public boolean checkmate() {
		return check && !noMoves();
	}
	
	protected boolean noMoves() {
		for(Move move : possibleMoves) {
			MoveChange change = makeMove(move);
			if(change.getState().isUradjeno()) {
				return true;
			}
		}
		return false;
	}

	public boolean stalemate() {
		return !check && !noMoves();
	}
	
	public boolean castling() {
		return false;
	}
	
	public MoveChange makeMove(Move move) {
		if(!possibleMove(move)) {
			return new MoveChange(this.board, move, MoveState.NEREGULARAN);
		}
		Board nextBoard = move.execute();
		Collection<Move> attacksOnKing = Player.checkAttack(nextBoard.getCurrentPlayer().getEnemy().king.getPosition(),
				nextBoard.getCurrentPlayer().possibleMoves);
		if(!attacksOnKing.isEmpty()) {
			return new MoveChange(this.board, move, MoveState.OSTAVLJA_U_SAHU);
		}
		return new MoveChange(nextBoard, move, MoveState.URADJENO);
	}
	
	public static Collection<Move> checkAttack(int kingPosition, Collection<Move> enemyMoves) {
		List<Move> attacking = new ArrayList<>();
		for(Move move : enemyMoves) {
			if(kingPosition == move.getDestination()) {
				attacking.add(move);
			}
		}
		return attacking;
	}
	
	public abstract Collection<Piece> currentPieces();
	
	public abstract PieceColor getPieceColor();
	
	public abstract Player getEnemy();
	
	public abstract Collection<Move> calculateCastling(Collection<Move> currentMoves, 
			Collection<Move> enemyMoves);
}
