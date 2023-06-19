package Ternilapilli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.Test;

public class TerniLapilliTest {
	@Test
	void test00GameIsCreatedCorrectly() {
		TerniLapilli game = new TerniLapilli();

		assertTrue(game.getXs().isEmpty());
		assertTrue(game.getOs().isEmpty());
	}

	@Test
	void test01PlayerXCanPutAToken() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));

		assertEquals(1, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getOs().isEmpty());
	}

	@Test
	void test02PlayerOCanPutAToken() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));

		assertEquals(1, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertEquals(1, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(2, 2)));
	}

	@Test
	void test03XCannotPlayWhenItsNotItsTurn() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		try {
			game.putXAt(new Position(2, 2));
			fail("Exception Error");
		} catch (RuntimeException anError) {
			assertEquals(TerniLapilli.notPlayerTurn, anError.getMessage());
			assertEquals(1, game.getXs().size());
			assertTrue(game.getXs().contains(new Position(1, 1)));
			assertTrue(game.getOs().isEmpty());
		}
	}

	@Test
	void test04OCannotPlayWhenItsNotItsTurn() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));

		try {
			game.putOAt(new Position(3, 3));
			fail("Exception Error");
		} catch (RuntimeException anError) {
			assertEquals(TerniLapilli.notPlayerTurn, anError.getMessage());
			assertEquals(1, game.getXs().size());
			assertTrue(game.getXs().contains(new Position(1, 1)));
			assertEquals(1, game.getOs().size());
			assertTrue(game.getOs().contains(new Position(2, 2)));
		}
	}

	@Test
	void test05CannotPutAnXInAnOccupiedPosition() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));

		try {
			game.putXAt(new Position(1, 1));
			fail("Exception Error");
		} catch (RuntimeException anError) {
			assertEquals(TerniLapilli.postionTakenError, anError.getMessage());
			assertEquals(1, game.getXs().size());
			assertTrue(game.getXs().contains(new Position(1, 1)));
			assertEquals(1, game.getOs().size());
			assertTrue(game.getOs().contains(new Position(2, 2)));
		}
	}

	@Test
	void test06CannotPutAnOInAnOccupiedPosition() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 2));
		try {
			game.putOAt(new Position(2, 2));
			fail("Exception Error");
		} catch (RuntimeException anError) {
			assertEquals(TerniLapilli.postionTakenError, anError.getMessage());
			assertEquals(2, game.getXs().size());
			assertTrue(game.getXs().contains(new Position(1, 1)));
			assertTrue(game.getXs().contains(new Position(1, 2)));
			assertEquals(1, game.getOs().size());
			assertTrue(game.getOs().contains(new Position(2, 2)));
		}
	}

	@Test
	void test07CannotPutAnOInAnOccupiedPosition() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		try {
			game.putOAt(new Position(1, 1));
		} catch (RuntimeException anError) {
			assertEquals(TerniLapilli.postionTakenError, anError.getMessage());
			assertEquals(1, game.getXs().size());
			assertTrue(game.getXs().contains(new Position(1, 1)));
			assertTrue(game.getOs().isEmpty());
		}
	}

	@Test
	void test08CannotPutAnXInAnOccupiedPosition() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		try {
			game.putXAt(new Position(2, 2));
		} catch (RuntimeException anError) {
			assertEquals(TerniLapilli.postionTakenError, anError.getMessage());
			assertEquals(1, game.getXs().size());
			assertTrue(game.getXs().contains(new Position(1, 1)));
			assertEquals(1, game.getOs().size());
			assertTrue(game.getOs().contains(new Position(2, 2)));
		}
	}

//	FALTA CAMBIAR NOMBRE
	@Test
	void test09NoOneWins() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(3, 3));
		game.putOAt(new Position(1, 2));
		
		assertNull(game.getWinner()); //se puede sacar
	    assertFalse(game.isGameOver()); //lo cambie por esto 
//		assertFalse(game.isWinnerX()); //saque estas dos lineas
//		assertFalse(game.isWinnerO());
	}

	@Test
	void test10CheckWinningRowsByX() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 3));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(1, 2));

		assertEquals("X", game.getWinner());
	    assertTrue(game.isGameOver());
	}

	@Test
	void test11CheckWinningRowsByO() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(3, 3));
		game.putOAt(new Position(1, 1));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(1, 3));
		game.putXAt(new Position(3, 1));
		game.putOAt(new Position(1, 2));

		assertEquals("O", game.getWinner());
	    assertTrue(game.isGameOver());
