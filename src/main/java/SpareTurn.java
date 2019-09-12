public class SpareTurn extends Turn {

    private Turn nextTurn;

    public SpareTurn(Turn nextTurn) {
        this.nextTurn = nextTurn;
    }

    public int getScore() {
        int score = DEFAULT_SCORE_SPARE_OR_STRIKE;
        if(nextTurn != null) {
            score += getBallValue(nextTurn.firstBall);
        }
        return score;
    }
}
