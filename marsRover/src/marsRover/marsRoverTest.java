package marsRover;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class marsRoverTest {

	@Test public void checkInitialPositionandDirection() {
		MarsRover rover = new MarsRover(new Point(1,1), new North());
		
		assertEquals(new North(),rover.getDirection());
		assertEquals(new Point(1,1), rover.getPosition());
	}

	
	@Test public void moveFowardN() {
		MarsRover rover = new MarsRover(new Point(1,1), new North());
		rover.executeCommand("f");
		    
		assertEquals(new Point(1, 2), rover.getPosition());
		}
	
	@Test public void moveBackwardsN() {
		MarsRover rover = new MarsRover(new Point(1,1), new North());
	    rover.executeCommand("b");
	    
	    assertEquals(new Point(1, 0), rover.getPosition());	
	}

	
	@Test public void rotateLeftN() {
		MarsRover rover = new MarsRover(new Point(1,1), new North());
	    rover.executeCommand("l");
	    
	    assertEquals(rover.getDirection(), new West());	
	}
	
	@Test public void rotateRightN() {
		MarsRover rover = new MarsRover(new Point(1,1), new North());
	    rover.executeCommand("r");
	    
	    assertEquals(rover.getDirection(), new East());
	}

	
	@Test public void moveFowardS() {
		MarsRover rover = new MarsRover(new Point(1,1), new South());
	    rover.executeCommand("f");
	    
	    assertEquals(new Point(1, 0), rover.getPosition());
	}
		
	@Test public void moveBackwardsS() {
		MarsRover rover = new MarsRover(new Point(1,1), new South());
	    rover.executeCommand("b");
	    assertEquals(new Point(1, 2), rover.getPosition());	
	}
	
	@Test public void rotateLeftS() {
		MarsRover rover = new MarsRover(new Point(1,1), new South());
	    rover.executeCommand("l");
	    assertEquals(rover.getDirection(), new East());;
	}
	
	@Test public void rotateRightS() {
		MarsRover rover = new MarsRover(new Point(1,1), new South());
	    rover.executeCommand("r");
	    assertEquals(rover.getDirection(), new West());
	}
	
	@Test public void movefowardW() {
		MarsRover rover = new MarsRover(new Point(1,1), new West());
		rover.executeCommand("f");
		    
		assertEquals(new Point(0, 1), rover.getPosition());
	}
	
	@Test public void moveBackwardsW() {
		MarsRover rover = new MarsRover(new Point(1,1), new West());
		rover.executeCommand("b");
		assertEquals(new Point(2, 1), rover.getPosition());
	}
	
	@Test public void rotateLeftW() {
		MarsRover rover = new MarsRover(new Point(1,1), new West());
	    rover.executeCommand("l");
	    
	    assertEquals(rover.getDirection(), new South());;
	}
	
	@Test public void rotateRightW() {
		MarsRover rover = new MarsRover(new Point(1,1), new West());
	    rover.executeCommand("r");
	    
	    assertEquals(rover.getDirection(), new North());;
	}
	
	@Test public void movefowardE() {
		MarsRover rover = new MarsRover(new Point(1,1), new East());
		rover.executeCommand("f");
		    
		assertEquals(new Point(2, 1), rover.getPosition());
	}
	
	@Test public void moveBackwardsE() {
		MarsRover rover = new MarsRover(new Point(1,1), new East());
		rover.executeCommand("b");
		    
		assertEquals(new Point(0, 1), rover.getPosition());
	}
	
	@Test public void rotateLeftE() {
		MarsRover rover = new MarsRover(new Point(1,1), new East());
		rover.executeCommand("l");
    
		assertEquals(rover.getDirection(), new North());;
	}
	
	@Test public void rotateRightE() {
		MarsRover rover = new MarsRover(new Point(1,1), new East());
	    rover.executeCommand("r");
	    
	    assertEquals(rover.getDirection(), new South());;
	}
/////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@Test public void DoubleRotationRight() {
		MarsRover rover = new MarsRover(new Point(1,1), new North());
	    rover.executeCommand("rr");
	   
	    assertEquals(rover.getDirection(), new South());;
	}
	
	@Test public void DoubleRotationLeft() {
		MarsRover rover = new MarsRover(new Point(1,1), new East());
	    rover.executeCommand("ll");
	   
	    assertEquals(rover.getDirection(), new West());;
	}
	
	@Test public void WholeRotationLeft() {
		MarsRover rover = new MarsRover(new Point(0,0), new East());
	    rover.executeCommand("llll");
	   
	    assertEquals(rover.getDirection(), new East());;
	}
	
	@Test public void WholeRotationRight() {
		MarsRover rover = new MarsRover(new Point(0,0), new East());
	    rover.executeCommand("rrrr");
	   
	    assertEquals(rover.getDirection(), new East());;
	}
	
	
	@Test
	public void handleMultipleCommands() {

	    MarsRover rover = new MarsRover(new Point(0,0), new North());
	    rover.executeCommand("ffrrbbll");
	    
	    assertEquals(new Point(0, 4), rover.getPosition());
	    assertEquals(new North(), rover.getDirection());
	}
	
	@Test
	public void handleLargeNumberofCommands() {
	    MarsRover rover = new MarsRover(new Point(0,0), new North());

	    StringBuilder commands = new StringBuilder();
	    for (int i = 0; i < 1000; i++) {
	        commands.append("f");
	    }
	    rover.executeCommand(commands.toString());

	    assertEquals(new Point(0, 1000), rover.getPosition());
	}
	@Test
    public void HandleEmptyCommands() {
        MarsRover marsRover = new MarsRover(new Point(0, 0), new North());
        marsRover.executeCommand("");
    }
	@Test
	public void handleInvalidCommands() {
	    MarsRover rover = new MarsRover(new Point(3, 4), new East());
	   
	    try {
	        rover.executeCommand("p");
	    } catch (IllegalArgumentException e) {
	        System.err.println(e.getMessage());
	    }
	    assertEquals(new Point(3, 4), rover.getPosition());
	    assertEquals(new East(), rover.getDirection());
	}

	

    @Test
	public void handleCommandsWithUnknownInstructions() {
	    MarsRover rover = new MarsRover(new Point(0, 0), new North());

	    try {
	        rover.executeCommand("fxyzblm");
	    } catch (IllegalArgumentException e) {
	        System.err.println(e.getMessage());
	    }


	    assertEquals(new Point(0, 1), rover.getPosition());
	    assertEquals(new North(), rover.getDirection());
	}

	@Test
	public void handleCommandsWithSpaces() {
	    MarsRover rover = new MarsRover(new Point(0, 0), new North());

	    try {
	        rover.executeCommand("f r b l");
	    } catch (IllegalArgumentException e) {
	        System.err.println(e.getMessage());
	    }

	    assertEquals(new Point(0, 1), rover.getPosition());
	    assertEquals(new North(), rover.getDirection());
	}

	
	@Test
	public void handleCommandsWithNumbers() {
	    MarsRover rover = new MarsRover(new Point(0, 0), new North());
	    try {
	        rover.executeCommand("fr32b1l4");
	    } catch (IllegalArgumentException e) {
	        System.err.println(e.getMessage());
	    }

	    assertEquals(new Point(0, 1), rover.getPosition());
	    assertEquals(new East(), rover.getDirection());
	} 

}
 

