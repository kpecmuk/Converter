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
    private BufferedReader fin;
    private String fileName;
    private Utils utils;

    public Loader(String fileName) {
        this.fileName = fileName;
        this.utils = new Utils();
    }

    /**
     * Открытие файла
     */
    private void openFile() {
        try {
            this.fin = new BufferedReader(new FileReader(new File(fileName)));
        } catch (FileNotFoundException e) {
            log.error(String.valueOf(e));
            System.exit(1);
        }
    }

    /**
     * Закрытие файла
     */
    private void closeFile() {
        try {
            this.fin.close();
        } catch (IOException e) {
            log.error(String.valueOf(e));
            System.exit(1);
        }
    }

    /**
     * Обработка файла с остановками.
     * Открываем файл, обрабатываем данные, закрываем файл.
     *
     * @param stopMap список всех остановочных пунктов
     */
    public void LoadStopsToStopMap(Map<String, Stop> stopMap) {

        openFile();
        String title, id;

        try {
            while ((title = fin.readLine()) != null) {
                id = fin.readLine();
                stopMap.put(id, new Stop(id, title));
            }
        } catch (IOException e) {
            log.error(String.valueOf(e));
            System.exit(1);
        }
        closeFile();
    }

    /**
     * Обработка файлов с маршрутами.
     * Открываем файл, получаем данные, сохраняем, закрываем файл.
     *
     * @param routeTimeList сюда сохраняются полученные данные
     */
    public void LoadRoutesToTimeList(List<Time> routeTimeList) {

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
                    routeTimeList.add(new Time(hour, minute, busNumber, busStopId, days));
                }
            }
        } catch (IOException e) {
            log.error(String.valueOf(e));
            System.exit(1);
        }
        closeFile();
    }

}
