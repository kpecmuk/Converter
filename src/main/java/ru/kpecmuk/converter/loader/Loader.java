package ru.kpecmuk.converter.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.stops.Stop;
import ru.kpecmuk.converter.timing.Time;
import ru.kpecmuk.converter.utils.Utils;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author kpecmuk
 * @since 09.11.2017
 */

public class Loader {
    private static final Logger log = LoggerFactory.getLogger(Loader.class);
    BufferedReader fin;
    private String fileName;
    private Utils utils;

    public Loader(String fileName) {
        this.fileName = fileName;
        this.utils = new Utils();
    }

    private void openFile() throws FileNotFoundException {
        this.fin = new BufferedReader(new FileReader(new File(fileName)));
    }

    private void closeFile() throws IOException {
        this.fin.close();
    }

    public void LoaderStopsToStopMap(Map<String, Stop> stopMap) throws IOException {

        openFile();
        String title, id;

        while ((title = fin.readLine()) != null) {
            id = fin.readLine();
            stopMap.put(id, new Stop(id, title));
        }
        closeFile();
    }

    public void LoaderRoutesToTimeList(List<Time> routeTimeList) throws IOException {

        openFile();
        String line, days = "1234567", busNumber = null, busStopId = null;

        while ((line = fin.readLine()) != null) {
            if (Objects.equals(line, "days")) {
                days = fin.readLine();
                continue;
            }
            if (Objects.equals(line, "bus")) {
                busNumber = fin.readLine();
                continue;
            }
            if (Objects.equals(line, "stop")) {
                busStopId = fin.readLine();
                continue;
            }
            line = utils.lineFilter(line);

            int hour = utils.convertToInt(line, 0);

            for (int i = 2; i < line.length() - 1; i = i + 2) {
                int minute = utils.convertToInt(line, i);
                routeTimeList.add(new Time(hour, minute, busNumber, busStopId, days));
            }
        }
        closeFile();
    }

}
