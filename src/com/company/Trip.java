package com.company;

import java.util.Objects;

public class Trip {
    private long route_id;
    private long trip_id;
    private String trip_headsign;
    private String trip_short_name;

    public Trip (long route_id,long trip_id,String trip_headsign,String trip_short_name) {
        this.route_id = route_id;
        this.trip_id = trip_id;
        this.trip_headsign = trip_headsign;
        this.trip_short_name = trip_short_name;
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
