package marsRover;

public abstract class Direction {
	public abstract Point moveForward(Point currentposition);
//	public abstract void moveForward(MarsRover marsRover);
	public abstract Point moveBackward(Point currentposition);
//	public abstract void moveBackward(MarsRover marsRover);
	public abstract void rotateLeft(MarsRover marsRover);
	public abstract void rotateRight(MarsRover marsRover);
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // Comparar las propiedades específicas de las subclases aca
        return true;
    }
}
