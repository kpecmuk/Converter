package ru.kpecmuk.converter.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.timing.Time;
import ru.kpecmuk.converter.timing.TimesList;
import ru.kpecmuk.converter.utils.Utils;

import java.io.IOException;
import java.util.Objects;

/**
 * @author kpecmuk
 * @since 09.11.2017
 */

public class LoadRoute extends Load {
    private static final Logger log = LoggerFactory.getLogger(LoadRoute.class);

    private TimesList timesList;
    private Utils utils;

    public LoadRoute(TimesList timesList, String fileName, Utils utils) throws IOException {
        super(fileName);
        this.timesList = timesList;
        this.utils = utils;
    }

    public final void load() throws IOException {
        openFile();
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
        closeFile();
    }
}
