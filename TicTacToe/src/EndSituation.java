public enum EndSituation {

    NOT_FINISHED, WIN, DRAW;

    public boolean isFinished() {
        return this != NOT_FINISHED;
    }

    public boolean hasWinner() {
        return this == WIN;
    }
}
