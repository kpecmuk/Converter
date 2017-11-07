package ru.kpecmuk.converter.stops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kpecmuk
 * @since 05.11.2017
 */

public class StopsList {
    private static final Logger log = LoggerFactory.getLogger(StopsList.class);

    private final Map<String, Stop> stopsList = new HashMap<>();

    public Map<String, Stop> getStopsList() {
        return this.stopsList;
    }
}
