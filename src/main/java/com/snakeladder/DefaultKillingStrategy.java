public class DefaultKillingStrategy implements KillingStrategy {
    @Override
    public void applyStrategy(Player player) {
        System.out.println("Snake bite! " + player.getName() + " stays at the new position.");
    }
}
