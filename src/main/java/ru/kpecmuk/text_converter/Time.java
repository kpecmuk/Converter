package ru.kpecmuk.text_converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 04.11.2017
 */

public class Time {
    private static final Logger log = LoggerFactory.getLogger(Time.class);
    private Integer hour;
    private Integer minute;
    private int busStopID;

    Time(Integer hour, Integer minute, int busStopID) {
        this.hour = hour;
        this.minute = minute;
        this.busStopID = busStopID;
    }

    /**
     * @return время в формате 00:00
     */
    String getTime() {
        String result = "";

        if (hour < 10)
            result = "0";
        result += hour + ":";
        if (minute < 10)
            result += "0";
        result += minute;

        return result;
    }

    public int getBusStopID() {
        return this.busStopID;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }
}
