import java.util.HashMap;
import java.util.Map;

public class Board {
    private int size;
    private Map<Integer, Entity> entities;

    public Board(int size) {
        this.size = size;
        this.entities = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    public void addEntity(Entity entity) {
        entities.put(entity.getStart(), entity);
    }

    public Entity getEntityAt(int position) {
        return entities.get(position);
    }

    public void displayBoard() {
        System.out.println("Board entities:");
        for (Map.Entry<Integer, Entity> entry : entities.entrySet()) {
            Entity entity = entry.getValue();
            System.out.println(entity.getEntityType() + " from " + entity.getStart() + " to " + entity.getEnd());
        }
    }
}
