package com.company;

import java.util.HashMap;
import java.util.Map;

public class SL_Trips_Routes {
    private Map<Long, String> routes = new HashMap<> ();
    private Map<Long, String> trips = new HashMap<> ();

    public void addRoutes(long routeID, String s){
        routes.put ( routeID, s ); // @param s is either long name or short name.
    }

    public void addTrips(long tripId, String s){
        trips.put ( tripId, s ); // @param s is trip_headsign
    }

    public Map<Long, String> getRoutes () {
        return routes;
    }

    public void setRoutes (Map<Long, String> routes) {
        this.routes = routes;
    }

    public Map<Long, String> getTrips () {
        return trips;
    }

    public void setTrips (Map<Long, String> trips) {
        this.trips = trips;
    }
}
