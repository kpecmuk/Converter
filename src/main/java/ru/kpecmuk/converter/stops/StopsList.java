package ru.kpecmuk.converter.stops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kpecmuk
 * @since 05.11.2017
 */

public class StopsList {
    private static final Logger log = LoggerFactory.getLogger(StopsList.class);

    private final List<Stop> stopsList = new ArrayList<>();

    public List<Stop> getStopsList() {
        return stopsList;
    }

}
