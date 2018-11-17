package player;

public enum MoveState {
	URADJENO {
		@Override
		public
		boolean isUradjeno() {
			return true;
		}
	},
	NEREGULARAN {
		@Override
		public
		boolean isUradjeno() {
			return false;
		} 
	},
	OSTAVLJA_U_SAHU {
		@Override
		public
		boolean isUradjeno() {
			return false;
		}
	};
	
	public abstract boolean isUradjeno();
}
