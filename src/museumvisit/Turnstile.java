package museumvisit;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Turnstile {

    private final MuseumSite originRoom;
    private final MuseumSite destinationRoom;

    public Turnstile(MuseumSite originRoom, MuseumSite destinationRoom) {
        assert !originRoom.equals(destinationRoom);
        this.originRoom = originRoom;
        this.destinationRoom = destinationRoom;
    }

    public Optional<MuseumSite> passToNextRoom() {
        MuseumSite dest = destinationRoom;
        MuseumSite orig = originRoom;
        if (destinationRoom.hashCode() >= originRoom.hashCode()) {
            dest = originRoom;
            orig = destinationRoom;
        }
        synchronized (dest) {
            synchronized (orig) {
                if (destinationRoom.hasAvailability()) {
                    destinationRoom.enter();
                    originRoom.exit();
                    return Optional.of(destinationRoom);
                }
            }
        }
        return Optional.empty();
    }

    public MuseumSite getOriginRoom() {
        return originRoom;
    }

    public MuseumSite getDestinationRoom() {
        return destinationRoom;
    }
}
