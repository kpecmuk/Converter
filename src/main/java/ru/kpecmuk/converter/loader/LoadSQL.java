package ru.kpecmuk.converter.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author kpecmuk
 * @since 28.11.2017
 */

public class LoadSQL extends LoadFile {
    private static final Logger log = LoggerFactory.getLogger(LoadSQL.class);

    private String sql = "";

    public LoadSQL(String fileName) {
        super(fileName);

        try {
            openFile();

            String line;
            while ((line = fin.readLine()) != null) {
                sql += line;
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public String getSQL() {
        return this.sql;
    }
}
