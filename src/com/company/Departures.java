package com.company;

import java.util.Date;

public class Departures implements Comparable<Departures> {
    private long trip_id;
    private Date departure_time;


    public Departures (long trip_id,Date departure_time) {
        this.trip_id = trip_id;
        this.departure_time = departure_time;
    }

    public long getTrip_id () {
        return trip_id;
    }

    public void setTrip_id (long trip_id) {
        this.trip_id = trip_id;
    }

    public Date getDeparture_time () {
        return departure_time;
    }

    public void setDeparture_time (Date departure_time) {
        this.departure_time = departure_time;
    }

    @Override
    public int compareTo (Departures o) {
        return  o.departure_time.compareTo ( this.departure_time ) <= 0 ? 1:-1;
    }


    @Override
    public String toString () {
        return trip_id+"";
    }
}
