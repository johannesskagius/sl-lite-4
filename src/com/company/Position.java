package com.company;

import java.util.Objects;

public class Position {
    //stop_id,stop_name,stop_lat,stop_lon,location_type
    private Double stop_lat;
    private Double stop_lon;

    public Position (Double stop_lat,Double stop_lon) {
        this.stop_lat = stop_lat;
        this.stop_lon = stop_lon;
    }

    public Double getStop_lat () {
        return stop_lat;
    }

    public void setStop_lat (Double stop_lat) {
        this.stop_lat = stop_lat;
    }

    public Double getStop_lon () {
        return stop_lon;
    }

    public void setStop_lon (Double stop_lon) {
        this.stop_lon = stop_lon;
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

    public double countHeuristicDistance (Position from,Position to) {
        final int SQUARED = 2;
        double xDiff = from.getStop_lat () - to.getStop_lat ();
        double yDiff = from.getStop_lon () - to.getStop_lon ();

        Double x = Math.pow ( xDiff,SQUARED );
        Double y = Math.pow ( yDiff,SQUARED );
        return Math.sqrt ( x + y );
    }
}
