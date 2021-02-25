package com.company;

public class TextFiles {
    private final String ROUTESFILE = "/Users/johannesskagius/Desktop/Skola/SL-Metro-4/src/com/sl_gtfs_data/sl_routes.txt";
    private final String STOPTIMES = "/Users/johannesskagius/Desktop/Skola/SL-Metro-4/src/com/sl_gtfs_data/sl_stop_times.txt";
    private final String NODESFILE = "/Users/johannesskagius/Desktop/Skola/SL-Metro-4/src/com/sl_gtfs_data/sl_stops.txt";
    private final String TRIPSFILE = "/Users/johannesskagius/Desktop/Skola/SL-Metro-4/src/com/sl_gtfs_data/sl_trips.txt";

    public String getROUTESFILE () {
        return ROUTESFILE;
    }

    public String getSTOPTIMES () {
        return STOPTIMES;
    }

    public String getNODESFILE () {
        return NODESFILE;
    }

    public String getTRIPSFILE () {
        return TRIPSFILE;
    }
}
