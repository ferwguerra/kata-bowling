import domain.Turn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BowlingScoreCalculator {

    private static final String TURN_SEPARATOR = "\\|";
    private static final int NUMBER_OF_TURNS = 10;
    private TurnFactory turnFactory = new TurnFactory();

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
        turnFactory.setTurnsString(turnsString);

        for (int currentTurnNumber = 0; currentTurnNumber < NUMBER_OF_TURNS; currentTurnNumber++) {
            Turn turn = turnFactory.getTurn(currentTurnNumber);
            turns.add(turn);
        }

        return turns;
    }
}
