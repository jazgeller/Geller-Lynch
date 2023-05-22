package marsRover;

public interface Command {
    boolean isValidCommand(char command);

    void execute(MarsRover marsRover);
}