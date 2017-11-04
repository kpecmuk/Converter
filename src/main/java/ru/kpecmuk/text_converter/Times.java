package ru.kpecmuk.text_converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kpecmuk
 * @since 04.11.2017
 */

public class Times {
    private static final Logger log = LoggerFactory.getLogger(Times.class);

    private List<Time> timeList;

    public Times() {
        this.timeList = new ArrayList<>();
    }

    public List<Time> getTimeList() {
        return timeList;
    }
}
