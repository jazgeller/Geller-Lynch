package marsRover;

public class MoveBackwardCommand implements Command {
    @Override
    public boolean isValidCommand(char command) {
        return command == 'b';
    }

    @Override
    public void execute(MarsRover marsRover) {
        marsRover.setPosition(marsRover.getDirection().moveBackward(marsRover.getPosition()));
    }
}
