package ru.kpecmuk.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.database.CreateTables;
import ru.kpecmuk.converter.database.SaveDaysToDB;
import ru.kpecmuk.converter.database.SaveRoutesToDB;
import ru.kpecmuk.converter.database.SaveStopsToDB;
import ru.kpecmuk.converter.stops.StopMap;
import ru.kpecmuk.converter.timing.AllRoutesTimeList;
import ru.kpecmuk.converter.timing.RouteTimeList;
import ru.kpecmuk.converter.utils.Utils;

import java.io.IOException;

/**
 * @author kpecmuk
 * @since 04.11.2017
 */

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final String PATH = ("src\\main\\resources\\");

    public static void main(String[] args) throws IOException {
        Utils utils = new Utils();

        AllRoutesTimeList allRoutesTimeList = new AllRoutesTimeList();
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route42work.txt", utils));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route42holy.txt", utils));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route06work.txt", utils));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route06holy.txt", utils));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route01work.txt", utils));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route01holy.txt", utils));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route08work.txt", utils));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route08holy.txt", utils));

        System.err.close();
        System.setErr(System.out);

        //Создаём таблицы в БД
        new CreateTables("jdbc:postgresql://localhost:5432/transport", "user", "user");

        //загружаем файл и сохраняем данные об остановках в таблицу
        new SaveStopsToDB("jdbc:postgresql://localhost:5432/transport", "user", "user",
                new StopMap(PATH + "Stops.txt"));

        //сохраняем данные о днях в таблицу
        new SaveDaysToDB("jdbc:postgresql://localhost:5432/transport", "user", "user");

        // сохраняем данные о маршрутах
        new SaveRoutesToDB("jdbc:postgresql://localhost:5432/transport", "user", "user");

        //сохранение времени движения
        allRoutesTimeList.saveToDB();
    }
}
