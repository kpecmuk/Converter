package ru.kpecmuk.converter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.routes.Route;
import ru.kpecmuk.converter.routes.RouteMap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

/**
 * @author kpecmuk
 * @since 13.11.2017
 */

public class SaveRoutesToDB extends Database {
    private static final Logger log = LoggerFactory.getLogger(SaveRoutesToDB.class);

    SaveRoutesToDB(String url, String user, String password) {
        super(url, user, password);
    }

    public void save(RouteMap map) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
            log.info("Opened database successfully");

            Statement stmt = con.createStatement();
            for (Map.Entry<String, Route> pair : map.getRouteMap().entrySet()) {
                String sql = "INSERT INTO routes (id, title) VALUES ('" + pair.getValue().getId() +
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
        log.info("Records created successfully");
    }
}
