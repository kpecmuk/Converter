package ru.kpecmuk.converter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author kpecmuk
 * @since 12.11.2017
 */

public class Database {
    private static final Logger log = LoggerFactory.getLogger(Database.class);

    public void action(Action action) throws IOException {
        action.execute();
    }
}
