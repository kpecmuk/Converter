package ru.kpecmuk.converter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 12.11.2017
 */

abstract class Database {
    private static final Logger log = LoggerFactory.getLogger(Database.class);

    // JDBC URL, username and password of MySQL server
    final String url;
    final String user;
    final String password;

    Database(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
}
