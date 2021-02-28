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


    private void test () {
        Route r = new Route ();
        long startID = nodesByName.get ( "Zinkensdamm T-bana" );
        long finID = nodesByName.get ( "T-Centralen T-bana" );
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
