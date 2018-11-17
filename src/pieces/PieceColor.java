package pieces;

import board.BoardUtil;
import player.White;
import player.Black;
import player.Player;

public enum PieceColor {
	WHITE {
		public int direction() {
			return 1;
		}

		@Override
		public Player choosePlayer(White white, Black black) {
			return white;
		}

		@Override
		public int oppositeDirection() {
			return -1;
		}

		@Override
		public boolean tileForPawnPromotion(int position) {
			return BoardUtil.EIGHTH_ROW[position];
		}
	},
	BLACK {
		public int direction() {
			return -1;
		}

		@Override
		public Player choosePlayer(White white, Black black) {
			return black;
		}

		@Override
		public int oppositeDirection() {
			return 1;
		}

		@Override
		public boolean tileForPawnPromotion(int position) {
			return BoardUtil.FIRST_ROW[position];
		}
	};
	
	public abstract int direction();
	
	public abstract int oppositeDirection();

	public abstract Player choosePlayer(White white, Black black);
	
	public abstract boolean tileForPawnPromotion(int position);

}
