package ru.kpecmuk.converter.timing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Данные о времени, автобусе, остановке и днях работы
 * Иными словами... данные о том кто, где, когда и во сколько должен быть
 *
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
        return this.days;
    }
}
