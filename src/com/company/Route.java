//  @author josk3261 Johannes Skagius
// Stockholms university
// Kurs: ALDA - algoritmer och datastrukturer

package com.company;

import java.sql.Time;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Route {
    private final Trip_Route_Info tripInfo;
    private final AStar aStar = new AStar ();
    private List<Node> route = new LinkedList<> ();
    private Node startNode;
    private Node endNode;

    public Route (Trip_Route_Info tripInfo) {
        this.tripInfo = tripInfo;
    }

    /**
     * This method summarizes the necessary information which should be returned to the user interface.
     *
     * @param start the start position in the graph
     * @param end   the end position in the graph
     * @return String filled with information about the route.
     */
    public String getRouteDescription (Node start,Node end) {
        Time d = Time.valueOf ( LocalTime.now () );
        Time firstDeparture = null;
        route = aStar.getRoute ( start,end );
        StringBuilder s = new StringBuilder ();
        for (int i = 0; i < route.size (); i++) {
            Node ett = route.get ( i );
            Node tva;
            if (i != route.size () - 1) {
                tva = route.get ( i + 1 );
                long trip = ett.getDeparture ( tva ).getTrip_id ();
                d = ett.getNextDepartureTime ( new Departures ( tva,trip,d ) );
                if (firstDeparture == null)
                    firstDeparture = d;
                s.append ( "\n" ).append ( i ).append ( ", " ).append ( tripInfo.getTripInfo ( trip ) );
            } else {
                s.append ( "\n" ).append ( i ).append ( ", " ).append ( ett );
            }
        }
        String complete = "";
        if(firstDeparture != null)
         complete = "Travel from: " + route.get ( 0 ) + ", to: " + route.get ( route.size () - 1 ) + ", Next departure: " + firstDeparture.toString ();
        else // firstDeparture.toString is null the times the randomly selected nodes are the same.
            complete = "Travel from: " + route.get ( 0 ) + ", to: " + route.get ( route.size () - 1 );
        return complete + s;
    }
}
