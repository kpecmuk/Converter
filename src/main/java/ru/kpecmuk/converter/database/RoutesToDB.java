package ru.kpecmuk.converter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.actions.Action;
import ru.kpecmuk.converter.data_maps.DataMap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

import static ru.kpecmuk.converter.Main.ROUTES_FILE_NAME;

/**
 * @author kpecmuk
 * @since 13.11.2017
 */

public class RoutesToDB extends Database implements Action {
    private static final Logger log = LoggerFactory.getLogger(RoutesToDB.class);

    /**
     * В DataMap происходит загрузка файла c именем ROUTES_FILE_NAME
     * DataMap создаёт список HashMap
     * Этот список передаётся в saveRoutesToDB в качестве параметра и
     * там полученные данные заносятся в таблицу routes.
     */
    @Override
    public void execute() {
        saveRoutesToDB(new DataMap(ROUTES_FILE_NAME));
    }

    /**
     * Сохраняем названия маршрутов в SQL таблицу из списка HashMap
     *
     * @param map HashMap с маршрутами
     */
    private void saveRoutesToDB(DataMap map) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(getHost(), getLogin(), getPassword());
            con.setAutoCommit(false);
            log.info("Opened database successfully");

            Statement stmt = con.createStatement();
            for (Map.Entry<String, String> pair : map.get().entrySet()) {
                String sql = "INSERT INTO routes (id, title) VALUES ('" + pair.getKey() +
                        "', '" + pair.getValue() + "');";
                stmt.executeUpdate(sql);
            }

            stmt.close();
            con.commit();
            con.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            System.exit(1);
        }
        log.info("ROUTES created successfully");
    }
}
