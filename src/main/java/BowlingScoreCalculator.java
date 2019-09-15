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

        for (int turnNumber = 0; turnNumber < NUMBER_OF_TURNS; turnNumber++) {
            String turnString = turnsString.get(turnNumber);

            if (!isExtraBallSeparator(turnString)) {
                Turn turn;
                if (isTenthTurn(turnNumber)) {
                    turn = new TenthTurn(new Turn(turnString), getNextTurnIfExists(turnsString, turnNumber + 1));
                } else if (isSpare(turnString)) {
                    turn = new SpareTurn(getNextTurnIfExists(turnsString, turnNumber));
                } else if (isStrike(turnString)) {
                    turn = new StrikeTurn(getNextTurnIfExists(turnsString, turnNumber), getNextTurnIfExists(turnsString, turnNumber + 1));
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

    private Turn getNextTurnIfExists(List<String> turnsString, int index) {
        Turn turn = null;

        if (hasNextTurn(turnsString, index)) {
            if (turnsString.get(index + 1).isEmpty()) {
                index = index + 1;
            }
            turn = new Turn(turnsString.get(index + 1));
        }
        return turn;
    }

    private boolean hasNextTurn(List<String> turnsString, int index) {
        return turnsString.size() > index + 1;
    }
}
