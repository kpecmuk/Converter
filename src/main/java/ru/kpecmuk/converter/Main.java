package ru.kpecmuk.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.stops.Stop;
import ru.kpecmuk.converter.stops.StopsList;
import ru.kpecmuk.converter.timing.Time;
import ru.kpecmuk.converter.timing.TimesList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * @author kpecmuk
 * @since 04.11.2017
 */

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final String PATH = ("src\\main\\java\\ru\\kpecmuk\\converter\\routes\\");
    private static String busStopId;
    private static String busNumber;

    public static void main(String[] args) throws IOException {

        TimesList timing42 = new TimesList();


        BufferedReader fin = new BufferedReader(new FileReader(new File(PATH + "Route42work.txt")));
        String line;

        while ((line = fin.readLine()) != null) {
            if (Objects.equals(line, "bus")) {
                busNumber = fin.readLine();
                line = fin.readLine();
            }
            if (Objects.equals(line, "stop")) {
                busStopId = fin.readLine();
                line = fin.readLine();
            }
            line = lineFilter(line);

            //Сразу забираем часы, так как они идут первыми символами в строке
            int hour = convertToInt(line, 0);

            //Теперь забираем минуты и закидываем всё в список TimeList
            for (int i = 2; i < line.length() - 1; i = i + 2) {
                int minute = convertToInt(line, i);
                timing42.getTimeList().add(new Time(hour, minute, busNumber, busStopId));
            }
        }
        fin.close();

        fin = new BufferedReader(new FileReader(new File(PATH + "Stops.txt")));

        String title, id;
        StopsList stopsList = new StopsList();

        while ((title = fin.readLine()) != null) {
            id = fin.readLine();
            stopsList.getStopsList().add(new Stop(id, title));
        }
        fin.close();

        timing42.getTimeList().forEach(time -> {
            String t = time.getTime();
            String stopTitle = null;

            for (Stop stop : stopsList.getStopsList()) {
                if (Objects.equals(time.getBusStopID(), stop.getId())) {
                    stopTitle = stop.getTitle();
                    break;
                }
            }

            System.out.println(time.getTime() + " - " + stopTitle);
        });
    }

    /**
     * @param line строка на входе типа 6112438
     * @param i    порядковый индекс как в массиве
     * @return result типа int, составленный из i и i+1
     */
    private static int convertToInt(String line, int i) {
        int result = Character.getNumericValue(line.charAt(i));
        result = result * 10 + Character.getNumericValue(line.charAt(i + 1));
        return result;
    }

    /**
     * Фильтруем строку, выкидываем всё кроме цифр
     *
     * @param line строка вида 6	112438
     * @return строку вида 6112438
     */
    private static String lineFilter(String line) {
        String result = line.replaceAll("\\D+", "");
        if (result.length() % 2 == 1) {
            result = "0" + result;
        }
        return result;
    }
}
