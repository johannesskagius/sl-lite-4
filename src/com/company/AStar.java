/**
 * @author josk3261 Johannes Skagius
 * Stockholms universitet
 * Kurs: ALDA - algoritmer och datastrukturer
 */
package com.company;

import java.time.Duration;
import java.util.*;


//Inspired by https://en.wikipedia.org/wiki/A*_search_algorithm#Pseudocode
public class AStar {
    /**
     *
     * This is the A* algoritm that finds a path between two positions in a graph. It needs two inparameters a start position and a target (end) node.
     *
     *
     * @param start
     * @param end
     * @return
     * @throws IllegalArgumentException ()
     */
    protected LinkedList<Node> getRoute (Node start,Node end) {
        Queue<Node> openSet = new PriorityQueue<> ();
        openSet.offer ( start );
        Map<Node, Node> cameFrom = new HashMap<> ();
        final Duration defaultValue = Duration.ofHours ( 100 ); // Should be infinity
        //Gscore is the cost of the cheapest path from start to n currently known
        Map<Node, Duration> gScore = new HashMap<> ();
        gScore.put ( start,Duration.ZERO );
        //fScore for node n, fScore(n) = gScore(n)+h(n) or the heuristic cost of n.
        // fScore represent our current best guess as to how short a path from start to finish can be
        // D efault value infinity
        Map<Node, Duration> fScore = new HashMap<> ();
        fScore.put ( start,Duration.ofMinutes ( 2 ) );
        Node current = null;
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
                    gScore.put ( c.getKey ().getConnectedTo (),tentative_gScore );
                    fScore.put ( c.getKey ().getConnectedTo (),tentative_gScore.plus ( c.getKey ().getConnectedTo ().calcHeuristicLength ( end ) ) );
                    if (!openSet.contains ( c.getKey ().getConnectedTo () )) {
                        openSet.offer ( c.getKey ().getConnectedTo () );
                    }
                }
            }
        }
        throw new IllegalArgumentException ();
    }

    /**
     *This method is reconstructing the path generated from the A* algorithm above. It takes three in parameters
     * @param cameFrom is a Map containing the previous Node and the Node it's going to.
     * @param current Current is the latest position where the A* was at and when it's reaching this point Current node is going to be the target node.
     * @param end
     * @return Returns a LinkedList containing the reconstructed and the closest path from the startposition to the end position.
     */
    protected LinkedList<Node> reconstructPath (Map<Node, Node> cameFrom,Node current, Node end) {
        LinkedList<Node> route2 = new LinkedList<> ();
        route2.add ( current );
        while (cameFrom.containsKey ( current )) {
            current = cameFrom.get ( current );
            route2.add ( current );
        }
        return route2;
    }
}
