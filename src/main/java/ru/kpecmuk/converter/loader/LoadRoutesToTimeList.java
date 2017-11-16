package ru.kpecmuk.converter.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.timing.CommonTimesList;
import ru.kpecmuk.converter.timing.Time;
import ru.kpecmuk.converter.utils.Utils;

import java.io.IOException;
import java.util.Objects;

/**
 * @author kpecmuk
 * @since 09.11.2017
 */

public class LoadRoutesToTimeList extends Load {
    private static final Logger log = LoggerFactory.getLogger(LoadRoutesToTimeList.class);

    private final CommonTimesList commonTimesList;

    public LoadRoutesToTimeList(CommonTimesList commonTimesList, String fileName, Utils utils) throws IOException {
        super(fileName);
        this.commonTimesList = commonTimesList;


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
                this.commonTimesList.getTimeList().add(new Time(hour, minute, busNumber, busStopId, days));
            }
        }
        closeFile();
    }

//    public final void load() throws IOException {
//        openFile();
//        String line, days = "1234567", busNumber = null, busStopId = null;
//
//        while ((line = fin.readLine()) != null) {
//            if (Objects.equals(line, "days")) {
//                days = fin.readLine();
//                continue;
//            }
//            if (Objects.equals(line, "bus")) {
//                busNumber = fin.readLine();
//                continue;
//            }
//            if (Objects.equals(line, "stop")) {
//                busStopId = fin.readLine();
//                continue;
//            }
//            line = utils.lineFilter(line);
//
//            int hour = utils.convertToInt(line, 0);
//
//            for (int i = 2; i < line.length() - 1; i = i + 2) {
//                int minute = utils.convertToInt(line, i);
//                commonTimesList.getTimeList().add(new Time(hour, minute, busNumber, busStopId, days));
//            }
//        }
//        closeFile();
//    }
}
