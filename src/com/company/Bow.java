//  @author josk3261 Johannes Skagius
// Stockholms university
// Kurs: ALDA - algoritmer och datastrukturer

package com.company;

import java.time.Duration;
import java.util.*;


/**
 * TODO notes
 */
public class Bow {
    private long trip_id;
    private Duration cost;
    private Node connectedTo;

    /**
     * @param cost          is the cost of moving between the two connected nodes
     * @param connectedTo   is the nod this bow is connected to.
     * @param trip_id       is the ID which belongs to the trip.
     */
    public Bow (Duration cost,Node connectedTo, long trip_id) {
        this.connectedTo = connectedTo;
        this.trip_id = trip_id;
        this.cost = cost;
    }

    public Duration getCost () {
        return cost;
    }

    public Node getConnectedTo () {
        return connectedTo;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Bow)) return false;
        Bow bow = (Bow) o;
        return Objects.equals ( connectedTo,bow.connectedTo );
    }

    @Override
    public int hashCode () {
        return Objects.hash ( connectedTo );
    }

    @Override
    public String toString () {
        return connectedTo.toString () +", :"+ cost.toMinutes ();
    }
}

