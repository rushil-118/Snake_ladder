public class Snake implements Entity {
    private int start;
    private int end;

    public Snake(int start, int end) {
        if (start <= end) {
            throw new IllegalArgumentException("Snake start position must be greater than end position");
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
        return "Snake";
    }

    @Override
    public String toString() {
        return "Snake from " + start + " to " + end;
    }
}
