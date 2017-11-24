package ru.kpecmuk.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.actions.Process;
import ru.kpecmuk.converter.database.*;

/**
 * @author kpecmuk
 * @since 04.11.2017
 */

public class Main {
    public static final String PATH = ("src\\main\\resources\\");
    public static final String STOPS_FILE_NAME = PATH + "Stops.txt";
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // Подключаем интерфейс Action
        Process process = new Process();

        //Создаём таблицы в БД
        process.setProcess(new CreateTables());
        process.execute();

        //загружаем файл и сохраняем данные об остановках в таблицу
        process.setProcess(new SaveStopsToDB());
        process.execute();

        //сохраняем данные о днях в таблицу
        process.setProcess(new SaveDaysToDB());
        process.execute();

        // сохраняем данные о маршрутах
        process.setProcess(new SaveRoutesToDB());
        process.execute();

        //сохранение времени движения
        process.setProcess(new SaveTimeToDB());
        process.execute();
    }
}
