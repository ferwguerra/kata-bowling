package domain;

public class StrikeTurn extends Turn {

    private Character nextFirstBall;
    private Character nextSecondBall;

    public StrikeTurn(Turn nextTurn, Turn nextNextTurn) {
        nextFirstBall = nextTurn.firstBall;

        if (isStrikeBall(nextFirstBall)) {
            nextSecondBall = nextNextTurn.firstBall;
        } else {
            nextSecondBall = nextTurn.secondBall;
        }
    }

    public int getScore() {
        int score = DEFAULT_SCORE_SPARE_OR_STRIKE;

        if (isSpareBall(nextSecondBall)) {
            score += DEFAULT_SCORE_SPARE_OR_STRIKE;
        } else {
            score += getScoreBall(nextFirstBall) + getScoreBall(nextSecondBall);
        }

        return score;
    }

}
