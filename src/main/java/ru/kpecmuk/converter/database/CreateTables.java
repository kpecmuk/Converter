package ru.kpecmuk.converter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * executeQuery - SELECT
 * executeUpdate - INSERT, UPDATE, DELETE, CREATE TABLE и DROP TABLE
 *
 * @author kpecmuk
 * @since 11.11.2017
 */

public class CreateTables extends Database implements Action {
    private static final Logger log = LoggerFactory.getLogger(CreateTables.class);

    private void createTables() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(getHost(), getLogin(), getPassword());
            con.setAutoCommit(false);
            log.info("Opened database successfully");

            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS routes (" +
                    "id VARCHAR(10) NOT NULL PRIMARY KEY, " +
                    "title VARCHAR(50) NOT NULL)";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS stops (" +
                    "id VARCHAR(10) NOT NULL PRIMARY KEY, " +
                    "title VARCHAR(50) NOT NULL)";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS days (" +
                    "id VARCHAR(7) NOT NULL PRIMARY KEY, " +
                    "title VARCHAR(100) NOT NULL)";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS schedule (" +
                    "time TIME, " +
                    "route VARCHAR(10) CONSTRAINT schedule_route_fk REFERENCES routes(id) ON DELETE CASCADE, " +
                    "stop VARCHAR(10) CONSTRAINT schedule_stop_fk REFERENCES stops(id) ON DELETE CASCADE, " +
                    "days VARCHAR(7) CONSTRAINT schedule_days_fk REFERENCES days(id) ON DELETE CASCADE," +
                    "CONSTRAINT schedule_time_route_stop_days_pk PRIMARY KEY (time, route, stop, days))";
            stmt.executeUpdate(sql);

            stmt.close();
            con.commit();
            con.close();
        } catch (Exception e) {
            log.error(String.valueOf(e));
            System.exit(1);
        }
        log.info("Tables: routes, stops, days, time created successfully");
    }

    @Override
    public void execute() {
        createTables();
    }
}
