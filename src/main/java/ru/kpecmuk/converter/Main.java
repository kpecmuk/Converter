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
    public static final String DAYS_FILE_NAME = PATH + "Days.txt";
    public static final String ROUTES_FILE_NAME = PATH + "Routes.txt";

    public static final String ROUTES_TABLE_FILE_NAME = PATH + "Routes.sql";
    public static final String STOPS_TABLE_FILE_NAME = PATH + "Stops.sql";
    public static final String DAYS_TABLE_FILE_NAME = PATH + "Days.sql";
    public static final String SCHEDULE_TABLE_FILE_NAME = PATH + "Schedule.sql";

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        if (!new Connection().getResult()) System.exit(1);

        // Подключаем интерфейс Action
        Process process = new Process();

        //Создаём таблицы в БД
        process.setProcess(new CreateTables());
        process.execute();

        //загружаем файл и сохраняем данные об остановках в таблицу
        process.setProcess(new StopsToDB());
        process.execute();

        //сохраняем данные о днях в таблицу
        process.setProcess(new DaysToDB());
        process.execute();

        // сохраняем данные о маршрутах
        process.setProcess(new RoutesToDB());
        process.execute();

        //сохранение времени движения
        process.setProcess(new TimeToDB());
        process.execute();
    }
}
