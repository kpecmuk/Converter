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

public class SaveRoutesToDB extends Database {
    private static final Logger log = LoggerFactory.getLogger(SaveRoutesToDB.class);
    private Map<String, String> map = new HashMap<>();

    public SaveRoutesToDB(String url, String user, String password) {
        super(url, user, password);

        this.map.put("1a-b", "Щетинина-Лоста");
        this.map.put("1b-a", "Лоста-Щетинина");
        this.map.put("2a-b", "Бригантина-Льнокомбинат");
        this.map.put("2b-a", "Льнокомбинат-Бригантина");
        this.map.put("6a-b", "Дальняя-Областная детская больница");
        this.map.put("6b-a", "Областная детская больница-Дальняя");
        this.map.put("12a-b", "6 мкр.-ВПЗ");
        this.map.put("12b-a", "ВПЗ-6 мкр.");
        this.map.put("15a-b", "Архангельская-ВПЗ");
        this.map.put("15b-a", "ВПЗ-Архангельская");
        this.map.put("16a-b", "Дальняя-ВПЗ");
        this.map.put("16b-a", "ВПЗ-Дальняя");
        this.map.put("17a-b", "ВоГУ-6 мкр");
        this.map.put("17b-a", "6 мкр-ВоГУ");
        this.map.put("42a-b", "Дальняя-Екимцево");
        this.map.put("42b-a", "Екимцево-Дальняя");
    }

    public final void save() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
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
