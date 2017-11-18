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
    public static final String PATH = ("src\\main\\resources\\");
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static String URL = "jdbc:postgresql://localhost:5432/transport";
    public static String USER = "user";
    public static String PASSWORD = "user";

    public static void main(String[] args) throws IOException {

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
