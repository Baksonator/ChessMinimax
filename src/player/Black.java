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

public class Black extends Player {
	
	public Black(Board board, Collection<Move> whiteMoves, Collection<Move> blackMoves) {
		super(board, whiteMoves, blackMoves);
	}

	@Override
	public Collection<Piece> currentPieces() {
		return board.getBlackPieces();
	}

	@Override
	public PieceColor getPieceColor() {
		return PieceColor.BLACK;
	}

	@Override
	public Player getEnemy() {
		return board.getWhite();
	}

	@Override
	public Collection<Move> calculateCastling(Collection<Move> currentMoves, 
			Collection<Move> enemyMoves) {
		List<Move> castles = new ArrayList<>();
		if(king.isFirstMove() && !check()) {
			if(!board.getTile(61).occupied() && !board.getTile(62).occupied()) {
				Tile rookTile = board.getTile(63);
				if(rookTile.occupied() && rookTile.getPiece().isFirstMove()) {
					if(Player.checkAttack(61, enemyMoves).isEmpty() &&
							Player.checkAttack(62, enemyMoves).isEmpty() &&
							rookTile.getPiece().getType().isRook()) {
						castles.add(new CastleKingSide(board, king, 62, (Rook)rookTile.getPiece(), rookTile.getPosition(), 61));
					}
				}
			}
			if(!board.getTile(59).occupied() && !board.getTile(58).occupied() && !board.getTile(57).occupied()) {
				Tile rookTile = board.getTile(56);
				if(rookTile.occupied() && rookTile.getPiece().isFirstMove() && 
						Player.checkAttack(59, enemyMoves).isEmpty() &&
						Player.checkAttack(58, enemyMoves).isEmpty() &&
						rookTile.getPiece().getType().isRook()) {
					castles.add(new CastleQueenSide(board, king, 58, (Rook)rookTile.getPiece(), rookTile.getPosition(), 59));
				}
			}
		}
		return castles;
	}
}
