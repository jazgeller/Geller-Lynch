package Ternilapilli;

import java.util.Set;

public class TerniLapilli {
    public static String canOnlySlideToAdjacentGaps = "Solo se puede mover a casilleros adyacentes";
	public static String cannotPutAFourthToken = "No se puede poner una cuarta ficha";
    public static String cannotPlayWhenGameIsOver = "Juego terminado";
    public static String postionTakenError = "La posición ya está ocupada";
    public static String notPlayerTurn = "No es el turno del jugador";
    public static String canNotSliceWhenNotAllPiecesOnBoardErrorMessage = "No se puede mover hasta que no se hayan colocado todas las ichas en el tablero";
    
    private static Player playerX;
    private static Player playerO;
    public static String turn;
    public static String winner;
    
    public TerniLapilli() {
        playerX = new PlayerX();
        playerO = new PlayerO();
        turn = "X";
        winner = null;
    }
    
    public Set<Position> getXs() {
        return playerX.getTokens();
    }
    
    public Set<Position> getOs() {
        return playerO.getTokens();
    }
    
    public boolean isPlayerX() {
        return playerX.isPlayerTurn();
    }

    public boolean isPlayerO() {
        return playerO.isPlayerTurn();
    }
    
    
    public void putXAt(Position position) {
    playerX.putTokenAt(position);
    }
    
    public void putOAt(Position position) {
    playerO.putTokenAt(position);
    }

    public void slideXFrom(Position token, Position slider) {
   	 playerX.slideTokenFrom(token, slider);
    }
    
    public void slideOFrom(Position token, Position slider) {
     playerO.slideTokenFrom(token, slider);
       }
   	 
   	 public static void switchTurn() {
        if (turn.equals("X")) {
            turn = "O";
        } else {
            turn = "X";
        }
    }

    public String getTurn() {
        return turn;
    }

    public String getWinner() {
        return winner;
    }

    public boolean isGameOver() {
        return winner != null;
    }

    public static void checkForWinner() {
        Set<Position> positions = (turn.equals("X")) ? playerX.getTokens() : playerO.getTokens();
        if (hasWon(positions)) {
            winner = turn;
        }
    }

    public static boolean hasWon(Set<Position> position) {
        return hasCompletedRowOrColumn(position) || hasLeftDiagonal(position) || hasRightDiagonal(position);
    }
    
    private static boolean hasRightDiagonal(Set<Position> lista) {
        for (int iterator = 1; iterator <= 3; iterator++) {
            if (!lista.contains(new Position(iterator, 4 - iterator))) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean hasLeftDiagonal(Set<Position> lista) {
        for (int iterator = 1; iterator <= 3; iterator++) {
            if (!lista.contains(new Position(iterator, iterator))) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean hasCompletedRowOrColumn(Set<Position> lista) {
        for (int iterator = 0; iterator <= 3; iterator++) {
            int observableRow = iterator;
            int observableColumn = iterator;
            int counterRows = (int) lista.stream().filter(p -> p.getRow() == observableRow).count();
            int counterColumns = (int) lista.stream().filter(p -> p.getColumn() == observableColumn).count();
            if (counterRows == 3 || counterColumns == 3) {
                return true;
            }
        }
        return false;
    }
}