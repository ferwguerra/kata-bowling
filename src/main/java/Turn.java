public class Turn {
    protected final int DEFAULT_SCORE_SPARE_OR_STRIKE = 10;
    protected Character firstBall;
    protected Character secondBall;

    public Turn() {
    }

    public Turn(String turnString) {
        firstBall = turnString.charAt(0);
        if (!isStrike(turnString)) {
            secondBall = turnString.charAt(1);
        }
    }


    public int getScore() {
        return getBallValue(firstBall) + getBallValue(secondBall);
    }

    protected int getBallValue(Character ball) {
        int value;

        if (isMissBall(ball)) {
            value = 0;
        } else if (isStrikeBall(ball) || isSpareBall(ball)) {
            value = DEFAULT_SCORE_SPARE_OR_STRIKE;
        } else {
            value = Character.getNumericValue(ball);
        }

        return value;
    }

    protected boolean isStrike() {
        return this.secondBall == null;
    }

    protected boolean isSpareBall(Character ball) {
        return ball == '/';
    }

    protected boolean isStrikeBall(Character ball) {
        return ball == 'X';
    }

    protected boolean isMissBall(Character ball) {
        return ball == '-';
    }

    protected boolean isStrike(String turnString) {
        return turnString.length() == 1;
    }
}
