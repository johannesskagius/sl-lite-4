/**
 * @author josk3261 Johannes Skagius
 * Stockholms universitet
 * Kurs: ALDA - algoritmer och datastrukturer
 */

package com.company;

import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

public class Route {
    private SL_Trips_Routes sl_trips;
    private List<Node> route = new LinkedList<> ();
    private AStar aStar = new AStar ();
    private Node startNode;
    private Node endNode;

    public Route (SL_Trips_Routes sl_trips) {
        this.sl_trips = sl_trips;
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    public String getRouteDescription (Node start,Node end) {
        Time d = Time.valueOf ( LocalTime.now () );
        Time firstDeparture = null;
        route = aStar.getRoute ( start,end );
        Collections.reverse ( route );
        Node previous = null;
        String s ="";
        for (int i = 0; i < route.size (); i++) {
            Node ett = route.get ( i );
            Node tva = null;
            if (i != route.size () - 1) {
                tva = route.get ( i + 1 );
                long trip = ett.getDeparture ( tva ).getTrip_id ();
                d = ett.getNextDepartureTime ( new Departures ( tva, trip, d ) );
                if(firstDeparture == null) firstDeparture =d;
                s += "\n" + i + ", " + sl_trips.getTripInfo ( trip );
            } else {
                s += "\n" + i + ", " + ett;
            }
        }
        String x = "Travel from: " + route.get ( 0 ) + ", to: " + route.get ( route.size () - 1 ) +", Next departure: " + firstDeparture.toString ();
        return x+s;
    }
}
