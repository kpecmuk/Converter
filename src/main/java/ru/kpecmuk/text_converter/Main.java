package ru.kpecmuk.text_converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author kpecmuk
 * @since 04.11.2017
 */

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final String PATH = ("E:\\Coding\\Java_prog\\Converter\\src\\main\\java\\ru\\kpecmuk\\text_converter\\");

    public static void main(String[] args) throws IOException {

        Times busTimes = new Times();
        BufferedReader fin = new BufferedReader(new FileReader(new File(PATH + "TextFile.txt")));
        String line;

        while ((line = fin.readLine()) != null) {
            line = lineFilter(line);

            int hour = convertToInt(line, 0);

            for (int i = 2; i < line.length() - 1; i = i + 2) {
                int minute = convertToInt(line, i);
                busTimes.getTimeList().add(new Time(hour, minute, 1));
            }
        }

        busTimes.getTimeList().forEach(time -> System.out.println(time.print()));

    }

    private static int convertToInt(String line, int i) {
        int result = Character.getNumericValue(line.charAt(i));
        result = result * 10 + Character.getNumericValue(line.charAt(i + 1));
        return result;
    }

    private static String lineFilter(String line) {
        String result = line.replaceAll("\\D+", "");
        if (result.length() % 2 == 1) {
            result = "0" + result;
        }
        return result;
    }
}
