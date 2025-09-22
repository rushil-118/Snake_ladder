public class ExactNumberWinningStrategy implements WinningStrategy {
    @Override
    public boolean hasWon(int position, int boardSize) {
        return position == boardSize;
    }
}
