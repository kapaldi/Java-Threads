package museumvisit;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Visitor implements Runnable {

    private final String name;
    private MuseumSite currentRoom;

    public Visitor(String name, MuseumSite initialRoom) {
        this.name = name;
        assert (initialRoom instanceof Entrance);
        this.currentRoom = initialRoom;
    }

    public void run() {
        Turnstile choice;
        Optional<MuseumSite> newRoom;
        while (thereAreMoreSitesToVisit()) {
            simulateVisitToCurrentRoom();
            choice = pickRandomTurnstile();
            newRoom = choice.passToNextRoom();
            if (!newRoom.isPresent()) {
                waitSomeTimeBeforeRetrying();
            } else {
                currentRoom = newRoom.get();
            }
        }
    }

    private boolean thereAreMoreSitesToVisit() {
        return !currentRoom.getExitTurnstiles().isEmpty();
    }

    private Turnstile pickRandomTurnstile() {
        List<Turnstile> exitTurnstiles = currentRoom.getExitTurnstiles();
        assert !exitTurnstiles.isEmpty();

        Collections.shuffle(exitTurnstiles);
        return exitTurnstiles.stream().findAny().get();
    }

    /* Wait in each room some time between 1 and 200 milliseconds. */
    private void simulateVisitToCurrentRoom() {
        final int randomVisitTimeInMillis = (int) (Math.random() * 200) + 1;
        try {
            Thread.sleep(randomVisitTimeInMillis);
        } catch (InterruptedException e) {
        }
    }

    private void waitSomeTimeBeforeRetrying() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public String toString() {
        return "Visitor " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Visitor) {
            return this.name.equals(((Visitor) obj).name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), name);
    }

    public static void main(String[] args) {
    }
}
