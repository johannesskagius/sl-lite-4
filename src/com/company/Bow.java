package com.company;

import java.time.Duration;
import java.util.Date;
import java.util.Objects;
import java.util.SortedSet;

public class Bow {
    private Duration weight;    // Ändra till tid / se till att det är samma enhet
    private Node connectedTo;
    private SortedSet<Date> departures;

    public Bow (Duration weight,Node connectedTo) {
        this.connectedTo = connectedTo;
        this.weight = weight;
        //this.departures =;
    }

    public void addDepartureTime(Date d){
        departures.add ( d );
    }

    public Duration getWeight () {
        return weight;
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
        return connectedTo.toString () +", :"+ weight.toMinutes ();
    }
}

