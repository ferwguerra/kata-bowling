import java.util.ArrayList;
import java.util.List;

public class TenthTurn extends Turn {

    private List<Character> balls = new ArrayList<>();

    public TenthTurn(Turn currentTurn, Turn nextTurn) {
        if (currentTurn != null) {
            balls.add(currentTurn.firstBall);
            balls.add(currentTurn.secondBall);
        }
        if (nextTurn != null) {
            balls.add(nextTurn.firstBall);
            balls.add(nextTurn.secondBall);
        }
    }

    @Override
    public int getScore() {
        int score = 0;
        int lastValueBall = 0;
        for (Character ball : balls) {
            if (ball != null) {
                if (isSpareBall(ball) || isStrikeBall(ball)) {
                    score += DEFAULT_SCORE_SPARE_OR_STRIKE - lastValueBall;
                    lastValueBall = 0;
                } else if (!isMissBall(ball)) {
                    lastValueBall = getBallValue(ball);
                    score += getBallValue(ball);
                }
            }
        }
        return score;
    }
}
