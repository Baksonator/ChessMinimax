package board;

import java.util.HashMap;
import java.util.Map;

public class BoardUtil {

	public static final boolean[] FIRST_COLUMN = initColumn(0);
	public static final boolean[] SECOND_COLUMN = initColumn(1);
	public static final boolean[] SEVENTH_COLUMN = initColumn(6);
	public static final boolean[] EIGHTH_COLUMN = initColumn(7);
	public static final boolean[] FIRST_ROW = initRow(0);
	public static final boolean[] SECOND_ROW = initRow(8);
	public static final boolean[] THRID_ROW = initRow(16);
	public static final boolean[] FOURTH_ROW = initRow(24);
	public static final boolean[] FIFTH_ROW = initRow(32);
	public static final boolean[] SIXTH_ROW = initRow(40);
	public static final boolean[] SEVENTH_ROW = initRow(48);
	public static final boolean[] EIGHTH_ROW = initRow(56);
	public static final String[] ALGEBRA_NOTATION = initializeNotation();
	public static final Map<String, Integer> POSITION_TO_COORDINATE = makeMap();
	
	public static boolean validPosition(int destination) {
		return destination >= 0 && destination < 64;
	}
	
	private static boolean[] initColumn(int column) {
		boolean[] columns = new boolean[64];
		while(column < 64) {
			columns[column] = true;
			column += 8;
		}
		return columns;
	}
	
	private static boolean[] initRow(int row) {
		boolean[] rows = new boolean[64];
		do {
			rows[row] = true;
			row++;
		} while(row % 8 != 0);
		return rows;
	}
	
	
	public static int getCoordinate(String position) {
		return POSITION_TO_COORDINATE.get(position);
	}
	
	public static String getPosition(int cooridnate) {
		return ALGEBRA_NOTATION[cooridnate];
	}
	
	private static String[] initializeNotation() {
		return new String[] {
				"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1",
				"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
				"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
				"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
				"a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
				"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
				"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
				"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
		};
	}
	
	private static Map<String, Integer> makeMap() {
		Map<String, Integer> coordinate = new HashMap<>();
		for(int i = 0; i < 64; i++) {
			coordinate.put(ALGEBRA_NOTATION[i], i);
		}
		return coordinate;
	}
}
