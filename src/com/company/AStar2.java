package com.company;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class AStar2 {
    private Route route;
    private Stack<Node> visitedNodes = new Stack<> ();
    private Stack<Duration> closestYet = new Stack<> ();
    private Map<Node, Duration> goToNodeForCost = new HashMap<> ();

    public AStar2 (Route route) {
        this.route = route;
    }

    public Route getPath (Node start,Node end) {
        Bow b = null;
        Node current = start;
        goToNodeForCost.put ( current,calcHeuristicCost ( null,current,end ) );
        visitedNodes.push ( current );
        route.addNode ( current );
        closestYet.push ( calcHeuristicCost ( null,start,end ) );
        while (!checkIfFinish ( current,end )) {
            Node cheapest = null;
            Duration currentCheapest = calcHeuristicCost ( null,current,end );
            if (current.gotChilds ()) {
                Map<Bow, Duration> childs = current.getConnectedNodes ();
                //Go through all nodes and add them to the register
                for (Map.Entry<Bow, Duration> currentBow : childs.entrySet ()) {
                    //Get the node at the current iteration
                    Node compareNode = currentBow.getKey ().getConnectedTo ();
                    //Calculate the cost to move there incl the heuristic cost
                    Duration cost = calcHeuristicCost ( currentBow.getKey (),compareNode,end );
                    //If the cost is less than the currentchepast for this iteration and less then the closest yet continue on the node
                    if (cost.compareTo ( currentCheapest ) < 0  /*|| cost < closestYet.peek ()*/) {
                        if(!compareNode.isVisited ()) {
                            cheapest = compareNode;
                        }
                    }
                    if(!goToNodeForCost.containsKey ( compareNode ))
                        goToNodeForCost.put ( compareNode,cost );
                    else
                        if( cost.compareTo (  goToNodeForCost.get ( compareNode ) ) <0)
                            goToNodeForCost.put ( compareNode, cost );
                }
                if (cheapest != null) {
                    closestYet.push ( calcHeuristicCost ( b,current,end ) );
                    visitedNodes.push ( cheapest );
                    cheapest.setVisited ( true );
                    current = cheapest;
                }
                else {
                    //Get an earlier node from the stack
                    visitedNodes.pop ();
                    current = visitedNodes.peek ();

                }
            }
        }
        return route;
    }

    private Duration calcHeuristicCost (Bow b,Node current,Node end) {
        Duration cost;
        if (b == null) {
            cost = current.calcHeuristicLength ( end );
        } else {
            cost = b.getCost ().plus (  current.calcHeuristicLength ( end ) ) ;
        }
        return cost;
    }

    private boolean checkIfFinish (Node current,Node end) {
        return current.equals ( end );
    }
}
