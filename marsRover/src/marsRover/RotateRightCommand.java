package marsRover;

public class RotateRightCommand implements Command {
    @Override
    public boolean isValidCommand(char command) {
        return command == 'r';
    }

    @Override
    public void execute(MarsRover marsRover) {
        marsRover.getDirection().rotateRight(marsRover);
    }
}
