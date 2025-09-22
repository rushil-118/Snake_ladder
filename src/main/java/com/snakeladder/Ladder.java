public class Ladder implements Entity {
    private int start;
    private int end;

    public Ladder(int start, int end) {
        if (start >= end) {
            throw new IllegalArgumentException("Ladder start position must be less than end position");
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public int getEnd() {
        return end;
    }

    @Override
    public String getEntityType() {
        return "Ladder";
    }

    @Override
    public String toString() {
        return "Ladder from " + start + " to " + end;
    }
}
