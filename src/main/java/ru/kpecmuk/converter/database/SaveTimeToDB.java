package ru.kpecmuk.converter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.timing.Time;
import ru.kpecmuk.converter.timing.TimesList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author kpecmuk
 * @since 12.11.2017
 */

public class SaveTimeToDB extends Database {
    private static final Logger log = LoggerFactory.getLogger(SaveTimeToDB.class);

    public SaveTimeToDB(String url, String user, String password) {
        super(url, user, password);
    }

    public void save(TimesList timesList) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
            log.info("Opened database successfully");

            Statement stmt = con.createStatement();
            for (Time time : timesList.getTimeList()) {
                String sql = "INSERT INTO schedule (time, route, stop, days) " +
                        "VALUES ('" + time.getHour() + ":" + time.getMinute() + "," +
                        "', '" + time.getBusNumber() + "', '" + time.getBusStopID() + "', '" + time.getDays() + "');";
                stmt.executeUpdate(sql);
            }

            stmt.close();
            con.commit();
            con.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        log.info("SCHEDULE created successfully");
    }
}
