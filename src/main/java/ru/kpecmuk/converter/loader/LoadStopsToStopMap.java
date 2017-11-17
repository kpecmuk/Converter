package ru.kpecmuk.converter.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.stops.Stop;

import java.io.IOException;
import java.util.Map;

/**
 * @author kpecmuk
 * @since 09.11.2017
 */

public class LoadStopsToStopMap extends Load {
    private static final Logger log = LoggerFactory.getLogger(LoadStopsToStopMap.class);

    public LoadStopsToStopMap(String fileName, Map<String, Stop> stopMap) throws IOException {
        super(fileName);

        openFile();
        String title, id;

        while ((title = fin.readLine()) != null) {
            id = fin.readLine();
            stopMap.put(id, new Stop(id, title));
        }
        closeFile();
    }
}
