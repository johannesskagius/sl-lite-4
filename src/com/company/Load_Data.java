//  @author josk3261 Johannes Skagius
// Stockholms university
// Kurs: ALDA - algoritmer och datastrukturer

package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;


/**
 * This is a support class which will load all the necessary information from the provided TXT files.
 *
 */

public class Load_Data {
    private final TextFiles textFiles = new TextFiles ();
    private final Map<Long, Node> nodes = new HashMap<> ();
    private final Run run;


    public Load_Data (Run run) {
        this.run = run;
    }

    public Map<Long, Node> addNodes () {
        try {
            FileReader inFile = new FileReader ( textFiles.getNODESFILE () );
            BufferedReader in = new BufferedReader ( inFile );
            String line;
            Node sl_stop;
            int i = 0;
            while ((line = in.readLine ()) != null) {
                if (i != 0) {
                    String[] token = line.split ( "," ); //regulert uttryck betyder splitta den här raden kring kommatecken.
                    //stop_id,stop_name,stop_lat,stop_lon
                    String latitud = token[2];
                    String longitud = token[3];

                    sl_stop = new Node ( Long.parseLong ( token[0] ),token[1],new Position ( Double.parseDouble ( token[2] ),Double.parseDouble ( token[3] ) ) );
                    nodes.put ( Long.parseLong ( token[0] ),sl_stop );
                }
                i++;
            }
            closeReaders ( inFile,in );
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return nodes;
    }

    public void loadDepartures (Map<Long, ArrayList<Departures>> departures) {
        final int GOING_TO_NOD_ID = 0;
        for (Map.Entry<Long, ArrayList<Departures>> x : departures.entrySet ()) {       //O(N)
            ArrayList<Departures> xDep = x.getValue ();
            int i = 0;
            for(Departures d : xDep) {
                nodes.get ( x.getKey () ).addDepartures ( xDep.get ( i ).getGoingTo (),d );     //O(N)
                i++;
            }
        }
    }

    public Map<Long, Node> addBows () {
        Map<Long, ArrayList<Departures>> departures = new HashMap<> ();
        final int MS_TO_MIN = 60000;
        try {
            FileReader inFile = new FileReader ( textFiles.getSTOPTIMES () );
            BufferedReader in = new BufferedReader ( inFile );
            String line;
            int i = 0;
            String[] previous = new String[0];
            while ((line = in.readLine ()) != null) {
                String[] token = new String[0];
                if (i != 0) {
                    token = line.split ( "," ); //regulert uttryck betyder splitta den här raden kring kommatecken.
                }
                i++;
                if ((previous.length > 0) && previous[0].equals ( token[0] )) {
                    long d = stringToDate ( token[1] ).getTime () / MS_TO_MIN - stringToDate ( previous[2] ).getTime () / MS_TO_MIN;
                    Duration l = Duration.ofMinutes ( d );
                    Bow b = new Bow ( l,nodes.get ( Long.parseLong ( token[3] ) ) );
                    nodes.get ( Long.parseLong ( previous[3] ) ).addConnection ( b );
                    departures.computeIfAbsent ( Long.parseLong ( previous[3] ), v-> new ArrayList<> () ).add ( new Departures ( nodes.get ( Long.parseLong ( token[3] ) ) ,Long.parseLong ( token[0] ), stringToDate ( token[2] ) ) );
                }
                previous = token; //Previous 3
            }
            closeReaders ( inFile,in );
        } catch (IOException e) {
            e.printStackTrace ();
        }
        loadDepartures ( departures );
        return nodes;
    }

    private Time stringToDate (String s) {
        final String PATTERN = "HH:mm:ss";
        Date date = Calendar.getInstance ().getTime ();
        SimpleDateFormat format = new SimpleDateFormat ( PATTERN ); //RETURNERR 1 januari 1970. Därför fungerar inte treeset!!
        Time t = null;
        try {
            t = new Time ( format.parse ( s ).getTime () );
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        try {
            //date = format.parse ( s );
            format.parse ( s );
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        return t;
    }

    private void closeReaders (FileReader inFile,BufferedReader in) {
        try {
            in.close ();
            inFile.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public Map<String, Long> addNodesByName () {
        Map<String, Long> nodesByName = new HashMap<> ();
        for (Map.Entry<Long, Node> current : nodes.entrySet ()) {
            nodesByName.put ( current.getValue ().getStop_name (),current.getKey () );
        }
        return nodesByName;
    }

    public Map<Long, Trip> getTrips () {
        Map<Long, Trip> tripsInfo = new HashMap<> ();
        try {
            FileReader inFile = new FileReader ( textFiles.getTRIPSFILE ());
            BufferedReader in = new BufferedReader ( inFile );
            String line;
            int i = 0;
            String[] previous = new String[0];
            while ((line = in.readLine ()) != null) {
                String[] token;
                if (i != 0) {
                    token = line.split ( "," ); //regulert uttryck betyder splitta den här raden kring kommatecken.
                    tripsInfo.put ( Long.parseLong ( token[2] ), new Trip ( Long.parseLong ( token[0] ), Long.parseLong ( token[2] ), token[3], token[4] ));
                }
                i++;
            }
            closeReaders ( inFile,in );
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return tripsInfo;
    }

    public Map<Long, String> getRoutes () {
        Map<Long, String> routes = new HashMap<> ();
        try {
            FileReader inFile = new FileReader ( textFiles.getROUTESFILE ());
            BufferedReader in = new BufferedReader ( inFile );
            String line;
            int i = 0;
            String[] previous = new String[0];
            while ((line = in.readLine ()) != null) {
                String[] token;
                if (i != 0) {
                    token = line.split ( "," ); //regulert uttryck betyder splitta den här raden kring kommatecken.
                    String s = token[2] + ", " + token[3];
                    routes.put ( Long.parseLong ( token[0] ), s );
                }
                i++;
            }
            closeReaders ( inFile,in );
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return routes;
    }
}
