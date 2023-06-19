package Ternilapilli;

public class PlayerO extends Player {

    private static final String PLAYER_SYMBOL = "O";
    
    @Override
    protected boolean isPlayerTurn() {
        return TerniLapilli.turn.equals(PLAYER_SYMBOL);
    }
    
    @Override
    protected boolean isGameOver() {
        return TerniLapilli.winner != null;
    }
    
    @Override
    protected boolean hasWon() {
        return TerniLapilli.hasWon(tokens);
    }
    
    @Override
    protected void setWinner() {
        TerniLapilli.winner = PLAYER_SYMBOL;
    }
    
    @Override
    protected void switchTurn() {
        TerniLapilli.turn = "X";
    }
}