package player;

import board.Move;
import board.Board;

public class MoveChange {

	private Board board;
	private Move move;
	private MoveState state;
	
	public MoveChange(Board board, Move move, MoveState state) {
		this.board = board;
		this.move = move;
		this.state = state;
	}

	public Board getBoard() {
		return board;
	}

	public Move getMove() {
		return move;
	}

	public MoveState getState() {
		return state;
	}
	
}
