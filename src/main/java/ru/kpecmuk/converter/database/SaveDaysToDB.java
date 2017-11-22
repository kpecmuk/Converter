package ru.kpecmuk.converter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kpecmuk
 * @since 12.11.2017
 */

public class SaveDaysToDB extends Database implements Action {
    private static final Logger log = LoggerFactory.getLogger(SaveDaysToDB.class);

    @Override
    public void execute() {
        Map<String, String> days = new HashMap<>();

        days.put("1", "Понедельник");
        days.put("2", "Вторник");
        days.put("3", "Среда");
        days.put("4", "Четверг");
        days.put("5", "Пятница");
        days.put("6", "Суббота");
        days.put("7", "Воскресенье");
        days.put("12345", "Пн - Пт");
        days.put("67", "Сб - Вс");
        days.put("8", "Праздник");

        //  Сохранение карты дней недели в таблицу days
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(getHost(), getLogin(), getPassword());
            con.setAutoCommit(false);
            log.info("Opened database successfully");

            Statement stmt = con.createStatement();
            for (Map.Entry<String, String> pair : days.entrySet()) {
                String sql = "INSERT INTO days (id, title) VALUES ('" + pair.getKey() +
                        "', '" + pair.getValue() + "');";
                stmt.executeUpdate(sql);
            }

            stmt.close();
            con.commit();
            con.close();
        } catch (Exception e) {
            log.error(String.valueOf(e));
            System.exit(1);
        }
        log.info("DAYS created successfully");
    }
}
