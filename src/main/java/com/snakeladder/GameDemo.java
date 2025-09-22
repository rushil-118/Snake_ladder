import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Snake and Ladder Game");
        
        Board board = classicBoard();
        System.out.println("Using classic board game.");
        
        System.out.print("Enter number of players (2-4): ");
        int numPlayers = Integer.parseInt(input.nextLine().trim());
        numPlayers = Math.max(2, Math.min(4, numPlayers));
        
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = input.nextLine().trim();
            if (name.isEmpty()) {
                name = "Player" + i;
            }
            players.add(new HumanPlayer(name));
        }

        Game game = Game.builder()
            .withBoard(board)
            .withPlayers(players)
            .withDice(new SixSidedDice())
            .withWinningStrategy(new DefaultWinningStrategy())
            .withKillingStrategy(new DefaultKillingStrategy())
            .build();

        game.playGame();
        
        input.close();
    }
    
    private static Board classicBoard() {
        Board board = new Board(100);

        board.addEntity(new Snake(62, 19));
        board.addEntity(new Snake(64, 60));
        board.addEntity(new Snake(87, 24));
        board.addEntity(new Snake(93, 73));
        board.addEntity(new Snake(95, 75));
        board.addEntity(new Snake(99, 78));

        board.addEntity(new Ladder(1, 38));
        board.addEntity(new Ladder(4, 14));
        board.addEntity(new Ladder(9, 31));
        board.addEntity(new Ladder(21, 42));
        board.addEntity(new Ladder(28, 84));
        board.addEntity(new Ladder(51, 67));
        board.addEntity(new Ladder(71, 91));
        board.addEntity(new Ladder(80, 100));
        
        return board;
    }
}
