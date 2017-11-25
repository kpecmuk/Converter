package ru.kpecmuk.converter.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.actions.Action;
import ru.kpecmuk.converter.data_maps.Time_data;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Обработка days, bus, stop ключей
 *
 * @author kpecmuk
 * @since 24.11.2017
 */

public class LoadRoutesToTimeList extends LoadFile implements Action {
    private static final Logger log = LoggerFactory.getLogger(LoadRoutesToTimeList.class);

    private List<Time_data> routeTimeDataList;

    public LoadRoutesToTimeList(String fileName, List<Time_data> routeTimeDataList) {
        super(fileName);
        this.routeTimeDataList = routeTimeDataList;
    }

    /**
     * Обработка файлов с маршрутами.
     * Открываем файл, получаем данные, сохраняем, закрываем файл.
     */
    @Override
    public final void execute() {

        openFile();
        String line, days = "1234567", busNumber = null, busStopId = null;

        try {
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
                    routeTimeDataList.add(new Time_data(hour, minute, busNumber, busStopId, days));
                }
            }
        } catch (IOException e) {
            log.error(String.valueOf(e));
            System.exit(1);
        }
        closeFile();
    }
}
