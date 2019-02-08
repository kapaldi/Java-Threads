package museumvisit;

public class Entrance extends MuseumSite {

    public Entrance() {
        super("Entrance");
    }

    @Override
    public void enter() {
        // Method does not need to maintain occupancy as room has no occupancy limit.
    }

    @Override
    public void exit() {
        // Method does not need to maintain occupancy as room has no occupancy limit.
    }

    // always available
    @Override
    public boolean hasAvailability() {
        return true;
    }

    // getExitTurnstile like super's

}
