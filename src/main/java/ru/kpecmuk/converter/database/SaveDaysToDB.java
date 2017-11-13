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

public class SaveDaysToDB extends Database {
    private static final Logger log = LoggerFactory.getLogger(SaveDaysToDB.class);

    private Map<String, String> days = new HashMap<>();

    public SaveDaysToDB(String url, String user, String password) {
        super(url, user, password);
        this.days.put("1", "Понедельник");
        this.days.put("2", "Вторник");
        this.days.put("3", "Среда");
        this.days.put("4", "Четверг");
        this.days.put("5", "Пятница");
        this.days.put("6", "Суббота");
        this.days.put("7", "Воскресенье");
        this.days.put("12345", "Пн - Пт");
        this.days.put("67", "Сб - Вс");
        this.days.put("8", "Праздник");
    }

    /**
     * Сохранение карты дней недели в таблицу days
     */
    public final void save() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
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
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        log.info("DAYS created successfully");
    }
}
