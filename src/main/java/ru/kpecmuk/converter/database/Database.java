package ru.kpecmuk.converter.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Шапка для работы с БД
 * Загружаем данные о хосте, логине, пароле и сохраняем их для дальнейших подключений
 *
 * @author kpecmuk
 * @since 19.11.2017
 */

class Database {
    private static final Logger log = LoggerFactory.getLogger(Database.class);
    private String host;
    private String login;
    private String password;

    Database() {
        Properties property = new Properties();
        String propFileName = "config.properties";
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {

            property.load(inputStream);

            this.host = property.getProperty("db.host");
            this.login = property.getProperty("db.login");
            this.password = property.getProperty("db.password");

            inputStream.close();

        } catch (IOException e) {
            log.error("Файл '" + propFileName + "' не найден");
        }
    }

    String getHost() {
        return this.host;
    }

    String getLogin() {
        return this.login;
    }

    String getPassword() {
        return this.password;
    }
}
