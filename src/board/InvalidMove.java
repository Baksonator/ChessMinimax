package board;


public class InvalidMove extends Move {

	public InvalidMove() {
		super(null, 65);
	}

	@Override
	public Board execute() {
		throw new RuntimeException("Not possible");
	}
	
	@Override
	public int getTrenutnaPozicija() {
		return -1;
	}
}
