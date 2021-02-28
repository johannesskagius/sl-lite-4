package com.company;

import java.time.Duration;
import java.util.*;

public class AStar {
    private Route route;
    private Stack<Node> visitedNodes = new Stack<> ();
    private Set<Node> visitedd = new HashSet<> ();
    private ArrayList<Node> visited = new ArrayList<> ();
    private Double heuristicMin;

    public AStar (Route route) {
        this.route = route;
    }

    //A* fungerar med
    public Route getPath (Node start,Node end) {
        //heuristicMin = start.calcHeuristicLength ( end );
        visitedNodes.push ( start );
        Node current = start;
        //Kolla om startnode
        while (checkIfFinish ( end,current )) {
            if (checkIfFinish ( end,current )) {
                if (!visitedNodes.peek ().equals ( current )) {
                    visitedNodes.push ( current );
                    visited.add ( current );
                }
                //Kolla om node har barn
                if (current.gotChilds ()) { // Om det finns barn gå till det billigaste, ej besökta bar
                    //ArrayList<Node> r = getPath ( findCheapestChild ( current,end ),end ).getRouteArray ();
                    Node cheapestChild = findCheapestChild ( current,end );
                    current = cheapestChild;
                }
            }
        }
        convertToRoute ( current );
        resetAllNodes ();
        turnToClosestRoute ( start );
        return route.getRoute ();
    }

    private void turnToClosestRoute (Node start) {
        ArrayList<Node> r = new ArrayList<> ();
        boolean routeFound = false;
        Node n;
        while (!routeFound) {
            n = visitedNodes.pop ();
            if (n.equals ( start )) {
                r.add ( n );
                routeFound = true;
            } else
                r.add ( n );
        }
        route.setRoute ( r );
    }

    private void resetAllNodes () {
        ArrayList<Node> xArr = route.getRouteArray ();
        for (Node x : xArr) {
            x.setVisited ( false );
        }
    }

    private void convertToRoute (Node current) {
        visitedNodes.push ( current );
        for (Node n : visitedNodes) {
            route.addNode ( n );
            visitedNodes.peek ();
        }
    }

    private Node findCheapestChild (Node current,Node end) {//Här vet vi att noden har barn,
        double HEURISTICMAX = 1000000; //Should be infinity;
        Node cheapestConnectedNode = null; // new Node (  );
        //Get the cheapest child if not visited.
        Map<Bow, Duration> n = current.getConnectedNodes ();
        //Get the cheapest unvisitedNode
        double heuristicCost = 0;

        for (Map.Entry<Bow, Duration> currentBow : n.entrySet ()) {
            //heuristicCost = currentBow.getValue ().doubleValue () + currentBow.getKey ().getConnectedTo ().calcHeuristicLength ( end );
            Node x = currentBow.getKey ().getConnectedTo ();
            //heuristicCost = x.calcHeuristicLength (end)+ currentBow.getValue ().doubleValue ();
            if (heuristicCost < HEURISTICMAX && !x.isVisited () || x.equals ( end )) {
                HEURISTICMAX = heuristicCost;
                cheapestConnectedNode = x;
            }
        }

        if (cheapestConnectedNode == null) {
            visitedNodes.pop ();
            cheapestConnectedNode = visitedNodes.peek ();
        }
        cheapestConnectedNode.setVisited ( true );
        return cheapestConnectedNode;
    }

    private boolean checkIfFinish (Node end,Node current) {
        return !current.equals ( end );
    }

}
