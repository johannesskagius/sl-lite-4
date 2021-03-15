//  @author josk3261 Johannes Skagius
// Stockholms university
// Kurs: ALDA - algoritmer och datastrukturer

package com.company;

import java.time.Duration;
import java.util.Objects;

public class Position {
    private final Double stop_lat;
    private final Double stop_lon;

    public Position (Double stop_lat,Double stop_lon) {
        this.stop_lat = stop_lat;
        this.stop_lon = stop_lon;
    }

    public Double getStop_lat () {
        return stop_lat;
    }

    public Double getStop_lon () {
        return stop_lon;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return stop_lat.equals ( position.stop_lat ) && stop_lon.equals ( position.stop_lon );
    }

    //TODO fix hashcode
    @Override
    public int hashCode () {
        return Objects.hash ( stop_lat,stop_lon );
    }

    @Override
    public String toString () {
        return stop_lat + ", " + stop_lon;
    }

    /**
     * This method calculate sthe heuristic distance between two nodes.
     *
     *
     * @param from the first node
     * @param to the second node
     * @return the Duration in minutes.
     */
    public Duration countHeuristicDistance (Position from,Position to) {
        final int SQUARED = 2;
        double xDiff = from.getStop_lat () - to.getStop_lat ();
        double yDiff = from.getStop_lon () - to.getStop_lon ();
        double x = Math.pow ( xDiff,SQUARED );
        double y = Math.pow ( yDiff,SQUARED );
        double d = Math.sqrt ( x + y );
        return Duration.ofMinutes ( (long)d );
    }
}
