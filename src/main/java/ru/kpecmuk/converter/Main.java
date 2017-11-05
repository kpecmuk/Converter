package ru.kpecmuk.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final String PATH = ("src\\main\\java\\ru\\kpecmuk\\text_converter\\");
    private static int busStopId = 0;
    private static int busNumber = 42;

    public static void main(String[] args) throws IOException {

        Times busTimes = new Times();
        BufferedReader fin = new BufferedReader(new FileReader(new File(PATH + "Route42.txt")));
        String line;

        while ((line = fin.readLine()) != null) {
            if (Objects.equals(line, "bus")) {
                line = fin.readLine();
                busNumber = convertToInt(lineFilter(line), 0);
                line = fin.readLine();
            }
            if (Objects.equals(line, "stop")) {
                line = fin.readLine();
                busStopId = convertToInt(lineFilter(line), 0);
                line = fin.readLine();
            }
            line = lineFilter(line);

            //Сразу забираем часы, так как они идут первыми символами в строке
            int hour = convertToInt(line, 0);

            //Теперь забираем минуты и закидываем всё в список TimeList
            for (int i = 2; i < line.length() - 1; i = i + 2) {
                int minute = convertToInt(line, i);
                busTimes.getTimeList().add(new Time(hour, minute, busNumber, busStopId));
            }
        }
        busTimes.getTimeList().forEach(time -> System.out.println(time.getTime()));
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
     * Фильтруем строку, выкидываем всё вроме цифр
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
