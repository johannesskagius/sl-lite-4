package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SupportClassForAddingData {
    private TextFiles textFiles = new TextFiles ();
    private Map<Long, Node> nodes = new HashMap<> ();
    private Main main;

    public SupportClassForAddingData (Main main) {
        this.main = main;
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

                    sl_stop = new Node ( Long.parseLong ( token[0] ) ,token[1],new Position ( Double.parseDouble ( token[2] ),Double.parseDouble ( token[3] ) ) );
                    nodes.put ( Long.parseLong ( token[0] ),sl_stop );
                }
                i++;
            }
            closeReaders ( inFile,in );
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return nodes;
    }

    public Map<Long, Node> addBows () {
        final int MS_TO_S = 1000;
        final int S_TO_MIN = 60;
        try {
            FileReader inFile = new FileReader ( textFiles.getSTOPTIMES () );
            BufferedReader in = new BufferedReader ( inFile );
            String line;
            int i = 0;
            String []previous = new String[0];
            while ((line = in.readLine ()) != null) {
                String[] token = new String[0];
                if (i != 0) {
                    token = line.split ( "," ); //regulert uttryck betyder splitta den här raden kring kommatecken.

                }
                i++;
                if((previous.length > 0) && previous[0].equals ( token[0] )){
                    long l = stringToDate ( token[1] ).getTime ()-stringToDate ( previous[2] ).getTime ();
                    l /= MS_TO_S;
                    l /= S_TO_MIN;
                    nodes.get ( Long.parseLong ( previous[3] ) ).addConnection ( nodes.get ( Long.parseLong ( token[3] ) ), l );
                }
                previous = token; //Previous 3
            }
            closeReaders ( inFile,in );
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return nodes;
    }

    private Date stringToDate (String s) {
        final String PATTERN = "HH:mm:ss";
        Date date = new Date ();
        SimpleDateFormat format = new SimpleDateFormat ( PATTERN );
        try {
            date = format.parse ( s );
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        return date;
    }

    private void closeReaders (FileReader inFile,BufferedReader in) {
        try {
            in.close ();
            inFile.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    private Double stringToDouble (String s) {
        return Double.parseDouble ( s );
    }

    public Map<String, Long> addNodesByName () {
        Map<String, Long> nodesByName = new HashMap<> ();
        for(Map.Entry<Long, Node> current: nodes.entrySet ()){
            nodesByName.put ( current.getValue ().getStop_name (), current.getKey () );
        }
        return nodesByName;
    }
}
