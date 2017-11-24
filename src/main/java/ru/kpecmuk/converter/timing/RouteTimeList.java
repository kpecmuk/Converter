package ru.kpecmuk.converter.timing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.loader.Loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * График движения одного маршрута
 *
 * @author kpecmuk
 * @since 04.11.2017
 */

public class RouteTimeList {
    private static final Logger log = LoggerFactory.getLogger(RouteTimeList.class);

    private final List<Time> routeTimeList = new ArrayList<>();

    public RouteTimeList(String fileName) throws IOException {
        Loader loader = new Loader(fileName);
        loader.loadRoutesToTimeList(this.routeTimeList);
    }

    public List<Time> get() {
        return this.routeTimeList;
    }
}