//	    assertFalse(game.isWinnerX());
//		assertTrue(game.isWinnerO());
	}

	@Test
	void test12CheckWinningByColumnsByX() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(3, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(1, 2));
		game.putXAt(new Position(3, 3));

		assertEquals("X", game.getWinner());
		assertTrue(game.isGameOver());
//		assertTrue(game.isWinnerX());
//		assertFalse(game.isWinnerO());
	}

	@Test
	void test13CheckWinningByColumnsByO() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(3, 3));
		game.putOAt(new Position(2, 3));
		game.putXAt(new Position(3, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 1));

		assertEquals("O", game.getWinner());
		assertTrue(game.isGameOver());
//		assertFalse(game.isWinnerX());
//		assertTrue(game.isWinnerO());
	}

	@Test
	void test14CheckHasWon() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(3, 3));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(1, 2));
		
		assertEquals("O", game.getWinner());
		assertTrue(game.isGameOver());

//		assertFalse(game.XHasWon());
//		assertTrue(game.OHasWon());
	}
	
	@Test
	void test15CheckWinningByLeftDiagonalByX() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 2));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(3, 3));

		assertEquals("X", game.getWinner());
		assertTrue(game.isGameOver());
//		assertTrue(game.XHasWon());
//		assertFalse(game.OHasWon());
	}

	@Test
	void test16CheckWinningByLeftDiagonalByO() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(2, 3));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 3));
		game.putXAt(new Position(1, 2));
		game.putOAt(new Position(1, 1));

		assertEquals("O", game.getWinner());
		assertTrue(game.isGameOver());
	}
	
	@Test
	void test17CheckWinningByRightDiagonalByX() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 3));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 2));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(3, 1));

		assertEquals("X", game.getWinner());
		assertTrue(game.isGameOver());
//		assertTrue(game.XHasWon());
//		assertFalse(game.OHasWon());
	}

	@Test
	void test18CheckWinningByRightDiagonalByO() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(2, 3));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(1, 3));
		game.putXAt(new Position(1, 2));
		game.putOAt(new Position(3, 1));

		assertEquals("O", game.getWinner());
		assertTrue(game.isGameOver());
	}

	@Test
	void test19OCannotPlayWhenGameIsOver() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 3));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 2));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(3, 1));

		assertThrowsLike(()-> game.putOAt(new Position(3, 3)), TerniLapilli.cannotPlayWhenGameIsOver);
	}

	@Test
	void test20XCannotPutAFourthToken() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 3));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 3));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(3, 1));
		game.putOAt(new Position(1, 1));

		assertThrowsLike(() -> game.putXAt(new Position(3, 3)), TerniLapilli.cannotPutAFourthToken);
		assertEquals(3, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 3)));
		assertTrue(game.getXs().contains(new Position(2, 3)));
		assertTrue(game.getXs().contains(new Position(3, 1)));
	}

	@Test
	void test21SlideTokenForX() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(1, 3));
		game.putXAt(new Position(1, 2));
		game.putOAt(new Position(3, 2));

		game.slideXFrom(new Position(2, 1), new Position(3, 1));
		assertEquals(3, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(3, 1)));
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getXs().contains(new Position(1, 2)));
	}

	@Test
	void test22SlideTokenForXAndO() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(1, 3));
		game.putXAt(new Position(1, 2));
		game.putOAt(new Position(3, 2));

		game.slideXFrom(new Position(2, 1), new Position(3, 1));
		game.slideOFrom(new Position(3, 2), new Position(3, 3));
		assertEquals(3, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(3, 1)));
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getXs().contains(new Position(1, 2)));
		assertEquals(3, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(3, 3)));
		assertTrue(game.getOs().contains(new Position(1, 3)));
		assertTrue(game.getOs().contains(new Position(2, 2)));
	}

	@Test
	void test23CheckXWinsBySlidingByRow() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 2));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 3));
		game.putOAt(new Position(3, 2));

		game.slideXFrom(new Position(2, 3), new Position(1, 3));
		assertEquals("X", game.getWinner());
		assertTrue(game.isGameOver());
//		assertTrue(game.isWinnerX());
//		assertFalse(game.isWinnerO());

	}

	@Test
	void test24ChecksOWinsBySlidingByRow() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 2));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(3, 3));
		game.putOAt(new Position(3, 2));

		game.slideXFrom(new Position(1, 2), new Position(1, 3));
		game.slideOFrom(new Position(3, 2), new Position(2, 3));
		
		assertEquals("O", game.getWinner());
		assertTrue(game.isGameOver());
