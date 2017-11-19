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
 * @since 13.11.2017
 */

public class SaveRoutesToDB extends Database implements Action {
    private static final Logger log = LoggerFactory.getLogger(SaveRoutesToDB.class);

    @Override
    public void execute() {
        Map<String, String> map = new HashMap<>();

        map.put("1a-b", "Щетинина-Лоста");
        map.put("1b-a", "Лоста-Щетинина");
        map.put("2a-b", "Бригантина-Льнокомбинат");
        map.put("2b-a", "Льнокомбинат-Бригантина");
        map.put("6a-b", "Дальняя-Областная детская больница");
        map.put("6b-a", "Областная детская больница-Дальняя");
        map.put("8a-b", "Щетинина-Разина");
        map.put("8b-a", "Разина-Щетинина");
        map.put("12a-b", "6 мкр.-ВПЗ");
        map.put("12b-a", "ВПЗ-6 мкр.");
        map.put("15a-b", "Архангельская-ВПЗ");
        map.put("15b-a", "ВПЗ-Архангельская");
        map.put("16a-b", "Дальняя-ВПЗ");
        map.put("16b-a", "ВПЗ-Дальняя");
        map.put("17a-b", "ВоГУ-6 мкр");
        map.put("17b-a", "6 мкр-ВоГУ");
        map.put("42a-b", "Дальняя-Екимцево");
        map.put("42b-a", "Екимцево-Дальняя");

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(getHost(), getLogin(), getPassword());
            con.setAutoCommit(false);
            log.info("Opened database successfully");

            Statement stmt = con.createStatement();
            for (Map.Entry<String, String> pair : map.entrySet()) {
                String sql = "INSERT INTO routes (id, title) VALUES ('" + pair.getKey() +
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
        log.info("ROUTES created successfully");
    }
}
