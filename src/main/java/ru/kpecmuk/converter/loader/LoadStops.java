package ru.kpecmuk.converter.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.stops.Stop;
import ru.kpecmuk.converter.stops.StopMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author kpecmuk
 * @since 09.11.2017
 */

public class LoadStops {
    private static final Logger log = LoggerFactory.getLogger(LoadStops.class);
    private String fileName;
    private StopMap stopMap;

    public LoadStops(String fileName, StopMap stopMap) {
        this.fileName = fileName;
        this.stopMap = stopMap;
    }

    public final void load() throws IOException {
        BufferedReader fin = new BufferedReader(new FileReader(new File(fileName)));
        String title, id;

        while ((title = fin.readLine()) != null) {
            id = fin.readLine();
            stopMap.getStopMap().put(id, new Stop(id, title));
        }
        fin.close();
    }
}
