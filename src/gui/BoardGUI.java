package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import board.Tile;
import board.Move;
import board.Board;
import board.BoardUtil;
import board.Move.MoveMaking;
import minimax.MiniMax;
import minimax.MoveStrategy;
import pieces.Piece;
import player.MoveChange;

public class BoardGUI {
	private JFrame gameFrame;
	private MoveHistory moveHistory;
	private TakenPieces takenPieces;
	private BoardPanel boardPanel;
	private MoveLog moveLog;
	private Board board;
	private Tile startTile;
	private Tile destinationTile;
	private Piece movedHuman;
	private int number = 5;
	
	public BoardGUI() {
		gameFrame = new JFrame("Chess");
		gameFrame.setLayout(new BorderLayout());
		JMenuBar menu = new JMenuBar();
		createMenu(menu);
		gameFrame.setJMenuBar(menu);
		
		board = Board.makeCustomBoard();
		moveHistory = new MoveHistory();
		takenPieces = new TakenPieces();
		boardPanel = new BoardPanel();
		moveLog = new MoveLog();
		JButton button = new JButton("Computer move");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MoveStrategy strategy = new MiniMax(number);
				number -= 2;
				Move move = strategy.execute(board);
				MoveChange change = board.getCurrentPlayer().makeMove(move);
				if(change.getState().isUradjeno()) {
					board = change.getBoard();
					moveLog.addMove(move);
				}
				moveHistory.redo(board, moveLog);
				takenPieces.redo(moveLog);
				boardPanel.drawBoard(board);
				
			}
		});
		gameFrame.add(takenPieces, BorderLayout.WEST);
		gameFrame.add(boardPanel, BorderLayout.CENTER);
		gameFrame.add(moveHistory, BorderLayout.EAST);
		gameFrame.add(button, BorderLayout.SOUTH);
		
		gameFrame.setVisible(true);
		gameFrame.setSize(600, 600);
	}
	
	private void createMenu(JMenuBar menu) {
		menu.add(createFileMenu());
	}
	
	private JMenu createFileMenu() {
		JMenu file = new JMenu("File");
		JMenuItem openPGN = new JMenuItem("Load file");
		openPGN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		file.add(openPGN);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		file.add(exit);
		return file;
	}
	
	public static class MoveLog {
		private List<Move> moves;
		
		public MoveLog() {
			moves = new ArrayList<>();
		}

		public List<Move> getMoves() {
			return moves;
		}
		
		public void addMove(Move move) {
			moves.add(move);
		}
		
		public int size() {
			return moves.size();
		}
		
		public void clear() {
			moves.clear();
		}
		
		public Move removeMove(int index) {
			return moves.remove(index);
		}
		
		public boolean removeMove(Move move) {
			return moves.remove(move);
		}
	}
	
	private class BoardPanel extends JPanel {
		List<TilePanel> tilePanels;
		
		public BoardPanel() {
			super(new GridLayout(8, 8));
			tilePanels = new ArrayList<>();
			int j = 0;
			for(int i = 63; i >= 0; i--) {
				if((i + 1) % 8 == 0) {
					j = i - 7;
				}
				TilePanel tilePanel = new TilePanel(this, j);
				tilePanels.add(tilePanel);
				add(tilePanel);
				j++;
			}
			setPreferredSize(new Dimension(400, 350));
			validate();
		}
		
		public void drawBoard(Board board) {
			removeAll();
			for(TilePanel tilePanel : tilePanels) {
				tilePanel.drawTile(board);
				add(tilePanel);
			}
			validate();
			repaint();
		}
	}
	
	private class TilePanel extends JPanel {
		private int tileId;
		
		public TilePanel(BoardPanel boardPanel, int tileId) {
			super(new GridBagLayout());
			this.tileId = tileId;
			setPreferredSize(new Dimension(10, 10));
			odrediBojuPolja();
			determineIconPiece(board);
			addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(SwingUtilities.isRightMouseButton(arg0)) {
						startTile = null;
						destinationTile = null;
						movedHuman = null;
					} else {
						if(SwingUtilities.isLeftMouseButton(arg0)) {
//							if(tabla.getAnPasan() != null) {
//								System.out.println(tabla.getAnPasan() + "" + tabla.getAnPasan().getPozicija());
//							}
							System.out.println("Clicked tile " + tileId);
							if(startTile == null) {
//								System.out.println(tabla.getTrenutni().getMoguciPotezi());
								startTile = board.getTile(tileId);
								movedHuman = startTile.getPiece();
								//System.out.println(pomerenaCovek);
								if(movedHuman == null) {
									startTile = null;
								}
							} else {
								destinationTile = board.getTile(tileId);
								Move move = MoveMaking.createMove(board, startTile.getPosition(), 
										destinationTile.getPosition());
								MoveChange change = board.getCurrentPlayer().makeMove(move);
								if(change.getState().isUradjeno()) {
									board = change.getBoard();
									moveLog.addMove(move);
//									System.out.println();
//									System.out.println(tabla);
								}
								startTile = null;
								destinationTile = null;
								movedHuman = null;
							}
							SwingUtilities.invokeLater(new Runnable() {
								
								@Override
								public void run() {
									moveHistory.redo(board, moveLog);
									takenPieces.redo(moveLog);
									boardPanel.drawBoard(board);
								}
							});
						}
					}
				}
			});
			validate();
		}
		
		public void drawTile(Board board) {
			odrediBojuPolja();
			determineIconPiece(board);
			validate();
			repaint();
		}

		private void determineIconPiece(Board board) {
			removeAll();
			if(board.getTile(tileId).occupied()) {
				ImageIcon img = new ImageIcon(getClass().getResource(board.getTile(tileId).getPiece()
						.getPieceColor().toString().substring(0, 1) + board.getTile(tileId).getPiece().toString()+".gif"));
				add(new JLabel(img));
			}
		}
		
		private void odrediBojuPolja() {
			if(BoardUtil.FIRST_ROW[tileId] || BoardUtil.THRID_ROW[tileId] || 
					BoardUtil.FIFTH_ROW[tileId] ||
					BoardUtil.SEVENTH_ROW[tileId]) {
				setBackground(tileId % 2 != 0 ? Color.decode("#FFFACD") : Color.decode("#593E1A"));
			} else {
				if(BoardUtil.SECOND_ROW[tileId] || BoardUtil.FOURTH_ROW[tileId] ||
						BoardUtil.SIXTH_ROW[tileId] ||
						BoardUtil.EIGHTH_ROW[tileId]) {
					setBackground(tileId % 2 == 0 ? Color.decode("#FFFACD") : Color.decode("#593E1A"));
				}
			}
		}
	}
}
