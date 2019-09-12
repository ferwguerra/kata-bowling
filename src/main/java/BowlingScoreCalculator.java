import java.util.LinkedList;
import java.util.List;

public class BowlingScoreCalculator {

    public static final String TURN_SEPARATOR = "\\|";

    public int calculateScore(String game) {
        int score;
        List<Turn> turns = getTurns(game);
        score = turns.stream().mapToInt(Turn::getScore).sum();
        return score;
    }

    private List<Turn> getTurns(String game) {
        List<Turn> turns = new LinkedList<>();
        boolean isExtraTurn = false;

        String[] turnsString = game.split(TURN_SEPARATOR);

        for (int i = 0; i < turnsString.length; i++) {
            String currentTurnString = turnsString[i];
            Turn currentTurn;

            if (isSeparatorForExtraTurn(currentTurnString)) {
                isExtraTurn = true;
            } else {
                Turn nextTurn = getNextTurnIfExists(turnsString, i);

                if (isSpare(currentTurnString)) {
                    currentTurn = new SpareTurn(nextTurn);
                } else if (currentTurnString.equals("X")) {
                    Turn nextNextTurn = getNextTurnIfExists(turnsString, i + 1);
                    currentTurn = new StrikeTurn(nextTurn, nextNextTurn);
                } else {
                    currentTurn = new Turn(currentTurnString, isExtraTurn);
                }

                turns.add(currentTurn);
            }

        }

        return turns;
    }

    private boolean isSpare(String currentTurn) {
        return currentTurn.endsWith("/");
    }

    private boolean isSeparatorForExtraTurn(String currentTurn) {
        return currentTurn.isEmpty();
    }

    private Turn getNextTurnIfExists(String[] turnsString, int i) {
        Turn turn = null;
        boolean isExtraTurn = false;
        if (hasNextTurn(turnsString, i)) {
            if (turnsString[i + 1].isEmpty()) {
                isExtraTurn = true;
                i = i + 1;
            }
            turn = new Turn(turnsString[i + 1], isExtraTurn);
        }
        return turn;
    }

    private boolean hasNextTurn(String[] turnsString, int position) {
        return turnsString.length > position + 1;
    }
}
