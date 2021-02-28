package com.company;

import java.time.Duration;
import java.util.*;


//Inspired by https://en.wikipedia.org/wiki/A*_search_algorithm#Pseudocode
public class AStar {
    private Route route;
    List<Node> open = new ArrayList<> ();
    Map<Node, Duration> closed = new HashMap<> ();

    public AStar (Route route) {
        this.route = route;
    }

    public LinkedList<Node> getRoute2 (Node start,Node end) {
        Queue<Node> openSet = new PriorityQueue<> ();
        openSet.offer ( start );
        Map<Node, Node> cameFrom = new HashMap<> ();
        final Duration defaultValue = Duration.ofHours ( 100 ); // Should be infinity

        //Gscore is the cost of the cheapest path from start to n currently known
        Map<Node, Duration> gScore = new HashMap<> ();
        gScore.put ( start,Duration.ZERO );

        //fScore for node n, fScore(n) = gScore(n)+h(n) or the heuristic cost of n.
        // fScore represent our current best guess as to how short a path from start to finish can be
        //Default value infinity
        Map<Node, Duration> fScore = new HashMap<> ();
        fScore.put ( start,Duration.ofMinutes ( 2 ) );
        Node current = null;
        int i = 0;
        while (!openSet.isEmpty ()) {
            current = openSet.peek ();
            if (current.equals ( end )) {
               return reconstructPath ( cameFrom,current, end );
            }
            openSet.poll ();
            Duration tentative_gScore;
            Map<Bow, Duration> connectedNodes = current.getConnectedNodes ();
            for (Map.Entry<Bow, Duration> c : connectedNodes.entrySet ()) {
                tentative_gScore = gScore.get ( current ).plus ( c.getValue () );
                //if true the this path is better than the previous
                if (tentative_gScore.compareTo ( gScore.getOrDefault ( c.getKey ().getConnectedTo (),defaultValue ) ) < 0) {
                    cameFrom.put ( c.getKey ().getConnectedTo (),current );
                    // if(gScore.containsKey ( c.getKey ().getConnectedTo () )){}
                    gScore.put ( c.getKey ().getConnectedTo (),tentative_gScore );
                    fScore.put ( c.getKey ().getConnectedTo (),tentative_gScore.plus ( c.getKey ().getConnectedTo ().calcHeuristicLength ( end ) ) );
                    if (!openSet.contains ( c.getKey ().getConnectedTo () )) {
                        openSet.offer ( c.getKey ().getConnectedTo () );
                    }
                }
            }
            System.out.println (i++);
        }
        throw new IllegalArgumentException ();
    }

    public LinkedList<Node> reconstructPath (Map<Node, Node> cameFrom,Node current, Node end) {
        LinkedList<Node> route2 = new LinkedList<> ();
        while (cameFrom.containsKey ( current )) {
            current = cameFrom.get ( current );
            route2.add ( current );
        }
        route2.add ( end );

        return route2;
    }


    private Duration calcHeuristicCostToEnd (Bow b,Node end) {
        Duration cost;
        if (b == null) {
            cost = b.getConnectedTo ().calcHeuristicLength ( end );
        } else {
            cost = b.getCost ().plus ( b.getConnectedTo ().calcHeuristicLength ( end ) );
        }
        return cost;
    }
}
