package board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pieces.PieceColor;
import pieces.Queen;
import pieces.Piece;
import pieces.King;
import pieces.Bishop;
import pieces.Pawn;
import pieces.Knight;
import pieces.Rook;
import player.White;
import player.Black;
import player.Player;

public class Board {
	
	private ArrayList<Tile> board = new ArrayList<>();
	private Collection<Piece> whitePieces;
	private Collection<Piece> blackPieces;
	private White white;
	private Black black;
	private Player currentPlayer;
	private Pawn enPassant;
	
	public Board(Builder builder) {
		board = makeBoard(builder);
		whitePieces = currentPieces(board, PieceColor.WHITE);
		blackPieces = currentPieces(board, PieceColor.BLACK);
		enPassant = builder.enPassant;
		Collection<Move> beliPotezi = possibleMoves(whitePieces);
		Collection<Move> crniPotezi = possibleMoves(blackPieces);
		white = new White(this, beliPotezi, crniPotezi);
		black = new Black(this, beliPotezi, crniPotezi);
		currentPlayer = builder.onTheMove.choosePlayer(white, black);
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public Pawn getEnPassant() {
		return enPassant;
	}

	public Tile getTile(int position) {
		return board.get(position);
	}
	
	private Collection<Move> possibleMoves(Collection<Piece> pieces) {
		List<Move> possibleMoves = new ArrayList<>();
		for(Piece p : pieces) {
			possibleMoves.addAll(p.possibleMoves(this));
		}
		return possibleMoves;
	}
	
	public Collection<Move> allPossibleMoves() {
		List<Move> all = new ArrayList<>();
		all.addAll(possibleMoves(whitePieces));
		all.addAll(possibleMoves(blackPieces));
		all.addAll(white.calculateCastling(white.getPossibleMoves(), black.getPossibleMoves()));
		all.addAll(black.calculateCastling(black.getPossibleMoves(), white.getPossibleMoves()));
		return all;
	}
	
	private static Collection<Piece> currentPieces(ArrayList<Tile> board, PieceColor pieceColor) {
		List<Piece> currentPieces = new ArrayList<>();
		for(int i = 0; i < 64; i++) {
			if(board.get(i).occupied()) {
				Piece piece = board.get(i).getPiece();
				if(piece.getPieceColor() == pieceColor) {
					currentPieces.add(piece);
				}
			}
		}
		return currentPieces;
	}
	
	private static ArrayList<Tile> makeBoard(Builder builder) {
		ArrayList<Tile> tiles = new ArrayList<>();
		for(int i = 0; i < 64; i++) {
			tiles.add(Tile.createTile(i, builder.boardPieces.get(i)));
		}
		return tiles;
	}
	
	public static Board makeStartBoard() {
		Builder builder = new Builder();
		builder.setBoardPiece(new Rook(0, PieceColor.WHITE));
		builder.setBoardPiece(new Knight(1, PieceColor.WHITE));
		builder.setBoardPiece(new Bishop(2, PieceColor.WHITE));
		builder.setBoardPiece(new Queen(3, PieceColor.WHITE));
		builder.setBoardPiece(new King(4, PieceColor.WHITE));
		builder.setBoardPiece(new Bishop(5, PieceColor.WHITE));
		builder.setBoardPiece(new Knight(6, PieceColor.WHITE));
		builder.setBoardPiece(new Rook(7, PieceColor.WHITE));
		builder.setBoardPiece(new Pawn(8, PieceColor.WHITE));
		builder.setBoardPiece(new Pawn(9, PieceColor.WHITE));
		builder.setBoardPiece(new Pawn(10, PieceColor.WHITE));
		builder.setBoardPiece(new Pawn(11, PieceColor.WHITE));
		builder.setBoardPiece(new Pawn(12, PieceColor.WHITE));
		builder.setBoardPiece(new Pawn(13, PieceColor.WHITE));
		builder.setBoardPiece(new Pawn(14, PieceColor.WHITE));
		builder.setBoardPiece(new Pawn(15, PieceColor.WHITE));
		
		builder.setBoardPiece(new Rook(63, PieceColor.BLACK));
		builder.setBoardPiece(new Knight(62, PieceColor.BLACK));
		builder.setBoardPiece(new Bishop(61, PieceColor.BLACK));
		builder.setBoardPiece(new King(60, PieceColor.BLACK));
		builder.setBoardPiece(new Queen(59, PieceColor.BLACK));
		builder.setBoardPiece(new Bishop(58, PieceColor.BLACK));
		builder.setBoardPiece(new Knight(57, PieceColor.BLACK));
		builder.setBoardPiece(new Rook(56, PieceColor.BLACK));
		builder.setBoardPiece(new Pawn(55, PieceColor.BLACK));
		builder.setBoardPiece(new Pawn(54, PieceColor.BLACK));
		builder.setBoardPiece(new Pawn(53, PieceColor.BLACK));
		builder.setBoardPiece(new Pawn(52, PieceColor.BLACK));
		builder.setBoardPiece(new Pawn(51, PieceColor.BLACK));
		builder.setBoardPiece(new Pawn(50, PieceColor.BLACK));
		builder.setBoardPiece(new Pawn(49, PieceColor.BLACK));
		builder.setBoardPiece(new Pawn(48, PieceColor.BLACK));
		
		builder.setOnTheMove(PieceColor.WHITE);
		return builder.build();
	}
	
	public static Board makeCustomBoard() {
		Builder builder = new Builder();
		builder.setBoardPiece(new Rook(61, PieceColor.WHITE));
//		builder.setFigura(new Skakac(1, Boja.BELA));
		builder.setBoardPiece(new Bishop(17, PieceColor.WHITE));
		builder.setBoardPiece(new Queen(56, PieceColor.WHITE));
		builder.setBoardPiece(new King(12, PieceColor.WHITE));
		builder.setBoardPiece(new Bishop(11, PieceColor.WHITE));
//		builder.setFigura(new Skakac(17, Boja.BELA));
//		builder.setFigura(new Top(4, Boja.BELA));
//		builder.setFigura(new Pesak(8, Boja.BELA));
//		builder.setFigura(new Pesak(9, Boja.BELA));
		builder.setBoardPiece(new Pawn(10, PieceColor.WHITE));
		builder.setBoardPiece(new Pawn(19, PieceColor.WHITE));
		builder.setBoardPiece(new Pawn(28, PieceColor.WHITE));
//		builder.setFigura(new Pesak(13, Boja.BELA));
		builder.setBoardPiece(new Pawn(30, PieceColor.WHITE));
		builder.setBoardPiece(new Pawn(23, PieceColor.WHITE));
		
		builder.setBoardPiece(new Rook(52, PieceColor.BLACK));
//		builder.setFigura(new Skakac(51, Boja.CRNA));
		builder.setBoardPiece(new Bishop(27, PieceColor.BLACK));
		builder.setBoardPiece(new King(51, PieceColor.BLACK));
		builder.setBoardPiece(new Queen(41, PieceColor.BLACK));
//		builder.setFigura(new Lovac(51, Boja.CRNA));
		builder.setBoardPiece(new Knight(45, PieceColor.BLACK));
//		builder.setFigura(new Top(61, Boja.CRNA));
		builder.setBoardPiece(new Pawn(47, PieceColor.BLACK));
		builder.setBoardPiece(new Pawn(38, PieceColor.BLACK));
//		builder.setFigura(new Pesak(53, Boja.CRNA));
		builder.setBoardPiece(new Pawn(36, PieceColor.BLACK));
//		builder.setFigura(new Pesak(35, Boja.CRNA));
		builder.setBoardPiece(new Pawn(34, PieceColor.BLACK));
		builder.setBoardPiece(new Pawn(25, PieceColor.BLACK));
//		builder.setFigura(new Pesak(40, Boja.CRNA));
		
		builder.setOnTheMove(PieceColor.WHITE);
		return builder.build();
	}
	
	public Player getWhite() {
		return white;
	}

	public void setWhite(White white) {
		this.white = white;
	}

	public Player getBlack() {
		return black;
	}

	public void setBlack(Black black) {
		this.black = black;
	}

	public Collection<Piece> getWhitePieces() {
		return whitePieces;
	}

	public void setWhitePieces(Collection<Piece> whitePieces) {
		this.whitePieces = whitePieces;
	}

	public Collection<Piece> getBlackPieces() {
		return blackPieces;
	}

	public void setBlackPieces(Collection<Piece> blackPieces) {
		this.blackPieces = blackPieces;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int j = 0;
		for(int i = 63; i >= 0; i--) {
			if((i + 1) % 8 == 0) {
				j = i - 7;
			}
			String s = board.get(j).toString();
			sb.append(String.format("%3s", s));
			if((i) % 8 == 0) {
				sb.append("\n");
			}
			j++;
		}
		return sb.toString();
	}
	
	public static class Builder {
		Map<Integer, Piece> boardPieces;
		PieceColor onTheMove;
		Pawn enPassant;
		
		public Builder() {
			boardPieces = new HashMap<>();
		}
		
		public Builder setBoardPiece(Piece figura) {
			boardPieces.put(figura.getPosition(), figura);
			return this;
		}
		
		public Builder setOnTheMove(PieceColor potez) {
			onTheMove = potez;
			return this;
		}
		
		public Board build() {
			return new Board(this);
		}

		public void setEnPassant(Pawn enPassant) {
			this.enPassant = enPassant;
		}
	}
	
	
}
