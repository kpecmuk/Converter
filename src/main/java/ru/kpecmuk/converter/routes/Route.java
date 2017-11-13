package ru.kpecmuk.converter.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 13.11.2017
 */

public class Route {
    private static final Logger log = LoggerFactory.getLogger(Route.class);

    private String id;
    private String title;

    public Route(String id, String title) {
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
