package com.company;

import java.util.HashMap;
import java.util.Map;

public class Main {
    SupportClassForAddingData s = new SupportClassForAddingData ( this );
    private Map<Long, Node> nodes = new HashMap<> ();
    private Map<String, Long> nodesByName = new HashMap<> ();
    private Map<Long, String> routeNames = new HashMap<> ();
    private Map<Long, Trip> tripsInfo = new HashMap<> ();

    private SL_Trips_Routes sl_trips = new SL_Trips_Routes ();


    public static void main(String[] args) {
        Main m = new Main ();
        m.addSlRoutes ();
        m.loadTrips();
        m.addNodes();
        m.test();
    }

    private void loadTrips () {
        tripsInfo = s.getTrips ();
    }

    private void addSlRoutes () {
        routeNames = s.getRoutes();
    }


    private void test () {
        Route r = new Route (sl_trips);
        long startID = nodesByName.get ( "Mariatorget T-bana" );
        long finID = nodesByName.get ( "Sickla station" );
        Node start = nodes.get ( startID );
        Node finish = nodes.get ( finID );
        r.getRoute ( start, finish );
        System.out.println (r.getEndNode ());
        //r2.getEndNode ().toString ();

    }


    private void addNodes () {
        SupportClassForAddingData s = new SupportClassForAddingData (this);
        nodes = s.addNodes ();
        nodesByName = s.addNodesByName();
        nodes = s.addBows ();
        System.out.println ("s");
    }

    private void run (){

    }
}
