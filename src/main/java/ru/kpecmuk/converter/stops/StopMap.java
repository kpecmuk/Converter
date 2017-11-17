package ru.kpecmuk.converter.stops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.loader.LoadStopsToStopMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Список остановок
 *
 * @author kpecmuk
 * @since 05.11.2017
 */

public class StopMap {
    private static final Logger log = LoggerFactory.getLogger(StopMap.class);

    private final Map<String, Stop> stopMap = new HashMap<>();

    public StopMap(String fileName) throws IOException {
        new LoadStopsToStopMap(fileName, this.stopMap);
    }

    public Map<String, Stop> get() {
        return this.stopMap;
    }
}
