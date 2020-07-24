public class Moves {

    private int bitMask = 0;
    private static int movesPlayed = 0;
    private static final int BOARD_AREA = 9;
    private static final int BOARD_DIM = 3;
    private static final int movesAllowed = 9;
    private static final int[] winningMasks = {7, 56, 73, 84, 146, 273, 292, 448};
    private static EndSituation game = EndSituation.NOT_FINISHED;

    public void resetBitMask() {
        this.bitMask = 0;
    }

    public static void resetMovesPlayed() {
        Moves.movesPlayed = 0;
    }

    public static void resetGame() {
        Moves.game = EndSituation.NOT_FINISHED;
    }

    private static void incrementMovesPlayed() {
        Moves.movesPlayed++;
    }

    public static EndSituation getGame() {
        return game;
    }

    public static boolean isX() {
        if (!game.isFinished()) {
            incrementMovesPlayed();
        }
        return movesPlayed % 2 == 1;
    }

    public void plays(int row, int column) {
        row--;
        column--;

        int shift = BOARD_AREA - (row * BOARD_DIM + column) - 1;
        bitMask |= (1 << shift);

        if (isOver()) {
            game = EndSituation.WIN;
        } else if (movesPlayed == movesAllowed) {
            game = EndSituation.DRAW;
        }
    }

    private boolean isOver() {
        for (int winningMask : winningMasks) {
            if ((bitMask & winningMask) == winningMask)
                return true;
        }
        return false;
    }

    public static String endMessage() {
        String message;
        if (game.hasWinner()) {
            message = isX() ? "X" : "O";
            message += " has won!";
        } else {
            message = "It's a draw!";
        }
        return message;
    }
}
