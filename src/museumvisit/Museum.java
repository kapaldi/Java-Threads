package museumvisit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import org.w3c.dom.events.MutationEvent;

public class Museum {

    private final Entrance entrance;
    private final Exit exit;
    private final Set<MuseumSite> sites;

    public Museum(Entrance entrance, Exit exit, Set<MuseumSite> sites) {
        this.entrance = entrance;
        this.exit = exit;
        this.sites = sites;
    }

    /* Example run and concluding messages to indicate final state. */
    public static void main(String[] args) {
        final int numberOfVisitors = 5;
        final Museum museum = buildLoopyMuseum();

        // Create the threads for the visitors and get them moving.
        List<Thread> visitors = new ArrayList<>();
        IntStream.range(0, numberOfVisitors)
                .sequential()
                .forEach(
                        i -> {
                            Thread visitorThread =
                                    new Thread(new Visitor(Integer.toString(i), museum.getEntrance()));
                            visitors.add(visitorThread);
                            visitorThread.start();
                        });

        // Wait for them to complete their visit.
        visitors.forEach(
                v -> {
                    try {
                        v.join();
                    } catch (InterruptedException e) {
                    }
                });

        // Checking no one is left behind.
        if (museum.getExit().getOccupancy() == numberOfVisitors) {
            System.out.println("\nAll the visitors reached the exit\n");
        } else {
            System.out.println(
                    "\n"
                            + (numberOfVisitors - museum.getExit().getOccupancy())
                            + " visitors did not reach the exit. Where are they?\n");
        }

        System.out.println(
                "Threads created: "
                        + visitors.size()
                        + " starting at the "
                        + museum.getEntrance().toString()
                        + "\n");

        System.out.println("Occupancy status for each room (should all be zero, but the exit site):");
        museum
                .getSites()
                .forEach(
                        s -> {
                            System.out.println("Site " + s.getName() + " final occupancy: " + s.getOccupancy());
                        });
    }

    public static Museum buildSimpleMuseum() {
        Set<MuseumSite> sites = new HashSet<>();
        Entrance entrance = new Entrance();
        Exit exit = new Exit();

        ExhibitionRoom room = new ExhibitionRoom("Exhibition Room", 10);
        entrance.addExitTurnstile(new Turnstile(entrance, room));
        room.addExitTurnstile(new Turnstile(room, exit));

        sites.add(room);

        return new Museum(entrance, exit, sites);
    }

    public static Museum buildLoopyMuseum() {
        Set<MuseumSite> sites = new HashSet<>();
        Entrance entrance = new Entrance();
        Exit exit = new Exit();

        ExhibitionRoom venom = new ExhibitionRoom("VenomKillerAndCureRoom", 10);
        ExhibitionRoom whales = new ExhibitionRoom("Whales Exhibition Room", 10);

        entrance.addExitTurnstile(new Turnstile(entrance, venom));

        venom.addExitTurnstile(new Turnstile(venom, whales));
        venom.addExitTurnstile(new Turnstile(venom, exit));

        // Unidirectional so need 2 turnstiles between the 2 same rooms.
        whales.addExitTurnstile(new Turnstile(whales, venom));

        sites.add(venom);
        sites.add(whales);

        return new Museum(entrance, exit, sites);
    }

    public Entrance getEntrance() {
        return entrance;
    }

    public Exit getExit() {
        return exit;
    }

    public Set<MuseumSite> getSites() {
        return sites;
    }
}
