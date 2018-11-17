package player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import board.CastleKingSide;
import board.Tile;
import board.Move;
import board.Board;
import board.CastleQueenSide;
import pieces.PieceColor;
import pieces.Piece;
import pieces.Rook;

public class White extends Player {

	public White(Board board, Collection<Move> whiteMoves, Collection<Move> blackMoves) {
		super(board, blackMoves, whiteMoves);
	}

	@Override
	public Collection<Piece> currentPieces() {
		return board.getWhitePieces();
	}

	@Override
	public PieceColor getPieceColor() {
		return PieceColor.WHITE;
	}

	@Override
	public Player getEnemy() {
		return board.getBlack();
	}

	@Override
	public Collection<Move> calculateCastling(Collection<Move> currentMoves,
			Collection<Move> enemyMoves) {
		List<Move> castles = new ArrayList<>();
		if(getKing().isFirstMove() && !check()) {
			if(!board.getTile(5).occupied() && !board.getTile(6).occupied()) {
				Tile rookTile = board.getTile(7);
				if(rookTile.occupied() && rookTile.getPiece().isFirstMove()) {
					if(Player.checkAttack(5, enemyMoves).isEmpty() &&
							Player.checkAttack(6, enemyMoves).isEmpty() &&
							rookTile.getPiece().getType().isRook()) {
						castles.add(new CastleKingSide(board, getKing(), 6, (Rook)rookTile.getPiece(), rookTile.getPosition(), 5));
					}
				}
			}
			if(!board.getTile(3).occupied() && !board.getTile(2).occupied() && !board.getTile(1).occupied()) {
				Tile rookTile = board.getTile(0);
				if(rookTile.occupied() && rookTile.getPiece().isFirstMove() && 
						Player.checkAttack(3, enemyMoves).isEmpty() &&
						Player.checkAttack(2, enemyMoves).isEmpty() &&
						rookTile.getPiece().getType().isRook()) {
					castles.add(new CastleQueenSide(board, getKing(), 2, (Rook)rookTile.getPiece(), rookTile.getPosition(), 3));
				}
			}
		}
		return castles;
	}
}
