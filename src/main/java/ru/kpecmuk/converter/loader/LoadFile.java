package ru.kpecmuk.converter.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.utils.Utils;

import java.io.*;

/**
 * @author kpecmuk
 * @since 09.11.2017
 */

class LoadFile {
    private static final Logger log = LoggerFactory.getLogger(LoadFile.class);

    BufferedReader fin;
    Utils utils;
    private String fileName;

    LoadFile(String fileName) {
        this.fileName = fileName;
        this.utils = new Utils();
    }

    /**
     * Открытие файла
     */
    void openFile() {
        try {
            this.fin = new BufferedReader(new FileReader(new File(fileName)));
        } catch (FileNotFoundException e) {
            log.error(String.valueOf(e));
            System.exit(1);
        }
    }

    /**
     * Закрытие файла
     */
    void closeFile() {
        try {
            this.fin.close();
        } catch (IOException e) {
            log.error(String.valueOf(e));
            System.exit(1);
        }
    }
}
