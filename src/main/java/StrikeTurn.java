public class StrikeTurn extends Turn {

    private Character nextFirstBall;
    private Character nextSecondBall;

    public StrikeTurn(Turn nextTurn, Turn nextNextTurn) {
        if (hasNextTurn(nextTurn)) {
            this.nextFirstBall = nextTurn.firstBall;
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

        if (isSpareBall(nextSecondBall)) {
            score += DEFAULT_SCORE_SPARE_OR_STRIKE;
        } else {
            score += getBallValue(nextFirstBall) + getBallValue(nextSecondBall);
        }


        return score;
    }

}
