package ru.kpecmuk.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.database.*;
import ru.kpecmuk.converter.loader.LoadRoutesToTimeList;
import ru.kpecmuk.converter.loader.LoadStopsToStopMap;
import ru.kpecmuk.converter.stops.StopMap;
import ru.kpecmuk.converter.timing.AllRoutesTimeList;
import ru.kpecmuk.converter.timing.CommonTimesList;
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

        StopMap stopMap = new StopMap();
        LoadStopsToStopMap stops = new LoadStopsToStopMap(PATH + "Stops.txt", stopMap);
        stops.load();

        AllRoutesTimeList allRoutesTimeList = new AllRoutesTimeList();

        CommonTimesList timing42 = new CommonTimesList();
        LoadRoutesToTimeList route42w = new LoadRoutesToTimeList(timing42, PATH + "Route42work.txt", utils);
        LoadRoutesToTimeList route42h = new LoadRoutesToTimeList(timing42, PATH + "Route42holy.txt", utils);


        CommonTimesList timing6 = new CommonTimesList();
        LoadRoutesToTimeList route6w = new LoadRoutesToTimeList(timing6, PATH + "Route06work.txt", utils);
        LoadRoutesToTimeList route6h = new LoadRoutesToTimeList(timing6, PATH + "Route06holy.txt", utils);

        CommonTimesList timing1 = new CommonTimesList();
        LoadRoutesToTimeList route1w = new LoadRoutesToTimeList(timing1, PATH + "Route01work.txt", utils);
        LoadRoutesToTimeList route1h = new LoadRoutesToTimeList(timing1, PATH + "Route01holy.txt", utils);

        CommonTimesList timing8 = new CommonTimesList();
        LoadRoutesToTimeList route8w = new LoadRoutesToTimeList(timing8, PATH + "Route08work.txt", utils);
//        route8w.load();
        LoadRoutesToTimeList route8h = new LoadRoutesToTimeList(timing8, PATH + "Route08holy.txt", utils);
//        route8h.load();

//        allRoutesTimeList.getAllRoutesTimeList().add(timing1);
//        allRoutesTimeList.getAllRoutesTimeList().add(timing6);
//        allRoutesTimeList.getAllRoutesTimeList().add(timing8);
//        allRoutesTimeList.getAllRoutesTimeList().add(timing42);

        System.err.close();
        System.setErr(System.out);

        //Создаём таблицы
        CreateTables tables = new CreateTables("jdbc:postgresql://localhost:5432/transport", "user", "user");
        tables.create();

        //сохраняем данные об остановках в таблицу
        SaveStopsToDB stopsToDB = new SaveStopsToDB("jdbc:postgresql://localhost:5432/transport", "user", "user");
        stopsToDB.save(stopMap);

        //сохраняем данные о днях в таблицу
        SaveDaysToDB daysToDB = new SaveDaysToDB("jdbc:postgresql://localhost:5432/transport", "user", "user");
        daysToDB.save();

        //
        SaveRoutesToDB routesToDB = new SaveRoutesToDB("jdbc:postgresql://localhost:5432/transport", "user", "user");
        routesToDB.save();

        //сохранение времени
        SaveTimeToDB saveTimeToDB = new SaveTimeToDB("jdbc:postgresql://localhost:5432/transport", "user", "user");
        //TODO: общий список времени, состоящий из отдельных кусков, каждого маршрута
        saveTimeToDB.save(timing42);
        saveTimeToDB.save(timing6);
        saveTimeToDB.save(timing1);
    }
}
