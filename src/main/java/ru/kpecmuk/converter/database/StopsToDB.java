package ru.kpecmuk.converter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.actions.Action;
import ru.kpecmuk.converter.data_maps.DataMap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

import static ru.kpecmuk.converter.Main.STOPS_FILE_NAME;

/**
 * @author kpecmuk
 * @since 12.11.2017
 */

public class StopsToDB extends Database implements Action {
    private static final Logger log = LoggerFactory.getLogger(StopsToDB.class);

    @Override
    public void execute() {
        saveStopsToDB(new DataMap(STOPS_FILE_NAME));
    }

    /**
     * Сохраняем остановки в SQL таблицу
     *
     * @param map HashMap с остановками
     */
    private void saveStopsToDB(DataMap map) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(getHost(), getLogin(), getPassword());
            con.setAutoCommit(false);
            log.info("Opened database successfully");

            Statement stmt = con.createStatement();
            for (Map.Entry<String, String> pair : map.get().entrySet()) {
                String sql = "INSERT INTO stops (id, title) VALUES ('" + pair.getKey() +
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
        log.info("STOPS created successfully");
    }
}
