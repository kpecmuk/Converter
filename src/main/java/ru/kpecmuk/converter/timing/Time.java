package ru.kpecmuk.converter.timing;

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
    private String busStopID;
    private String busNumber;
    private String days;

    public Time(int hour, int minute, String busNumber, String busStopID, String days) {
        this.hour = hour;
        this.minute = minute;
        this.busNumber = busNumber;
        this.busStopID = busStopID;
        this.days = days;
    }

    /**
     * @return время в формате 00:00
     */
    public String getTime() {
        String result = "";

        if (hour < 10)
            result = "0";
        result += hour + ":";
        if (minute < 10)
            result += "0";
        result += minute;

        return result;
    }

    public String getBusNumber() {
        return this.busNumber;
    }

    public String getBusStopID() {
        return this.busStopID;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public String getDays() {
        return days;
    }
}
