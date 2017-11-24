package ru.kpecmuk.converter.stops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.actions.Process;
import ru.kpecmuk.converter.loader.LoadStopsToStopMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Список остановок, загружаем остановки из файла через LOADER
 *
 * @author kpecmuk
 * @since 05.11.2017
 */

public class StopMap {
    private static final Logger log = LoggerFactory.getLogger(StopMap.class);

    private final Map<String, Stop> stopMap = new HashMap<>();

    public StopMap(String fileName) {
        Process process = new Process();
        process.setProcess(new LoadStopsToStopMap(fileName, this.stopMap));
        process.execute();
    }

    public Map<String, Stop> get() {
        return this.stopMap;
    }
}
