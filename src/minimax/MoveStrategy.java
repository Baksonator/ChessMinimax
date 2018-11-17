package minimax;

import board.Move;
import board.Board;

public interface MoveStrategy {

	Move execute(Board board);
}
