public class Turn {
    protected final int DEFAULT_SCORE_SPARE_OR_STRIKE = 10;
    protected Character firstBall;
    protected Character secondBall;
    protected boolean isExtraTurn;

    public Turn() {}

    public Turn(String turnString, boolean isExtraTurn) {
        firstBall = turnString.charAt(0);
        if (turnString.length() > 1) {
            secondBall = turnString.charAt(1);
        }
        this.isExtraTurn = isExtraTurn;
    }

    public int getScore() {
        return getBallValue(firstBall) + getBallValue(secondBall);
    }

    protected int getBallValue(Character ball) {
        int value = 0;

        if(ball != null) {
            if (isMissBall(ball)) {
                value = 0;
            } else if (isStrikeBall(ball) || isSpareBall(ball)) {
                value = DEFAULT_SCORE_SPARE_OR_STRIKE;
            } else {
                value = Character.getNumericValue(ball);
            }
        }


        return value;
    }

    protected boolean isStrike(Turn turn) {
        return turn.secondBall == null;
    }

    protected boolean isSpareBall(Character ball) {
        return ball == '/';
    }

    private boolean isStrikeBall(Character ball) {
        return ball == 'X';
    }

    private boolean isMissBall(Character ball) {
        return ball == '-';
    }

}
