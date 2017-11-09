package ru.kpecmuk.converter.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.timing.Time;
import ru.kpecmuk.converter.timing.TimesList;
import ru.kpecmuk.converter.utils.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * @author kpecmuk
 * @since 09.11.2017
 */

public class LoadRoute {
    private static final Logger log = LoggerFactory.getLogger(LoadRoute.class);

    private TimesList timesList;
    private String fileName;
    private Utils utils;

    public LoadRoute(TimesList timesList, String fileName, Utils utils) throws IOException {
        this.timesList = timesList;
        this.fileName = fileName;
        this.utils = utils;
    }

    public final void load() throws IOException {

        BufferedReader fin = new BufferedReader(new FileReader(new File(fileName)));
        String line, busNumber = null, busStopId = null;

        while ((line = fin.readLine()) != null) {
            if (Objects.equals(line, "bus")) {
                busNumber = fin.readLine();
                line = fin.readLine();
            }
            if (Objects.equals(line, "stop")) {
                busStopId = fin.readLine();
                line = fin.readLine();
            }
            line = utils.lineFilter(line);

            int hour = utils.convertToInt(line, 0);

            for (int i = 2; i < line.length() - 1; i = i + 2) {
                int minute = utils.convertToInt(line, i);
                timesList.getTimeList().add(new Time(hour, minute, busNumber, busStopId));
            }
        }
        fin.close();
    }
}