//		assertTrue(game.isWinnerO());
//		assertFalse(game.isWinnerX());

	}

	@Test
	void test25CheckXWinsBySlidinByColumn() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(2, 3));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(1, 3));

		game.slideXFrom(new Position(3, 2), new Position(3, 1));

		assertEquals("X", game.getWinner());
		assertTrue(game.isGameOver());

	}

	@Test
	void test26CheckOWinsBySlidingByColumn() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 3));
		game.putXAt(new Position(2, 2));
		game.putOAt(new Position(1, 3));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(1, 2));

		game.slideXFrom(new Position(2, 3), new Position(3, 3));

		assertEquals("X", game.getWinner());
		assertTrue(game.isGameOver());

	}

	@Test
	void test27CheckXWinsBySlidingInADiagonal() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 2));
		game.putOAt(new Position(2, 3));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(1, 3));

		game.slideXFrom(new Position(2, 3), new Position(3, 3));

		assertEquals("X", game.getWinner());
		assertTrue(game.isGameOver());

	}

	@Test
	void test28CheckOWinsBySlidingInADiagonal() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 1));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(2, 3));

		game.slideXFrom(new Position(3, 2), new Position(3, 3));
		game.slideOFrom(new Position(2, 3), new Position(1, 3));

		assertEquals("O", game.getWinner());
		assertTrue(game.isGameOver());

	}

	@Test
	void test29CannotSlideAnXInPositionTakenByX() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 1));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(2, 3));

		assertThrowsLike(()-> game.slideXFrom(new Position(2, 1), new Position(1, 1)), TerniLapilli.postionTakenError);
		assertEquals(3, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getXs().contains(new Position(2, 1)));
		assertTrue(game.getXs().contains(new Position(3, 2)));
	}

	@Test
	void test30CannotSlideAnOInAPositionTakenByO() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 1));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(2, 3));
		game.slideXFrom(new Position(2, 1), new Position(1, 2));
		assertThrowsLike(()-> game.slideOFrom(new Position(3, 1), new Position(2, 2)), TerniLapilli.postionTakenError);
		assertEquals(3, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(2, 2)));
		assertTrue(game.getOs().contains(new Position(2, 3)));
		assertTrue(game.getOs().contains(new Position(3, 1)));
	}

	@Test
	void test31CannotSlideAnOInAnPositionTakenPositionByX() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		try {
			game.putOAt(new Position(1, 1));
		} catch (RuntimeException anError) {
			assertEquals(TerniLapilli.postionTakenError, anError.getMessage());
			assertEquals(1, game.getXs().size());
			assertTrue(game.getXs().contains(new Position(1, 1)));
			assertTrue(game.getOs().isEmpty());
		}
	}

	@Test
	void test32CannotPutAnXInAnByO() {
		TerniLapilli game = new TerniLapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		try {
			game.putXAt(new Position(2, 2));
		} catch (RuntimeException anError) {
			assertEquals(TerniLapilli.postionTakenError, anError.getMessage());
			assertEquals(1, game.getXs().size());
			assertTrue(game.getXs().contains(new Position(1, 1)));
			assertEquals(1, game.getOs().size());
			assertTrue(game.getOs().contains(new Position(2, 2)));
		}
	}
	   @Test
	    public void test33CannotSlideXIfNotAllTokensWerePut() {
	        TerniLapilli game = new TerniLapilli();
	        game.putXAt( new Position( 2, 2 ) );
	        game.putOAt( new Position( 1, 1 ) );
	        game.putXAt( new Position( 1, 3 ) );
	        game.putOAt( new Position( 3, 1 ) );

	        assertThrowsLike(()->  game.slideXFrom(new Position(1,3), new Position(1,2)), 
	    			TerniLapilli.canNotSliceWhenNotAllPiecesOnBoardErrorMessage);
	}
	   
	   @Test
	    void test34CannotSlideOIfNotAllTokensWerePut() {
	        TerniLapilli game = new TerniLapilli();
	        game.putXAt(new Position(1, 1));
	        game.putOAt(new Position(2, 2));
	        game.putXAt(new Position(1, 3));
	        game.putOAt(new Position(3, 1));

	        assertThrows(RuntimeException.class, () -> game.slideOFrom(new Position(2, 2), new Position(3, 2)),
	                TerniLapilli.canNotSliceWhenNotAllPiecesOnBoardErrorMessage);
	    } 
	 
	private void assertThrowsLike(Executable lambda, String message) {
		assertEquals(message, assertThrows(RuntimeException.class, lambda).getMessage());
	}

}