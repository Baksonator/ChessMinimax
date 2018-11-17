package board;

import board.Move.MoveMaking;
import minimax.MiniMax;
import minimax.MoveStrategy;
import player.MoveChange;

public class BoardTest {

//	 @Test
//	    public void initialBoard() {
//
//	        final Tabla board = Tabla.napraviPocetnuTablu();
//	        assertEquals(board.getTrenutni().getMoguciPotezi().size(), 20);
//	        assertEquals(board.getTrenutni().getProtivnik().getMoguciPotezi().size(), 20);
//	        assertFalse(board.getTrenutni().sah());
//	        assertFalse(board.getTrenutni().sahMat());
//	        assertFalse(board.getTrenutni().rokada());
////	        assertTrue(board.getTrenutni().isKingSideCastleCapable());
////	        assertTrue(board.getTrenutni().isQueenSideCastleCapable());
//	        assertEquals(board.getTrenutni(), board.getBeli());
//	        assertEquals(board.getTrenutni().getProtivnik(), board.getCrni());
//	        assertFalse(board.getTrenutni().getProtivnik().sah());
//	        assertFalse(board.getTrenutni().getProtivnik().sahMat());
//	        assertFalse(board.getTrenutni().getProtivnik().rokada());
//	        assertTrue(board.getTrenutni().getProtivnik().isKingSideCastleCapable());
//	        assertTrue(board.getTrenutni().getProtivnik().isQueenSideCastleCapable());
//	        assertTrue(board.getBeli().toString().equals("Beli"));
//	        assertTrue(board.getCrni().toString().equals("Crni"));
	        

//	        final Iterable<Piece> allPieces = board.getAllPieces();
//	        final Iterable<Move> allMoves = Iterables.concat(board.whitePlayer().getLegalMoves(), board.blackPlayer().getLegalMoves());
//	        for(final Move move : allMoves) {
//	            assertFalse(move.isAttack());
//	            assertFalse(move.isCastlingMove());
//	            assertEquals(MoveUtils.exchangeScore(move), 1);
//	        }
//
//	        assertEquals(Iterables.size(allMoves), 40);
//	        assertEquals(Iterables.size(allPieces), 32);
//	        assertFalse(BoardUtils.isEndGame(board));
//	        assertFalse(BoardUtils.isThreatenedBoardImmediate(board));
//	        assertEquals(StandardBoardEvaluator.get().evaluate(board, 0), 0);
//	        assertEquals(board.getTile(35).getPiece(), null);
//	        assertEquals(board.getTile(35).getTileCoordinate(), 35);

//	    }
	 
//	 @Test
//	 public void testirajMatUDva() {
//		 Tabla tabla = Tabla.napraviProzivoljnu();
//		 PotezPromena promena1 = tabla.getTrenutni().napraviPotez(PravljenjePoteza.kreirajPotez(tabla,
//				 TablaPonasanja.getKoordinata("f2"), TablaPonasanja.getKoordinata("f3")));
//		 assertTrue(promena1.getStanje().isUradjeno());
//		 
//		 PotezPromena promena2 = promena1.getTabla().getTrenutni().napraviPotez(PravljenjePoteza.kreirajPotez(promena1.getTabla(),
//				 TablaPonasanja.getKoordinata("e7"), TablaPonasanja.getKoordinata("e5")));
//		 
//		 assertTrue(promena2.getStanje().isUradjeno());
//		 
//		 PotezPromena promena3 = promena2.getTabla().getTrenutni().napraviPotez(PravljenjePoteza.kreirajPotez(
//				 promena2.getTabla(), 
//				 TablaPonasanja.getKoordinata("g2"), TablaPonasanja.getKoordinata("g4")));
//		 
//		 assertTrue(promena3.getStanje().isUradjeno());
//		 
//		 StrategijaPoteza strategija = new MiniMax(4);
//		 Potez potez = strategija.izvrsi(tabla);
//		 Potez najPotez = PravljenjePoteza.kreirajPotez(tabla,
//				 TablaPonasanja.getKoordinata("f5"), TablaPonasanja.getKoordinata("h6"));
//		 
//		 assertEquals(potez, najPotez);
//	 }
}
