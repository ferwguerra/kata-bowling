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

        String[] turnsString = game.split(TURN_SEPARATOR);

        for (int i = 0; i < turnsString.length; i++) {
            String currentTurnString = turnsString[i];
            Turn aTurn = new Turn(currentTurnString);
            Turn nextTurn = getNextTurnIfExists(turnsString, i);
            Turn currentTurn;

            if (i == 9 && turnsString.length >= 9) {
                currentTurn = new TenthTurn(aTurn, getNextTurnIfExists(turnsString, i + 1));
                turns.add(currentTurn);
                break;
            } else {
                if (isSpare(currentTurnString)) {
                    currentTurn = new SpareTurn(nextTurn);
                } else if (currentTurnString.equals("X")) {
                    Turn nextNextTurn = getNextTurnIfExists(turnsString, i + 1);
                    currentTurn = new StrikeTurn(nextTurn, nextNextTurn);
                } else {
                    currentTurn = new Turn(currentTurnString);
                }

            }
            turns.add(currentTurn);

        }

        return turns;
    }

    private boolean isSpare(String currentTurn) {
        return currentTurn.endsWith("/");
    }

    private Turn getNextTurnIfExists(String[] turnsString, int i) {
        Turn turn = null;
        if (hasNextTurn(turnsString, i)) {
            if (turnsString[i + 1].isEmpty()) {
                i = i + 1;
            }
            turn = new Turn(turnsString[i + 1]);
        }
        return turn;
    }

    private boolean hasNextTurn(String[] turnsString, int position) {
        return turnsString.length > position + 1;
    }
}
