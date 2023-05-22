package marsRover;

public class MoveForwardCommand implements Command {
    @Override
    public boolean isValidCommand(char command) {
        return command == 'f';
    }

    @Override
    public void execute(MarsRover marsRover) {
        marsRover.setPosition(marsRover.getDirection().moveForward(marsRover.getPosition()));
    }
}

