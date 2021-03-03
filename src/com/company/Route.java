package com.company;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Route {
    private SL_Trips_Routes sl_trips = new SL_Trips_Routes ();
    private List<Node> route = new LinkedList<> ();
    private AStar aStar = new AStar ( this );
    private Node startNode;
    private Node endNode;

    public Route (SL_Trips_Routes sl_trips) {
        this.sl_trips = sl_trips;
    }

    public String getRouteDescription (Node start,Node end) {
        Date d = new Date ();
        route = aStar.getRoute2 ( start,end );
        Node previous = null;
        String s = "Travel from: " + route.get ( 0 ) + ", to: " + route.get ( route.size () - 1 );
        for (int i = 0; i < route.size (); i++) {
            Node ett = route.get ( i );

            Node tva = null;
            if (i != route.size () - 1) {
                tva = route.get ( i + 1 );
                long trip = ett.getDeparture ( tva ).getTrip_id ();
                d = ett.getNextDepartureTime ( new Departures ( tva, trip, d ) );
                s += "\n" + i + ", " + sl_trips.getTripInfo ( trip );
            } else {
                s += "\n" + i + ", " + ett;
            }
        }
        return s;
    }


    public Route getRoute () {
        return this;
    }

    public void setRoute (ArrayList<Node> route) {
        this.route = route;
    }

    public Node addNode (Node node) {
        if (route.size () == 0)
            startNode = node;
        route.add ( node );
        endNode = node;
        return node;
    }

    public int getNrOfStops () {
        return route.size ();
    }

    public Node getStartNode () {
        return startNode;
    }

    public Node getEndNode () {
        return endNode;
    }
}
