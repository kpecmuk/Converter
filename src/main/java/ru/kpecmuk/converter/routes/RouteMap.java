package ru.kpecmuk.converter.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kpecmuk
 * @since 13.11.2017
 */

public class RouteMap {
    private static final Logger log = LoggerFactory.getLogger(RouteMap.class);

    private final Map<String, Route> routeMap = new HashMap<>();

    public final Map<String, Route> getRouteMap() {
        return this.routeMap;
    }
}
