package com.company;



import java.sql.Time;
import java.time.Duration;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Node implements Comparable<Node> {
    private Long stop_id;
    private String stop_name;
    private Position position;
    private boolean isVisited;
    private Duration heuristicDistance;
    private Duration cost;
    private double costInclHeu;
    private Map<Bow, Duration> connectedNodes = new HashMap<> ();
    private Map<Node, Bow> connectedNodeToBow = new HashMap<> ();

    public Node (Long stop_id,String stop_name,Position position) {
        this.stop_id = stop_id;
        this.stop_name = stop_name;
        this.position = position;
    }

    public void addConnection(Node in, Duration weight){
        Bow b = new Bow (weight, in);
        cost=weight;
        connectedNodes.put ( b, weight );
    }

    public Bow getBow(Node b){
        return connectedNodeToBow.get ( b );
    }

    public boolean isVisited () {
        return isVisited;
    }

    public Duration getCost(Bow b){
        return connectedNodes.get ( b );
    }

    public boolean gotChilds(){
        return connectedNodes.size () != 0;
    }

    public Long getStop_id () {
        return stop_id;
    }

    public void setStop_id (Long stop_id) {
        this.stop_id = stop_id;
    }

    public String getStop_name () {
        return stop_name;
    }

    public void setStop_name (String stop_name) {
        this.stop_name = stop_name;
    }

    public Position getPosition () {
        return position;
    }

    public void setPosition (Position position) {
        this.position = position;
    }

    public Duration getHeuristicDistance () {
        return heuristicDistance;
    }

    public void setHeuristicDistance (Duration n) {
        this.heuristicDistance = n;//position.countHeuristicDistance ( this.position, n.position );
    }

    public Duration getCost () {
        return cost;
    }

    public void setCost (Duration cost) {
        this.cost = cost;
    }

    public Map<Bow, Duration> getConnectedNodes () {
        return connectedNodes;
    }

    public void setConnectedNodes (Map<Bow, Duration> connectedNodes) {
        this.connectedNodes = connectedNodes;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return stop_id.equals ( node.stop_id );
    }

    @Override
    public int hashCode () {
        return Objects.hashCode ( stop_id );
    }

    public void setVisited (boolean b) {
        isVisited = b;
    }
    public Duration calcHeuristicLength (Node n) {
        heuristicDistance =position.countHeuristicDistance ( this.position, n.position );
        return heuristicDistance;
    }

    @Override
    public String toString () {
        return stop_name ;
    }

    @Override
    public int compareTo (Node o) { // Returnerar negativt om this.heuristic är större än den som kommer in.
        return  o.cost.compareTo ( this.cost ) <= 0 ? 1:-1;
    }
}
