package com.company;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private Map<Long, Node> nodes = new HashMap<> ();
    private Map<String, Long> nodesByName = new HashMap<> ();

    public static void main(String[] args) {
        Main m = new Main ();
        m.addNodes();
        m.addBows();
        m.test();
    }

    /*
    * Stationer som inte fungerar
    * Lidingö baggeby station
    *
    * */

    private void test () {
        Route r = new Route ();
        long startID = nodesByName.get ( "Mariatorget T-bana" );
        long finID = nodesByName.get ( "Sickla station" );
        Node start = nodes.get ( startID );
        Node finish = nodes.get ( finID );
        //Route r2 =

        r.getRoute3 ( start, finish );
        System.out.println (r.getEndNode ());
        //r2.getEndNode ().toString ();

    }

    private void addBows () {
        SupportClassForAddingData s = new SupportClassForAddingData (this);
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
