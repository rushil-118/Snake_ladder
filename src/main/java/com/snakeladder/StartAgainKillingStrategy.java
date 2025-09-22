public class StartAgainKillingStrategy implements KillingStrategy {
    @Override
    public void applyStrategy(Player player) {
        player.setPosition(0);
        System.out.println("Snake bite! " + player.getName() + " goes back to start!");
    }
}
