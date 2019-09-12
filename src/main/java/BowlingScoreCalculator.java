import java.util.LinkedList;
import java.util.List;

public class BowlingScoreCalculator {

    public static final String TURN_SEPARATOR = "\\|";

    public int calculateScore(String game) {
        int score = 0;

        List<Turn> turns = parseGame(game);

        for (Turn turn : turns) {
            score += turn.getScore();
        }

        return score;
    }

    private List<Turn> parseGame(String game) {
        List<Turn> turns = new LinkedList<>();
        boolean isExtraTurn = false;

        String[] turnsString = game.split(TURN_SEPARATOR);

        for (int i = 0; i < turnsString.length; i++) {
            String currentTurn = turnsString[i];

            if(!currentTurn.isEmpty()) {
                Turn nextTurn = getNextTurnIfExists(turnsString, i);
                Turn nextNextTurn = getNextTurnIfExists(turnsString, i + 1);

                if (currentTurn.endsWith("/")) {
                    turns.add(new SpareTurn(nextTurn));
                } else if (currentTurn.equals("X")) {
                    turns.add(new StrikeTurn(nextTurn, nextNextTurn));
                } else {
                    turns.add(new Turn(currentTurn, isExtraTurn));
                }
            } else {
                isExtraTurn = true;
            }

        }

        return turns;
    }

    private Turn getNextTurnIfExists(String[] turnsString, int i) {
        Turn turn = null;
        boolean isExtraTurn = false;
        if (hasNextTurn(turnsString, i)) {
            if(turnsString[i + 1].isEmpty()) {
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
