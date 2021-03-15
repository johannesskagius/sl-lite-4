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
public class Run {
    private final Trip_Route_Info tripInfo = new Trip_Route_Info ();
    private Load_Data loadData = new Load_Data ( this );
    private Map<Long, Node> nodes = new HashMap<> ();
    private Map<String, Long> nodesByName = new HashMap<> ();

    public static void main (String[] args) {
        Run m = new Run ();
        m.addSlRoutes ();
        m.loadTrips ();
        m.addNodes ();
        m.run ();
    }

    private void loadTrips () {
        tripInfo.setTrips ( loadData.getTrips () );
    }

    private void addSlRoutes () {
        tripInfo.setRoutes ( loadData.getRoutes () );
    }

    private void test () {
        Route r = new Route ( tripInfo );
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
        long startTime = 0;
        long endTime = 0;
        long effiency = 0;
        String s = "";
        Route r = new Route ( tripInfo );
        Random random = new Random ();
        int start;
        int fin;
        for (int i = 0; i < choice; i++) {
            start = random.nextInt ( 443 );
            fin = random.nextInt ( 443 );
            Node startNode = getNode ( start );
            Node finNode = getNode ( fin );
            startTime = System.currentTimeMillis ();
            s = r.getRouteDescription ( startNode,finNode );
            endTime = System.currentTimeMillis ();
            effiency += (endTime - startTime);
        }
        if (choice == 2) {
            System.out.println ( s );
            System.out.println ( "Found in: " + (endTime - startTime) + "ms" );
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
        Load_Data s = new Load_Data ( this );
        nodes = s.addNodes ();
        nodesByName = s.addNodesByName ();
        nodes = s.addBows ();
    }

    private void run () {
        Scanner scanner = new Scanner ( System.in );
        int choice;
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
