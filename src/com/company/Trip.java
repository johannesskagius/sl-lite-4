//  @author josk3261 Johannes Skagius
// Stockholms university
// Kurs: ALDA - algoritmer och datastrukturer

package com.company;

import java.util.Objects;

public class Trip {
    private final long route_id;
    private final long trip_id;
    private final String trip_headsign;
    private final String trip_short_name;

    public Trip (long route_id,long trip_id,String trip_headsign,String trip_short_name) {
        this.route_id = route_id;
        this.trip_id = trip_id;
        this.trip_headsign = trip_headsign;
        this.trip_short_name = trip_short_name;
    }

    public long getRoute_id () {
        return route_id;
    }

    public String getTrip_headsign () {
        return trip_headsign;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Trip)) return false;
        Trip trip = (Trip) o;
        return route_id == trip.route_id && trip_id == trip.trip_id;
    }

    @Override
    public int hashCode () {
        return Objects.hash ( route_id,trip_id );
    }
}
