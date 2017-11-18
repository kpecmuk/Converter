package ru.kpecmuk.converter.database;

import java.io.IOException;

/**
 * @author kpecmuk
 * @since 18.11.2017
 */
interface Action {

    void execute() throws IOException;
}
