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

public class CreateDB extends Database {
    private static final Logger log = LoggerFactory.getLogger(CreateDB.class);

    public CreateDB(String url, String user, String password) {
        super(url, user, password);
    }

    public void send() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
            log.info("Opened database successfully");

            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS routes (id VARCHAR(10) NOT NULL CONSTRAINT routes_pk PRIMARY KEY," +
                    " title VARCHAR(50) NOT NULL)";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS stops (id VARCHAR(10) NOT NULL CONSTRAINT stops_pk PRIMARY KEY," +
                    " title VARCHAR(50) NOT NULL);";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS days (id VARCHAR(7) NOT NULL CONSTRAINT days_pk PRIMARY KEY," +
                    " title VARCHAR(100) NOT NULL);";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS time(" +
                    "pk INTEGER NOT NULL CONSTRAINT time_pk PRIMARY KEY, " +
                    "hour SMALLINT, " +
                    "minute  SMALLINT, " +
                    "route VARCHAR(10), " +
                    "stop_id VARCHAR(10), " +
                    "days VARCHAR(7));";
            stmt.executeUpdate(sql);

            stmt.close();
            con.commit();
            con.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        log.info("Tables: routes, stops, days, time created successfully");
    }
}
