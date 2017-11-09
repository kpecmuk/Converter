package ru.kpecmuk.converter.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.stops.Stop;
import ru.kpecmuk.converter.stops.StopMap;

import java.io.IOException;

/**
 * @author kpecmuk
 * @since 09.11.2017
 */

public class LoadStops extends Load {
    private static final Logger log = LoggerFactory.getLogger(LoadStops.class);
    private StopMap stopMap;

    public LoadStops(String fileName, StopMap stopMap) {
        super(fileName);
        this.stopMap = stopMap;
    }

    public final void load() throws IOException {
        openFile();
        String title, id;

        while ((title = fin.readLine()) != null) {
            id = fin.readLine();
            stopMap.getStopMap().put(id, new Stop(id, title));
        }
        closeFile();
    }
}
