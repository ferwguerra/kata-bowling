package domain;

import java.util.ArrayList;
import java.util.List;

public class TenthTurn extends Turn {

    private List<Character> balls = new ArrayList<>();

    public TenthTurn(Turn currentTurn, Turn extraBallTurn) {
        if (currentTurn.isStrike()) {
            balls.add(currentTurn.firstBall);
            balls.add(extraBallTurn.firstBall);
            balls.add(extraBallTurn.secondBall);
        } else {
            balls.add(currentTurn.firstBall);
            balls.add(currentTurn.secondBall);
            if (currentTurn.isSpareBall(currentTurn.secondBall)) {
                balls.add(extraBallTurn.firstBall);
            }
        }
    }

    @Override
    public int getScore() {
        int score = 0;
        int scoreLastBall = 0;

        for (Character ball : balls) {
            if (isSpareBall(ball) || isStrikeBall(ball)) {
                score += DEFAULT_SCORE_SPARE_OR_STRIKE - scoreLastBall;
                scoreLastBall = 0;
            } else {
                scoreLastBall = getScoreBall(ball);
                score += scoreLastBall;
            }
        }
        return score;
    }
}
