package com.company;

import java.util.ArrayList;

public class Route {
    private ArrayList<Node> route = new ArrayList<> ();
    //private AStar aStar = new AStar (this);
    private AStar2 aStar2 = new AStar2 (this);
    private A3 a3 = new A3(this);
    private Node startNode;
    private Node endNode;

    public Route () {
    }

//    public Route getRoute (Node s,Node n){
//        return aStar.getPath ( s,n );
//    }

    public Route getRoute2 (Node s,Node n){
        return aStar2.getPath ( s,n );
    }

    public void getRoute3(Node start, Node end){
        a3.getRoute2(start, end);
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

    protected ArrayList<Node> getRouteArray () {
        return route;
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
