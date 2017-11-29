package ru.kpecmuk.converter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 08.11.2017
 */

public class Utils {
    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    /**
     * @param line строка на входе типа 6112438
     * @param i    порядковый индекс как в массиве
     * @return getResult типа int, составленный из i и i+1
     */
    public int convertToInt(String line, int i) {
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
    public String lineFilter(String line) {
        String result = line.replaceAll("\\D+", "");
        if (result.length() % 2 == 1) {
            result = "0" + result;
        }
        return result;
    }
}
