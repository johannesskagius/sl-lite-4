package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Main {
    SupportClassForAddingData s = new SupportClassForAddingData ( this );
    private Map<Long, Node> nodes = new HashMap<> ();
    private Map<String, Long> nodesByName = new HashMap<> ();
    private SL_Trips_Routes sl_trips = new SL_Trips_Routes ();

    /**
     *
     * @param args
     */
    public static void main (String[] args) {
        Main m = new Main ();
        m.addSlRoutes ();
        m.loadTrips ();
        m.addNodes ();
        long start = System.currentTimeMillis ();
        m.test ();
        System.out.println ( "Route found within: " + (System.currentTimeMillis () - start) + "ms" );
    }

    /**
     *
     */
    private void loadTrips () {
        sl_trips.setTrips ( s.getTrips () );
    }

    private void addSlRoutes () {
        sl_trips.setRoutes ( s.getRoutes () );
    }

    /**
     *
     */

    private void test () {
        Route r = new Route ( sl_trips );
        long startID = nodesByName.get ( "Gullmarsplan T-bana" );
        long finID = nodesByName.get ( "Stockholm Östermalmstorg" );
        Node start = nodes.get ( startID );
        Node finish = nodes.get ( finID );
        String s = r.getRouteDescription ( start,finish );
        System.out.println ( s );
    }

    private void addNodes () {
        SupportClassForAddingData s = new SupportClassForAddingData ( this );
        nodes = s.addNodes ();
        nodesByName = s.addNodesByName ();
        nodes = s.addBows ();
    }

    private void run () {

    }
}
