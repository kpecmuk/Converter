package ru.kpecmuk.converter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Проверяем есть ли доступ к БД
 *
 * @author kpecmuk
 * @since 29.11.2017
 */

public class Connection extends Database {
    private static final Logger log = LoggerFactory.getLogger(Connection.class);

    private boolean result = true;


    public Connection() {

        java.sql.Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(getHost(), getLogin(), getPassword());

        } catch (SQLException | ClassNotFoundException e) {
            this.result = false;
            log.error(e.getMessage());

        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
    }

    public boolean getResult() {
        return this.result;
    }
}
