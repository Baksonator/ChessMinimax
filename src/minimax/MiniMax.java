package minimax;

import board.Move;
import board.Board;
import board.BoardUtil;
import board.Move.MoveMaking;
import pieces.PieceColor;
import player.MoveChange;

public class MiniMax implements MoveStrategy {

	private BoardEvaluator boardEvaluator;
	private int depth;
	
	public MiniMax(int dubina) {
		boardEvaluator = new MateEvaluator();
		this.depth = dubina;
	}
	
	@Override
	public Move execute(Board board) {
		long start = System.currentTimeMillis();
		Move bestMove = null;
		int maxValue = Integer.MIN_VALUE;
		int minValue = Integer.MAX_VALUE;
		int currentValue;
		System.out.println(board.getCurrentPlayer() + "Thinking to depth " + depth);
		//int brPotez = tabla.getTrenutni().getMoguciPotezi().size();
		for(Move move : board.getCurrentPlayer().getPossibleMoves()) {
			MoveChange moveChange = board.getCurrentPlayer().makeMove(move);
			if(moveChange.getState().isUradjeno()) {
				currentValue = board.getCurrentPlayer().getPieceColor().equals(PieceColor.WHITE) ? 
						min(moveChange.getBoard(), depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE) :
						max(moveChange.getBoard(), depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
				if(board.getCurrentPlayer().getPieceColor().equals(PieceColor.WHITE) && currentValue >= maxValue) {
					if(moveChange.getBoard().getCurrentPlayer().stalemate()) {
						continue;
					}
					maxValue = currentValue;
					bestMove = move;
					System.out.println(bestMove + " " + currentValue);
				} else if(board.getCurrentPlayer().getPieceColor().equals(PieceColor.BLACK) && currentValue <= minValue) {
					minValue = currentValue;
					bestMove = move;
				}
			}
		}
		long end = System.currentTimeMillis() - start;
//		System.out.println("Razmisljao je " + ((kraj - pocetak) / 1000) );
		return bestMove;
	}
	
	public int min(Board board, int depth, int alpha, int beta) {
		if(depth == 0) {
//			System.out.println(procenjivacTable.proceni(tabla, dubina));
//			if(procenjivacTable.proceni(tabla, dubina) > 0) {
//				System.out.println("Jeste");
//			}
			return boardEvaluator.evaluate(board, depth);
		}
		int minValue = Integer.MAX_VALUE;
		for(Move move : board.getCurrentPlayer().getPossibleMoves()) {
			MoveChange moveChange = board.getCurrentPlayer().makeMove(move);
			if(moveChange.getState().isUradjeno()) {
				int currentValue = max(moveChange.getBoard(), depth - 1, alpha, beta);
				if(currentValue <= minValue) {
					minValue = currentValue;
				}
				beta = Integer.min(beta, minValue);
				if(beta <= alpha) {
					break;
				}
			}
		}
		return minValue;
	}
	
	public int max(Board board, int depth, int alpha, int beta) {
		if(depth == 0) {
//			System.out.println(procenjivacTable.proceni(tabla, dubina));
			return boardEvaluator.evaluate(board, depth);
		}
		int maxValue = Integer.MIN_VALUE;
		for(Move move : board.getCurrentPlayer().getPossibleMoves()) {
			MoveChange moveChange = board.getCurrentPlayer().makeMove(move);
			if(moveChange.getState().isUradjeno()) {
				int currentValue = min(moveChange.getBoard(), depth - 1, alpha, beta);
				if(currentValue >= maxValue) {
					maxValue = currentValue;
				}
				alpha = Integer.max(alpha, maxValue);
				if(beta <= alpha) {
					break;
				}
			}
		}
		return maxValue;
	}
	
	private static boolean endOfGame(Board board) {
		return board.getCurrentPlayer().checkmate() || board.getCurrentPlayer().stalemate();
	}

	@Override
	public String toString() {
		return "MiniMax";
	}
}
