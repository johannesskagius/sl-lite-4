package com.company;



import java.sql.Time;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

public class Node implements Comparable<Node> {
    private Long stop_id;
    private String stop_name;
    private Position position;
    private Duration heuristicDistance;
    private Duration cost;
    private Map<Bow, Duration> connectedNodes = new HashMap<> ();
    private Map<Node, TreeSet<Departures>> departures = new HashMap<> ();


    /**
     *
     * @param stop_id
     * @param stop_name
     * @param position
     */
    public Node (Long stop_id,String stop_name,Position position) {
        this.stop_id = stop_id;
        this.stop_name = stop_name;
        this.position = position;
    }

    /**
     *
     * @param n
     * @param d
     */
    public void addDepartures(Node n, Departures d){
        departures.computeIfAbsent (n, v-> new TreeSet<> () {
        }).add ( d );
    }

    /**
     *
     * @param b
     */
    public void addConnection(Bow b){
        this.cost = b.getCost ();
        connectedNodes.put ( b, b.getCost () );
    }

    /**
     *
     * @param goingTo
     * @return
     */
    public Departures getDeparture(Node goingTo){
        return departures.get ( goingTo ).first ();
    }

    public Time getNextDepartureTime(Departures d){
        Departures x = departures.get ( d.getGoingTo () ).higher ( d );
        return departures.get ( d.getGoingTo () ).higher ( d ).getDeparture_time ();
    }

    /**
     *
     * @param b
     * @return
     */
    public Duration getCost(Bow b){
        return connectedNodes.get ( b );
    }

    /**
     *
     * @return
     */
    public boolean gotChilds(){
        return connectedNodes.size () != 0;
    }

    /**
     *
     * @return
     */
    public Long getStop_id () {
        return stop_id;
    }

    /**
     *
     * @param stop_id
     */
    public void setStop_id (Long stop_id) {
        this.stop_id = stop_id;
    }

    /**
     *
     * @return
     */
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
