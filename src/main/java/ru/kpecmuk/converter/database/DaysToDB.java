package ru.kpecmuk.converter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.actions.Action;
import ru.kpecmuk.converter.data_maps.DataMap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

import static ru.kpecmuk.converter.Main.DAYS_FILE_NAME;

/**
 * @author kpecmuk
 * @since 12.11.2017
 */

public class DaysToDB extends Database implements Action {
    private static final Logger log = LoggerFactory.getLogger(DaysToDB.class);

    /**
     * В DataMap происходит загрузка файла c именем DAYS_FILE_NAME
     * DataMap создаёт список HashMap
     * Этот список передаётся в saveDaysToDB в качестве параметра и
     * там полученные данные заносятся в таблицу days.
     */
    @Override
    public void execute() {
        saveDaysToDB(new DataMap(DAYS_FILE_NAME));
    }

    /**
     * Сохраняем данные из списка HashMap в таблицу days
     *
     * @param map HashMap с днями, когда маршрут обслуживается
     */
    private void saveDaysToDB(DataMap map) {
        //  Сохранение карты дней недели в таблицу days
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(getHost(), getLogin(), getPassword());
            con.setAutoCommit(false);
            log.info("Opened database successfully");

            Statement stmt = con.createStatement();
            for (Map.Entry<String, String> pair : map.get().entrySet()) {
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
