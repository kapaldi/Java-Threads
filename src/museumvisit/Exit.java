package museumvisit;

import java.util.Collections;
import java.util.List;

/* In Exit state, individual threads don't have to be tracked as individuals enter this state iff the thread
 *  is leaving the museum. If the thread would like to re-enter museum, they will be treated as a new tracked
 *  individual since entering Entrance state.
 *
 *  This means, thread can always leave as there is no limited capacity for individuals outside the museum,
 *  in this model.
 */
public class Exit extends MuseumSite {

    public Exit() {
        super("Exit");
    }

    @Override
    public boolean hasAvailability() {
        return true;
    }

    @Override
    public List<Turnstile> getExitTurnstiles() {
        return Collections.emptyList();
    }
}
