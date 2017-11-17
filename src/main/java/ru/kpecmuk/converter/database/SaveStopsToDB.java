package ru.kpecmuk.converter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.stops.Stop;
import ru.kpecmuk.converter.stops.StopMap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

/**
 * @author kpecmuk
 * @since 12.11.2017
 */

public class SaveStopsToDB extends Database {
    private static final Logger log = LoggerFactory.getLogger(SaveStopsToDB.class);

    public SaveStopsToDB(String url, String user, String password, StopMap map) {
        super(url, user, password);

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
            log.info("Opened database successfully");

            Statement stmt = con.createStatement();
            for (Map.Entry<String, Stop> pair : map.get().entrySet()) {
                String sql = "INSERT INTO stops (id, title) VALUES ('" + pair.getValue().getId() +
                        "', '" + pair.getValue().getTitle() + "');";
                stmt.executeUpdate(sql);
            }

            stmt.close();
            con.commit();
            con.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        log.info("STOPS created successfully");
    }
}
