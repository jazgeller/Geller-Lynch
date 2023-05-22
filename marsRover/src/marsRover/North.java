package marsRover;

public class North extends Direction {
	
	@Override
	public Point moveForward(Point currentPosition) {
	    return currentPosition.add(0, 1);
//	public void moveForward(MarsRover marsRover) {
//		marsRover.setPosition(marsRover.getPosition().add(0, 1));
	}

	@Override
	public Point moveBackward(Point currentPosition) {
	    return currentPosition.add(0, -1);
//	public void moveBackward(MarsRover marsRover) {
//		 marsRover.setPosition(marsRover.getPosition().add(0, -1));
	}

	@Override
	public void rotateRight(MarsRover marsRover) {
		marsRover.setDirection(new East());
	}

	@Override
	public void rotateLeft(MarsRover marsRover) {
		marsRover.setDirection(new West());
	}
}