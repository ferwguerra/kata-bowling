public class StrikeTurn extends Turn {

    private Character nextFirstBall;
    private Character nextSecondBall;
    private boolean isNextExtraBall;

    public StrikeTurn(Turn nextTurn, Turn nextNextTurn) {
        if (hasNextTurn(nextTurn)) {
            this.nextFirstBall = nextTurn.firstBall;
            this.isNextExtraBall = nextTurn.isExtraTurn;
            if (!nextTurn.isStrike()) {
                this.nextSecondBall = nextTurn.secondBall;
            } else {
                nextSecondBall = nextNextTurn.firstBall;
            }
        }
    }

    private boolean hasNextTurn(Turn nextTurn) {
        return nextTurn != null;
    }

    public int getScore() {
        int score = DEFAULT_SCORE_SPARE_OR_STRIKE;

        if (!isExtraTurn()) {

            if (isSpareBall(nextSecondBall) && !isNextExtraBall) {
                score += DEFAULT_SCORE_SPARE_OR_STRIKE;
            } else {
                if (!isNextExtraBall) {
                    score += getBallValue(nextFirstBall) + getBallValue(nextSecondBall);
                }
            }

        }

        return score;
    }

    private boolean isExtraTurn() {
        return nextFirstBall == null;
    }
}
