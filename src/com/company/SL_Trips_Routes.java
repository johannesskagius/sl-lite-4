//  @author josk3261 Johannes Skagius
// Stockholms university
// Kurs: ALDA - algoritmer och datastrukturer

package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class SL_Trips_Routes {
    private Map<Long, String> routes = new HashMap<> ();
    private Map<Long, Trip> trips = new HashMap<> ();

    /**
     * This method returns the connected information between a route and trips.
     *
     * A trip contain the necessary information between two nodes, departure from, departure to, duration, trip_id
     * A route contains one to many trips and contains the information which will be presented to the user interface, like final destination, route id etc
     * @see <a href="https://developers.google.com/transit/gtfs/reference"> Google Transit API </a>
     * @param tripID is the ID for a trip, which is the key for routes.
     * @return String containing route id and the headsign.
     */
    public String getTripInfo(long tripID){
        Trip t = trips.get ( tripID );
        String route = routes.get ( t.getRoute_id () );
        return route+", "+ t.getTrip_headsign ();
        }

    public void setRoutes (Map<Long, String> routes) {
        this.routes = routes;
    }

    public void setTrips (Map<Long, Trip> trips) {
        this.trips = trips;
    }
}
