package ru.kpecmuk.converter.stops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Место остановки автобуса
 * ID идентификатор
 * TITLE название
 *
 * @author kpecmuk
 * @since 05.11.2017
 */

public class Stop {
    private static final Logger log = LoggerFactory.getLogger(Stop.class);
    private String id;
    private String title;

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
