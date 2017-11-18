package ru.kpecmuk.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.database.*;

import java.io.IOException;

/**
 * @author kpecmuk
 * @since 04.11.2017
 */

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static final String PATH = ("src\\main\\resources\\");
    public static final String STOPS_FILE_NAME = PATH + "Stops.txt";

    public static void main(String[] args) throws IOException {
        // Подключаю интерфейс Action
        Database db = new Database();

        //Создаём таблицы в БД
        db.action(new CreateTables());

        //загружаем файл и сохраняем данные об остановках в таблицу
        db.action(new SaveStopsToDB());

        //сохраняем данные о днях в таблицу
        db.action(new SaveDaysToDB());

        // сохраняем данные о маршрутах
        db.action(new SaveRoutesToDB());

        //сохранение времени движения
        db.action(new SaveTimeToDB());
    }
}
