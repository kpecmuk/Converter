package ru.kpecmuk.converter.timing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.actions.Process;
import ru.kpecmuk.converter.loader.LoadRoutesToTimeList;

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

    private final List<Time> routeTimeDataList = new ArrayList<>();

    public RouteTimeList(String fileName) {
        Process process = new Process();
        process.setProcess(new LoadRoutesToTimeList(fileName, this.routeTimeDataList));
        process.execute();
    }

    public List<Time> get() {
        return this.routeTimeDataList;
    }
}
