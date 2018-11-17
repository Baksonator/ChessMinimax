package minimax;

import board.Board;
import pieces.Piece;
import player.Player;

public class MateEvaluator implements BoardEvaluator {
	
	@Override
	public int evaluate(Board board, int depth) {
		return playerScore(board, board.getWhite(), depth) - playerScore(board, board.getBlack(), depth);
	}
	
	private int playerScore(Board board, Player player, int depth) {
//		return vrednostFigure(igrac) + mobilnost(igrac) + sah(igrac) + sahMat(igrac, dubina) + rokada(igrac);
		return checkmate(player, depth);
	}

	private static int pieceValues(Player player) {
		int sum = 0;
		for(Piece piece : player.currentPieces()) {
			sum += piece.getPieceValues();
		}
		return sum;
	}
	
	private static int mobility(Player player) {
		return player.getPossibleMoves().size();
	}
	
	private static int check(Player player) {
		return player.getEnemy().check() ? 50 : 0;
	}
	
	private static int checkmate(Player player, int depth) {
		return player.getEnemy().checkmate() ? 100000 : 0;
	}
	
	private static int depthBonus(int depth) {
		return depth == 0 ? 1 : 100 * depth;
	}
	
	private static int castle(Player player) {
		return player.castling() ? 60 : 0;
	}
	
	private static int checkmateMe(Player player, int depth) {
		return player.checkmate() ? 1000000 : 0;
	}
	
	private static int stalemate(Player player) {
		return player.getEnemy().stalemate() ? -1000000 : 0;
	}
}
