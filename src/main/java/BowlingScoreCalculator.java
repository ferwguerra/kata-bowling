import domain.SpareTurn;
import domain.StrikeTurn;
import domain.TenthTurn;
import domain.Turn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BowlingScoreCalculator {

    public static final String TURN_SEPARATOR = "\\|";
    public static final int NUMBER_OF_TURNS = 10;

    public int calculateScore(String game) {
        int score;
        List<Turn> turns = getTurns(game);
        score = turns.stream().mapToInt(Turn::getScore).sum();
        return score;
    }

    private List<Turn> getTurns(String game) {
        List<String> turnsString = Arrays.asList(game.split(TURN_SEPARATOR));
        return buildTurns(turnsString);
    }

    private List<Turn> buildTurns(List<String> turnsString) {
        List<Turn> turns = new ArrayList<>();

        for (int currentTurnNumber = 0; currentTurnNumber < NUMBER_OF_TURNS; currentTurnNumber++) {
            String turnString = turnsString.get(currentTurnNumber);

            if (!isExtraBallSeparator(turnString)) {
                Turn turn;
                if (isTenthTurn(currentTurnNumber)) {
                    turn = new TenthTurn(new Turn(turnString), getNextTurnToTurnNumber(currentTurnNumber + 1, turnsString));
                } else if (isSpare(turnString)) {
                    turn = new SpareTurn(getNextTurnToTurnNumber(currentTurnNumber, turnsString));
                } else if (isStrike(turnString)) {
                    turn = new StrikeTurn(getNextTurnToTurnNumber(currentTurnNumber, turnsString), getNextTurnToTurnNumber(currentTurnNumber + 1, turnsString));
                } else {
                    turn = new Turn(turnString);
                }
                turns.add(turn);
            }
        }

        return turns;
    }

    private boolean isExtraBallSeparator(String turnsString) {
        return turnsString.isEmpty();
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

    private Turn getNextTurnToTurnNumber(int turnNumber, List<String> turnsString) {
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
}
