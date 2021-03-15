//  @author josk3261 Johannes Skagius
// Stockholms university
// Kurs: ALDA - algoritmer och datastrukturer
package com.company;

import java.time.Duration;
import java.util.*;

public class AStar {
    /**
     * This method finds and returns the most time efficient path between two nodes.
     * <p>
     * Returns a LinkedList containing the most efficient path (in time) between two positions in a graph.
     * This works in two steps. getRoute is the first method in the steps and is mapping the most efficient paths
     * to the nodes it crosses, the second method {@link #reconstructPath(Map,Node)}
     * reconstructs the closest path between the two given parameters
     * <p>
     *
     * @param start is the start position of the A* search.
     * @param end   is the target position for the A* search.
     * @return The most efficient path between two positions.
     * @throws IllegalArgumentException if there is no matching end node or no connection to the end node.
     * @see <a href="https://en.wikipedia.org/wiki/A*_search_algorithm#Pseudocode"> for more general information about the A*
     * algorithm </a>
     */
    protected LinkedList<Node> getRoute (Node start,Node end) {
        LinkedList<Node> result = null;
        boolean finished = false;
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
        Node current;
        while (!openSet.isEmpty ()) {
            current = openSet.peek ();
            if (current.equals ( end )) {
                result = reconstructPath ( cameFrom,current );
                finished = true;
                break;
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
        if (!finished) {
            throw new IllegalArgumentException ();
        }
        return result;
    }

    /**
     * This method reconstructs the most efficient path between the two given positions.
     *
     * @param cameFrom is a Map containing the previous Node and the Node it's going to.
     * @param current  Current is the latest position where the A* was at and when it's reaching this point Current node is going to be the target node.
     * @return Returns a LinkedList containing the reconstructed and the closest path from the startposition to the end position.
     */
    protected LinkedList<Node> reconstructPath (Map<Node, Node> cameFrom,Node current) {
        LinkedList<Node> closestRoute = new LinkedList<> ();
        closestRoute.add ( current );
        while (cameFrom.containsKey ( current )) {
            current = cameFrom.get ( current );
            closestRoute.add ( current );
        }
        Collections.reverse ( closestRoute );
        return closestRoute;
    }
}
