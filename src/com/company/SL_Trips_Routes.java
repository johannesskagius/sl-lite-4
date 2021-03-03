package com.company;

import java.util.HashMap;
import java.util.Map;

public class SL_Trips_Routes {
    private Map<Long, String> routes = new HashMap<> ();
    private Map<Long, Trip> trips = new HashMap<> ();

    public void addRoutes(long routeID, String s){
        routes.put ( routeID, s ); // @param s is either long name or short name.
    }

    public void addTrips(long tripId, Trip s){
        trips.put ( tripId, s ); // @param s is trip_headsign
    }

    public Map<Long, String> getRoutes () {
        return routes;
    }


    public String getTripInfo(long tripID){
        Trip t = trips.get ( tripID );
        String route = routes.get ( t.getRoute_id () );
        return route+", "+ t.getTrip_headsign ();
        }

    public void setRoutes (Map<Long, String> routes) {
        this.routes = routes;
    }

    public Map<Long, Trip> getTrips () {
        return trips;
    }

    public void setTrips (Map<Long, Trip> trips) {
        this.trips = trips;
    }
}
