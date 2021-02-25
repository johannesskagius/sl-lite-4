package com.company;

import java.util.Date;

public class Weight {
    //Tiden mellan två stationer ex
    //trip_id         ,arrival_time     ,departure_time,stop_id,stop_sequence,pickup_type,drop_off_type
    //22750069773107    ,8:33:00        ,8:33:00        ,740004383          ,1              ,0      ,1
    //22750069773107    ,8:34:00        ,8:34:00        ,740035997,2,0,0

    //Ovan skulle vikten vara 8:33:00-8:34:00

    private long weight;

    public Weight (Date departureFromTime,Date arrivalToNext) {
        long d  = departureFromTime.getTime () - arrivalToNext.getTime ();
        this.weight = d;
    }

    public long getWeight () {
        return weight;
    }
}
