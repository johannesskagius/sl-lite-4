package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Route {
    private SL_Trips_Routes sl_trips = new SL_Trips_Routes ();
    private List<Node> route = new LinkedList<> ();
    private AStar aStar = new AStar (this);
    private Node startNode;
    private Node endNode;

    public Route (SL_Trips_Routes sl_trips) {
    }

    public void getRoute(Node start, Node end){
        route = aStar.getRoute2(start, end);
    }

    public Route getRoute(){
        return this;
    }

    public Node addNode (Node node) {
        if (route.size () == 0)
            startNode = node;
        route.add ( node );
        endNode = node;
        return node;
    }

    public void setRoute (ArrayList<Node> route) {
        this.route = route;
    }

    public int getNrOfStops () {
        return route.size ();
    }

    public Node getStartNode () {
        return startNode;
    }

    public Node getEndNode () {
        return endNode;
    }
}
