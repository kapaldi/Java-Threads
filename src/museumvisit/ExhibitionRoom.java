package museumvisit;

public class ExhibitionRoom extends MuseumSite {

    private final int CAPACITY;

    public ExhibitionRoom(String name, int capacity) {
        super(name);
        assert capacity > 0;
        this.CAPACITY = capacity;
    }

    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public boolean hasAvailability() {
        return occupancy < CAPACITY;
    }
}
