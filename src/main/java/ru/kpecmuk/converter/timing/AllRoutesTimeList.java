package ru.kpecmuk.converter.timing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kpecmuk
 * @since 17.11.2017
 */

public class AllRoutesTimeList {
    private static final Logger log = LoggerFactory.getLogger(AllRoutesTimeList.class);

    private final List<RouteTimeList> allRoutesTimeList = new ArrayList<>();

    public List<RouteTimeList> get() {
        return this.allRoutesTimeList;
    }
}
