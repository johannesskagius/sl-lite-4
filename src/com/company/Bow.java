package com.company;

import java.time.Duration;
import java.util.*;

public class Bow {
    private long trip_id;
    private Duration cost;    // Ändra till tid / se till att det är samma enhet
    private Node connectedTo;

    public Bow (Duration weight,Node connectedTo, long trip_id) {
        this.connectedTo = connectedTo;
        this.trip_id = trip_id;
        this.cost = weight;
        //this.departures =;
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

