package marsRover;

public class West extends Direction {
	

	public Point moveForward(Point currentPosition) {
	    return currentPosition.add(-1, 0);
//	public void moveForward(MarsRover marsRover) {
//		 marsRover.setPosition(marsRover.getPosition().add(-1, 0));
	}

	public Point moveBackward(Point currentPosition) {
	    return currentPosition.add(1, 0);
//	public void moveBackward(MarsRover marsRover) {
//		 marsRover.setPosition(marsRover.getPosition().add(1, 0));
	}

	public void rotateRight(MarsRover marsRover) {
		 marsRover.setDirection(new North());
	}

	public void rotateLeft(MarsRover marsRover) {
	    marsRover.setDirection(new South());
	}

}