import domain.SpareTurn;
import domain.StrikeTurn;
import domain.TenthTurn;
import domain.Turn;

import java.util.List;

public class TurnFactory {

    private List<String> turnsString;

    public Turn getTurn(int currentTurnNumber) {
        String turnString = turnsString.get(currentTurnNumber);
        Turn turn;

        if (isTenthTurn(currentTurnNumber)) {
            turn = new TenthTurn(new Turn(turnString), getNextTurnToTurnNumber(currentTurnNumber + 1));
        } else if (isSpare(turnString)) {
            turn = new SpareTurn(getNextTurnToTurnNumber(currentTurnNumber));
        } else if (isStrike(turnString)) {
            turn = new StrikeTurn(getNextTurnToTurnNumber(currentTurnNumber), getNextTurnToTurnNumber(currentTurnNumber + 1));
        } else {
            turn = new Turn(turnString);
        }

        return turn;
    }

    private boolean isTenthTurn(int index) {
        return index == 9;
    }

    private boolean isStrike(String currentTurn) {
        return currentTurn.equals("X");
    }

    private boolean isSpare(String currentTurn) {
        return currentTurn.endsWith("/");
    }

    private Turn getNextTurnToTurnNumber(int turnNumber) {
        Turn turn = null;
        int nextTurnNumber = turnNumber + 1;

        if (hasNextTurn(turnsString, turnNumber)) {
            if (isExtraBallSeparator(turnsString.get(nextTurnNumber))) {
                nextTurnNumber += 1;
            }
            turn = new Turn(turnsString.get(nextTurnNumber));
        }
        return turn;
    }

    private boolean hasNextTurn(List<String> turnsString, int currentTurnNumber) {
        return turnsString.size() > currentTurnNumber + 1;
    }

    private boolean isExtraBallSeparator(String turnsString) {
        return turnsString.isEmpty();
    }

    public void setTurnsString(List<String> turnsString) {
        this.turnsString = turnsString;
    }
}
