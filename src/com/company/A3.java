package com.company;

import java.time.Duration;
import java.util.*;


//Inspired by https://mat.uab.cat/~alseda/MasterOpt/AStar-Algorithm.pdf
public class A3 {
    private final Route route;
    List<Node> open = new ArrayList<> ();
    Map<Node, Duration> closed = new HashMap<> ();

    public A3 (Route route) {
        this.route = route;
    }

    public void getRoute (Node start,Node end) {
        start.calcHeuristicLength ( end );
        open.add ( start );
        Node current = null;
        while (open.size () != 0) { //fixa en redan sorterad lista
            try {
                Collections.sort ( open );
            } catch (Exception e) {
                e.printStackTrace ();
            }

            current = open.get ( 0 );
            if (current.equals ( end )) {
                break;
            }
            Duration costToSuccessor = null;
            //Generate each state node_successor that come after node_current
            Map<Bow, Duration> successorMap = current.getConnectedNodes ();
            for (Map.Entry<Bow, Duration> currentSuccessor : successorMap.entrySet ()) {
                Node currentsSuccessor = currentSuccessor.getKey ().getConnectedTo ();
                currentsSuccessor.calcHeuristicLength ( end );          //Räknar heuristiken många gånger för samma nod. Bör korrigeras
                costToSuccessor = currentSuccessor.getKey ().getConnectedTo ().calcHeuristicLength ( end ).plus ( currentSuccessor.getValue () );
                //Duration costToSuccessor = currentSuccessor.getKey ().getWeight ().plus ( calcHeuristicCostToEnd ( currentSuccessor.getKey (), end ) );
                currentsSuccessor.setHeuristicDistance ( costToSuccessor );
                if (currentsSuccessor.equals ( end )) {
                    break;
                }
                if (open.contains ( currentsSuccessor )) {
                    if (currentsSuccessor.getHeuristicDistance ().compareTo ( costToSuccessor ) <= 0)
                        continue; // den här måste vara fel
                } else if (closed.containsKey ( currentsSuccessor )) {
                    //if g(node_successor) ≤ successor_current_cost continue (to line 20)
                    if (currentsSuccessor.getHeuristicDistance ().compareTo ( costToSuccessor ) <= 0) {
                        if (currentsSuccessor.getHeuristicDistance ().compareTo ( costToSuccessor ) <= 0) {
                            continue;
                        }
                        closed.remove ( currentsSuccessor );      //
                        open.add ( currentsSuccessor );
                    }
                } else {
                    open.add ( currentsSuccessor );
                }
                currentsSuccessor.setCost ( currentsSuccessor.getCost ( currentSuccessor.getKey () ) );
            }
            closed.put ( current,costToSuccessor );
            open.remove ( 0 );
        }
    }


    public void getRoute2 (Node start,Node end) {
        Queue<Node> openSet = new PriorityQueue<> ();
        openSet.offer ( start );
        Map<Node, Node> cameFrom = new HashMap<> ();
        final Duration defaultValue = Duration.ofHours ( 3 );

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
                reconstructPath ( cameFrom,current );
            }
            openSet.poll ();
            Duration tentative_gScore;
            Map<Bow, Duration> connectedNodes = current.getConnectedNodes ();
            for (Map.Entry<Bow, Duration> c : connectedNodes.entrySet ()) {
                tentative_gScore = gScore.get ( current ).plus ( c.getValue () );
                //if true the this path is better than the previous
                if (tentative_gScore.compareTo ( gScore.getOrDefault ( c.getKey ().getConnectedTo (),Duration.ofHours ( 3 ) ) ) < 0) {
                    cameFrom.put ( c.getKey ().getConnectedTo (),current );
                    // if(gScore.containsKey ( c.getKey ().getConnectedTo () )){}
                    gScore.put ( c.getKey ().getConnectedTo (),tentative_gScore );
                    fScore.put ( c.getKey ().getConnectedTo (),tentative_gScore.plus ( c.getKey ().getConnectedTo ().calcHeuristicLength ( end ) ) );
                    if (!openSet.contains ( c.getKey ().getConnectedTo () )) {
                        openSet.offer ( c.getKey ().getConnectedTo () );
                    }
                }
            }
            System.out.println ( i++ );
        }
    }

    public void reconstructPath (Map<Node, Node> cameFrom,Node current) {
        while (cameFrom.containsKey ( current )) {
            System.out.println ( 2 );
        }
    }


    private Duration calcHeuristicCostToEnd (Bow b,Node end) {
        Duration cost;
        if (b == null) {
            cost = b.getConnectedTo ().calcHeuristicLength ( end );
        } else {
            cost = b.getWeight ().plus ( b.getConnectedTo ().calcHeuristicLength ( end ) );
        }
        return cost;
    }
}
