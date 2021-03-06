package ru.kpecmuk.converter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.actions.Action;
import ru.kpecmuk.converter.timing.AllRoutesTimeList;
import ru.kpecmuk.converter.timing.RouteTimeList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static ru.kpecmuk.converter.Main.PATH;

/**
 * @author kpecmuk
 * @since 12.11.2017
 */

public class TimeToDB extends Database implements Action {
    private static final Logger log = LoggerFactory.getLogger(TimeToDB.class);

    private void saveTimeToDB(AllRoutesTimeList allRoutesTimeList) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(getHost(), getLogin(), getPassword());
            con.setAutoCommit(false);
            log.info("Opened database successfully");

            Statement stmt = con.createStatement();

            for (RouteTimeList routeTimeList : allRoutesTimeList.get())
                routeTimeList.get().forEach(time -> {
                    try {
                        stmt.executeUpdate("INSERT INTO schedule (time, route, stop, days) " +
                                "VALUES ('" + time.getHour() + ":" + time.getMinute() + "," +
                                "','" + time.getBusNumber() + "','" + time.getBusStopID() + "','" + time.getDays() + "');");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

            stmt.close();
            con.commit();
            con.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            System.exit(1);
        }
        log.info("SCHEDULE created successfully");
    }

    /**
     * Загружаем файлы маршрутов со временем движения по остановкам.
     * Для этого создаётся список AllRoutesTimeList, состоящий из
     * отдельных расписаний каждого маршрута RouteTimeList
     */
    @Override
    public void execute() {

        AllRoutesTimeList allRoutesTimeList = new AllRoutesTimeList();
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route01work.txt"));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route01holy.txt"));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route06work.txt"));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route06holy.txt"));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route08work.txt"));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route08holy.txt"));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route15work.txt"));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route15holy.txt"));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route16work.txt"));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route16holy.txt"));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route42work.txt"));
        allRoutesTimeList.get().add(new RouteTimeList(PATH + "Route42holy.txt"));

        saveTimeToDB(allRoutesTimeList);
    }
}
