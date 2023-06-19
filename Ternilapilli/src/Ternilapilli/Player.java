package Ternilapilli;

import java.util.Set;
import java.util.HashSet;
public abstract class Player {
    protected Set<Position> tokens;
    protected String tokenSymbol;
    
    public Player() {
        tokens = new HashSet<>();
    }
    
    protected abstract boolean isPlayerTurn();
    protected abstract boolean isGameOver();
    protected abstract boolean hasWon();
    protected abstract void setWinner();
    protected abstract void switchTurn();
    
    public Set<Position> getTokens() {
        return tokens;
    }
//    public void putTokenAt(Position position) {
//        performAction(position, null);
//    }
//
//    public void slideTokenFrom(Position token, Position slider) {
//        performAction(token, slider);
//    }
//
//    private void performAction(Position token, Position slider) {
//    	
//        if (isGameOver()) {
//            throw new RuntimeException(TerniLapilli.cannotPlayWhenGameIsOver);
//        }
//        if (!isPlayerTurn()) {
//            throw new RuntimeException(TerniLapilli.notPlayerTurn);
//        }
//        if (slider != null && tokens.contains(slider)) {
//            throw new RuntimeException(TerniLapilli.postionTakenError);
//        }
//        if (slider != null && !moveLegalFrom(token, slider)) {
//            throw new RuntimeException(TerniLapilli.canOnlySlideToAdjacentGaps);
//        }
//        if (slider != null && !allTokensOnBoard()) {
//            throw new RuntimeException(TerniLapilli.canNotSliceWhenNotAllPiecesOnBoardErrorMessage);
//        }
//
//        if (slider != null) {
//            tokens.remove(token);
//            tokens.add(slider);
//        } else {
//            tokens.add(token);
//        }
//
//        if (hasWon()) {
//            setWinner();
//        }
//        TerniLapilli.switchTurn();
//        TerniLapilli.checkForWinner();
//    }
    public void putTokenAt(Position position) {	
    	if(allTokensOnBoard()) {
			throw new RuntimeException(TerniLapilli.cannotPutAFourthToken);
		}
        if (isGameOver()) {
            throw new RuntimeException(TerniLapilli.cannotPlayWhenGameIsOver);
        }
        if (!isPlayerTurn()) {
            throw new RuntimeException(TerniLapilli.notPlayerTurn);
        }
        if (tokens.contains(position)) {
            throw new RuntimeException(TerniLapilli.postionTakenError);
        }
        tokens.add(position);
        if (hasWon()) {
            setWinner();
        }
        TerniLapilli.switchTurn();
        TerniLapilli.checkForWinner();
    }

    public void slideTokenFrom(Position token, Position slider) {
        if (isGameOver()) {
            throw new RuntimeException(TerniLapilli.cannotPlayWhenGameIsOver);
        }
        if (!isPlayerTurn()) {
            throw new RuntimeException(TerniLapilli.notPlayerTurn);
        }
        if (tokens.contains(slider)) {
            throw new RuntimeException(TerniLapilli.postionTakenError);
        }
        if (!moveLegalFrom(token, slider)) {
            throw new RuntimeException(TerniLapilli.canOnlySlideToAdjacentGaps);
        }
        if (!allTokensOnBoard()) {
            throw new RuntimeException(TerniLapilli.canNotSliceWhenNotAllPiecesOnBoardErrorMessage);
        }
        tokens.remove(token);
        tokens.add(slider);
        if (hasWon()) {
            setWinner();
        }
        TerniLapilli.switchTurn();
        TerniLapilli.checkForWinner();
    }
    
 ///armar las clases slideAction y putAction

    private boolean moveLegalFrom(Position token, Position slider) {
        return Math.abs(slider.getRow() - token.getRow()) < 2
                && Math.abs(slider.getColumn() - token.getColumn()) < 2
                && 0 < slider.getColumn() && slider.getColumn() < 4
                && 0 < token.getColumn() && token.getColumn() < 4
                && 0 < slider.getRow() && slider.getRow() < 4
                && 0 < token.getRow() && token.getRow() < 4;
    }
    
    	boolean allTokensOnBoard() {
        return tokens.size() == 3;
    }
}