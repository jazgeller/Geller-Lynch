package marsRover;

import java.util.Arrays;
import java.util.List;

public class MarsRover {
    private Point position;
    private Direction direction;
    private List<Command> commands;

public MarsRover(Point initialPosition, Direction initialDirection) {
	position = initialPosition;
    direction = initialDirection;
    commands = Arrays.asList(
                	new MoveForwardCommand(),
                    new MoveBackwardCommand(),
                    new RotateLeftCommand(),
                    new RotateRightCommand()
            );
        }
public void executeCommand(String commandsString) {
    try {
        commandsString.chars()
                .mapToObj(command -> (char) command)
                .forEach(command -> {
                    Command matchingCommand = commands.stream()
                            .filter(c -> c.isValidCommand(command))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Unknown command: " + command));
                    matchingCommand.execute(this);
                });
    } catch (IllegalArgumentException e) {
        System.err.println(e.getMessage());
    }
}



//        public void executeCommand(String commandsString) {
//            commandsString.chars()
//                    .mapToObj(command -> (char) command)
//                    .forEach(command -> {
//                        Command matchingCommand = commands.stream()
//                                .filter(c -> c.isValidCommand(command))
//                                .findFirst()
//                                .orElseThrow(() -> new IllegalArgumentException("Invalid command: " + command));
//                        matchingCommand.execute(this);
//                    });

 
    
public boolean canHandleCommand(char command) {
       return commands.stream().anyMatch(c -> c.isValidCommand(command));
    }

public Point getPosition() {
  return position;
}

public void setPosition(Point newPosition) {
  position = newPosition;
}

public Direction getDirection() {
  return direction;
}

public void setDirection(Direction newDirection) {
  direction = newDirection;
}
}