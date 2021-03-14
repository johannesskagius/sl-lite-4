//  @author josk3261 Johannes Skagius
// Stockholms university
// Kurs: ALDA - algoritmer och datastrukturer

package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 *
 */
public class Main {
    SupportClassForAddingData s = new SupportClassForAddingData ( this );
    private Map<Long, Node> nodes = new HashMap<> ();
    private Map<String, Long> nodesByName = new HashMap<> ();
    private SL_Trips_Routes sl_trips = new SL_Trips_Routes ();


    public static void main (String[] args) {
        Main m = new Main ();
        m.addSlRoutes ();
        m.loadTrips ();
        m.addNodes ();
        m.run ();
    }

    private void loadTrips () {
        sl_trips.setTrips ( s.getTrips () );
    }

    private void addSlRoutes () {
        sl_trips.setRoutes ( s.getRoutes () );
    }

    private void test () {
        Route r = new Route ( sl_trips );
        long startID = nodesByName.get ( "Gullmarsplan T-bana" );
        long finID = nodesByName.get ( "Stockholm Östermalmstorg" );
        Node start = nodes.get ( startID );
        Node finish = nodes.get ( finID );
        String s = r.getRouteDescription ( start,finish );
        System.out.println ( s );
    }

    /**
     * Final test for this program to make sure everything works.
     * <p>
     * Chooses to nodes randomly, finds the most time efficient path between them and record the time it took to find the path.
     */
    private void efficiencyTest (int choice) {
        long effiency = 0;
        String s = "";
        Route r = new Route ( sl_trips );
        Random random = new Random ();
        int start;
        int fin;
        for (int i = 0; i < choice; i++) {
            start = random.nextInt ( 443 );
            fin = random.nextInt ( 443 );
            Node startNode = getNode ( start );
            Node finNode = getNode ( fin );
            long startTime = System.currentTimeMillis ();
            s = r.getRouteDescription ( startNode,finNode );
            long endTime = System.currentTimeMillis ();
            effiency += (endTime - startTime);
        }
        if (choice == 2) {
            System.out.println ( s );
        } else {
            System.out.println ( "average: " + effiency / choice + "ms" );
        }
    }

    private Node getNode (int start) {
        int i = 0;
        for (Map.Entry<Long, Node> x : nodes.entrySet ()) {
            if (i == start) {
                return x.getValue ();
            }
            i++;
        }
        return null;
    }

    private void addNodes () {
        SupportClassForAddingData s = new SupportClassForAddingData ( this );
        nodes = s.addNodes ();
        nodesByName = s.addNodesByName ();
        nodes = s.addBows ();
    }

    private void run () {
        Scanner scanner = new Scanner ( System.in );
        int choice = 0;
        do {
            System.out.println ( "What would you like to do?" );
            System.out.println ( "Enter: 1 printing the route guide between two randomized stations" );
            System.out.println ( "Enter: 2 getting the average time for finding x number of paths" );
            System.out.println ( "Enter: 3 to quit" );
            choice = getChoice ( scanner );
            switch (choice) {
                case 1 -> efficiencyTest ( 2 );
                case 2 -> {
                    System.out.println ( "How many would you like to run?" );
                    int howMany = getChoice ( scanner );
                    efficiencyTest ( howMany );
                }
            }
        } while (choice != 3);
        scanner.close ();
    }

    private int getChoice (Scanner scanner) {
        try {
            return Integer.parseInt ( scanner.nextLine () );
        } catch (NumberFormatException e) {
            return getChoice ( scanner );
        }
    }
}
