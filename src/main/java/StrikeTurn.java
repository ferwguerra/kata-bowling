public class StrikeTurn extends Turn {

    private Character nextFirstBall;
    private Character nextSecondBall;
    private boolean isNextBallExtra;

    public StrikeTurn(Turn nextTurn, Turn nextNextTurn) {
        if (nextTurn != null) {
            this.nextFirstBall = nextTurn.firstBall;
            this.isNextBallExtra = nextTurn.isExtraTurn;
            if (nextTurn.secondBall != null) {
                this.nextSecondBall = nextTurn.secondBall;
            } else {
                nextSecondBall = nextNextTurn.firstBall;
            }
        }
    }

    public int getScore() {
        int score = DEFAULT_SCORE_SPARE_OR_STRIKE;

        if (!isExtraBall()) {
            if (nextSecondBall == '/') {
                score += DEFAULT_SCORE_SPARE_OR_STRIKE;
            } else {
                if (!isNextBallExtra) {
                    score += getBallValue(nextFirstBall) + getBallValue(nextSecondBall);
                }
            }
        }

        return score;
    }

    private boolean isExtraBall() {
        return nextFirstBall == null;
    }
}
