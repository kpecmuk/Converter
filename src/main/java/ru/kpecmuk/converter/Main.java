package ru.kpecmuk.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.loader.LoadRoute;
import ru.kpecmuk.converter.loader.LoadStops;
import ru.kpecmuk.converter.stops.StopMap;
import ru.kpecmuk.converter.timing.TimesList;
import ru.kpecmuk.converter.utils.CreateDB;
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
        LoadRoute route42 = new LoadRoute(timing42, PATH + "Route42work.txt", utils);
        route42.load();

        StopMap stopMap = new StopMap();
        LoadStops stops = new LoadStops(PATH + "Stops.txt", stopMap);
        stops.load();


        timing42.getTimeList().forEach(time ->
                System.out.println(time.getTime()
                        + " - " + stopMap.getStopMap().get(time.getBusStopID()).getTitle()));

        System.err.close();
        System.setErr(System.out);

        CreateDB createDB = new CreateDB("jdbc:postgresql://localhost:5432/transport", "postgres", "retry");
        createDB.send();
    }
}
