package marsRover;

public class RotateLeftCommand implements Command {
    @Override
    public boolean isValidCommand(char command) {
        return command == 'l';
    }

    @Override
    public void execute(MarsRover marsRover) {
        marsRover.getDirection().rotateLeft(marsRover);
    }
}

