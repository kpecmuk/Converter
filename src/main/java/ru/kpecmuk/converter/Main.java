package ru.kpecmuk.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.database.CreateTables;
import ru.kpecmuk.converter.database.SaveDaysToDB;
import ru.kpecmuk.converter.database.SaveStopsToDB;
import ru.kpecmuk.converter.database.SaveTimeToDB;
import ru.kpecmuk.converter.loader.LoadStops;
import ru.kpecmuk.converter.loader.LoadTime;
import ru.kpecmuk.converter.stops.StopMap;
import ru.kpecmuk.converter.timing.TimesList;
import ru.kpecmuk.converter.utils.Utils;

import java.io.IOException;

/**
 * @author kpecmuk
 * @since 04.11.2017
 */

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final String PATH = ("src\\main\\java\\ru\\kpecmuk\\converter\\routes\\");

    public static void main(String[] args) throws IOException {
        Utils utils = new Utils();
        TimesList timing42 = new TimesList();
        LoadTime route42 = new LoadTime(timing42, PATH + "Route42work.txt", utils);
        route42.load();

        StopMap stopMap = new StopMap();
        LoadStops stops = new LoadStops(PATH + "Stops.txt", stopMap);
        stops.load();


        timing42.getTimeList().forEach(time ->
                System.out.println(time.getTime()
                        + " - " + stopMap.getStopMap().get(time.getBusStopID()).getTitle()));

        System.err.close();
        System.setErr(System.out);

        //Создаём таблицы
        CreateTables tables = new CreateTables("jdbc:postgresql://localhost:5432/transport", "user", "user");
        tables.create();

        //сохраняем данные об остановках в таблицу
        SaveStopsToDB saveStopsToDB = new SaveStopsToDB("jdbc:postgresql://localhost:5432/transport", "user", "user");
        saveStopsToDB.save(stopMap);

        //сохраняем данные о днях в таблицу
        SaveDaysToDB saveDaysToDB = new SaveDaysToDB("jdbc:postgresql://localhost:5432/transport", "user", "user");
        saveDaysToDB.createDays();
        saveDaysToDB.save();

        //сохранение времени
        SaveTimeToDB saveTimeToDB = new SaveTimeToDB("jdbc:postgresql://localhost:5432/transport", "user", "user");
        saveTimeToDB.save(timing42);
    }
}
