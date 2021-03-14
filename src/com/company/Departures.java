//  @author josk3261 Johannes Skagius
// Stockholms university
// Kurs: ALDA - algoritmer och datastrukturer
package com.company;

import java.sql.Time;
import java.util.Date;

public class Departures implements Comparable<Departures> {
    private Node goingTo;
    private long trip_id;
    private Time departure_time;

    /**
     * Constructs a departure containing the following information
     * @param goingTo           To where the departure is going
     * @param trip_id           The identification number of the trip.
     * @param departure_time    The departure time
     */
    public Departures (Node goingTo,long trip_id,Time departure_time) {
        this.goingTo = goingTo;
        this.trip_id = trip_id;
        this.departure_time = departure_time;
    }

    public long getTrip_id () {
        return trip_id;
    }

    public Node getGoingTo () {
        return goingTo;
    }

    public Time getDeparture_time () {
        return departure_time;
    }


    @Override
    public int compareTo (Departures o) {
        return  o.departure_time.compareTo ( this.departure_time ) <= 0 ? 1:-1;
    }

    @Override
    public String toString () {
        return goingTo+"";
    }
}
