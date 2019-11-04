# Java-Threads
Concurrent simulation of a museum visit.

Explored the following concepts:

1. Detecting and understanding race conditions.
2. Synchronisation methods for safe-access to variables/objects.
3. Inheritance and abstract classes.

To gain a practical understanding of concurrency, this model museum visit (involving multiple threads) was created. Each thread spawned acted as a "person" who enters and exits rooms and the museum as a whole.

----

The model allows for each person to roam freely between the different rooms (of which there are currently 4) following the permitted direction. Each room is connected by one or more turnstiles where each people can move towards a chosen room iff there is space.

As there is no time limit and choice of rooms to visit next is randomised without external factors, it is possible that some threads continuously keep cycling through exhibition rooms (in the case where the two rooms are connected, permitting flow in both directions. Although this is by design, meaning possible tests - namely `consistencyLoopyMuseum` in `MuseumTest` may not complete certain runs and hang, there are possible ways of solving the issue of threads not exiting the museum.

1. Implement "hours" where the threads are able to roam for a certain period of time before they must all leave.
2. Have a "bias" for each thread which influences their choice of rooms towards rooms they have yet to visit, where visits are less frequent and away from rooms that are visited consistently.
3. Prevent cyclic visits and introduce a "one-direction tour" that has a single deterministic path between entrance and exit.

----

To further extend this program one can expand on the current model to simulate an actual museum, adding "attractiveness" in each ExhibitionRoom which influences room choice, operating hours and incidents. With the addition of statistical analysis of thread behaviour, room frequency and average time spent in each room (and more), the simulation can be used to analyse possible behaviours of people in current/renovated/planned museums.
