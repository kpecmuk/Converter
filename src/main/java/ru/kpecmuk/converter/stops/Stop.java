package ru.kpecmuk.converter.stops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 05.11.2017
 */

public class Stop {
    private static final Logger log = LoggerFactory.getLogger(Stop.class);
    private int id;
    private String title;
    private float lat;
    private float lng;

    public Stop(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
