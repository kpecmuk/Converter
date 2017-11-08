package ru.kpecmuk.converter.stops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 05.11.2017
 */

public class Stop {
    private static final Logger log = LoggerFactory.getLogger(Stop.class);
    private String id;
    private String title;
    private float lat;
    private float lng;

    public Stop(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}
