package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import board.Move;
import board.Board;
import gui.BoardGUI.MoveLog;
import pieces.PieceColor;

public class MoveHistory extends JPanel {
	
	private DataModel model;
	private JScrollPane scroll;
	
	public MoveHistory() {
		setLayout(new BorderLayout());
		model = new DataModel();
		JTable table = new JTable(model);
		table.setRowHeight(15);
		scroll = new JScrollPane(table);
		scroll.setColumnHeaderView(table.getTableHeader());
		scroll.setPreferredSize(new Dimension(100, 400));
		add(scroll, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void redo(Board board, MoveLog moveLog) {
		int currentRow = 0;
		model.clear();
		for(Move move : moveLog.getMoves()) {
			String moveText = move.toString();
			if(move.getMovedPiece().getPieceColor().equals(PieceColor.WHITE)) {
				model.setValueAt(moveText, currentRow, 0);
			} else if(move.getMovedPiece().getPieceColor().equals(PieceColor.BLACK)) {
				model.setValueAt(moveText, currentRow, 1);
				currentRow++;
			}
		}
		if(moveLog.getMoves().size() > 0) {
			Move lastMove = moveLog.getMoves().get(moveLog.size() - 1);
			String moveText = lastMove.toString();
			if(lastMove.getMovedPiece().getPieceColor().equals(PieceColor.WHITE)) {
				model.setValueAt(moveText + calculateCheckAndCheckmate(board), currentRow, 0);
			} else if(lastMove.getMovedPiece().getPieceColor().equals(PieceColor.BLACK)) {
				model.setValueAt(moveText + calculateCheckAndCheckmate(board), currentRow - 1, 1);
			}
		}
		JScrollBar scrollbar = scroll.getVerticalScrollBar();
		scrollbar.setValue(scrollbar.getMaximum());
	}
	
	private String calculateCheckAndCheckmate(Board board) {
		if(board.getCurrentPlayer().checkmate()) {
			return "#";
		} else if(board.getCurrentPlayer().check()) {
			return "+";
		}
		return "";
	}
	
}
