package ru.kpecmuk.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 04.11.2017
 */

public class Time {
    private static final Logger log = LoggerFactory.getLogger(Time.class);
    private int hour;
    private int minute;
    private int busStopID;
    private int busNumber;

    Time(int hour, int minute, int busNumber, int busStopID) {
        this.hour = hour;
        this.minute = minute;
        this.busNumber = busNumber;
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

    public int getBusNumber() {
        return this.busNumber;
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
