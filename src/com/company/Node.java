//  @author josk3261 Johannes Skagius
// Stockholms university
// Kurs: ALDA - algoritmer och datastrukturer

package com.company;


import java.sql.Time;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class Node implements Comparable<Node> {
    private final Long stop_id;
    private final String stop_name;
    private final Position position;
    private final Map<Bow, Duration> connectedNodes = new HashMap<> ();
    private final Map<Node, TreeSet<Departures>> departures = new HashMap<> ();
    private Duration cost;

    public Node (Long stop_id,String stop_name,Position position) {
        this.stop_id = stop_id;
        this.stop_name = stop_name;
        this.position = position;
    }

    public void addDepartures (Node n,Departures d) {
        departures.computeIfAbsent ( n,v -> new TreeSet<> () {
        } ).add ( d );
    }

    public void addConnection (Bow b) {
        this.cost = b.getCost ();
        connectedNodes.put ( b,b.getCost () );
    }

    public Departures getDeparture (Node goingTo) {
        return departures.get ( goingTo ).first ();
    }

    /**
     * Returnes the time for a specific departure
     *
     * @param d is a departure to node. This is used to find a departu
     * @return the next departure based on after the System.Time time.
     * @throws NullPointerException if there is no later departure. In that case send the first departure next day
     */
    public Time getNextDepartureTime (Departures d) {
        Time t;
        try {
            t = departures.get ( d.getGoingTo () ).higher ( d ).getDeparture_time ();
        } catch (NullPointerException e) {
            t = departures.get ( d.getGoingTo () ).first ().getDeparture_time ();
        }
        return t;
    }

    public String getStop_name () {
        return stop_name;
    }

    public Map<Bow, Duration> getConnectedNodes () {
        return connectedNodes;
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
        return position.countHeuristicDistance ( this.position,n.position );
    }

    @Override
    public String toString () {
        return stop_name;
    }

    @Override
    public int compareTo (Node o) { // Returnerar negativt om this.heuristic är större än den som kommer in.
        return o.cost.compareTo ( this.cost ) <= 0 ? 1 : -1;
    }
}
