import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private WinningStrategy winningStrategy;
    private KillingStrategy killingStrategy;
    private int currentPlayerIndex;
    private Scanner scanner;

    private Game(GameBuilder builder) {
        this.board = builder.board;
        this.players = builder.players;
        this.dice = builder.dice;
        this.winningStrategy = builder.winningStrategy;
        this.killingStrategy = builder.killingStrategy;
        this.currentPlayerIndex = 0;
        this.scanner = new Scanner(System.in);
    }

    public static GameBuilder builder() {
        return new GameBuilder();
    }

    public static class GameBuilder {
        private Board board;
        private List<Player> players;
        private Dice dice;
        private WinningStrategy winningStrategy;
        private KillingStrategy killingStrategy;

        public GameBuilder withBoard(Board board) {
            this.board = board;
            return this;
        }

        public GameBuilder withPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public GameBuilder withDice(Dice dice) {
            this.dice = dice;
            return this;
        }

        public GameBuilder withWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }

        public GameBuilder withKillingStrategy(KillingStrategy killingStrategy) {
            this.killingStrategy = killingStrategy;
            return this;
        }

        public Game build() {
            if (board == null) {
                throw new IllegalStateException("Board is required");
            }
            if (players == null || players.isEmpty()) {
                throw new IllegalStateException("At least one player is required");
            }
            if (dice == null) {
                throw new IllegalStateException("Dice is required");
            }
            if (winningStrategy == null) {
                throw new IllegalStateException("Winning strategy is required");
            }
            if (killingStrategy == null) {
                throw new IllegalStateException("Killing strategy is required");
            }

            return new Game(this);
        }
    }

    public void playGame() {
        System.out.println("Starting Snake and Ladder Game!");
        System.out.println("Board size: " + board.getSize());
        System.out.println("Players: ");
        for (Player player : players) {
            System.out.println("- " + player.getName());
        }
        System.out.println();

        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("Turn: " + currentPlayer.getName() + " (Position: " + currentPlayer.getPosition() + ")");
            
            System.out.print("Press Enter to roll the dice...");
            scanner.nextLine();
            int diceValue = dice.roll();
            System.out.println("Dice rolled: " + diceValue);

            int newPosition = currentPlayer.getPosition() + diceValue;
            
            if (newPosition > board.getSize()) {
                if (winningStrategy.hasWon(newPosition, board.getSize())) {
                    currentPlayer.setPosition(board.getSize());
                    System.out.println(currentPlayer.getName() + " wins the game!");
                    return;
                } else {
                    System.out.println("Need exact number to win! Stay at position " + currentPlayer.getPosition());
                    nextPlayer();
                    continue;
                }
            }

            currentPlayer.setPosition(newPosition);

            Entity entity = board.getEntityAt(newPosition);
            if (entity != null) {
                System.out.println("Hit a " + entity.getEntityType() + "!");
                currentPlayer.setPosition(entity.getEnd());
                System.out.println("Moved to position: " + currentPlayer.getPosition());
                
                if (entity.getEntityType().equals("Snake")) {
                    killingStrategy.applyStrategy(currentPlayer);
                }
            }

            if (winningStrategy.hasWon(currentPlayer.getPosition(), board.getSize())) {
                System.out.println(currentPlayer.getName() + " wins the game!");
                return;
            }

            System.out.println(currentPlayer.getName() + " is now at position: " + currentPlayer.getPosition());
            System.out.println();

            nextPlayer();
        }
    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}
