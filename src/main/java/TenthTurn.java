import java.util.ArrayList;
import java.util.List;

public class TenthTurn extends Turn {

    private List<Character> balls = new ArrayList<>();

    public TenthTurn(Turn currentTurn, Turn nextTurn) {
        if (currentTurn.isStrike()) {
            balls.add(currentTurn.firstBall);
            balls.add(nextTurn.firstBall);
            balls.add(nextTurn.secondBall);
        } else {
            balls.add(currentTurn.firstBall);
            balls.add(currentTurn.secondBall);
            if (currentTurn.isSpareBall(currentTurn.secondBall)) {
                balls.add(nextTurn.firstBall);
            }
        }
    }

    @Override
    public int getScore() {
        int score = 0;
        int lastValueBall = 0;

        for (Character ball : balls) {
            if (isSpareBall(ball) || isStrikeBall(ball)) {
                score += DEFAULT_SCORE_SPARE_OR_STRIKE - lastValueBall;
                lastValueBall = 0;
            } else if (!isMissBall(ball)) {
                lastValueBall = getBallValue(ball);
                score += getBallValue(ball);
            }
        }
        return score;
    }
}
