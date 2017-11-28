package ru.kpecmuk.converter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.actions.Action;
import ru.kpecmuk.converter.loader.LoadSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static ru.kpecmuk.converter.Main.*;

/**
 * executeQuery - SELECT
 * executeUpdate - INSERT, UPDATE, DELETE, CREATE TABLE и DROP TABLE
 *
 * @author kpecmuk
 * @since 11.11.2017
 */

public class CreateTables extends Database implements Action {
    private static final Logger log = LoggerFactory.getLogger(CreateTables.class);

    /**
     * Создаём таблицы в базе данных
     * для будущего хранения в них данных
     */
    @Override
    public void execute() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(getHost(), getLogin(), getPassword());
            con.setAutoCommit(false);
            log.info("Opened database successfully");

            Statement stmt = con.createStatement();

            stmt.executeUpdate(new LoadSQL(ROUTES_TABLE_FILE_NAME).get());
            stmt.executeUpdate(new LoadSQL(STOPS_TABLE_FILE_NAME).get());
            stmt.executeUpdate(new LoadSQL(DAYS_TABLE_FILE_NAME).get());
            stmt.executeUpdate(new LoadSQL(SCHEDULE_TABLE_FILE_NAME).get());

            stmt.close();
            con.commit();
            con.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            System.exit(1);
        }
        log.info("Tables: routes, stops, days, time created successfully");
    }
}
