package ru.kpecmuk.converter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.timing.RouteTimeList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author kpecmuk
 * @since 12.11.2017
 */

public class SaveTimeToDB extends Database {
    private static final Logger log = LoggerFactory.getLogger(SaveTimeToDB.class);

    public SaveTimeToDB(String url, String user, String password) {
        super(url, user, password);
    }

    public void save(List<RouteTimeList> allRoutesTimeList) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
            log.info("Opened database successfully");

            Statement stmt = con.createStatement();

            for (RouteTimeList routeTimeList : allRoutesTimeList) {
                routeTimeList.get().forEach(time -> {
                    try {
                        stmt.executeUpdate("INSERT INTO schedule (time, route, stop, days) " +
                                "VALUES ('" + time.getHour() + ":" + time.getMinute() + "," +
                                "','" + time.getBusNumber() + "','" + time.getBusStopID() + "','" + time.getDays() + "');");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }

            stmt.close();
            con.commit();
            con.close();
        } catch (
                Exception e)

        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        log.info("SCHEDULE created successfully");
    }
}
