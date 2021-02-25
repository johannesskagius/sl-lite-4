package com.company;

import java.util.*;

public class A3 {
    private Route route;
    private List<Node> open = new ArrayList<> ();
    private Map<Double, Node> closed = new HashMap<> ();

    public A3 (Route route) {
        this.route = route;
    }

    public void getRoute (Node start,Node end) {
        start.calcHeuristicLength ( end );
        open.add ( start);
        Collections.sort ( open );
        Node current = null;
        while (open.size ()!=0){
            current = open.get ( 0 );
            if(current.equals ( end )){
                break;
            }
            //Generate each state node_successor that come after node_current
            if(current.gotChilds ()){
                Map<Bow, Long> successorMap = current.getConnectedNodes ();
                for(Map.Entry<Bow, Long> currentSuccessor : successorMap.entrySet ()){
                    Node currentsSuccessor = currentSuccessor.getKey ().getConnectedTo ();
                    currentsSuccessor.calcHeuristicLength ( end );
                   double successorCurrentCost = currentSuccessor.getKey ().getConnectedTo ().calcHeuristicLength ( end );
                   if(open.contains ( currentsSuccessor )){
                       if(currentsSuccessor.getHeuristicDistance () <= successorCurrentCost)continue;
                   }else if(closed.containsKey ( closed.containsKey ( currentsSuccessor ) )){
                       //if g(node_successor) ≤ successor_current_cost continue (to line 20)
                       if(currentsSuccessor.getHeuristicDistance () <= successorCurrentCost){
                           if(currentsSuccessor.getHeuristicDistance () <= successorCurrentCost){
                               continue;
                           }
                           closed.remove ( currentsSuccessor.getStop_id () );
                           open.add ( currentsSuccessor );
                           Collections.sort ( open );
                       }
                   }else{
                       open.add ( currentsSuccessor );
                       Collections.sort ( open );
                   }
                   current = currentsSuccessor;
                }
                closed.put ( current.getStop_id ().doubleValue (), current );
            }
        }
    }

    private Double calcHeuristicCost (Bow b, Node current,Node end) {
        double cost;
        if (b == null) {
            cost = current.calcHeuristicLength ( end );
        } else {
            cost = b.getWeight () + current.calcHeuristicLength ( end );
        }
        return cost;
    }
}
