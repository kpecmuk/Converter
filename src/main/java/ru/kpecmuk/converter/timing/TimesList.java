package ru.kpecmuk.converter.timing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kpecmuk
 * @since 04.11.2017
 */

public class TimesList {
    private static final Logger log = LoggerFactory.getLogger(TimesList.class);
    private final List<Time> timeList = new ArrayList<>();

    public List<Time> getTimeList() {
        return this.timeList;
    }
}
