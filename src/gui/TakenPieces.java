package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import board.Move;
import gui.BoardGUI.MoveLog;
import pieces.PieceColor;
import pieces.Piece;

public class TakenPieces extends JPanel {
	
	private JPanel upper;
	private JPanel lower;
	
	public TakenPieces() {
		super(new BorderLayout());
		setBackground(Color.decode("0xFDF5E6"));
		setBorder(new EtchedBorder(EtchedBorder.RAISED));
		upper = new JPanel(new GridLayout(8, 2));
		lower = new JPanel(new GridLayout(8, 2));
		upper.setBackground(Color.decode("0xFDF5E6"));
		lower.setBackground(Color.decode("0xFDF5E6"));
		add(upper, BorderLayout.NORTH);
		add(lower, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(40, 80));
	}
	
	public void redo(MoveLog moveLog) {
		upper.removeAll();
		lower.removeAll();
		List<Piece> whiteTaken = new ArrayList<>();
		List<Piece> blackTaken = new ArrayList<>();
		for(Move move : moveLog.getMoves()) {
			if(move.isAttack()) {
				Piece takenPiece = move.attackedPiece();
				if(takenPiece.getPieceColor().equals(PieceColor.WHITE)) {
					whiteTaken.add(takenPiece);
				} else {
					blackTaken.add(takenPiece);
				}
			}
		}
		Collections.sort(whiteTaken, new Comparator<Piece>() {
			@Override
			public int compare(Piece o1, Piece o2) {
				return Integer.compare(o1.getPieceValues(), o2.getPieceValues());
			}
		});
		
		Collections.sort(blackTaken, new Comparator<Piece>() {
			@Override
			public int compare(Piece o1, Piece o2) {
				return Integer.compare(o1.getPieceValues(), o2.getPieceValues());
			}
		});
		for(Piece piece : whiteTaken) {
			ImageIcon img = new ImageIcon(getClass().getResource(piece.getPieceColor().toString().substring(0, 1)
					+ piece.toString() + ".gif"));
			lower.add(new JLabel(img));
		}
		for(Piece piece : blackTaken) {
			ImageIcon img = new ImageIcon(getClass().getResource(piece.getPieceColor().toString().substring(0, 1)
					+ piece.toString() + ".gif"));
			lower.add(new JLabel(img));
		}
		validate();
	}
	
}
