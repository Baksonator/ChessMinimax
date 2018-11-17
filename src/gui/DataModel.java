package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import board.Move;

public class DataModel extends DefaultTableModel {
	
	private List<Turn> values;
	private static final String[] NAMES = {"White", "Black"};
	
	public DataModel() {
		values = new ArrayList<>();
	}
	
	public void clear() {
		values.clear();
		setRowCount(0);
	}
	
	@Override
	public int getRowCount() {
		if(values == null) {
			return 0;
		}
		return values.size();
	}
	
	@Override
	public int getColumnCount() {
		return NAMES.length;
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		Turn currentRow = values.get(row);
		if(column == 0) {
			return currentRow.getWhiteMove();
		} else if(column == 1) {
			return currentRow.getBlackMove();
		}
		return null;
	}
	
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		Turn currentRow;
		if(values.size() <= row) {
			currentRow = new Turn();
			values.add(currentRow);
		} else {
			currentRow = values.get(row);
		}
		if(column == 0) {
			currentRow.setWhiteMove((String)aValue);
			fireTableRowsInserted(row, row);
		} else if(column == 1) {
			currentRow.setBlackMove((String)aValue);
			fireTableCellUpdated(row, column);
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Move.class;
	}
	
	@Override
	public String getColumnName(int column) {
		return NAMES[column];
	}
}
